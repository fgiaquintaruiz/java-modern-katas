package com.fgiaquinta.katas.language;

import com.fgiaquinta.katas.domain.Aircraft;
import com.fgiaquinta.katas.domain.AircraftCategory;
import com.fgiaquinta.katas.domain.Airport;
import com.fgiaquinta.katas.domain.Charter;
import com.fgiaquinta.katas.domain.Customer;
import com.fgiaquinta.katas.domain.EngineType;
import com.fgiaquinta.katas.language.E06SealedStatus.Cancelled;
import com.fgiaquinta.katas.language.E06SealedStatus.Delayed;
import com.fgiaquinta.katas.language.E06SealedStatus.Scheduled;
import com.fgiaquinta.katas.language.E06SealedStatus.Status;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.fgiaquinta.katas.domain.TestData.aircraft;
import static com.fgiaquinta.katas.domain.TestData.airport;
import static com.fgiaquinta.katas.domain.TestData.charter;
import static com.fgiaquinta.katas.domain.TestData.customer;
import static org.assertj.core.api.Assertions.assertThat;

class E06SealedStatusTest {

    private final Customer ana = customer("C1", "Ana", "Spain", true);
    private final Aircraft plane = aircraft("EC-001", AircraftCategory.REGIONAL, EngineType.PISTON, 11, 1000, "30.00");
    private final Airport madrid = airport("MAD", "Madrid", "Spain", plane);
    private final Charter r1 = charter("R1", ana, plane, madrid, madrid, "2024-01-01", "2024-01-02", "30.00");

    @Test
    @DisplayName("describes a scheduled status with the charter id")
    void describe_withScheduled_returnsScheduledAndCharterId() {
        assertThat(E06SealedStatus.describe(new Scheduled(r1))).isEqualTo("Scheduled R1");
    }

    @Test
    @DisplayName("describes a short delay plainly")
    void describe_withShortDelay_returnsDelayedMinutes() {
        assertThat(E06SealedStatus.describe(new Delayed(15))).isEqualTo("Delayed 15 min");
        assertThat(E06SealedStatus.describe(new Delayed(59))).isEqualTo("Delayed 59 min");
    }

    @Test
    @DisplayName("describes a delay of an hour or more as heavy")
    void describe_withDelayFromSixtyMinutes_returnsHeavilyDelayed() {
        assertThat(E06SealedStatus.describe(new Delayed(60))).isEqualTo("Heavily delayed 60 min");
        assertThat(E06SealedStatus.describe(new Delayed(90))).isEqualTo("Heavily delayed 90 min");
    }

    @Test
    @DisplayName("describes a cancellation with its reason")
    void describe_withCancelled_returnsCancelledAndReason() {
        assertThat(E06SealedStatus.describe(new Cancelled("weather"))).isEqualTo("Cancelled: weather");
    }

    @Test
    @DisplayName("demonstration: the hierarchy is sealed and permits exactly three records")
    void status_type_isSealedWithThreePermittedSubclasses() {
        assertThat(Status.class.isSealed()).isTrue();
        assertThat(Status.class.getPermittedSubclasses())
                .containsExactlyInAnyOrder(Scheduled.class, Delayed.class, Cancelled.class);
    }
}
