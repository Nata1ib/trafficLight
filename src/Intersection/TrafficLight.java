package Intersection;

import java.util.*;
import java.util.concurrent.*;

public class TrafficLight extends Thread {

    private Set<String> movingDirections = new ConcurrentSkipListSet<>();
    private List<Car> cars = new CopyOnWriteArrayList<>();;
    private final Object lock = new Object();

    private List<Set<String>> possibleDirections = new CopyOnWriteArrayList<>();

    public List<Car> getCars() { return cars;}

    public List<Set<String>> getPossibleDirections() { return possibleDirections;}

    public void allowDirections() {
        System.out.println("Светофор открыт для направлений: " + movingDirections);
        synchronized (lock) {
            Iterator<Car> iterator = cars.iterator();
            while (iterator.hasNext()) {
                Car car = iterator.next();
                if (movingDirections.contains(car.getDirection())) {
                    synchronized (car) {
                        car.notify();
                    }
                }
            }
            movingDirections.clear();
        }
    }

    public void setAllowedDirections() {
        Random r = new Random();
        movingDirections.addAll(possibleDirections.get(r.nextInt(possibleDirections.size())));
    }

    @Override
    public void run() {
        while (true) {
            if (cars.size() > 0) {
                setAllowedDirections();
                allowDirections();
            }
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
