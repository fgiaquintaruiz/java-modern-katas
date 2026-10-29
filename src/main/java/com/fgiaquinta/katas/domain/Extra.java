package com.fgiaquinta.katas.domain;

import java.math.BigDecimal;

/**
 * An optional paid add-on attached to a charter, e.g. catering or
 * additional insurance.
 *
 * @param name  human-readable name of the extra
 * @param price flat price charged for the whole charter
 */
public record Extra(
        String name,
        BigDecimal price
) {
}
