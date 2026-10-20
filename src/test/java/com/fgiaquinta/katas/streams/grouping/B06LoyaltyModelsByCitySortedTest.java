package com.fgiaquinta.katas.streams.grouping;

import com.fgiaquinta.katas.domain.Branch;
import com.fgiaquinta.katas.domain.Customer;
import com.fgiaquinta.katas.domain.FuelType;
import com.fgiaquinta.katas.domain.Rental;
import com.fgiaquinta.katas.domain.Vehicle;
import com.fgiaquinta.katas.domain.VehicleCategory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.SortedMap;
import java.util.SortedSet;

import static com.fgiaquinta.katas.domain.TestData.branch;
import static com.fgiaquinta.katas.domain.TestData.customer;
import static com.fgiaquinta.katas.domain.TestData.rental;
import static com.fgiaquinta.katas.domain.TestData.vehicle;
import static org.assertj.core.api.Assertions.assertThat;

class B06LoyaltyModelsByCitySortedTest {

    @Test
    @DisplayName("groups loyalty members' vehicle models by city, sorted, excluding non-member-only cities")
    void loyaltyModelsByCity_withMixedMembership_excludesCitiesWithOnlyNonMemberRentals() {
        Customer loyal = customer("C1", "Ana", "Spain", true);
        Customer nonLoyal = customer("C2", "Bea", "Spain", false);

        Branch madrid = branch("MAD", "Madrid", "Spain");
        Branch barcelona = branch("BCN", "Barcelona", "Spain");
        Branch sevilla = branch("SEV", "Sevilla", "Spain");

        Vehicle zeta = vehicle("Zeta", VehicleCategory.ECONOMY, FuelType.PETROL, 4, 1000, "30.00");
        Vehicle alpha = vehicle("Alpha", VehicleCategory.COMPACT, FuelType.PETROL, 5, 1200, "35.00");
        Vehicle beta = vehicle("Beta", VehicleCategory.SUV, FuelType.DIESEL, 5, 2000, "60.00");
        Vehicle gamma = vehicle("Gamma", VehicleCategory.VAN, FuelType.DIESEL, 7, 500, "70.00");
        Vehicle delta = vehicle("Delta", VehicleCategory.LUXURY, FuelType.ELECTRIC, 4, 100, "120.00");

        List<Rental> rentals = List.of(
                rental("R1", loyal, zeta, madrid, madrid, "2024-01-01", "2024-01-02", "30.00"),
                rental("R2", loyal, alpha, madrid, madrid, "2024-01-03", "2024-01-04", "35.00"),
                rental("R3", nonLoyal, beta, madrid, madrid, "2024-01-05", "2024-01-06", "60.00"),
                rental("R4", nonLoyal, gamma, barcelona, barcelona, "2024-01-07", "2024-01-08", "70.00"),
                rental("R5", loyal, delta, sevilla, sevilla, "2024-01-09", "2024-01-10", "120.00")
        );

        SortedMap<String, SortedSet<String>> result = B06LoyaltyModelsByCitySorted.loyaltyModelsByCity(rentals);

        // Barcelona's only rental (R4) is by a non-loyalty customer -> must be absent.
        assertThat(result.keySet()).containsExactly("Madrid", "Sevilla");
        // Madrid loyalty models: Zeta (R1), Alpha (R2); Beta (R3) is non-loyalty, excluded; alphabetical order.
        assertThat(result.get("Madrid")).containsExactly("Alpha", "Zeta");
        // Sevilla loyalty models: Delta (R5).
        assertThat(result.get("Sevilla")).containsExactly("Delta");
    }

    @Test
    @DisplayName("returns an empty sorted map when the rental list is empty")
    void loyaltyModelsByCity_withEmptyList_returnsEmptyMap() {
        SortedMap<String, SortedSet<String>> result = B06LoyaltyModelsByCitySorted.loyaltyModelsByCity(List.of());

        assertThat(result).isEmpty();
    }
}
