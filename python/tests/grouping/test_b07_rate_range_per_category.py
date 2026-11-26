from katas.domain import AircraftCategory, EngineType
from katas.grouping.b07_rate_range_per_category import rate_range_per_category
from tests.testdata import aircraft, money

REGIONAL = AircraftCategory.REGIONAL
WIDE_BODY = AircraftCategory.WIDE_BODY
PISTON = EngineType.PISTON
TURBOFAN = EngineType.TURBOFAN


def test_rate_range_per_category_with_multiple_aircraft_per_category_computes_min_and_max():
    fleet = [
        aircraft("EC-R01", REGIONAL, PISTON, 11, 1000, "30.00"),
        aircraft("EC-R02", REGIONAL, PISTON, 11, 1100, "45.00"),
        aircraft("EC-R03", REGIONAL, PISTON, 11, 1200, "20.00"),
        aircraft("EC-W01", WIDE_BODY, TURBOFAN, 300, 2000, "90.00"),
    ]

    result = rate_range_per_category(fleet)

    # REGIONAL rates: 30.00, 45.00, 20.00 -> min 20.00, max 45.00
    assert result[REGIONAL].min == money("20.00")
    assert result[REGIONAL].max == money("45.00")
    # WIDE_BODY: single rate 90.00 -> min == max == 90.00
    assert result[WIDE_BODY].min == money("90.00")
    assert result[WIDE_BODY].max == money("90.00")
    assert len(result) == 2


def test_rate_range_per_category_with_empty_list_returns_empty_map():
    result = rate_range_per_category([])

    assert result == {}
