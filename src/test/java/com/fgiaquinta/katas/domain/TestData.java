package com.fgiaquinta.katas.domain;

import java.math.BigDecimal;
import java.util.List;

/**
 * Static factory helpers to build small, inline domain datasets in tests
 * without repeating constructor boilerplate. Not production code: only
 * referenced from {@code src/test}.
 */
public final class TestData {

    private TestData() {
    }

    public static BigDecimal money(String amount) {
        return new BigDecimal(amount).setScale(2);
    }

    public static Vehicle vehicle(String plate, VehicleCategory category, FuelType fuel, int seats,
                                   int mileageKm, String dailyRate, String... features) {
        return new Vehicle(plate, plate, category, fuel, seats, mileageKm, money(dailyRate), List.of(features));
    }

    public static Branch branch(String code, String city, String country, Vehicle... fleet) {
        return new Branch(code, city, country, List.of(fleet));
    }

    public static Customer customer(String id, String name, String country, boolean loyalty) {
        return new Customer(id, name, country, loyalty);
    }

    public static Extra extra(String name, String price) {
        return new Extra(name, money(price));
    }

    public static Rental rental(String id, Customer customer, Vehicle vehicle, Branch pickup, Branch dropoff,
                                 String startIso, String endIso, String totalPrice, Extra... extras) {
        return new Rental(id, customer, vehicle, pickup, dropoff,
                java.time.LocalDate.parse(startIso), java.time.LocalDate.parse(endIso),
                money(totalPrice), List.of(extras));
    }
}
