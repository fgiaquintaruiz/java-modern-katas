package com.fgiaquinta.katas.domain;

import java.util.List;

/**
 * A charter airline base (airport + its local fleet).
 *
 * @param code    short airport code, e.g. {@code "MAD"}
 * @param city    city where the airport operates
 * @param country ISO-ish country name or code used across the katas
 * @param fleet   aircraft currently assigned to this airport
 */
public record Airport(
        String code,
        String city,
        String country,
        List<Aircraft> fleet
) {

    public Airport {
        fleet = List.copyOf(fleet);
    }
}
