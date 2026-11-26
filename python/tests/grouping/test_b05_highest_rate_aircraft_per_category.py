from katas.domain import AircraftCategory, EngineType
from katas.grouping.b05_highest_rate_aircraft_per_category import highest_rate_per_category
from tests.testdata import aircraft

REGIONAL = AircraftCategory.REGIONAL
WIDE_BODY = AircraftCategory.WIDE_BODY
PISTON = EngineType.PISTON
TURBOFAN = EngineType.TURBOFAN


def test_highest_rate_per_category_with_ties_and_distinct_rates_picks_highest_keeping_first_on_tie() -> None:  # fmt: skip
    regional1 = aircraft("EC-R01", REGIONAL, PISTON, 11, 1000, "30.00")
    regional2 = aircraft("EC-R02", REGIONAL, PISTON, 11, 1200, "45.00")
    wide_body1 = aircraft("EC-W01", WIDE_BODY, TURBOFAN, 300, 2000, "90.00")
    wide_body2 = aircraft("EC-W02", WIDE_BODY, TURBOFAN, 300, 2100, "90.00")  # tie with wide_body1

    fleet = [regional1, regional2, wide_body1, wide_body2]

    result = highest_rate_per_category(fleet)

    # REGIONAL: 45.00 (regional2) > 30.00 (regional1)
    assert result[REGIONAL] == regional2
    # WIDE_BODY: tie at 90.00, wide_body1 appears first in encounter order -> wide_body1 wins
    assert result[WIDE_BODY] == wide_body1
    assert len(result) == 2


def test_highest_rate_per_category_with_empty_list_returns_empty_map() -> None:
    result = highest_rate_per_category([])

    assert result == {}
