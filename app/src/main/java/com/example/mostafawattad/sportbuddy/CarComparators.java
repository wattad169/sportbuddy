package com.example.mostafawattad.sportbuddy;

import java.util.Comparator;

import com.example.mostafawattad.sportbuddy.data.event;

/**
 * Created by Ingo on 28.07.2015.
 */
public class CarComparators {

    public static Comparator<event> getCarProducerComparator() {
        return new CarProducerComparator();
    }

    public static Comparator<event> getCarPowerComparator() {
        return new CarPowerComparator();
    }

    public static Comparator<event> getCarNameComparator() {
        return new CarNameComparator();
    }

    public static Comparator<event> getCarPriceComparator() {
        return new CarPriceComparator();
    }


    private static class CarProducerComparator implements Comparator<event> {

        @Override
        public int compare(event event1, event event2) {
            return event1.getType().getName().compareTo(event2.getType().getName());
        }
    }

    private static class CarPowerComparator implements Comparator<event> {

        @Override
        public int compare(event event1, event event2) {
            return event1.getPs() - event2.getPs();
        }
    }

    private static class CarNameComparator implements Comparator<event> {

        @Override
        public int compare(event event1, event event2) {
            return event1.getName().compareTo(event2.getName());
        }
    }

    private static class CarPriceComparator implements Comparator<event> {

        @Override
        public int compare(event event1, event event2) {
            if (event1.getPrice() < event2.getPrice()) return -1;
            if (event1.getPrice() > event2.getPrice()) return 1;
            return 0;
        }
    }

}
