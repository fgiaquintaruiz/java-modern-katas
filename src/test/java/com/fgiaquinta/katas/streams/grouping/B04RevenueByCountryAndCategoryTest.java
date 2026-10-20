package com.fgiaquinta.katas.streams.grouping;

import com.fgiaquinta.katas.domain.Branch;
import com.fgiaquinta.katas.domain.Customer;
import com.fgiaquinta.katas.domain.FuelType;
import com.fgiaquinta.katas.domain.Rental;
import com.fgiaquinta.katas.domain.Vehicle;
import com.fgiaquinta.katas.domain.VehicleCategory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import static com.fgiaquinta.katas.domain.TestData.branch;
import static com.fgiaquinta.katas.domain.TestData.customer;
import static com.fgiaquinta.katas.domain.TestData.rental;
import static com.fgiaquinta.katas.domain.TestData.vehicle;
import static org.assertj.core.api.Assertions.assertThat;

class B04RevenueByCountryAndCategoryTest {

    private static final Customer CUSTOMER = customer("C1", "Ana", "Spain", true);

    @Test
    @DisplayName("sums totalPrice grouped by pickup country then by vehicle category")
    void revenueByCountryAndCategory_withMultipleCountriesAndCategories_sumsEachBucket() {
        Vehicle economyCar = vehicle("P1", VehicleCategory.ECONOMY, FuelType.PETROL, 4, 1000, "30.00");
        Vehicle suv = vehicle("P2", VehicleCategory.SUV, FuelType.DIESEL, 5, 2000, "60.00");
        Branch spainBranch = branch("MAD", "Madrid", "Spain", economyCar, suv);
        Branch franceBranch = branch("PAR", "Paris", "France", economyCar);

        List<Rental> rentals = List.of(
                rental("R1", CUSTOMER, economyCar, spainBranch, spainBranch, "2024-01-01", "2024-01-03", "100.00"),
                rental("R2", CUSTOMER, economyCar, spainBranch, spainBranch, "2024-01-05", "2024-01-07", "50.00"),
                rental("R3", CUSTOMER, suv, spainBranch, spainBranch, "2024-02-01", "2024-02-04", "200.00"),
                rental("R4", CUSTOMER, economyCar, franceBranch, franceBranch, "2024-03-01", "2024-03-02", "80.00")
        );

        Map<String, Map<VehicleCategory, BigDecimal>> result =
                B04RevenueByCountryAndCategory.revenueByCountryAndCategory(rentals);

        assertThat(result).containsOnlyKeys("Spain", "France");
        assertThat(result.get("Spain")).containsOnlyKeys(VehicleCategory.ECONOMY, VehicleCategory.SUV);
        // Spain / ECONOMY: 100.00 + 50.00 = 150.00
        assertThat(result.get("Spain").get(VehicleCategory.ECONOMY)).isEqualByComparingTo("150.00");
        // Spain / SUV: 200.00 (single rental)
        assertThat(result.get("Spain").get(VehicleCategory.SUV)).isEqualByComparingTo("200.00");
        assertThat(result.get("France")).containsOnlyKeys(VehicleCategory.ECONOMY);
        // France / ECONOMY: 80.00 (single rental)
        assertThat(result.get("France").get(VehicleCategory.ECONOMY)).isEqualByComparingTo("80.00");
    }

    @Test
    @DisplayName("returns an empty map when the rental list is empty")
    void revenueByCountryAndCategory_withEmptyList_returnsEmptyMap() {
        Map<String, Map<VehicleCategory, BigDecimal>> result =
                B04RevenueByCountryAndCategory.revenueByCountryAndCategory(List.of());

        assertThat(result).isEmpty();
    }
}
