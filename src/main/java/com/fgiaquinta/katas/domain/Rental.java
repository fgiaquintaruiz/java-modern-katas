package com.fgiaquinta.katas.domain;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

/**
 * A completed or booked rental agreement.
 *
 * @param id         unique rental identifier
 * @param customer   the renting customer
 * @param vehicle    the rented vehicle
 * @param pickup     branch where the vehicle was picked up
 * @param dropoff    branch where the vehicle was (or will be) returned
 * @param start      first day of the rental, inclusive
 * @param end        last day of the rental, exclusive (see {@link #days()})
 * @param totalPrice final price actually charged, including extras
 * @param extras     paid add-ons attached to this rental
 */
public record Rental(
        String id,
        Customer customer,
        Vehicle vehicle,
        Branch pickup,
        Branch dropoff,
        LocalDate start,
        LocalDate end,
        BigDecimal totalPrice,
        List<Extra> extras
) {

    public Rental {
        if (!end.isAfter(start)) {
            throw new IllegalArgumentException("end (" + end + ") must be after start (" + start + ")");
        }
        extras = List.copyOf(extras);
    }

    /**
     * Number of rental days, computed with {@code end} treated as
     * exclusive: a rental from day 1 to day 4 lasts 3 days.
     */
    public long days() {
        return ChronoUnit.DAYS.between(start, end);
    }
}
