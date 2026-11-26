from katas.domain import AircraftCategory, EngineType
from katas.grouping.b03_average_price_by_duration import average_price_by_long_charter
from tests.testdata import aircraft, airport, charter, customer

REGIONAL = AircraftCategory.REGIONAL
PISTON = EngineType.PISTON

CUSTOMER = customer("C1", "Ana", "Spain", True)
AIRCRAFT = aircraft("EC-P01", REGIONAL, PISTON, 11, 1000, "30.00")
AIRPORT = airport("MAD", "Madrid", "Spain", AIRCRAFT)


def test_average_price_by_long_charter_with_mixed_durations_averages_each_partition_separately():
    charters = [
        # days() = end - start, end exclusive
        charter("R1", CUSTOMER, AIRCRAFT, AIRPORT, AIRPORT, "2024-01-01", "2024-01-04", "120.00"),  # 3 days
        charter("R2", CUSTOMER, AIRCRAFT, AIRPORT, AIRPORT, "2024-01-01", "2024-01-02", "50.00"),   # 1 day
        charter("R3", CUSTOMER, AIRCRAFT, AIRPORT, AIRPORT, "2024-01-01", "2024-01-06", "200.00"),  # 5 days
        charter("R4", CUSTOMER, AIRCRAFT, AIRPORT, AIRPORT, "2024-01-01", "2024-01-03", "80.00"),   # 2 days
    ]

    result = average_price_by_long_charter(charters, 3)

    # long (days >= 3): R1 (120.00) + R3 (200.00) = 320.00 / 2 = 160.0
    assert result[True] == 160.0
    # short (days < 3): R2 (50.00) + R4 (80.00) = 130.00 / 2 = 65.0
    assert result[False] == 65.0


def test_average_price_by_long_charter_with_empty_list_returns_zero_average_for_both_partitions():
    result = average_price_by_long_charter([], 3)

    assert result == {True: 0.0, False: 0.0}
