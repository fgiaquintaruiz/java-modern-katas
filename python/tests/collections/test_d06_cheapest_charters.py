from decimal import Decimal
from heapq import heappop, heappush

from katas.collections.d06_cheapest_charters import cheapest
from katas.domain import AircraftCategory, Charter, EngineType
from tests.testdata import aircraft, airport, charter, customer

ana = customer("C1", "Ana", "Spain", True)
plane = aircraft("EC-001", AircraftCategory.REGIONAL, EngineType.PISTON, 11, 1000, "30.00")
madrid = airport("MAD", "Madrid", "Spain", plane)


def priced(charter_id: str, price: str) -> Charter:
    return charter(charter_id, ana, plane, madrid, madrid, "2024-01-01", "2024-01-02", price)


# Descending prices: pushing them in this order leaves the heap list as 10, 20, 40, 50, 30.
def descending() -> list[Charter]:
    return [priced("R50", "50.00"), priced("R40", "40.00"), priced("R30", "30.00"),
            priced("R20", "20.00"), priced("R10", "10.00")]


def test_cheapest_with_k_smaller_than_size_polls_k_cheapest_in_price_order():
    result = cheapest(descending(), 3)

    assert [c.id for c in result] == ["R10", "R20", "R30"]


def test_cheapest_with_k_larger_than_size_holds_all():
    result = cheapest(descending(), 10)

    assert len(result) == 5
    assert result[0].id == "R10"


def test_cheapest_with_empty_list_returns_empty_list():
    assert cheapest([], 3) == []


def test_heapq_iteration_versus_popping_only_popping_is_sorted():
    # Demonstration: iterating a heap is not sorted, popping it is.
    heap: list[Decimal] = []
    for c in descending():
        heappush(heap, c.total_price)

    iterated = list(heap)
    assert iterated != sorted(iterated)

    popped = [heappop(heap) for _ in range(len(heap))]
    assert popped == sorted(iterated)
