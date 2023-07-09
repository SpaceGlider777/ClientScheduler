package util;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

/**
 * Utility class for checking if times are during business hours.
 */
public class TimezoneUtil {
    /**
     * Business opening hour.
     */
    private static final int OPENING_HOUR = 8;

    /**
     * Business closing hour.
     */
    private static final int CLOSING_HOUR = 22;

    /**
     * Business timezone.
     */
    private static final String BUSINESS_TIMEZONE = "US/Eastern";

    /**
     * Checks if the given LocalDateTime is during business hours.
     *
     * @param dateTime The LocalDateTime.
     * @return true if dateTime is during business hours, false otherwise.
     */
    public static boolean isDuringBusinessHours(LocalDateTime dateTime) {
        ZonedDateTime zdt = ZonedDateTime.of(dateTime, ZoneId.systemDefault());
        ZonedDateTime est = zdt.withZoneSameInstant(ZoneId.of(BUSINESS_TIMEZONE));

        if (est.getHour() < OPENING_HOUR || est.getHour() > CLOSING_HOUR) {
            return false;
        }
        return true;
    }
}
