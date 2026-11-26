from katas.domain import AircraftCategory, EngineType
from katas.grouping.b06_loyalty_models_by_city_sorted import loyalty_models_by_city
from tests.testdata import aircraft, airport, charter, customer

REGIONAL = AircraftCategory.REGIONAL
NARROW_BODY = AircraftCategory.NARROW_BODY
WIDE_BODY = AircraftCategory.WIDE_BODY
CARGO = AircraftCategory.CARGO
BUSINESS_JET = AircraftCategory.BUSINESS_JET
PISTON = EngineType.PISTON
TURBOFAN = EngineType.TURBOFAN
TURBOPROP = EngineType.TURBOPROP
ELECTRIC = EngineType.ELECTRIC


def test_loyalty_models_by_city_with_mixed_membership_excludes_cities_with_only_non_member_charters() -> None:  # fmt: skip
    loyal = customer("C1", "Ana", "Spain", True)
    non_loyal = customer("C2", "Bea", "Spain", False)

    madrid = airport("MAD", "Madrid", "Spain")
    barcelona = airport("BCN", "Barcelona", "Spain")
    sevilla = airport("SEV", "Sevilla", "Spain")

    # The helper derives the model from (category, engine): Tecnam P2012, A320neo, A350-900,
    # ATR 72F, Beta ALIA.
    tecnam = aircraft("EC-P12", REGIONAL, PISTON, 11, 1000, "30.00")
    a320neo = aircraft("EC-A32", NARROW_BODY, TURBOFAN, 180, 1200, "35.00")
    a350 = aircraft("EC-A35", WIDE_BODY, TURBOFAN, 300, 2000, "60.00")
    atr72f = aircraft("EC-ATF", CARGO, TURBOPROP, 0, 500, "70.00")
    alia = aircraft("EC-ALI", BUSINESS_JET, ELECTRIC, 6, 100, "120.00")

    charters = [
        charter("R1", loyal, tecnam, madrid, madrid, "2024-01-01", "2024-01-02", "30.00"),
        charter("R2", loyal, a320neo, madrid, madrid, "2024-01-03", "2024-01-04", "35.00"),
        charter("R3", non_loyal, a350, madrid, madrid, "2024-01-05", "2024-01-06", "60.00"),
        charter("R4", non_loyal, atr72f, barcelona, barcelona, "2024-01-07", "2024-01-08", "70.00"),
        charter("R5", loyal, alia, sevilla, sevilla, "2024-01-09", "2024-01-10", "120.00"),
    ]

    result = loyalty_models_by_city(charters)

    # Barcelona's only charter (R4) is by a non-loyalty customer -> must be absent. Keys in
    # ascending order.
    assert list(result.keys()) == ["Madrid", "Sevilla"]
    # Madrid loyalty models: Tecnam P2012 (R1), A320neo (R2); A350-900 (R3) is non-loyalty,
    # excluded; alphabetical order.
    assert result["Madrid"] == ["A320neo", "Tecnam P2012"]
    # Sevilla loyalty models: Beta ALIA (R5).
    assert result["Sevilla"] == ["Beta ALIA"]


def test_loyalty_models_by_city_with_empty_list_returns_empty_map() -> None:
    result = loyalty_models_by_city([])

    assert result == {}
