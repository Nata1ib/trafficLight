import Intersection.Car;
import Intersection.TrafficLight;

import java.util.Set;

public class Main {
    public static void main(String[] args) {
        TrafficLight trafficLight = new TrafficLight();

        Car[] cars = {
                new Car("North-South", trafficLight, Set.of("North-South")),
                new Car("South-West", trafficLight, Set.of("South-West")),
                new Car("West-East", trafficLight, Set.of("West-East"))
        };

        for (Car car : cars) {
            car.start();
        }

        trafficLight.start();
    }
}
