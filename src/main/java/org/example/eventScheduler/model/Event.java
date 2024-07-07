package org.example.eventScheduler.model;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class Event implements Comparable<Event> {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    private String title;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    private ZoneId timezone;

    //constructor
    public Event(String title, LocalDateTime startDateTime, LocalDateTime endDateTime, ZoneId timezone) {
        this.title = title;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.timezone = timezone;
    }

    //getters and setters
    public void setTitle(){
        this.title = title;
    }
    public String getTitle(){
        return title;
    }

    public void setStartDateTime(LocalDateTime startDateTime){
        this.startDateTime = startDateTime;
    }
    public LocalDateTime getStartDateTime(){
        return startDateTime;
    }

    public void setEndDateTime(LocalDateTime endDateTime){
        this.endDateTime = endDateTime;
    }
    public LocalDateTime getEndDateTime(){
        return endDateTime;
    }

    public void setTimezone(ZoneId timezone){
        this.timezone = timezone;
    }
    public ZoneId getTimezone(){
        return timezone;
    }

    @Override
    public String toString() {
        return String.format("Event: %s%nStart: %s%nEnd: %s%nTime Zone: %s",
                title,
                startDateTime.format(FORMATTER),
                endDateTime.format(FORMATTER),
                timezone);
    }

    @Override
    public int compareTo(Event other) {
        //this methods sorts based on the stardatetime
        //if the value less than 0 is returned if the string is less than the other string
        //and a value greater than 0 if the string is greater than the other string
        return this.startDateTime.compareTo(other.startDateTime);
    }
}
