package edu.uic.cs422.chicagovoter;

public class Events {

    String eventName;
    String eventDate;
    String eventLocation;
    String eventDesc;

    public Events(String eventName, String eventDate, String eventLocation, String eventDesc)
    {
        this.eventName = eventName;
        this.eventDate = eventDate;
        this.eventLocation = eventLocation;
        this.eventDesc = eventDesc;
    }
}
