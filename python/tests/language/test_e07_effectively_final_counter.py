import pytest

from katas.domain import AircraftCategory, EngineType
from katas.language.e07_effectively_final_counter import count_long_charters, id_suppliers
from tests.testdata import aircraft, airport, charter, customer

ana = customer("C1", "Ana", "Spain", True)
plane = aircraft("EC-001", AircraftCategory.REGIONAL, EngineType.PISTON, 11, 1000, "30.00")
madrid = airport("MAD", "Madrid", "Spain", plane)

one_day = charter("R1", ana, plane, madrid, madrid, "2024-01-01", "2024-01-02", "30.00")
two_days = charter("R2", ana, plane, madrid, madrid, "2024-01-01", "2024-01-03", "60.00")
three_days = charter("R3", ana, plane, madrid, madrid, "2024-01-01", "2024-01-04", "90.00")


def test_count_long_charters_with_mixed_durations_counts_those_at_or_above_threshold():
    assert count_long_charters([one_day, two_days, three_days], 2) == 2


def test_count_long_charters_with_no_match_returns_zero():
    assert count_long_charters([one_day, two_days], 5) == 0


def test_count_long_charters_with_empty_list_returns_zero():
    assert count_long_charters([], 1) == 0


def test_count_long_charters_with_threshold_one_counts_all():
    assert count_long_charters([one_day, two_days, three_days], 1) == 3


def test_id_suppliers_each_remember_their_own_charter_not_the_last_one():
    suppliers = id_suppliers([one_day, two_days, three_days])

    # Late binding would make all three answer "R3".
    assert [supply() for supply in suppliers] == ["R1", "R2", "R3"]


def test_nested_function_rebinding_without_nonlocal_raises_unbound_local_error():
    # Demonstration of the rule itself: assignment makes the name local to the nested function.
    def outer() -> int:
        total = 0

        def add(n: int) -> None:
            total += n  # noqa: F823, F841  # local by assignment, read before bound

        add(1)
        return total

    with pytest.raises(UnboundLocalError):
        outer()
