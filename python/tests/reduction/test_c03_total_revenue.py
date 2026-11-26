from decimal import Decimal

from katas.domain import AircraftCategory, EngineType
from katas.reduction.c03_total_revenue import total_revenue
from tests.testdata import aircraft, airport, charter, customer, money

REGIONAL = AircraftCategory.REGIONAL
PISTON = EngineType.PISTON


def test_total_revenue_with_multiple_charters_sums_all_total_prices():
    alice = customer("C1", "Alice", "ES", True)
    plane = aircraft("EC-AAA", REGIONAL, PISTON, 11, 10_000, "35.00")
    madrid = airport("MAD", "Madrid", "ES")

    charters = [
        charter("R1", alice, plane, madrid, madrid, "2026-01-01", "2026-01-02", "120.00"),
        charter("R2", alice, plane, madrid, madrid, "2026-02-01", "2026-02-02", "80.50"),
        charter("R3", alice, plane, madrid, madrid, "2026-03-01", "2026-03-02", "45.25"),
    ]

    result = total_revenue(charters)

    # 120.00 + 80.50 + 45.25 = 245.75
    assert result == money("245.75")


def test_total_revenue_with_empty_list_returns_zero():
    result = total_revenue([])

    assert result == Decimal(0)
