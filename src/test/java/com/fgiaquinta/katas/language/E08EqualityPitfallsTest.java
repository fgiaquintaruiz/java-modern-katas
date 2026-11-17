package com.fgiaquinta.katas.language;

import com.fgiaquinta.katas.language.E08EqualityPitfalls.Results;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class E08EqualityPitfallsTest {

    @Test
    @DisplayName("boxed Integers are the same object only inside the cache range")
    void compare_boxedIntegers_areIdenticalOnlyInsideCacheRange() {
        Results results = E08EqualityPitfalls.compare();

        assertThat(results.valueOf127Identical()).isTrue();
        assertThat(results.valueOfMinus128Identical()).isTrue();
        assertThat(results.valueOf128Identical()).isFalse();
        assertThat(results.valueOfMinus129Identical()).isFalse();
        assertThat(results.valueOf128Equal()).isTrue();
    }

    @Test
    @DisplayName("string literals share the pool, new String does not, intern brings it back")
    void compare_strings_identityFollowsThePool() {
        Results results = E08EqualityPitfalls.compare();

        assertThat(results.literalsIdentical()).isTrue();
        assertThat(results.newStringIdenticalToLiteral()).isFalse();
        assertThat(results.newStringEqualToLiteral()).isTrue();
        assertThat(results.internedIdenticalToLiteral()).isTrue();
    }
}
