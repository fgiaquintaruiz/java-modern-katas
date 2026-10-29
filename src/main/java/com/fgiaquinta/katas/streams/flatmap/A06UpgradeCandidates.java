package com.fgiaquinta.katas.streams.flatmap;

import com.fgiaquinta.katas.domain.Charter;

import java.util.List;

/**
 * Exercise A06 — Upgrade candidates.
 *
 * <h2>Statement</h2>
 * Given a list of {@link Charter}, for each charter consider every aircraft in the origin airport's
 * fleet and collect those whose {@link AircraftCategory} tier is strictly higher than the tier of
 * the aircraft actually chartered, wrapped as {@link Upgrade}. Tier is the enum's declaration order
 * (e.g. {@code WIDE_BODY} ranks above {@code NARROW_BODY}).
 *
 * <h2>Concepts</h2>
 * a nested {@code flatMap} whose inner lambda closes over the outer charter; enum {@code compareTo} as the tier comparison; cartesian-style expansion of one charter against a whole fleet.
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
     * A candidate upgrade: the charter that could be upgraded, and the
     * registration of the origin-airport aircraft offered as the upgrade.
     *
     * @param charterId id of the charter being considered for an upgrade
     * @param registration    registration of the candidate aircraft in the origin fleet
     */
    public record Upgrade(String charterId, String registration) {
    }

    public static List<Upgrade> upgradeCandidates(List<Charter> charters) {
        throw new UnsupportedOperationException("Not solved yet");
    }
}
