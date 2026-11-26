from katas.domain import AircraftCategory, EngineType
from katas.flatmap.a03_cities_offering_category import cities_offering
from tests.testdata import aircraft, airport

REGIONAL = AircraftCategory.REGIONAL
WIDE_BODY = AircraftCategory.WIDE_BODY
PISTON = EngineType.PISTON
TURBOFAN = EngineType.TURBOFAN


def test_cities_offering_with_two_airports_in_same_city_qualifying_returns_city_only_once() -> None:
    madrid1 = airport(
        "MAD1", "Madrid", "Spain", aircraft("EC-AAA", WIDE_BODY, TURBOFAN, 300, 10_000, "60.00")
    )
    madrid2 = airport(
        "MAD2", "Madrid", "Spain", aircraft("EC-BBB", WIDE_BODY, TURBOFAN, 300, 8_000, "58.00")
    )
    barcelona = airport(
        "BCN", "Barcelona", "Spain", aircraft("EC-CCC", REGIONAL, PISTON, 11, 5_000, "30.00")
    )
    valencia = airport(
        "VAL", "Valencia", "Spain", aircraft("EC-DDD", WIDE_BODY, TURBOFAN, 300, 2_000, "55.00")
    )

    result = cities_offering([madrid1, madrid2, barcelona, valencia], WIDE_BODY)

    # Madrid qualifies via two different airports but must appear once; Barcelona has no WIDE_BODY.
    assert result == {"Madrid", "Valencia"}


def test_cities_offering_when_no_airport_has_category_returns_empty_set() -> None:
    barcelona = airport(
        "BCN", "Barcelona", "Spain", aircraft("EC-CCC", REGIONAL, PISTON, 11, 5_000, "30.00")
    )

    result = cities_offering([barcelona], WIDE_BODY)

    assert result == set()
