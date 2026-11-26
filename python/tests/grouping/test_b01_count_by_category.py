from katas.domain import AircraftCategory, EngineType
from katas.grouping.b01_count_by_category import count_by_category
from tests.testdata import aircraft

REGIONAL = AircraftCategory.REGIONAL
NARROW_BODY = AircraftCategory.NARROW_BODY
WIDE_BODY = AircraftCategory.WIDE_BODY
PISTON = EngineType.PISTON
TURBOFAN = EngineType.TURBOFAN


def test_count_by_category_with_mixed_categories_counts_each_present_category() -> None:
    fleet = [
        aircraft("EC-P01", REGIONAL, PISTON, 11, 1000, "30.00"),
        aircraft("EC-P02", REGIONAL, PISTON, 11, 2000, "32.00"),
        aircraft("EC-P03", NARROW_BODY, TURBOFAN, 180, 1500, "40.00"),
        aircraft("EC-P04", WIDE_BODY, TURBOFAN, 300, 3000, "60.00"),
        aircraft("EC-P05", WIDE_BODY, TURBOFAN, 300, 3200, "62.00"),
    ]

    result = count_by_category(fleet)

    # REGIONAL: EC-P01, EC-P02 -> 2
    # NARROW_BODY: EC-P03 -> 1
    # WIDE_BODY: EC-P04, EC-P05 -> 2
    # CARGO and BUSINESS_JET have no aircraft, so they must be absent.
    assert result == {REGIONAL: 2, NARROW_BODY: 1, WIDE_BODY: 2}


def test_count_by_category_with_empty_list_returns_empty_map() -> None:
    result = count_by_category([])

    assert result == {}
