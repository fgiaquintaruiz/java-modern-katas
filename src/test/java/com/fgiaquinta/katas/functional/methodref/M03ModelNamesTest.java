package com.fgiaquinta.katas.functional.methodref;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.fgiaquinta.katas.domain.EngineType.TURBOFAN;
import static com.fgiaquinta.katas.domain.EngineType.PISTON;
import static com.fgiaquinta.katas.domain.TestData.aircraft;
import static com.fgiaquinta.katas.domain.AircraftCategory.REGIONAL;
import static com.fgiaquinta.katas.domain.AircraftCategory.WIDE_BODY;
import static org.assertj.core.api.Assertions.assertThat;

class M03ModelNamesTest {

    @Test
    @DisplayName("modelNames_withTwoAircraft_returnsModelsInInputOrder")
    void modelNames_withTwoAircraft_returnsModelsInInputOrder() {
        // TestData derives the model from category and engine: input order b, a gives "Tecnam P2012", "A350-900".
        var a = aircraft("EC-AAA", WIDE_BODY, TURBOFAN, 300, 10_000, "60.00");
        var b = aircraft("EC-BBB", REGIONAL, PISTON, 11, 5_000, "30.00");

        assertThat(M03ModelNames.modelNames(List.of(b, a))).containsExactly("Tecnam P2012", "A350-900");
    }

    @Test
    @DisplayName("modelNames_withEmptyList_returnsEmptyList")
    void modelNames_withEmptyList_returnsEmptyList() {
        assertThat(M03ModelNames.modelNames(List.of())).isEmpty();
    }
}
