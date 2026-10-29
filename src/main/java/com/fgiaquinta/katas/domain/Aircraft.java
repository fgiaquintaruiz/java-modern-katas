package com.fgiaquinta.katas.domain;

import java.math.BigDecimal;
import java.util.List;

/**
 * A single aircraft in an airport's fleet.
 *
 * @param registration aircraft registration, unique within the fleet
 * @param model        commercial model name
 * @param category     commercial tier, see {@link AircraftCategory}
 * @param engine       powerplant, see {@link EngineType}
 * @param seats        passenger seat count, excluding the crew
 * @param flightHours  accumulated airframe flight hours
 * @param dailyRate    list price per charter day, before extras
 * @param features     free-form equipment tags, e.g. {@code "WIFI"}, {@code "CATERING"}
 */
public record Aircraft(
        String registration,
        String model,
        AircraftCategory category,
        EngineType engine,
        int seats,
        int flightHours,
        BigDecimal dailyRate,
        List<String> features
) {

    public Aircraft {
        features = List.copyOf(features);
    }
}
