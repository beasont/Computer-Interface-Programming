package API.Alerts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/alerts")
public class AlertController {

    private final AlertService alertService;

    @Autowired
    public AlertController(AlertService alertService) {
        this.alertService = alertService;
    }

    // endpoint to get alerts by type
    @GetMapping
    public List<Alert> getAlertsByType(@RequestParam(required = false) String type) {
        if (type != null && type.equalsIgnoreCase("weather")) {
            return alertService.getWeatherAlerts();
        } else {
            return alertService.getAllAlerts();
        }
    }

    // endpoint to create a custom safety alert
    @PostMapping("/add")
    public Alert addCustomSafetyAlert(@RequestBody Alert alert) {
        return alertService.createAlert(alert);
    }

    // endpoint to update safety alert details
    @PutMapping("/update/{id}")
    public ResponseEntity<Alert> updateAlertDetails(@PathVariable("id") Long id, @RequestBody Alert alert) {
        Alert updatedAlert = alertService.updateAlert(id, alert);
        return ResponseEntity.ok().body(updatedAlert);
    }

    // endpoint to get an alert by ID
    @GetMapping("/{id}")
    public ResponseEntity<Alert> getAlertById(@PathVariable("id") Long id) {
        Alert alert = alertService.getAlertById(id);
        return ResponseEntity.ok().body(alert);
    }

    // endpoint to delete an alert by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAlertById(@PathVariable("id") Long id) {
        alertService.deleteAlertById(id);
        return ResponseEntity.noContent().build();
    }

    // endpoint to get all safety tips
    @GetMapping("/tips")
    public List<String> getSafetyTips() {
        return alertService.getSafetyTips();
    }

    // endpoint to add multiple custom safety alerts
    @PostMapping("/add/multiple")
    public List<Alert> addMultipleCustomSafetyAlerts(@RequestBody List<Alert> alerts) {
        return alertService.createMultipleAlerts(alerts);
    }

    // endpoint to update multiple safety alert details
    @PutMapping("/update/multiple")
    public ResponseEntity<List<Alert>> updateMultipleAlertDetails(@RequestBody List<Alert> alerts) {
        List<Alert> updatedAlerts = alertService.updateMultipleAlerts(alerts);
        return ResponseEntity.ok().body(updatedAlerts);
    }

    // endpoint to delete multiple alerts by ID
    @DeleteMapping("/delete/multiple")
    public ResponseEntity<Void> deleteMultipleAlertsById(@RequestBody List<Long> ids) {
        alertService.deleteMultipleAlertsById(ids);
        return ResponseEntity.noContent().build();
    }

}
