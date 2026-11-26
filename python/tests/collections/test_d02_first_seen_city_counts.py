from katas.collections.d02_first_seen_city_counts import aircraft_count_by_city
from katas.domain import AircraftCategory, EngineType
from tests.testdata import aircraft, airport

a1 = aircraft("EC-001", AircraftCategory.REGIONAL, EngineType.PISTON, 11, 1000, "30.00")
a2 = aircraft("EC-002", AircraftCategory.NARROW_BODY, EngineType.TURBOFAN, 180, 1200, "35.00")
a3 = aircraft("EC-003", AircraftCategory.WIDE_BODY, EngineType.TURBOFAN, 300, 2000, "60.00")


def test_aircraft_count_by_city_with_unsorted_cities_keeps_first_seen_order():
    airports = [
        airport("SEV", "Sevilla", "Spain", a1),
        airport("MAD", "Madrid", "Spain", a2, a3),
        airport("BCN", "Barcelona", "Spain", a1),
    ]

    result = aircraft_count_by_city(airports)

    # First-seen order, not alphabetical and not hash order.
    assert list(result.items()) == [("Sevilla", 1), ("Madrid", 2), ("Barcelona", 1)]


def test_aircraft_count_by_city_with_repeated_city_merges_counts_at_first_position():
    airports = [
        airport("MAD", "Madrid", "Spain", a1),
        airport("SEV", "Sevilla", "Spain", a2),
        airport("TOJ", "Madrid", "Spain", a2, a3),
    ]

    result = aircraft_count_by_city(airports)

    assert list(result.items()) == [("Madrid", 3), ("Sevilla", 1)]


def test_aircraft_count_by_city_with_empty_fleet_keeps_city_with_zero():
    airports = [airport("VLC", "Valencia", "Spain")]

    assert list(aircraft_count_by_city(airports).items()) == [("Valencia", 0)]


def test_aircraft_count_by_city_with_empty_list_returns_empty_dict():
    assert aircraft_count_by_city([]) == {}
