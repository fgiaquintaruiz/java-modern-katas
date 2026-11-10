package com.fgiaquinta.katas.streams.flatmap;

import com.fgiaquinta.katas.domain.Aircraft;
import com.fgiaquinta.katas.domain.Airport;

import java.util.List;

/**
 * Exercise A07 — Large aircraft with {@code mapMulti}.
 *
 * <h2>Statement</h2>
 * Given a list of {@link Airport} and a minimum seat count, return every aircraft across all airports
 * whose {@code seats} is greater than or equal to {@code minSeats}. This exercise MUST be implemented
 * with {@link java.util.stream.Stream#mapMulti}; a {@code flatMap}-based solution is NOT accepted,
 * even though it produces the same result.
 *
 * <h2>Concepts</h2>
 * {@code mapMulti} (Java 16) pushes elements downstream without allocating an inner {@code Stream}; it beats {@code flatMap} for small or empty inner groups; the {@code <Aircraft>mapMulti(...)} type-witness quirk.
 *
 * <h2>Internals</h2>
 * The outer stream hands mapMulti one airport. The second argument of the lambda is a Consumer,
 * created by toList when the pipeline is built, before the first airport moves. When the if calls
 * accept, the aircraft goes to the final list with nothing in between: no inner stream, no buffer.
 * Per airport, nothing is allocated.
 *
 * <h2>Anchor</h2>
 * It behaves like a luggage chain with a flap that sends each bag one way or the other.
 */
public final class A07LargeAircraftWithMapMulti {

    private A07LargeAircraftWithMapMulti() {
    }

    public static List<Aircraft> largeAircraft(List<Airport> airports, int minSeats) {
        return airports.stream().<Aircraft>mapMulti(
                (airport, aircraftConsumer)
                        -> airport.fleet().forEach(aircraft -> {
                    if (aircraft.seats() >= minSeats) {
                        aircraftConsumer.accept(aircraft);
                    }
                })).toList();
    }
}
