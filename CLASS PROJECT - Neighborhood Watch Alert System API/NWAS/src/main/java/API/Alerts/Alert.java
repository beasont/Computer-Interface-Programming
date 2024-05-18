package API.Alerts;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;

@JsonInclude(Include.NON_NULL) // configures the class to include only non-null properties in JSON serialization
@Entity
public class Alert {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long alertID; // primary key, a unique ID for each alert
    private String safetyTip; // for text-based safety tips that correspond to the alert
    private String alertType; // for storing the type of alert
    private String dateTime; // for the date and time of the alert

    public Alert() {
        this.dateTime = generateRandomFormattedDateTimeInLastFiveYears();
    }

    public Alert(String safetyTip, String alertType) {
        this.safetyTip = safetyTip;
        this.alertType = alertType;
        this.dateTime = generateRandomFormattedDateTimeInLastFiveYears();
    }

    public Long getAlertID() {
        return alertID;
    }

    public void setAlertID(Long alertID) {
        this.alertID = alertID;
    }

    public String getSafetyTip() {
        return safetyTip;
    }

    public void setSafetyTip(String safetyTip) {
        this.safetyTip = safetyTip;
    }

    public String getAlertType() {
        return alertType;
    }

    public void setAlertType(String alertType) {
        this.alertType = alertType;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    // helper method to generate a random formatted date and time within the last five years
    private String generateRandomFormattedDateTimeInLastFiveYears() {
        long now = System.currentTimeMillis();
        long fiveYearsAgo = now - (5L * 365 * 24 * 60 * 60 * 1000); // 5 years in milliseconds

        // generates a random time between five years ago and now
        long randomTime = ThreadLocalRandom.current().nextLong(fiveYearsAgo, now);

        // converts the generated time to LocalDateTime
        LocalDateTime randomDateTime = LocalDateTime.ofInstant(new Date(randomTime).toInstant(), ZoneId.systemDefault());

        // formats the random date and time to my personal preference
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE, yyyy-MM-dd, h:mm a");
        return randomDateTime.format(formatter);
    }
}
