from katas.domain import AircraftCategory, EngineType
from katas.reduction.c06_top3_aircraft_by_revenue import top3_registrations_by_revenue
from tests.testdata import aircraft, airport, charter, customer

REGIONAL = AircraftCategory.REGIONAL
NARROW_BODY = AircraftCategory.NARROW_BODY
WIDE_BODY = AircraftCategory.WIDE_BODY
CARGO = AircraftCategory.CARGO
PISTON = EngineType.PISTON
TURBOFAN = EngineType.TURBOFAN

CUSTOMER = customer("C1", "Alice", "ES", True)
AIRPORT = airport("MAD", "Madrid", "ES")


def test_top3_registrations_by_revenue_with_more_than_three_aircraft_returns_top_three_descending():
    v1 = aircraft("EC-AAA", REGIONAL, PISTON, 11, 10_000, "35.00")
    v2 = aircraft("EC-BBB", NARROW_BODY, TURBOFAN, 180, 20_000, "45.00")
    v3 = aircraft("EC-CCC", WIDE_BODY, TURBOFAN, 300, 15_000, "60.00")
    v4 = aircraft("EC-DDD", CARGO, TURBOFAN, 0, 30_000, "55.00")

    charters = [
        # EC-AAA revenue: 100.00 + 50.00 = 150.00
        charter("R1", CUSTOMER, v1, AIRPORT, AIRPORT, "2026-01-01", "2026-01-02", "100.00"),
        charter("R2", CUSTOMER, v1, AIRPORT, AIRPORT, "2026-01-05", "2026-01-06", "50.00"),
        # EC-BBB revenue: 200.00
        charter("R3", CUSTOMER, v2, AIRPORT, AIRPORT, "2026-02-01", "2026-02-02", "200.00"),
        # EC-CCC revenue: 120.00
        charter("R4", CUSTOMER, v3, AIRPORT, AIRPORT, "2026-03-01", "2026-03-02", "120.00"),
        # EC-DDD revenue: 90.00
        charter("R5", CUSTOMER, v4, AIRPORT, AIRPORT, "2026-04-01", "2026-04-02", "90.00"),
    ]

    result = top3_registrations_by_revenue(charters)

    # ranking: EC-BBB (200.00) > EC-AAA (150.00) > EC-CCC (120.00) > EC-DDD (90.00) -> EC-DDD excluded
    assert result == ["EC-BBB", "EC-AAA", "EC-CCC"]


def test_top3_registrations_by_revenue_with_tied_revenue_breaks_tie_by_registration_ascending():
    lowest = aircraft("EC-ZZZ", REGIONAL, PISTON, 11, 10_000, "35.00")
    tied_a = aircraft("EC-AAA", NARROW_BODY, TURBOFAN, 180, 20_000, "45.00")
    tied_b = aircraft("EC-BBB", WIDE_BODY, TURBOFAN, 300, 15_000, "60.00")

    charters = [
        # EC-ZZZ revenue: 100.00
        charter("R1", CUSTOMER, lowest, AIRPORT, AIRPORT, "2026-01-01", "2026-01-02", "100.00"),
        # EC-AAA revenue: 150.00, tied with EC-BBB
        charter("R2", CUSTOMER, tied_a, AIRPORT, AIRPORT, "2026-02-01", "2026-02-02", "150.00"),
        # EC-BBB revenue: 150.00, tied with EC-AAA
        charter("R3", CUSTOMER, tied_b, AIRPORT, AIRPORT, "2026-03-01", "2026-03-02", "150.00"),
    ]

    result = top3_registrations_by_revenue(charters)

    # EC-AAA and EC-BBB tie at 150.00 -> ascending registration order puts EC-AAA before EC-BBB
    assert result == ["EC-AAA", "EC-BBB", "EC-ZZZ"]


def test_top3_registrations_by_revenue_with_fewer_than_three_aircraft_returns_all_of_them():
    v1 = aircraft("EC-AAA", REGIONAL, PISTON, 11, 10_000, "35.00")
    v2 = aircraft("EC-BBB", NARROW_BODY, TURBOFAN, 180, 20_000, "45.00")

    charters = [
        # EC-AAA revenue: 120.00
        charter("R1", CUSTOMER, v1, AIRPORT, AIRPORT, "2026-01-01", "2026-01-02", "120.00"),
        # EC-BBB revenue: 80.00
        charter("R2", CUSTOMER, v2, AIRPORT, AIRPORT, "2026-02-01", "2026-02-02", "80.00"),
    ]

    result = top3_registrations_by_revenue(charters)

    assert result == ["EC-AAA", "EC-BBB"]
