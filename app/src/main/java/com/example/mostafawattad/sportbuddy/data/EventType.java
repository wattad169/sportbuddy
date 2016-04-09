package com.example.mostafawattad.sportbuddy.data;


/**
 * Created by Ingo on 18.07.2015.
 */
public class EventType {

    private final String name;
    private int logoRes;

    public EventType(int logoRes, String name) {
        this.logoRes = logoRes;
        this.name = name;
    }

    public int getLogo() {
        return logoRes;
    }

    public String getName() {
        return name;
    }
}
