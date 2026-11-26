from katas.domain import AircraftCategory, EngineType
from katas.flatmap.a06_upgrade_candidates import Upgrade, upgrade_candidates
from tests.testdata import aircraft, airport, charter, customer

REGIONAL = AircraftCategory.REGIONAL
NARROW_BODY = AircraftCategory.NARROW_BODY
WIDE_BODY = AircraftCategory.WIDE_BODY
CARGO = AircraftCategory.CARGO
BUSINESS_JET = AircraftCategory.BUSINESS_JET
PISTON = EngineType.PISTON
TURBOPROP = EngineType.TURBOPROP
TURBOFAN = EngineType.TURBOFAN


def test_upgrade_candidates_with_higher_tier_aircraft_in_origin_fleet_returns_them_in_fleet_order_per_charter():
    regional = aircraft("EC-REG", REGIONAL, PISTON, 11, 1_000, "30.00")
    narrow_body = aircraft("EC-NAR", NARROW_BODY, TURBOFAN, 180, 1_000, "35.00")
    wide_body = aircraft("EC-WID", WIDE_BODY, TURBOFAN, 300, 1_000, "50.00")
    cargo = aircraft("EC-CAR", CARGO, TURBOPROP, 0, 1_000, "60.00")
    business_jet = aircraft("EC-BUS", BUSINESS_JET, PISTON, 6, 1_000, "90.00")
    origin = airport("MAD", "Madrid", "Spain", regional, narrow_body, wide_body, cargo, business_jet)
    alice = customer("C1", "Alice", "ES", True)

    # chartered REGIONAL: every higher tier in the fleet (NARROW_BODY, WIDE_BODY, CARGO, BUSINESS_JET) qualifies.
    r1 = charter("R1", alice, regional, origin, origin, "2026-01-01", "2026-01-03", "60.00")
    # chartered WIDE_BODY: only strictly higher tiers in the fleet (CARGO, BUSINESS_JET) qualify.
    r2 = charter("R2", alice, wide_body, origin, origin, "2026-02-01", "2026-02-02", "50.00")

    result = upgrade_candidates([r1, r2])

    assert result == [
        Upgrade("R1", "EC-NAR"),
        Upgrade("R1", "EC-WID"),
        Upgrade("R1", "EC-CAR"),
        Upgrade("R1", "EC-BUS"),
        Upgrade("R2", "EC-CAR"),
        Upgrade("R2", "EC-BUS"),
    ]


def test_upgrade_candidates_when_chartered_aircraft_is_already_top_tier_returns_no_candidates():
    regional = aircraft("EC-REG", REGIONAL, PISTON, 11, 1_000, "30.00")
    business_jet = aircraft("EC-BUS", BUSINESS_JET, PISTON, 6, 1_000, "90.00")
    origin = airport("MAD", "Madrid", "Spain", regional, business_jet)
    alice = customer("C1", "Alice", "ES", True)
    # chartered BUSINESS_JET: no aircraft in the fleet outranks it, so no upgrade candidate exists.
    r1 = charter("R1", alice, business_jet, origin, origin, "2026-01-01", "2026-01-03", "270.00")

    result = upgrade_candidates([r1])

    assert result == []
