package com.fgiaquinta.katas.language;

import java.math.BigDecimal;

/**
 * Exercise E05 — Record internals.
 *
 * <h2>Statement</h2>
 * Complete {@link Quote} with a compact constructor, then implement {@link #quote} to build one. A negative
 * price is rejected, a zero price is accepted, and the route is stored without surrounding whitespace.
 *
 * <h2>Concepts</h2>
 * Compact canonical constructor (validate, then reassign the parameters, never the fields); accessors named after the components; {@code equals}, {@code hashCode} and {@code toString} generated from all components; a record is implicitly final and already extends {@code java.lang.Record}.
 *
 * <h2>Internals</h2>
 * TODO: fill after solving.
 * <h2>Anchor</h2>
 * TODO: your own image, written by you.
 */
public final class E05RecordInternals {

    private E05RecordInternals() {
    }

    /** Stub: the components are given, the constructor logic is yours. */
    public record Quote(String route, BigDecimal price) {
    }

    public static Quote quote(String route, BigDecimal price) {
        throw new UnsupportedOperationException("Not solved yet");
    }
}
