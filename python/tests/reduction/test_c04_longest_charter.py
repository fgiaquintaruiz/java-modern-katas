from katas.domain import AircraftCategory, EngineType
from katas.reduction.c04_longest_charter import longest_charter
from tests.testdata import aircraft, airport, charter, customer

REGIONAL = AircraftCategory.REGIONAL
PISTON = EngineType.PISTON

CUSTOMER = customer("C1", "Alice", "ES", True)
AIRCRAFT = aircraft("EC-AAA", REGIONAL, PISTON, 11, 10_000, "35.00")
AIRPORT = airport("MAD", "Madrid", "ES")


def test_longest_charter_with_distinct_durations_returns_the_longest_one() -> None:
    # 2026-01-01 to 2026-01-03 -> 2 days
    shortest = charter(
        "R1", CUSTOMER, AIRCRAFT, AIRPORT, AIRPORT, "2026-01-01", "2026-01-03", "60.00"
    )
    # 2026-02-01 to 2026-02-05 -> 4 days (the longest)
    longest = charter(
        "R2", CUSTOMER, AIRCRAFT, AIRPORT, AIRPORT, "2026-02-01", "2026-02-05", "140.00"
    )
    # 2026-03-01 to 2026-03-04 -> 3 days
    middle = charter(
        "R3", CUSTOMER, AIRCRAFT, AIRPORT, AIRPORT, "2026-03-01", "2026-03-04", "90.00"
    )

    result = longest_charter([shortest, longest, middle])

    assert result == longest


def test_longest_charter_with_tied_durations_returns_the_first_encountered() -> None:
    # 2026-01-01 to 2026-01-06 -> 5 days
    first = charter(
        "R1", CUSTOMER, AIRCRAFT, AIRPORT, AIRPORT, "2026-01-01", "2026-01-06", "150.00"
    )
    # 2026-02-01 to 2026-02-06 -> 5 days, same duration as "first"
    second = charter(
        "R2", CUSTOMER, AIRCRAFT, AIRPORT, AIRPORT, "2026-02-01", "2026-02-06", "150.00"
    )

    result = longest_charter([first, second])

    assert result == first


def test_longest_charter_with_empty_list_returns_empty_optional() -> None:
    result = longest_charter([])

    assert result is None
