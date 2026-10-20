package com.fgiaquinta.katas.domain;

import java.math.BigDecimal;
import java.util.List;

/**
 * A single vehicle in a branch's fleet.
 *
 * @param plate      license plate, unique within the fleet
 * @param model      commercial model name
 * @param category   commercial tier, see {@link VehicleCategory}
 * @param fuel       powertrain, see {@link FuelType}
 * @param seats      passenger seat count, including the driver
 * @param mileageKm  current odometer reading in kilometers
 * @param dailyRate  list price per rental day, before extras
 * @param features   free-form equipment tags, e.g. {@code "AC"}, {@code "GPS"}
 */
public record Vehicle(
        String plate,
        String model,
        VehicleCategory category,
        FuelType fuel,
        int seats,
        int mileageKm,
        BigDecimal dailyRate,
        List<String> features
) {

    public Vehicle {
        features = List.copyOf(features);
    }
}
