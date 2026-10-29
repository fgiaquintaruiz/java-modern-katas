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

    /** Builds an aircraft whose model is a real type matching the (category, engine) pair. */
    public static Aircraft aircraft(String registration, AircraftCategory category, EngineType engine, int seats,
                                    int flightHours, String dailyRate, String... features) {
        return aircraft(registration, realisticModel(category, engine), category, engine, seats, flightHours,
                dailyRate, features);
    }

    public static Aircraft aircraft(String registration, String model, AircraftCategory category, EngineType engine,
                                    int seats, int flightHours, String dailyRate, String... features) {
        return new Aircraft(registration, model, category, engine, seats, flightHours, money(dailyRate),
                List.of(features));
    }

    private static String realisticModel(AircraftCategory category, EngineType engine) {
        return switch (category) {
            case REGIONAL -> switch (engine) {
                case TURBOPROP -> "ATR 72";
                case PISTON -> "Tecnam P2012";
                case ELECTRIC -> "Eviation Alice";
                case TURBOFAN -> "Embraer E175";
            };
            case NARROW_BODY -> requireTurbofan(category, engine, "A320neo");
            case WIDE_BODY -> requireTurbofan(category, engine, "A350-900");
            case CARGO -> switch (engine) {
                case TURBOFAN -> "Boeing 777F";
                case TURBOPROP -> "ATR 72F";
                default -> throw unrealistic(category, engine);
            };
            case BUSINESS_JET -> switch (engine) {
                case TURBOFAN -> "Gulfstream G650";
                case TURBOPROP -> "Pilatus PC-12";
                case PISTON -> "Piper M350";
                case ELECTRIC -> "Beta ALIA";
            };
        };
    }

    private static String requireTurbofan(AircraftCategory category, EngineType engine, String model) {
        if (engine != EngineType.TURBOFAN) {
            throw unrealistic(category, engine);
        }
        return model;
    }

    private static IllegalArgumentException unrealistic(AircraftCategory category, EngineType engine) {
        return new IllegalArgumentException("No real aircraft for " + category + " + " + engine);
    }

    public static Airport airport(String code, String city, String country, Aircraft... fleet) {
        return new Airport(code, city, country, List.of(fleet));
    }

    public static Customer customer(String id, String name, String country, boolean loyalty) {
        return new Customer(id, name, country, loyalty);
    }

    public static Extra extra(String name, String price) {
        return new Extra(name, money(price));
    }

    public static Charter charter(String id, Customer customer, Aircraft aircraft, Airport origin, Airport destination,
                                  String startIso, String endIso, String totalPrice, Extra... extras) {
        return new Charter(id, customer, aircraft, origin, destination,
                java.time.LocalDate.parse(startIso), java.time.LocalDate.parse(endIso),
                money(totalPrice), List.of(extras));
    }
}
