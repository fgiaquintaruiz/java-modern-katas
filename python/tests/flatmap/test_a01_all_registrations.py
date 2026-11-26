from katas.domain import AircraftCategory, EngineType
from katas.flatmap.a01_all_registrations import all_registrations
from tests.testdata import aircraft, airport

REGIONAL = AircraftCategory.REGIONAL
PISTON = EngineType.PISTON


def test_all_registrations_with_multiple_airports_returns_registrations_in_airport_then_fleet_order():
    madrid = airport("MAD", "Madrid", "Spain",
                     aircraft("EC-AAA", REGIONAL, PISTON, 11, 10_000, "40.00"),
                     aircraft("EC-BBB", REGIONAL, PISTON, 11, 20_000, "42.00"))
    barcelona = airport("BCN", "Barcelona", "Spain")
    valencia = airport("VAL", "Valencia", "Spain",
                       # same registration as Madrid's first aircraft: duplicates must be preserved
                       aircraft("EC-AAA", REGIONAL, PISTON, 11, 5_000, "38.00"),
                       aircraft("EC-CCC", REGIONAL, PISTON, 11, 1_000, "45.00"))

    result = all_registrations([madrid, barcelona, valencia])

    # Madrid's fleet registrations, then Barcelona (empty, contributes nothing), then Valencia's fleet registrations.
    assert result == ["EC-AAA", "EC-BBB", "EC-AAA", "EC-CCC"]


def test_all_registrations_with_empty_airport_list_returns_empty_list():
    result = all_registrations([])

    assert result == []
