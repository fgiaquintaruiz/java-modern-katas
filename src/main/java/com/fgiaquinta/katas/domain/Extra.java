package com.fgiaquinta.katas.domain;

import java.math.BigDecimal;

/**
 * An optional paid add-on attached to a rental, e.g. a child seat or
 * additional insurance.
 *
 * @param name  human-readable name of the extra
 * @param price flat price charged for the whole rental
 */
public record Extra(
        String name,
        BigDecimal price
) {
}
