from katas.collections.d07_category_counts_outside_range import count_outside_range
from katas.domain import AircraftCategory, EngineType
from tests.testdata import aircraft

REGIONAL = AircraftCategory.REGIONAL
NARROW_BODY = AircraftCategory.NARROW_BODY
WIDE_BODY = AircraftCategory.WIDE_BODY
CARGO = AircraftCategory.CARGO
BUSINESS_JET = AircraftCategory.BUSINESS_JET

fleet = [
    aircraft("EC-001", REGIONAL, EngineType.PISTON, 11, 1000, "30.00"),
    aircraft("EC-002", REGIONAL, EngineType.TURBOPROP, 70, 1000, "40.00"),
    aircraft("EC-003", NARROW_BODY, EngineType.TURBOFAN, 180, 1200, "35.00"),
    aircraft("EC-004", BUSINESS_JET, EngineType.TURBOFAN, 12, 300, "120.00"),
]


def test_count_outside_range_with_middle_range_returns_complement_in_enum_order() -> None:
    result = count_outside_range(fleet, NARROW_BODY, CARGO)

    assert list(result.items()) == [(REGIONAL, 2), (BUSINESS_JET, 1)]


def test_count_outside_range_with_single_category_range_includes_zeros() -> None:
    result = count_outside_range(fleet, WIDE_BODY, WIDE_BODY)

    assert list(result.items()) == [(REGIONAL, 2), (NARROW_BODY, 1), (CARGO, 0), (BUSINESS_JET, 1)]


def test_count_outside_range_with_full_range_returns_empty() -> None:
    assert count_outside_range(fleet, REGIONAL, BUSINESS_JET) == {}


def test_count_outside_range_with_empty_fleet_returns_all_zeros() -> None:
    result = count_outside_range([], NARROW_BODY, CARGO)

    assert list(result.items()) == [(REGIONAL, 0), (BUSINESS_JET, 0)]
