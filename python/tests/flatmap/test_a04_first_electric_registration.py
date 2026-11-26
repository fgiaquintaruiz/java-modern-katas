from katas.domain import AircraftCategory, EngineType
from katas.flatmap.a04_first_electric_registration import first_electric_registration
from tests.testdata import aircraft, airport

REGIONAL = AircraftCategory.REGIONAL
PISTON = EngineType.PISTON
TURBOPROP = EngineType.TURBOPROP
ELECTRIC = EngineType.ELECTRIC


def test_first_electric_registration_with_electric_aircraft_in_second_airport_returns_first_match_in_encounter_order() -> None:  # fmt: skip
    madrid = airport(
        "MAD",
        "Madrid",
        "Spain",
        aircraft("EC-AAA", REGIONAL, PISTON, 11, 10_000, "40.00"),
        aircraft("EC-BBB", REGIONAL, TURBOPROP, 70, 20_000, "42.00"),
    )
    barcelona = airport(
        "BCN",
        "Barcelona",
        "Spain",
        # first electric aircraft encountered when scanning airports then fleet in order
        aircraft("EC-E01", REGIONAL, ELECTRIC, 9, 1_000, "50.00"),
        aircraft("EC-E02", REGIONAL, ELECTRIC, 9, 500, "52.00"),
    )

    result = first_electric_registration([madrid, barcelona])

    assert result == "EC-E01"


def test_first_electric_registration_with_no_electric_aircraft_returns_none() -> None:
    madrid = airport(
        "MAD",
        "Madrid",
        "Spain",
        aircraft("EC-AAA", REGIONAL, PISTON, 11, 10_000, "40.00"),
        aircraft("EC-BBB", REGIONAL, TURBOPROP, 70, 20_000, "42.00"),
    )

    result = first_electric_registration([madrid])

    assert result is None
