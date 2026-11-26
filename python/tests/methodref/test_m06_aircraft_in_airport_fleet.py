from katas.domain import AircraftCategory, EngineType
from katas.methodref.m06_aircraft_in_airport_fleet import in_fleet
from tests.testdata import aircraft, airport

REGIONAL = AircraftCategory.REGIONAL
WIDE_BODY = AircraftCategory.WIDE_BODY
PISTON = EngineType.PISTON
TURBOFAN = EngineType.TURBOFAN


def test_in_fleet_with_mixed_candidates_keeps_only_fleet_aircraft_in_input_order() -> None:
    # Only wide_body and regional are in the Madrid fleet; outsider is not.
    wide_body = aircraft("EC-AAA", WIDE_BODY, TURBOFAN, 300, 10_000, "60.00")
    regional = aircraft("EC-BBB", REGIONAL, PISTON, 11, 5_000, "30.00")
    outsider = aircraft("EC-CCC", REGIONAL, PISTON, 11, 7_000, "45.50")
    madrid = airport("MAD", "Madrid", "Spain", wide_body, regional)

    assert in_fleet([regional, outsider, wide_body], madrid) == [regional, wide_body]


def test_in_fleet_with_empty_fleet_returns_empty_list() -> None:
    wide_body = aircraft("EC-AAA", WIDE_BODY, TURBOFAN, 300, 10_000, "60.00")
    empty = airport("MAD", "Madrid", "Spain")

    assert in_fleet([wide_body], empty) == []
