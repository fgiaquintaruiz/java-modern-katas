from katas.domain import AircraftCategory, EngineType
from katas.flatmap.a02_distinct_features_sorted import distinct_features_sorted
from tests.testdata import aircraft, airport

REGIONAL = AircraftCategory.REGIONAL
PISTON = EngineType.PISTON


def test_distinct_features_sorted_with_overlapping_features_returns_distinct_names_alphabetically() -> None:  # fmt: skip
    madrid = airport(
        "MAD",
        "Madrid",
        "Spain",
        aircraft("EC-AAA", REGIONAL, PISTON, 11, 10_000, "40.00", "WIFI", "CATERING"),
        # "CATERING" repeated across aircraft, must appear only once in the result
        aircraft("EC-BBB", REGIONAL, PISTON, 11, 20_000, "42.00", "CATERING", "FLATBED"),
    )
    barcelona = airport(
        "BCN",
        "Barcelona",
        "Spain",
        aircraft("EC-CCC", REGIONAL, PISTON, 11, 5_000, "38.00", "WIFI", "PET_CABIN"),
    )

    result = distinct_features_sorted([madrid, barcelona])

    # Distinct features across all aircraft: WIFI, CATERING, FLATBED, PET_CABIN -> sorted
    # alphabetically.
    assert result == ["CATERING", "FLATBED", "PET_CABIN", "WIFI"]


def test_distinct_features_sorted_when_no_aircraft_has_features_returns_empty_list() -> None:
    madrid = airport(
        "MAD", "Madrid", "Spain", aircraft("EC-AAA", REGIONAL, PISTON, 11, 10_000, "40.00")
    )

    result = distinct_features_sorted([madrid])

    assert result == []
