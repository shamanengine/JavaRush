package com.javarush.test.level21.lesson16.big01;

import java.util.Comparator;

/**
 * @author Raccoon
 * @version 1.0, 30.06.2016.
 */
public class HorseByDistanceComparator implements Comparator<Horse> {
    @Override
    public int compare(Horse o1, Horse o2) {
        return (int) (o1.getDistance() - o2.getDistance());
    }
}
