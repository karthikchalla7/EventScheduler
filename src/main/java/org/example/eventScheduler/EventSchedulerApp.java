package org.example.eventScheduler;


import org.example.eventScheduler.model.Event;
import org.example.eventScheduler.service.EventService;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class EventSchedulerApp {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private EventService eventService = new EventService();
    private Scanner scanner = new Scanner(System.in);

    private  void printMenu(){
        System.out.println("\n1. Add Event");
        System.out.println("2. List All Events");
        System.out.println("3. List Upcoming Events");
        System.out.println("4. Calculate Event Duration");
        System.out.println("5. Convert Event Time Zone");
        System.out.println("6. Check Overlappig Events");
        System.out.println("7. Exit");
        System.out.println("Choose an option: ");
    }


    public void run(){
        while(true){
            printMenu();
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice){
                case 1:
                    addEvent();
                    break;
                case 2:
                    listEvents();
                    break;
                case 3:
                    listUpcomingEvents();
                    break;
                case 4:
                    calculateEventDuration();
                    break;
                case 5:
                    convertEventTimeZone();
                    break;
                case 6:
                    checkOverlappingEvents();
                    break;
                case 7:
                    System.out.println("GoodBye!");
                    return;
                default:
                    System.out.println("Invalid option.Please Try Again.");
            }
        }
    }


    private void addEvent(){
        System.out.println("Enter Event Title: ");
        String title = scanner.nextLine();

        System.out.println("Enter Start Date and Time (yyyy-MM-dd HH:mm):  ");
        LocalDateTime startDateTime = LocalDateTime.parse(scanner.nextLine(),FORMATTER);

        System.out.println("Enter End Date and Time (yyyy-MM-dd HH:mm): ");
        LocalDateTime endDateTime = LocalDateTime.parse(scanner.nextLine(),FORMATTER);

        System.out.println("Enter time zone (e.g Asia/Calcutta): ");
        ZoneId timezone = ZoneId.of(scanner.nextLine());

        Event event = new Event(title,startDateTime,endDateTime,timezone);
        eventService.addEvent(event);
        System.out.println("Event Added Succesfully!!");


    }

    private void listEvents(){
        List<Event> events = eventService.getAllEvents();
        if(events.isEmpty()){
            System.out.println("No Events Found.");
        }
        else{
            events.forEach(event -> {
                System.out.println(event);
                System.out.println("----------------");
            });
        }
    }

    private void listUpcomingEvents(){

        System.out.println("Enter the number of upcoming events to display: ");
        int limit = scanner.nextInt();
        scanner.nextLine();

        List<Event> upcoming = eventService.getUpcomingEvents(limit);
        if(upcoming.isEmpty()){
            System.out.println("No Upcoming Events found.");
        }
        else{
            upcoming.forEach(event -> {
                System.out.println(event);
                System.out.println("--------------");
            });
        }
    }

    private void calculateEventDuration(){
        Event event = selectEvent();
        if(event!=null){
            System.out.println("Event Duration: "+eventService.calculateEventDuration(event));
        }
    }

    private void convertEventTimeZone(){
        Event event = selectEvent();
        if(event!=null){
            System.out.println("Enter new time zone (e.g., America/New_York): ");
            ZoneId newtimezone = ZoneId.of(scanner.nextLine());

            Event convertedEvent = eventService.convertEventToTimeZone(event,newtimezone);
            System.out.println("Converted Event");
            System.out.println(convertedEvent);
        }
    }

    private void checkOverlappingEvents(){
        Event event = selectEvent();
        if(event!=null){
            List<Event> overlappingEvents = eventService.findOverlappingEvents(event);
            if (overlappingEvents.isEmpty()){
                System.out.println("No Overlapping events found");
            }else{
                System.out.println("Overlapping events: ");
                overlappingEvents.forEach(e->{
                    System.out.println(e);
                    System.out.println("-------------");
                });
            }
        }
    }

    private Event selectEvent(){
        List<Event> events = eventService.getAllEvents();
        if (events.isEmpty()){
            System.out.println("No Events found");
            return null;
        }

        System.out.println("Select an Event");
        for (int i = 0; i < events.size(); i++) {
            //in case to fomrat we can use printf rather than println
            System.out.printf("%d. %s%n",i+1,events.get(i).getTitle());
        }
        int selection = scanner.nextInt();
        scanner.nextLine();

        if(selection<1 || selection>events.size()){
            System.out.println("Invalid selection");
            return null;
        }
        return events.get(selection-1);
    }



    public static void main(String[] args) {

        new EventSchedulerApp().run();
    }
}