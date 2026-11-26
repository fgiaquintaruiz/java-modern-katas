from katas.domain import AircraftCategory, EngineType
from katas.reduction.c02_charter_duration_stats import LONG_MAX, LONG_MIN, duration_stats
from tests.testdata import aircraft, airport, charter, customer

REGIONAL = AircraftCategory.REGIONAL
PISTON = EngineType.PISTON


def test_duration_stats_with_multiple_charters_computes_all_statistics():
    alice = customer("C1", "Alice", "ES", True)
    plane = aircraft("EC-AAA", REGIONAL, PISTON, 11, 10_000, "35.00")
    madrid = airport("MAD", "Madrid", "ES")

    charters = [
        # 2026-01-01 to 2026-01-04, end exclusive -> 3 days
        charter("R1", alice, plane, madrid, madrid, "2026-01-01", "2026-01-04", "100.00"),
        # 2026-02-01 to 2026-02-06, end exclusive -> 5 days
        charter("R2", alice, plane, madrid, madrid, "2026-02-01", "2026-02-06", "150.00"),
        # 2026-03-01 to 2026-03-03, end exclusive -> 2 days
        charter("R3", alice, plane, madrid, madrid, "2026-03-01", "2026-03-03", "80.00"),
        # 2026-04-01 to 2026-04-06, end exclusive -> 5 days
        charter("R4", alice, plane, madrid, madrid, "2026-04-01", "2026-04-06", "150.00"),
    ]

    stats = duration_stats(charters)

    # days: 3, 5, 2, 5 -> count 4, sum 15, min 2, max 5, average 15/4 = 3.75
    assert stats.count == 4
    assert stats.sum == 15
    assert stats.min == 2
    assert stats.max == 5
    assert stats.average == 3.75


def test_duration_stats_with_empty_list_returns_jdk_sentinel_values():
    stats = duration_stats([])

    assert stats.count == 0
    assert stats.sum == 0
    assert stats.min == LONG_MAX
    assert stats.max == LONG_MIN
    assert stats.average == 0.0
