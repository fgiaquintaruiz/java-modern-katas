package com.fgiaquinta.katas.language;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class E03InitialisationOrderTest {

    @Test
    @DisplayName("statics run once in textual order, then each instance runs its initialiser block and constructor")
    void initialisationOrder_withTwoInstances_runsStaticsOnceThenInstancePartsTwice() {
        // The static field initialiser is declared before the static block.
        assertThat(E03InitialisationOrder.initialisationOrder()).containsExactly(
                "static-field", "static-block",
                "instance-block", "constructor",
                "instance-block", "constructor");
    }
}
