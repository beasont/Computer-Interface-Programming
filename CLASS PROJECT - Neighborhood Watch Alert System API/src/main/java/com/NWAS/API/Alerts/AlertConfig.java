package com.NWAS.API.Alerts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import jakarta.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.Arrays;

@Configuration
public class AlertConfig {

    private final AlertRepository alertRepository;

    @Autowired
    public AlertConfig(AlertRepository alertRepository) {
        this.alertRepository = alertRepository;
    }

    @PostConstruct
    public void init() {
        LocalDateTime currentTime = LocalDateTime.now();
        String formattedTime = currentTime.toString();

        // saves various weather-type alerts with custom safety tips into the repository
        alertRepository.save(new Alert(customSafetyTip(AlertType.TORNADO_WARNING), "Weather: " + AlertType.TORNADO));
        alertRepository.save(new Alert(customSafetyTip(AlertType.FLASH_FLOOD_WARNING), "Weather: " + AlertType.FLASH_FLOOD));
        alertRepository.save(new Alert(customSafetyTip(AlertType.BLIZZARD_WARNING), "Weather: " + AlertType.BLIZZARD));
        alertRepository.save(new Alert(customSafetyTip(AlertType.SEVERE_THUNDERSTORM_WARNING), "Weather: " + AlertType.SEVERE_THUNDERSTORM));
        alertRepository.save(new Alert(customSafetyTip(AlertType.EARTHQUAKE_WARNING), "Weather: " + AlertType.EARTHQUAKE));

        // saves a list of alerts related to various types of alerts from authorities
        Alert alert6 = new Alert("Take care to not park illegally in front of a neighbor's driveway.", "Alert from Authorities: Illegal Parking");
        Alert alert7 = new Alert("Stay indoors and report any suspicious activity to the authorities.", "Alert from Authorities: Ongoing Crime");
        Alert alert8 = new Alert("Join your neighborhood watch program for community safety.", "Alert from Authorities: Neighborhood Event");
        Alert alert9 = new Alert("Shots have been fired in your area. Remain indoors and stay safe.", "Alert from Authorities: Shots Fired");
        Alert alert10 = new Alert("A wild animal was seen walking around the area. Keep a safe distance.", "Alert from Authorities: Dangerous Animal");

        alertRepository.saveAll(Arrays.asList(alert6, alert7, alert8, alert9, alert10)); // saves all the alerts to the repository in one batch
    }

    // helper method that returns a custom safety tip based on the weather alert
    private String customSafetyTip(String alertType) {
        return switch (alertType) {
            case AlertType.TORNADO_WARNING -> "Take cover immediately, a tornado has been sighted in your area!";
            case AlertType.FLASH_FLOOD_WARNING -> "Be cautious of flooding, heavy rain may lead to flash floods!";
            case AlertType.BLIZZARD_WARNING -> "Prepare for extreme cold and heavy snowfall, a blizzard is forecasted!";
            case AlertType.SEVERE_THUNDERSTORM_WARNING -> "Seek shelter indoors, a severe thunderstorm is approaching!";
            case AlertType.EARTHQUAKE_WARNING -> "There is an active earthquake in your area, seek shelter and avoid all operations of any vehicles!";
            default -> "Unknown Weather Alert";
        };
    }

    // a static nested class containing constant strings for different weather-type alerts
    static class AlertType {
        public static final String FLASH_FLOOD_WARNING = "Flash Flood Warning";
        public static final String TORNADO_WARNING = "Tornado Warning";
        public static final String EARTHQUAKE_WARNING = "Earthquake Warning";
        public static final String SEVERE_THUNDERSTORM_WARNING = "Severe Thunderstorm Warning";
        public static final String BLIZZARD_WARNING = "Blizzard Warning";
        public static final String FLASH_FLOOD = "Flash Flood Warning";
        public static final String TORNADO = "Tornado Warning";
        public static final String EARTHQUAKE = "Earthquake Warning";
        public static final String SEVERE_THUNDERSTORM = "Severe Thunderstorm Warning";
        public static final String BLIZZARD = "Blizzard Warning";
    }
}
