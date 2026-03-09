package utils;

import dto.Car;
import dto.User;
import net.datafaker.Faker;
import utils.enums.Fuel;

import java.util.Locale;
import java.util.Random;

public class CarFactory {

    static Faker faker = new Faker(new Locale("en"));

    public static Car positiveCar() {
        Car car = Car.builder()
                .city("Haifa")
                .manufacture(faker.vehicle().manufacturer())
                .model(faker.vehicle().model())
                .year(Integer.toString(faker.number().numberBetween(0, 2026)))
                .fuel(faker.options().option(Fuel.DIESEL, Fuel.PETROL, Fuel.HYBRID, Fuel.ELECTRIC, Fuel.GAS))
                .seats(faker.number().numberBetween(2, 20))
                .carClass(faker.vehicle().carType())
                .serialNumber(faker.vehicle().licensePlate("IL"))
                .pricePerDay(faker.number().randomDouble(2,1,999))
                .about(faker.text().text(0, 500))
                .build();
        return car;
    }

}
