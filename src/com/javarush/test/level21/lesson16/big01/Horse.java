package com.javarush.test.level21.lesson16.big01;

/**
 * @author Raccoon
 * @version 1.0, 29.06.2016.
 */
public class Horse implements Comparable<Horse> {
    private String name;
    private double speed;
    private double distance;

    // Constructor
    public Horse(String name, double speed, double distance) {
        this.name = name;
        this.speed = speed;
        this.distance = distance;
    }

    // Getters & Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    // Move & Print Methods
    public void move() {
        distance += Math.random() * speed;
    }

    public void print() {
        for (int i = 0; i < (int) distance; i++) {
            System.out.print(".");
        }
        System.out.println(getName());
    }

    // Comparable

    @Override
    public int compareTo(Horse o) {
        return (int) (this.getDistance() - o.getDistance());
    }
}
