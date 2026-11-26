from katas.domain import AircraftCategory, EngineType
from katas.flatmap.a05_charter_extra_lines import extra_lines
from tests.testdata import aircraft, airport, charter, customer, extra

REGIONAL = AircraftCategory.REGIONAL
PISTON = EngineType.PISTON


def test_extra_lines_with_some_charters_lacking_extras_returns_only_lines_for_charters_with_extras_in_order() -> None:  # fmt: skip
    alice = customer("C1", "Alice", "ES", True)
    plane = aircraft("EC-AAA", REGIONAL, PISTON, 11, 10_000, "40.00")
    madrid = airport("MAD", "Madrid", "Spain", plane)

    r1 = charter(
        "R1",
        alice,
        plane,
        madrid,
        madrid,
        "2026-01-01",
        "2026-01-04",
        "150.00",
        extra("CATERING", "10.00"),
        extra("GROUND_TRANSFER", "5.00"),
    )
    # no extras: must not contribute any line
    r2 = charter("R2", alice, plane, madrid, madrid, "2026-02-01", "2026-02-03", "80.00")
    r3 = charter(
        "R3",
        alice,
        plane,
        madrid,
        madrid,
        "2026-03-01",
        "2026-03-02",
        "40.00",
        extra("INSURANCE", "20.00"),
    )

    result = extra_lines([r1, r2, r3])

    # Charter order first, then extras order within each charter; R2 contributes nothing.
    assert result == ["R1:CATERING", "R1:GROUND_TRANSFER", "R3:INSURANCE"]


def test_extra_lines_when_no_charter_has_extras_returns_empty_list() -> None:
    alice = customer("C1", "Alice", "ES", True)
    plane = aircraft("EC-AAA", REGIONAL, PISTON, 11, 10_000, "40.00")
    madrid = airport("MAD", "Madrid", "Spain", plane)
    r1 = charter("R1", alice, plane, madrid, madrid, "2026-01-01", "2026-01-04", "150.00")

    result = extra_lines([r1])

    assert result == []
