package dto;

import lombok.*;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor

public class Car {
    private String city;
    private String manufacture;
    private String model;
    private int year;
    private String fuel;
    private int seats;
    private String carClass;
    private String registrationNumber;
    private double price;
    private String about;
    private String photo;
}
