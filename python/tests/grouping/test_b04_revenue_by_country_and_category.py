from katas.domain import AircraftCategory, EngineType
from katas.grouping.b04_revenue_by_country_and_category import revenue_by_country_and_category
from tests.testdata import aircraft, airport, charter, customer, money

REGIONAL = AircraftCategory.REGIONAL
WIDE_BODY = AircraftCategory.WIDE_BODY
PISTON = EngineType.PISTON
TURBOFAN = EngineType.TURBOFAN

CUSTOMER = customer("C1", "Ana", "Spain", True)


def test_revenue_by_country_and_category_with_multiple_countries_and_categories_sums_each_bucket() -> None:  # fmt: skip
    regional = aircraft("EC-P01", REGIONAL, PISTON, 11, 1000, "30.00")
    wide_body = aircraft("EC-P02", WIDE_BODY, TURBOFAN, 300, 2000, "60.00")
    spain_airport = airport("MAD", "Madrid", "Spain", regional, wide_body)
    france_airport = airport("PAR", "Paris", "France", regional)

    charters = [
        charter(
            "R1",
            CUSTOMER,
            regional,
            spain_airport,
            spain_airport,
            "2024-01-01",
            "2024-01-03",
            "100.00",
        ),
        charter(
            "R2",
            CUSTOMER,
            regional,
            spain_airport,
            spain_airport,
            "2024-01-05",
            "2024-01-07",
            "50.00",
        ),
        charter(
            "R3",
            CUSTOMER,
            wide_body,
            spain_airport,
            spain_airport,
            "2024-02-01",
            "2024-02-04",
            "200.00",
        ),
        charter(
            "R4",
            CUSTOMER,
            regional,
            france_airport,
            france_airport,
            "2024-03-01",
            "2024-03-02",
            "80.00",
        ),
    ]

    result = revenue_by_country_and_category(charters)

    assert set(result.keys()) == {"Spain", "France"}
    assert set(result["Spain"].keys()) == {REGIONAL, WIDE_BODY}
    # Spain / REGIONAL: 100.00 + 50.00 = 150.00
    assert result["Spain"][REGIONAL] == money("150.00")
    # Spain / WIDE_BODY: 200.00 (single charter)
    assert result["Spain"][WIDE_BODY] == money("200.00")
    assert set(result["France"].keys()) == {REGIONAL}
    # France / REGIONAL: 80.00 (single charter)
    assert result["France"][REGIONAL] == money("80.00")


def test_revenue_by_country_and_category_with_empty_list_returns_empty_map() -> None:
    result = revenue_by_country_and_category([])

    assert result == {}
