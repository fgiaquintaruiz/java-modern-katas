package com.fgiaquinta.katas.domain;

import org.junit.jupiter.api.Test;

import static com.fgiaquinta.katas.domain.TestData.airport;
import static com.fgiaquinta.katas.domain.TestData.customer;
import static com.fgiaquinta.katas.domain.TestData.charter;
import static com.fgiaquinta.katas.domain.TestData.aircraft;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

/**
 * Sanity test for the shared domain model. Verifies the invariants that
 * every kata relies on: {@link Charter#days()} computes an end-exclusive
 * duration, and a {@link Charter} cannot be built with an end date that is
 * not strictly after its start date.
 */
class DomainModelTest {

    @Test
    void days_forThreeDayCharter_returnsThree() {
        var originAirport = airport("MAD", "Madrid", "ES",
                aircraft("EC-ABC", AircraftCategory.REGIONAL, EngineType.PISTON, 11, 15000, "35.00", "AC"));
        var customer = customer("C1", "Ada Lovelace", "ES", true);
        var charteredAircraft = originAirport.fleet().get(0);

        var charter = charter("R1", customer, charteredAircraft, originAirport, originAirport,
                "2026-10-01", "2026-10-04", "105.00");

        assertThat(charter.days()).isEqualTo(3L);
    }

    @Test
    void constructor_whenEndIsBeforeStart_throwsIllegalArgumentException() {
        var originAirport = airport("MAD", "Madrid", "ES",
                aircraft("EC-ABC", AircraftCategory.REGIONAL, EngineType.PISTON, 11, 15000, "35.00", "AC"));
        var customer = customer("C1", "Ada Lovelace", "ES", true);
        var charteredAircraft = originAirport.fleet().get(0);

        assertThatIllegalArgumentException().isThrownBy(() ->
                charter("R1", customer, charteredAircraft, originAirport, originAirport,
                        "2026-10-04", "2026-10-01", "105.00"));
    }
}
