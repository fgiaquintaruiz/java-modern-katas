package com.fgiaquinta.katas.domain;

/**
 * Commercial tier of a rental vehicle, from the cheapest to the most
 * expensive segment. The declaration order is ascending by tier, so the
 * natural {@code compareTo} / {@code Comparator.naturalOrder()} ordering
 * of this enum reflects the business ranking of categories, not just
 * declaration order coincidence.
 */
public enum VehicleCategory {
    ECONOMY,
    COMPACT,
    SUV,
    VAN,
    LUXURY
}
