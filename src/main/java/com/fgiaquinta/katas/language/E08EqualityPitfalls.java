package com.fgiaquinta.katas.language;

/**
 * Exercise E08 — Identity versus equality pitfalls.
 *
 * <h2>Statement</h2>
 * Fill a {@link Results} by actually comparing, with {@code ==} unless the name says equals, boxed
 * Integers obtained with {@code Integer.valueOf} and Strings. The test knows which comparisons are true.
 *
 * <h2>Concepts</h2>
 * {@code Integer.valueOf} caches -128 to 127; {@code ==} on references compares identity; string literals share the pool, {@code new String} does not; {@code intern()} returns the pooled instance; {@code equals} compares content.
 *
 * <h2>Internals</h2>
 * TODO: fill after solving.
 * <h2>Anchor</h2>
 * TODO: your own image, written by you.
 */
public final class E08EqualityPitfalls {

    private E08EqualityPitfalls() {
    }

    public record Results(
            boolean valueOf127Identical,
            boolean valueOfMinus128Identical,
            boolean valueOf128Identical,
            boolean valueOfMinus129Identical,
            boolean valueOf128Equal,
            boolean literalsIdentical,
            boolean newStringIdenticalToLiteral,
            boolean newStringEqualToLiteral,
            boolean internedIdenticalToLiteral) {
    }

    public static Results compare() {
        throw new UnsupportedOperationException("Not solved yet");
    }
}
