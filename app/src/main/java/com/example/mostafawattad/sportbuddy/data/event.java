package com.example.mostafawattad.sportbuddy.data;

/**
 * Created by Ingo on 18.07.2015.
 */
public class event implements Chargable {

    private final EventType type;
    private final String name;
    private final int ps;
    private final double price;
    private final String date;
    private final int size;
    private final String location;
    private final String id;


    public event(EventType event_type, String name,String date,String location,int size, int ps, double price,String id) {
        this.type = event_type;
        this.name = name;
        this.ps = ps;
        this.price = price;
        this.date =date;
        this.size=size;
        this.location = location;
        this.id=id;
    }
    public EventType getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public String getLocation(){
        return location;
    }
    public String getDate(){
        return date;
    }

    public int getPs() {
        return ps;
    }

    public String getId(){
        return id;
    }

    public int getKw() {
        return (int)(ps / 1.36);
    }

    public double getPrice() {
        return price;
    }
}
