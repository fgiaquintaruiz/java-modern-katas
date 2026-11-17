package com.fgiaquinta.katas.kt.language

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class E08EqualityPitfallsTest {

    @Test
    @DisplayName("compare_boxedIntegers_areIdenticalOnlyInsideCacheRangeButAlwaysEqual")
    fun compare_boxedIntegers_areIdenticalOnlyInsideCacheRangeButAlwaysEqual() {
        val results = compare()

        assertThat(results.valueOf127Identical).isTrue()
        assertThat(results.valueOfMinus128Identical).isTrue()
        assertThat(results.valueOf128Identical).isFalse()
        assertThat(results.valueOfMinus129Identical).isFalse()
        // Kotlin's == is equals: it is true even outside the cache.
        assertThat(results.valueOf128EqualsOperator).isTrue()
    }

    @Test
    @DisplayName("compare_strings_identityFollowsThePoolAndEqualsOperatorComparesContent")
    fun compare_strings_identityFollowsThePoolAndEqualsOperatorComparesContent() {
        val results = compare()

        assertThat(results.literalsIdentical).isTrue()
        assertThat(results.newStringIdenticalToLiteral).isFalse()
        assertThat(results.newStringEqualsOperatorToLiteral).isTrue()
        assertThat(results.internedIdenticalToLiteral).isTrue()
    }
}
