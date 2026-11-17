package com.fgiaquinta.katas.collections;

import com.fgiaquinta.katas.domain.Aircraft;
import com.fgiaquinta.katas.domain.AircraftCategory;
import com.fgiaquinta.katas.domain.EngineType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.NavigableSet;

import static com.fgiaquinta.katas.domain.TestData.aircraft;
import static org.assertj.core.api.Assertions.assertThat;

class D05RegistrationOrderTest {

    private static Aircraft withRegistration(String registration) {
        return aircraft(registration, AircraftCategory.REGIONAL, EngineType.PISTON, 11, 1000, "30.00");
    }

    private final List<Aircraft> fleet = List.of(
            withRegistration("EC-A350"),
            withRegistration("EC-P12"),
            withRegistration("EC-ATF"),
            withRegistration("EC-AL"),
            withRegistration("EC-ALI"));

    @Test
    @DisplayName("orders by length first, then alphabetically")
    void orderedRegistrations_withMixedLengths_ordersByLengthThenAlphabetically() {
        assertThat(D05RegistrationOrder.orderedRegistrations(fleet))
                .containsExactly("EC-AL", "EC-ALI", "EC-ATF", "EC-P12", "EC-A350");
    }

    @Test
    @DisplayName("exposes first, last, higher and lower according to the comparator")
    void orderedRegistrations_navigation_followsComparatorOrder() {
        NavigableSet<String> result = D05RegistrationOrder.orderedRegistrations(fleet);

        assertThat(result.first()).isEqualTo("EC-AL");
        assertThat(result.last()).isEqualTo("EC-A350");
        assertThat(result.higher("EC-ATF")).isEqualTo("EC-P12");
        assertThat(result.lower("EC-ATF")).isEqualTo("EC-ALI");
        assertThat(result.higher("EC-A350")).isNull();
        assertThat(result.lower("EC-AL")).isNull();
    }

    @Test
    @DisplayName("collapses registrations that the comparator considers equal, keeping the first inserted")
    void orderedRegistrations_withCaseVariant_collapsesToFirstInserted() {
        List<Aircraft> withVariant = List.of(
                withRegistration("EC-ATF"),
                withRegistration("ec-atf"),
                withRegistration("EC-AL"));

        NavigableSet<String> result = D05RegistrationOrder.orderedRegistrations(withVariant);

        assertThat(result).containsExactly("EC-AL", "EC-ATF");
        assertThat(result.contains("ec-atf")).isTrue();
    }

    @Test
    @DisplayName("returns an empty set for an empty fleet")
    void orderedRegistrations_withEmptyFleet_returnsEmptySet() {
        assertThat(D05RegistrationOrder.orderedRegistrations(List.of())).isEmpty();
    }
}
