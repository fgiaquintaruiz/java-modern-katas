package com.fgiaquinta.katas.kt.language

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class E03InitialisationOrderTest {

    @Test
    @DisplayName("initialisationOrder_withTwoInstances_runsCompanionLazilyOnceThenInstancePartsTwice")
    fun initialisationOrder_withTwoInstances_runsCompanionLazilyOnceThenInstancePartsTwice() {
        // "before" comes first: the companion has not run before the class is first used.
        assertThat(initialisationOrder()).containsExactly(
            "before", "companion",
            "property", "init", "secondary",
            "property", "init", "secondary")
    }
}
