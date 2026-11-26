from katas.domain import AircraftCategory, EngineType
from katas.grouping.b02_registrations_by_city import registrations_by_city
from tests.testdata import aircraft, airport

REGIONAL = AircraftCategory.REGIONAL
NARROW_BODY = AircraftCategory.NARROW_BODY
WIDE_BODY = AircraftCategory.WIDE_BODY
CARGO = AircraftCategory.CARGO
PISTON = EngineType.PISTON
TURBOFAN = EngineType.TURBOFAN
TURBOPROP = EngineType.TURBOPROP


def test_registrations_by_city_with_two_airports_in_same_city_merges_registrations_in_encounter_order() -> None:  # fmt: skip
    madrid1 = airport(
        "MAD1",
        "Madrid",
        "Spain",
        aircraft("EC-P01", REGIONAL, PISTON, 11, 1000, "30.00"),
        aircraft("EC-P02", NARROW_BODY, TURBOFAN, 180, 1200, "35.00"),
    )
    barcelona = airport(
        "BCN", "Barcelona", "Spain", aircraft("EC-P03", WIDE_BODY, TURBOFAN, 300, 2000, "55.00")
    )
    madrid2 = airport(
        "MAD2", "Madrid", "Spain", aircraft("EC-P04", CARGO, TURBOPROP, 0, 500, "70.00")
    )

    result = registrations_by_city([madrid1, barcelona, madrid2])

    # Madrid: EC-P01, EC-P02 (from madrid1), then EC-P04 (from madrid2) -> encounter order
    # preserved.
    assert result["Madrid"] == ["EC-P01", "EC-P02", "EC-P04"]
    # Barcelona: EC-P03 only.
    assert result["Barcelona"] == ["EC-P03"]
    assert len(result) == 2


def test_registrations_by_city_with_airport_without_aircraft_returns_no_entry_for_that_city() -> None:  # fmt: skip
    empty_airport = airport("VAL", "Valencia", "Spain")
    madrid = airport(
        "MAD", "Madrid", "Spain", aircraft("EC-P01", REGIONAL, PISTON, 11, 1000, "30.00")
    )

    result = registrations_by_city([empty_airport, madrid])

    assert set(result.keys()) == {"Madrid"}
    assert result["Madrid"] == ["EC-P01"]
