from katas.collections.d03_recently_viewed_charters import view_order
from katas.domain import AircraftCategory, Charter, EngineType
from tests.testdata import aircraft, airport, charter, customer

ana = customer("C1", "Ana", "Spain", True)
plane = aircraft("EC-001", AircraftCategory.REGIONAL, EngineType.PISTON, 11, 1000, "30.00")
madrid = airport("MAD", "Madrid", "Spain", plane)


def charter_with_id(charter_id: str) -> Charter:
    return charter(charter_id, ana, plane, madrid, madrid, "2024-01-01", "2024-01-02", "30.00")


def test_view_order_within_capacity_returns_view_order() -> None:
    views = [charter_with_id("R1"), charter_with_id("R2"), charter_with_id("R3")]

    assert view_order(3, views) == ["R1", "R2", "R3"]


def test_view_order_with_repeated_view_moves_it_to_the_end() -> None:
    views = [
        charter_with_id("R1"),
        charter_with_id("R2"),
        charter_with_id("R3"),
        charter_with_id("R1"),
    ]

    assert view_order(3, views) == ["R2", "R3", "R1"]


def test_view_order_over_capacity_after_repeated_view_evicts_least_recently_viewed() -> None:
    # R1 was viewed again, so R2 (not R1, the first inserted) is the one evicted by R4.
    views = [
        charter_with_id("R1"),
        charter_with_id("R2"),
        charter_with_id("R3"),
        charter_with_id("R1"),
        charter_with_id("R4"),
    ]

    assert view_order(3, views) == ["R3", "R1", "R4"]


def test_view_order_with_capacity_one_keeps_last_view() -> None:
    views = [charter_with_id("R1"), charter_with_id("R2"), charter_with_id("R2")]

    assert view_order(1, views) == ["R2"]


def test_view_order_with_no_views_returns_empty() -> None:
    assert view_order(3, []) == []
