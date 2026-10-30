package com.fgiaquinta.katas.streams.flatmap;

import com.fgiaquinta.katas.domain.Charter;
import com.fgiaquinta.katas.domain.Extra;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Exercise A05 — Charter extra lines.
 *
 * <h2>Statement</h2>
 * Given a list of {@link Charter}, produce one formatted line per extra attached to each charter,
 * using the format {@code "<charterId>:<extraName>"}. A charter with no extras contributes no lines
 * at all.
 *
 * <h2>Concepts</h2>
 * {@code flatMap} yielding variable-size inner streams, including {@code Stream.empty()};
 * one inner {@code Stream} allocated per outer element;
 * why {@code map} plus {@code filter} cannot express a one-to-many expansion.
 *
 * <h2>Internals</h2>
 * R2, the charter with no extras, produces zero elements: the inner stream has no elements, so the inner map runs zero times and flatMap pushes nothing downstream; an empty list has nothing to work with, so a filter on non-empty extras before flatMap was redundant, the inner stream takes care of it anyway.
 * The inner map lambda is nested inside the flatMap lambda, so it can read charter; the compiler requires charter to be final or effectively final, as if it were final: there is no reassignment, so it keeps its original value.
 * map works with one element in and one element out, so it can never turn R1 into two lines; flatMap takes one charter in and hands all its extras out, zero or many, flattening each charter's list of extras and handing each element downstream.
 * <h2>Anchor</h2>
 * A line of people passing bags of sand: if there is nothing to pass, then there is no weight to carry.
 * The nested lambdas are like a father and son: the son can look up to the father.
 */
public final class A05CharterExtraLines {

    private A05CharterExtraLines() {
    }

    public static List<String> extraLines(List<Charter> charters) {
        return charters.stream()
                .flatMap(charter -> charter.extras().stream()
                        .map(extra -> charter.id()
                                .concat(":")
                                .concat(extra.name()))
                ).toList();

    }
}
