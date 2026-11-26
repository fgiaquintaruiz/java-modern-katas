from decimal import Decimal

from katas.domain import AircraftCategory, EngineType
from katas.reduction.c05_weighted_average_price_per_day import weighted_average_price_per_day
from tests.testdata import aircraft, airport, charter, customer, money

REGIONAL = AircraftCategory.REGIONAL
PISTON = EngineType.PISTON


def _scale(value: Decimal) -> int:
    """Number of digits after the decimal point, the twin of BigDecimal.scale()."""
    exponent = value.as_tuple().exponent
    assert isinstance(exponent, int)  # finite Decimal, never NaN or Infinity here
    return -exponent


def test_weighted_average_price_per_day_with_non_exact_division_rounds_half_up_to_scale_2() -> None:
    alice = customer("C1", "Alice", "ES", True)
    plane = aircraft("EC-AAA", REGIONAL, PISTON, 11, 10_000, "35.00")
    madrid = airport("MAD", "Madrid", "ES")

    charters = [
        # 2026-01-01 to 2026-01-04 -> 3 days, price 60.00
        charter("R1", alice, plane, madrid, madrid, "2026-01-01", "2026-01-04", "60.00"),
        # 2026-02-01 to 2026-02-05 -> 4 days, price 40.00
        charter("R2", alice, plane, madrid, madrid, "2026-02-01", "2026-02-05", "40.00"),
    ]

    result = weighted_average_price_per_day(charters)

    # total price = 60.00 + 40.00 = 100.00, total days = 3 + 4 = 7
    # 100.00 / 7 = 14.2857142857... -> HALF_UP to scale 2 = 14.29
    assert result == money("14.29")
    assert _scale(result) == 2


def test_weighted_average_price_per_day_with_empty_list_returns_zero_at_scale_2() -> None:
    result = weighted_average_price_per_day([])

    assert result == Decimal(0)
    assert _scale(result) == 2
