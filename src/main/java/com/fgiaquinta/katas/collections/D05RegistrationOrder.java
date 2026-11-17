package com.fgiaquinta.katas.collections;

import com.fgiaquinta.katas.domain.Aircraft;

import java.util.List;
import java.util.NavigableSet;

/**
 * Exercise D05 — Registrations ordered by length, then alphabetically.
 *
 * <h2>Statement</h2>
 * Return the registrations of the given aircraft as a navigable set ordered by length first and then
 * alphabetically ignoring case. Two registrations that differ only in case are the same element.
 *
 * <h2>Concepts</h2>
 * {@code TreeSet} with a {@code Comparator} ({@code comparingInt} then {@code thenComparing(String.CASE_INSENSITIVE_ORDER)}); the comparator, not {@code equals}, decides duplicates; {@code first}, {@code last}, {@code higher}, {@code lower}.
 *
 * <h2>Internals</h2>
 * TODO: fill after solving.
 * <h2>Anchor</h2>
 * TODO: your own image, written by you.
 */
public final class D05RegistrationOrder {

    private D05RegistrationOrder() {
    }

    public static NavigableSet<String> orderedRegistrations(List<Aircraft> fleet) {
        throw new UnsupportedOperationException("Not solved yet");
    }
}
