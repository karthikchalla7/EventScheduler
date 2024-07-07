package org.example.eventScheduler.service;

import org.example.eventScheduler.model.Event;
import org.example.eventScheduler.util.DateTimeUtils;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class EventService {

    private List<Event> events = new ArrayList<>();

    public void addEvent(Event event){
        events.add(event);
        Collections.sort(events);
    }

    public List<Event> getAllEvents(){
        return new ArrayList<>(events);
    }

    public List<Event> getUpcomingEvents(int limit){
        LocalDateTime now = LocalDateTime.now();

        return events.stream()
                .filter(event -> event.getStartDateTime().isAfter(now))
                .limit(limit)
                .collect(Collectors.toList());

    }

    public Duration calculateEventDuration (Event event){
        return Duration.between(event.getStartDateTime(),event.getEndDateTime());
    }

    public Event convertEventToTimeZone(Event event, ZoneId newTimeZone){

        //no need to create object coz the convert method in datetimeutils class is static
        LocalDateTime newStartDateTime = DateTimeUtils.convertDateTime(event.getStartDateTime(),event.getTimezone(),newTimeZone);
        LocalDateTime newEndDateTime = DateTimeUtils.convertDateTime(event.getEndDateTime(),event.getTimezone(),newTimeZone);

        return new Event(event.getTitle(),newStartDateTime,newEndDateTime,newTimeZone);
    }

    public boolean eventOverlaps(Event event1, Event event2){
        return !event1.getEndDateTime().isBefore(event2.getStartDateTime()) &&
                !event2.getEndDateTime().isBefore(event1.getStartDateTime());
    }

    public List<Event> findOverlappingEvents(Event event){
        return events.stream()
                .filter(e -> !e.equals(event))
                .filter(e -> eventOverlaps(e,event))
                .collect(Collectors.toList());
    }
}
