package com.fgiaquinta.katas.streams.reduction;

import com.fgiaquinta.katas.domain.Airport;
import com.fgiaquinta.katas.domain.Customer;
import com.fgiaquinta.katas.domain.EngineType;
import com.fgiaquinta.katas.domain.Charter;
import com.fgiaquinta.katas.domain.Aircraft;
import com.fgiaquinta.katas.domain.AircraftCategory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static com.fgiaquinta.katas.domain.TestData.airport;
import static com.fgiaquinta.katas.domain.TestData.customer;
import static com.fgiaquinta.katas.domain.TestData.money;
import static com.fgiaquinta.katas.domain.TestData.charter;
import static com.fgiaquinta.katas.domain.TestData.aircraft;
import static org.assertj.core.api.Assertions.assertThat;

class C03TotalRevenueTest {

    @Test
    @DisplayName("sums totalPrice across all charters")
    void totalRevenue_withMultipleCharters_sumsAllTotalPrices() {
        Customer customer = customer("C1", "Alice", "ES", true);
        Aircraft aircraft = aircraft("EC-AAA", AircraftCategory.REGIONAL, EngineType.PISTON, 11, 10_000, "35.00");
        Airport airport = airport("MAD", "Madrid", "ES");

        List<Charter> charters = List.of(
                charter("R1", customer, aircraft, airport, airport, "2026-01-01", "2026-01-02", "120.00"),
                charter("R2", customer, aircraft, airport, airport, "2026-02-01", "2026-02-02", "80.50"),
                charter("R3", customer, aircraft, airport, airport, "2026-03-01", "2026-03-02", "45.25")
        );

        BigDecimal result = C03TotalRevenue.totalRevenue(charters);

        // 120.00 + 80.50 + 45.25 = 245.75
        assertThat(result).isEqualByComparingTo(money("245.75"));
    }

    @Test
    @DisplayName("returns BigDecimal.ZERO for an empty charter list")
    void totalRevenue_withEmptyList_returnsZero() {
        BigDecimal result = C03TotalRevenue.totalRevenue(List.of());

        assertThat(result).isEqualByComparingTo(BigDecimal.ZERO);
    }
}
