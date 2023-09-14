package Intersection;

import java.util.Set;

public class Car extends Thread {
    private String direction;

    private Set<String> possibleDirections;
    private TrafficLight trafficLight;

    public Car(String direction, TrafficLight trafficLight, Set<String> directions) {
        this.direction = direction;
        this.trafficLight = trafficLight;
        this.possibleDirections = directions;
    }

    public String getDirection() {
        return direction;
    }

    @Override
    public void run() {
        while (true) {
            System.out.println("Car with direction " + direction + " is coming to the intersection");
            trafficLight.getCars().add(this);
            trafficLight.getPossibleDirections().add(possibleDirections);
            try {
                synchronized (this) {
                    this.wait();
                }
                System.out.println("Car with direction " + direction + " is moving across the intersection");
                trafficLight.getCars().remove(this);
                trafficLight.getPossibleDirections().remove(possibleDirections);
                sleep((int) (5000 * Math.random()));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
