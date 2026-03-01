package utils;

import dto.Car;
import dto.User;
import net.datafaker.Faker;

import java.util.Locale;
import java.util.Random;

public class CarFactory {

    static Faker faker = new Faker(new Locale("en"));

    public static Car positiveCar() {
        Car car = Car.builder()
                .city("Haifa")
                .manufacture(faker.vehicle().make())
                .model(faker.vehicle().model())
                .year(faker.number().numberBetween(0, 2026))
                .fuel(faker.options().option("Diesel", "Petrol", "Hybrid", "Electric", "Gas"))
                .seats(faker.number().numberBetween(2, 20))
                .carClass(faker.vehicle().carType())
                .registrationNumber(faker.vehicle().licensePlate("IL"))
                .price(faker.number().numberBetween(0, 1000))
                .about(faker.lorem().sentence())
                .photo(carPhotoRandomizer())
                .build();
        return car;
    }

    private static String carPhotoRandomizer() {
        int carNumber = new Random().nextInt((10 - 1) + 1) + 1;
        return "src/test/resources/car_images/car-" + carNumber+".png";
    }
}
