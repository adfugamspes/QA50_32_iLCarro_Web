package utils;

import dto.User;
import net.datafaker.Faker;

public class UserFactory {
    static Faker faker = new Faker();

    public static User positiveUserRegistration(){
        User user = User.builder()
                .firstName(faker.name().firstName())
                .lastName(faker.name().lastName())
                .email(faker.internet().emailAddress())
                .password("Password!123").build();
        return user;
    }

    public static User positiveUserLogin(){
        User user = User.builder().email(PropertiesReader.getProperty("base.properties", "login)"))
                .password(PropertiesReader.getProperty("base.properties", "password")).build();
        return user;
    }
}
