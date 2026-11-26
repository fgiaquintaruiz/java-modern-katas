from katas.domain import AircraftCategory, EngineType
from katas.reduction.c01_total_flight_hours import total_flight_hours
from tests.testdata import aircraft

REGIONAL = AircraftCategory.REGIONAL
NARROW_BODY = AircraftCategory.NARROW_BODY
WIDE_BODY = AircraftCategory.WIDE_BODY
CARGO = AircraftCategory.CARGO
PISTON = EngineType.PISTON
TURBOFAN = EngineType.TURBOFAN


def test_total_flight_hours_with_multiple_aircraft_sums_all_flight_hours():
    fleet = [
        aircraft("EC-AAA", REGIONAL, PISTON, 11, 10_000, "35.00"),
        aircraft("EC-BBB", NARROW_BODY, TURBOFAN, 180, 25_000, "40.00"),
        aircraft("EC-CCC", WIDE_BODY, TURBOFAN, 300, 5_000, "60.00"),
        aircraft("EC-DDD", CARGO, TURBOFAN, 0, 60_000, "55.00"),
    ]

    result = total_flight_hours(fleet)

    # 10_000 + 25_000 + 5_000 + 60_000 = 100_000
    assert result == 100_000


def test_total_flight_hours_with_empty_list_returns_zero():
    result = total_flight_hours([])

    assert result == 0
