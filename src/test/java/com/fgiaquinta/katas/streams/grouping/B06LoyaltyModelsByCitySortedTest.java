package com.fgiaquinta.katas.streams.grouping;

import com.fgiaquinta.katas.domain.Airport;
import com.fgiaquinta.katas.domain.Customer;
import com.fgiaquinta.katas.domain.EngineType;
import com.fgiaquinta.katas.domain.Charter;
import com.fgiaquinta.katas.domain.Aircraft;
import com.fgiaquinta.katas.domain.AircraftCategory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.SortedMap;
import java.util.SortedSet;

import static com.fgiaquinta.katas.domain.TestData.airport;
import static com.fgiaquinta.katas.domain.TestData.customer;
import static com.fgiaquinta.katas.domain.TestData.charter;
import static com.fgiaquinta.katas.domain.TestData.aircraft;
import static org.assertj.core.api.Assertions.assertThat;

class B06LoyaltyModelsByCitySortedTest {

    @Test
    @DisplayName("groups loyalty members' aircraft models by city, sorted, excluding non-member-only cities")
    void loyaltyModelsByCity_withMixedMembership_excludesCitiesWithOnlyNonMemberCharters() {
        Customer loyal = customer("C1", "Ana", "Spain", true);
        Customer nonLoyal = customer("C2", "Bea", "Spain", false);

        Airport madrid = airport("MAD", "Madrid", "Spain");
        Airport barcelona = airport("BCN", "Barcelona", "Spain");
        Airport sevilla = airport("SEV", "Sevilla", "Spain");

        Aircraft tecnam = aircraft("EC-P12", "Tecnam P2012", AircraftCategory.REGIONAL, EngineType.PISTON, 11, 1000, "30.00");
        Aircraft a320neo = aircraft("EC-A32", "A320neo", AircraftCategory.NARROW_BODY, EngineType.TURBOFAN, 180, 1200, "35.00");
        Aircraft a350 = aircraft("EC-A35", "A350-900", AircraftCategory.WIDE_BODY, EngineType.TURBOFAN, 300, 2000, "60.00");
        Aircraft atr72f = aircraft("EC-ATF", "ATR 72F", AircraftCategory.CARGO, EngineType.TURBOPROP, 0, 500, "70.00");
        Aircraft alia = aircraft("EC-ALI", "Beta ALIA", AircraftCategory.BUSINESS_JET, EngineType.ELECTRIC, 6, 100, "120.00");

        List<Charter> charters = List.of(
                charter("R1", loyal, tecnam, madrid, madrid, "2024-01-01", "2024-01-02", "30.00"),
                charter("R2", loyal, a320neo, madrid, madrid, "2024-01-03", "2024-01-04", "35.00"),
                charter("R3", nonLoyal, a350, madrid, madrid, "2024-01-05", "2024-01-06", "60.00"),
                charter("R4", nonLoyal, atr72f, barcelona, barcelona, "2024-01-07", "2024-01-08", "70.00"),
                charter("R5", loyal, alia, sevilla, sevilla, "2024-01-09", "2024-01-10", "120.00")
        );

        SortedMap<String, SortedSet<String>> result = B06LoyaltyModelsByCitySorted.loyaltyModelsByCity(charters);

        // Barcelona's only charter (R4) is by a non-loyalty customer -> must be absent.
        assertThat(result.keySet()).containsExactly("Madrid", "Sevilla");
        // Madrid loyalty models: Tecnam P2012 (R1), A320neo (R2); A350-900 (R3) is non-loyalty, excluded; alphabetical order.
        assertThat(result.get("Madrid")).containsExactly("A320neo", "Tecnam P2012");
        // Sevilla loyalty models: Beta ALIA (R5).
        assertThat(result.get("Sevilla")).containsExactly("Beta ALIA");
    }

    @Test
    @DisplayName("returns an empty sorted map when the charter list is empty")
    void loyaltyModelsByCity_withEmptyList_returnsEmptyMap() {
        SortedMap<String, SortedSet<String>> result = B06LoyaltyModelsByCitySorted.loyaltyModelsByCity(List.of());

        assertThat(result).isEmpty();
    }
}
