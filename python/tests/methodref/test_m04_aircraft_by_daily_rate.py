from katas.domain import AircraftCategory, EngineType
from katas.methodref.m04_aircraft_by_daily_rate import sorted_by_daily_rate
from tests.testdata import aircraft

REGIONAL = AircraftCategory.REGIONAL
WIDE_BODY = AircraftCategory.WIDE_BODY
PISTON = EngineType.PISTON
TURBOFAN = EngineType.TURBOFAN


def test_sorted_by_daily_rate_with_unsorted_aircraft_returns_ascending_by_rate() -> None:
    # 30.00 < 45.50 < 60.00.
    wide_body = aircraft("EC-AAA", WIDE_BODY, TURBOFAN, 300, 10_000, "60.00")
    regional = aircraft("EC-BBB", REGIONAL, PISTON, 11, 5_000, "30.00")
    mid = aircraft("EC-CCC", REGIONAL, PISTON, 11, 7_000, "45.50")

    assert sorted_by_daily_rate([wide_body, regional, mid]) == [regional, mid, wide_body]


def test_sorted_by_daily_rate_does_not_modify_input() -> None:
    wide_body = aircraft("EC-AAA", WIDE_BODY, TURBOFAN, 300, 10_000, "60.00")
    regional = aircraft("EC-BBB", REGIONAL, PISTON, 11, 5_000, "30.00")
    source = [wide_body, regional]

    sorted_by_daily_rate(source)

    assert source == [wide_body, regional]


def test_sorted_by_daily_rate_with_empty_list_returns_empty_list() -> None:
    assert sorted_by_daily_rate([]) == []
