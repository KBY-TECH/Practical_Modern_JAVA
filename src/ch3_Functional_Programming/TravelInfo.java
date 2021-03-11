package ch3_Functional_Programming;

import lombok.*;


public class TravelInfo {
    private String name;
    private String country;
    private String city;
    private int days ;
    private int nights;

    @Builder
    public TravelInfo(String name, String country, String city, int days, int nights) {
        this.name = name;
        this.country = country;
        this.city = city;
        this.days = days;
        this.nights = nights;
    }

    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }

    @Override
    public String toString() {
        return "TravelInfo{" +
                "name='" + name + '\'' +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", days=" + days +
                ", nights=" + nights +
                '}';
    }

    public String getCity() {
        return city;
    }

    public int getDays() {
        return days;
    }

    public int getNights() {
        return nights;
    }
}

