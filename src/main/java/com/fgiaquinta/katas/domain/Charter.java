package com.fgiaquinta.katas.domain;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

/**
 * A completed or booked charter agreement.
 *
 * @param id          unique charter identifier
 * @param customer    the chartering customer
 * @param aircraft    the chartered aircraft
 * @param origin      airport where the aircraft departed
 * @param destination airport where the aircraft arrived (or will arrive)
 * @param start       first day of the charter, inclusive
 * @param end         last day of the charter, exclusive (see {@link #days()})
 * @param totalPrice  final price actually charged, including extras
 * @param extras      paid add-ons attached to this charter
 */
public record Charter(
        String id,
        Customer customer,
        Aircraft aircraft,
        Airport origin,
        Airport destination,
        LocalDate start,
        LocalDate end,
        BigDecimal totalPrice,
        List<Extra> extras
) {

    public Charter {
        if (!end.isAfter(start)) {
            throw new IllegalArgumentException("end (" + end + ") must be after start (" + start + ")");
        }
        extras = List.copyOf(extras);
    }

    /**
     * Number of charter days, computed with {@code end} treated as
     * exclusive: a charter from day 1 to day 4 lasts 3 days.
     */
    public long days() {
        return ChronoUnit.DAYS.between(start, end);
    }
}
