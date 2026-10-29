package com.fgiaquinta.katas.streams.grouping;

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
import java.util.Map;

import static com.fgiaquinta.katas.domain.TestData.airport;
import static com.fgiaquinta.katas.domain.TestData.customer;
import static com.fgiaquinta.katas.domain.TestData.charter;
import static com.fgiaquinta.katas.domain.TestData.aircraft;
import static org.assertj.core.api.Assertions.assertThat;

class B04RevenueByCountryAndCategoryTest {

    private static final Customer CUSTOMER = customer("C1", "Ana", "Spain", true);

    @Test
    @DisplayName("sums totalPrice grouped by origin country then by aircraft category")
    void revenueByCountryAndCategory_withMultipleCountriesAndCategories_sumsEachBucket() {
        Aircraft regional = aircraft("EC-P01", AircraftCategory.REGIONAL, EngineType.PISTON, 11, 1000, "30.00");
        Aircraft wideBody = aircraft("EC-P02", AircraftCategory.WIDE_BODY, EngineType.TURBOFAN, 300, 2000, "60.00");
        Airport spainAirport = airport("MAD", "Madrid", "Spain", regional, wideBody);
        Airport franceAirport = airport("PAR", "Paris", "France", regional);

        List<Charter> charters = List.of(
                charter("R1", CUSTOMER, regional, spainAirport, spainAirport, "2024-01-01", "2024-01-03", "100.00"),
                charter("R2", CUSTOMER, regional, spainAirport, spainAirport, "2024-01-05", "2024-01-07", "50.00"),
                charter("R3", CUSTOMER, wideBody, spainAirport, spainAirport, "2024-02-01", "2024-02-04", "200.00"),
                charter("R4", CUSTOMER, regional, franceAirport, franceAirport, "2024-03-01", "2024-03-02", "80.00")
        );

        Map<String, Map<AircraftCategory, BigDecimal>> result =
                B04RevenueByCountryAndCategory.revenueByCountryAndCategory(charters);

        assertThat(result).containsOnlyKeys("Spain", "France");
        assertThat(result.get("Spain")).containsOnlyKeys(AircraftCategory.REGIONAL, AircraftCategory.WIDE_BODY);
        // Spain / REGIONAL: 100.00 + 50.00 = 150.00
        assertThat(result.get("Spain").get(AircraftCategory.REGIONAL)).isEqualByComparingTo("150.00");
        // Spain / WIDE_BODY: 200.00 (single charter)
        assertThat(result.get("Spain").get(AircraftCategory.WIDE_BODY)).isEqualByComparingTo("200.00");
        assertThat(result.get("France")).containsOnlyKeys(AircraftCategory.REGIONAL);
        // France / REGIONAL: 80.00 (single charter)
        assertThat(result.get("France").get(AircraftCategory.REGIONAL)).isEqualByComparingTo("80.00");
    }

    @Test
    @DisplayName("returns an empty map when the charter list is empty")
    void revenueByCountryAndCategory_withEmptyList_returnsEmptyMap() {
        Map<String, Map<AircraftCategory, BigDecimal>> result =
                B04RevenueByCountryAndCategory.revenueByCountryAndCategory(List.of());

        assertThat(result).isEmpty();
    }
}
