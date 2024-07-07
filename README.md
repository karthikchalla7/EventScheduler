# Date-Time Event Scheduler

## Project Overview

This Java console application demonstrates the use of Java's Date-Time API to create and manage events across different time zones. It showcases various features of the Date-Time API and provides a practical example of event scheduling and management.

## Features

- Add events with start time, end time, and time zone
- List all events
- Display upcoming events
- Calculate event duration
- Convert event times between different time zones
- Check for overlapping events

## Technical Highlights

- Utilizes Java's Date-Time API (java.time package)
- Demonstrates use of LocalDateTime, ZoneId, and Duration classes
- Implements time zone conversion logic
- Showcases event overlap detection algorithm
- Uses Java streams for efficient data processing

## How to Run

1. Ensure you have Java 11 or later installed on your system.
2. Clone this repository or download the source code.
3. Navigate to the project root directory in the terminal.
4. Compile the Java files:


## Usage

Upon running the application, you'll be presented with a menu:

1. Add Event
2. List All Events
3. List Upcoming Events
4. Calculate Event Duration
5. Convert Event Time Zone
6. Check Overlapping Events
7. Exit

Select an option by entering the corresponding number and follow the prompts.

## Key Concepts Demonstrated

- Working with LocalDateTime for date and time representation
- Using ZoneId for time zone management
- Calculating durations between date-times
- Converting date-times between time zones
- Detecting time overlaps between events
- Sorting and filtering events based on time criteria

## Future Enhancements

- Persist events to a file or database
- Implement recurring events
- Add reminders or notifications for upcoming events
- Create a graphical user interface
