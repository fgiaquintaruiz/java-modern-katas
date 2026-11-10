package com.fgiaquinta.katas.streams.flatmap;

import com.fgiaquinta.katas.domain.AircraftCategory;
import com.fgiaquinta.katas.domain.Charter;

import java.util.ArrayList;
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
 * The outer stream hands flatMap one charter. The lambda builds an inner stream with the charter id,
 * filtering the aircraft that have a better category than the current one, but nothing runs until
 * the pipeline reaches toList. flatMap passes each one down to toList, and the try-with-resources
 * closes the inner stream. toList receives the upgrade objects one by one and cannot tell which
 * charter an upgrade came from, because at that point there is no charter lambda any more, only the
 * charter id inside the upgrade.
 * <h2>Anchor</h2>
 * It behaves like a bus full of people that stops at a hotel: each person has a basic room, and the
 * desk shows them all the other categories of rooms they can upgrade it to.
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
        return charters.stream()
                .flatMap(charter -> charter.origin()
                        .fleet().stream()
                        .filter(aircraft -> aircraft.category().compareTo(charter.aircraft().category()) > 0)
                        .map(aircraft -> new Upgrade(charter.id(), aircraft.registration()))
                ).toList();
    }
}
