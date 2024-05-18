package API.Alerts;

import API.CommunityMembers.CommunityMember;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class AlertService {

    private final AlertRepository alertRepository;
    private final Random random;
    private final Map<String, String> communityAlertTypes;

    @Autowired
    public AlertService(AlertRepository alertRepository) {
        this.alertRepository = alertRepository;
        this.random = new Random();
        // initializes communityAlertTypes field with a map of alert types and descriptions
        this.communityAlertTypes = Map.of(
                "Lost Dog", "A dog has been reported missing from the neighborhood.",
                "Missing Person", "A community member has been reported missing.",
                "Safety Hazard", "A safety hazard has been identified in the area.",
                "Suspicious Activity", "Suspicious activity has been observed in the neighborhood.",
                "Emergency Assistance", "Emergency assistance is required in the area.",
                "Neighborhood Watch Alert", "An alert from the neighborhood watch program.",
                "Car Crash", "A car crash has been reported in the area.",
                "Car Collision", "A collision between two or more vehicles has been witnessed in the neighborhood.",
                "Property Theft", "A theft was reported by someone in the neighborhood.",
                "Power Outage", "The neighborhood has reportedly lost power."
        );
    }

    // retrieve all alerts from the repository
    public List<Alert> getAllAlerts() {
        try {
            return alertRepository.findAll();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Alerts not found");
        }
    }

    // create a new alert
    public Alert createAlert(Alert alert) {
        try {
            // set the current date and time
            alert.setDateTime(alert.getDateTime());
            return alertRepository.save(alert);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to create alert");
        }
    }

    // retrieve all safety tips from the alerts
    public List<String> getSafetyTips() {
        try {
            List<Alert> alerts = alertRepository.findAll();
            // maps each alert to its safety tip and collects them into a list
            return alerts.stream()
                    .map(Alert::getSafetyTip)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Safety tips not found");
        }
    }

    // retrieve all weather-related alerts
    public List<Alert> getWeatherAlerts() {
        try {
            List<Alert> allAlerts = alertRepository.findAll();
            // filters the alerts to include only 'weather' type alerts
            return allAlerts.stream()
                    .filter(alert -> alert.getAlertType().toLowerCase().contains("weather"))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Weather alerts not found");
        }
    }

    // retrieves an alert by its ID
    public Alert getAlertById(Long id) {
        try {
            Optional<Alert> optionalAlert = alertRepository.findById(id);
            // returns the alert if found, otherwise throws a 404 exception
            return optionalAlert.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Alert not found with ID: " + id));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Alert not found with ID: " + id);
        }
    }

    // updates an existing alert, singled out by its ID
    public Alert updateAlert(Long id, Alert newAlert) {
        try {
            Alert existingAlert = getAlertById(id);
            existingAlert.setSafetyTip(newAlert.getSafetyTip());
            // updates the alert type if the new alert type is not null or empty
            if (newAlert.getAlertType() != null && !newAlert.getAlertType().isEmpty()) {
                existingAlert.setAlertType(newAlert.getAlertType());
            }
            return alertRepository.save(existingAlert);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to update alert with ID: " + id);
        }
    }

    // deletes an alert, singled out by its ID
    public void deleteAlertById(Long id) {
        try {
            if (!alertRepository.existsById(id)) {
                // checks if the alert exists, throws a 404 exception if not found
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Alert not found with ID: " + id);
            }
            alertRepository.deleteById(id);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to delete alert with ID: " + id);
        }
    }

    // creates an alert that is reported from a specific community member
    public void createAlertFromCommunityMember(CommunityMember member) {
        try {
            // generate an alert based on the community member's information
            String alertType = getRandomCommunityAlertType();
            String safetyTip = generateSafetyTipForMember(member, alertType);
            Alert alert = new Alert(safetyTip, alertType);
            alert.setDateTime(alert.getDateTime());
            alertRepository.save(alert);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to create alert from community member");
        }
    }

    // helper method to generate a safety tip for a community member, based on alert type
    private String generateSafetyTipForMember(CommunityMember member, String alertType) {
        // generate a safety tip based on the member's information and the specific alert type
        String alertDescription = communityAlertTypes.getOrDefault(alertType, "No description available");

        // get the current date and time
        LocalDateTime currentTime = LocalDateTime.now();

        // format the current date and time
        String formattedDateTime = currentTime.toString();

        return alertDescription + " Reported by: " + member.getName() + " " + member.getSurname() + ", Age: " + member.getAge() + ", Address: " + member.getAddressNumber();
    }

    private String getRandomCommunityAlertType() {
        try {
            // selects a random alert type from the map
            int index = random.nextInt(communityAlertTypes.size());
            return communityAlertTypes.keySet().toArray(new String[0])[index];
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to retrieve random community alert type");
        }
    }

    // create multiple new alerts
    public List<Alert> createMultipleAlerts(List<Alert> alerts) {
        try {
            alerts.forEach(alert -> alert.setDateTime(alert.getDateTime()));
            return alertRepository.saveAll(alerts);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to create multiple alerts");
        }
    }

    // update multiple existing alerts
    public List<Alert> updateMultipleAlerts(List<Alert> alerts) {
        try {
            return alerts.stream()
                    .map(alert -> updateAlert(alert.getAlertID(), alert))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to update multiple alerts");
        }
    }

    // delete multiple alerts by their IDs
    public void deleteMultipleAlertsById(List<Long> ids) {
        try {
            ids.forEach(this::deleteAlertById);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to delete multiple alerts");
        }
    }

}
