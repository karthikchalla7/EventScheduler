package org.example.eventScheduler.util;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class DateTimeUtils {

    //the below function converts the time to that specific with respect to the current zone.

    public static LocalDateTime convertDateTime(LocalDateTime datetime, ZoneId fromzone,ZoneId tozone){
        ZonedDateTime zonedatetime = datetime.atZone(fromzone);
        return zonedatetime.withZoneSameInstant(tozone).toLocalDateTime();
    }
}
