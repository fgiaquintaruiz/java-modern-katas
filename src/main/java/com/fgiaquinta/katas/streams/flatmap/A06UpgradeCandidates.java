package com.fgiaquinta.katas.streams.flatmap;

import com.fgiaquinta.katas.domain.Rental;

import java.util.List;

/**
 * Exercise A06 — Upgrade candidates.
 *
 * <h2>Statement</h2>
 * Given a list of {@link Rental}, for each rental consider every vehicle in the pickup branch's
 * fleet and collect those whose {@link VehicleCategory} tier is strictly higher than the tier of
 * the vehicle actually rented, wrapped as {@link Upgrade}. Tier is the enum's declaration order
 * (e.g. {@code SUV} ranks above {@code COMPACT}).
 *
 * <h2>Concepts</h2>
 * a nested {@code flatMap} whose inner lambda closes over the outer rental; enum {@code compareTo} as the tier comparison; cartesian-style expansion of one rental against a whole fleet.
 *
 * <h2>Internals</h2>
 * TODO after solving: explain what happens inside the pipeline, not what the code does.
 * <h2>Anchor</h2>
 * TODO after solving: one sentence, your own image from a world you know, that ties this operation to its mechanism.
 */
public final class A06UpgradeCandidates {

    private A06UpgradeCandidates() {
    }

    /**
     * A candidate upgrade: the rental that could be upgraded, and the
     * plate of the pickup-branch vehicle offered as the upgrade.
     *
     * @param rentalId id of the rental being considered for an upgrade
     * @param plate    plate of the candidate vehicle in the pickup fleet
     */
    public record Upgrade(String rentalId, String plate) {
    }

    public static List<Upgrade> upgradeCandidates(List<Rental> rentals) {
        throw new UnsupportedOperationException("Not solved yet");
    }
}
