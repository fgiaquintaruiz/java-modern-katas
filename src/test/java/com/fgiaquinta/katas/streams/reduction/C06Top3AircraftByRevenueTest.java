package com.fgiaquinta.katas.streams.reduction;

import com.fgiaquinta.katas.domain.Airport;
import com.fgiaquinta.katas.domain.Customer;
import com.fgiaquinta.katas.domain.EngineType;
import com.fgiaquinta.katas.domain.Charter;
import com.fgiaquinta.katas.domain.Aircraft;
import com.fgiaquinta.katas.domain.AircraftCategory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.fgiaquinta.katas.domain.TestData.airport;
import static com.fgiaquinta.katas.domain.TestData.customer;
import static com.fgiaquinta.katas.domain.TestData.charter;
import static com.fgiaquinta.katas.domain.TestData.aircraft;
import static org.assertj.core.api.Assertions.assertThat;

class C06Top3AircraftByRevenueTest {

    private static final Customer CUSTOMER = customer("C1", "Alice", "ES", true);
    private static final Airport AIRPORT = airport("MAD", "Madrid", "ES");

    @Test
    @DisplayName("returns the registrations of the three aircraft with the highest summed revenue, highest first")
    void top3RegistrationsByRevenue_withMoreThanThreeAircraft_returnsTopThreeDescending() {
        Aircraft v1 = aircraft("EC-AAA", AircraftCategory.REGIONAL, EngineType.PISTON, 11, 10_000, "35.00");
        Aircraft v2 = aircraft("EC-BBB", AircraftCategory.NARROW_BODY, EngineType.TURBOFAN, 180, 20_000, "45.00");
        Aircraft v3 = aircraft("EC-CCC", AircraftCategory.WIDE_BODY, EngineType.TURBOFAN, 300, 15_000, "60.00");
        Aircraft v4 = aircraft("EC-DDD", AircraftCategory.CARGO, EngineType.TURBOFAN, 0, 30_000, "55.00");

        List<Charter> charters = List.of(
                // EC-AAA revenue: 100.00 + 50.00 = 150.00
                charter("R1", CUSTOMER, v1, AIRPORT, AIRPORT, "2026-01-01", "2026-01-02", "100.00"),
                charter("R2", CUSTOMER, v1, AIRPORT, AIRPORT, "2026-01-05", "2026-01-06", "50.00"),
                // EC-BBB revenue: 200.00
                charter("R3", CUSTOMER, v2, AIRPORT, AIRPORT, "2026-02-01", "2026-02-02", "200.00"),
                // EC-CCC revenue: 120.00
                charter("R4", CUSTOMER, v3, AIRPORT, AIRPORT, "2026-03-01", "2026-03-02", "120.00"),
                // EC-DDD revenue: 90.00
                charter("R5", CUSTOMER, v4, AIRPORT, AIRPORT, "2026-04-01", "2026-04-02", "90.00")
        );

        List<String> result = C06Top3AircraftByRevenue.top3RegistrationsByRevenue(charters);

        // ranking: EC-BBB (200.00) > EC-AAA (150.00) > EC-CCC (120.00) > EC-DDD (90.00) -> EC-DDD excluded
        assertThat(result).containsExactly("EC-BBB", "EC-AAA", "EC-CCC");
    }

    @Test
    @DisplayName("breaks a revenue tie by registration ascending order")
    void top3RegistrationsByRevenue_withTiedRevenue_breaksTieByRegistrationAscending() {
        Aircraft lowest = aircraft("EC-ZZZ", AircraftCategory.REGIONAL, EngineType.PISTON, 11, 10_000, "35.00");
        Aircraft tiedA = aircraft("EC-AAA", AircraftCategory.NARROW_BODY, EngineType.TURBOFAN, 180, 20_000, "45.00");
        Aircraft tiedB = aircraft("EC-BBB", AircraftCategory.WIDE_BODY, EngineType.TURBOFAN, 300, 15_000, "60.00");

        List<Charter> charters = List.of(
                // EC-ZZZ revenue: 100.00
                charter("R1", CUSTOMER, lowest, AIRPORT, AIRPORT, "2026-01-01", "2026-01-02", "100.00"),
                // EC-AAA revenue: 150.00, tied with EC-BBB
                charter("R2", CUSTOMER, tiedA, AIRPORT, AIRPORT, "2026-02-01", "2026-02-02", "150.00"),
                // EC-BBB revenue: 150.00, tied with EC-AAA
                charter("R3", CUSTOMER, tiedB, AIRPORT, AIRPORT, "2026-03-01", "2026-03-02", "150.00")
        );

        List<String> result = C06Top3AircraftByRevenue.top3RegistrationsByRevenue(charters);

        // EC-AAA and EC-BBB tie at 150.00 -> ascending registration order puts EC-AAA before EC-BBB
        assertThat(result).containsExactly("EC-AAA", "EC-BBB", "EC-ZZZ");
    }

    @Test
    @DisplayName("returns all registrations when fewer than three distinct aircraft are present")
    void top3RegistrationsByRevenue_withFewerThanThreeAircraft_returnsAllOfThem() {
        Aircraft v1 = aircraft("EC-AAA", AircraftCategory.REGIONAL, EngineType.PISTON, 11, 10_000, "35.00");
        Aircraft v2 = aircraft("EC-BBB", AircraftCategory.NARROW_BODY, EngineType.TURBOFAN, 180, 20_000, "45.00");

        List<Charter> charters = List.of(
                // EC-AAA revenue: 120.00
                charter("R1", CUSTOMER, v1, AIRPORT, AIRPORT, "2026-01-01", "2026-01-02", "120.00"),
                // EC-BBB revenue: 80.00
                charter("R2", CUSTOMER, v2, AIRPORT, AIRPORT, "2026-02-01", "2026-02-02", "80.00")
        );

        List<String> result = C06Top3AircraftByRevenue.top3RegistrationsByRevenue(charters);

        assertThat(result).containsExactly("EC-AAA", "EC-BBB");
    }
}
