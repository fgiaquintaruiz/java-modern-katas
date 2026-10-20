package com.fgiaquinta.katas.domain;

import java.util.List;

/**
 * A rental branch (office + its local fleet).
 *
 * @param code    short branch code, e.g. {@code "MAD"}
 * @param city    city where the branch operates
 * @param country ISO-ish country name or code used across the katas
 * @param fleet   vehicles currently assigned to this branch
 */
public record Branch(
        String code,
        String city,
        String country,
        List<Vehicle> fleet
) {

    public Branch {
        fleet = List.copyOf(fleet);
    }
}
