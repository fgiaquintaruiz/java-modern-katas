from katas.domain import AircraftCategory, EngineType
from katas.flatmap.a07_large_aircraft_with_map_multi import large_aircraft
from tests.testdata import aircraft, airport

REGIONAL = AircraftCategory.REGIONAL
WIDE_BODY = AircraftCategory.WIDE_BODY
CARGO = AircraftCategory.CARGO
PISTON = EngineType.PISTON
TURBOPROP = EngineType.TURBOPROP
TURBOFAN = EngineType.TURBOFAN


def test_large_aircraft_with_mixed_seat_counts_returns_only_aircraft_meeting_min_seats_in_encounter_order():
    regional = aircraft("EC-AAA", REGIONAL, PISTON, 4, 10_000, "30.00")
    seven_seats = aircraft("EC-BBB", WIDE_BODY, TURBOFAN, 7, 20_000, "55.00")
    five_seats = aircraft("EC-CCC", REGIONAL, PISTON, 5, 5_000, "35.00")
    nine_seats = aircraft("EC-DDD", CARGO, TURBOPROP, 9, 1_000, "70.00")
    madrid = airport("MAD", "Madrid", "Spain", regional, seven_seats)
    barcelona = airport("BCN", "Barcelona", "Spain", five_seats, nine_seats)

    result = large_aircraft([madrid, barcelona], 5)

    # seats >= 5: EC-AAA (4) excluded; EC-BBB (7), EC-CCC (5), EC-DDD (9) included, in encounter order.
    assert result == [seven_seats, five_seats, nine_seats]


def test_large_aircraft_when_min_seats_exceeds_every_aircraft_returns_empty_list():
    regional = aircraft("EC-AAA", REGIONAL, PISTON, 4, 10_000, "30.00")
    madrid = airport("MAD", "Madrid", "Spain", regional)

    result = large_aircraft([madrid], 100)

    assert result == []
