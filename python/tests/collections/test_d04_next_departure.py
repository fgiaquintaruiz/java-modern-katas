from datetime import time

from katas.collections.d04_next_departure import neighbours
from katas.domain import AircraftCategory, Charter, EngineType
from tests.testdata import aircraft, airport, charter, customer

ana = customer("C1", "Ana", "Spain", True)
plane = aircraft("EC-001", AircraftCategory.REGIONAL, EngineType.PISTON, 11, 1000, "30.00")
madrid = airport("MAD", "Madrid", "Spain", plane)

r1 = charter("R1", ana, plane, madrid, madrid, "2024-01-01", "2024-01-02", "30.00")
r2 = charter("R2", ana, plane, madrid, madrid, "2024-01-01", "2024-01-02", "30.00")
r3 = charter("R3", ana, plane, madrid, madrid, "2024-01-01", "2024-01-02", "30.00")

NOON = time(12, 0)


def schedule() -> dict[time, Charter]:
    # Deliberately not in time order: the insertion order of a dict is not the departure order.
    return {time(18, 45): r3, time(8, 0): r1, time(12, 30): r2}


def test_neighbours_with_time_between_departures_returns_previous_and_next() -> None:
    result = neighbours(schedule(), time(10, 0))

    assert result.previous == r1
    assert result.next == r2


def test_neighbours_with_time_equal_to_departure_returns_that_departure_as_next() -> None:
    # Next is at-or-after, previous is strictly before.
    result = neighbours(schedule(), time(12, 30))

    assert result.next == r2
    assert result.previous == r1


def test_neighbours_with_time_before_first_departure_returns_empty_previous() -> None:
    result = neighbours(schedule(), time(7, 0))

    assert result.previous is None
    assert result.next == r1


def test_neighbours_with_time_after_last_departure_returns_empty_next() -> None:
    result = neighbours(schedule(), time(19, 0))

    assert result.next is None
    assert result.previous == r3


def test_neighbours_with_empty_schedule_returns_both_empty() -> None:
    result = neighbours({}, NOON)

    assert result.previous is None
    assert result.next is None
