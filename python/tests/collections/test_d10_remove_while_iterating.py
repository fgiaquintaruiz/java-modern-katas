from katas.collections.d10_remove_while_iterating import remove_shorter_than
from katas.domain import AircraftCategory, Charter, EngineType
from tests.testdata import aircraft, airport, charter, customer

ana = customer("C1", "Ana", "Spain", True)
plane = aircraft("EC-001", AircraftCategory.REGIONAL, EngineType.PISTON, 11, 1000, "30.00")
madrid = airport("MAD", "Madrid", "Spain", plane)

one_day = charter("R1", ana, plane, madrid, madrid, "2024-01-01", "2024-01-02", "30.00")
two_days = charter("R2", ana, plane, madrid, madrid, "2024-01-01", "2024-01-03", "60.00")
three_days = charter("R3", ana, plane, madrid, madrid, "2024-01-01", "2024-01-04", "90.00")
one_day_again = charter("R4", ana, plane, madrid, madrid, "2024-02-01", "2024-02-02", "30.00")


def mutable_list() -> list[Charter]:
    return [one_day, two_days, three_days, one_day_again]


def test_for_each_removal_with_adjacent_matches_silently_skips_one() -> None:
    # Demonstration: Python raises nothing; removing the current element shifts the next one
    # into its index, so the iterator steps over it and one_day_again survives.
    charters = [one_day, one_day_again, three_days]

    for c in charters:
        if c.days() < 2:
            charters.remove(c)

    assert charters == [one_day_again, three_days]


def test_remove_shorter_than_with_mixed_durations_removes_only_shorter_ones() -> None:
    charters = mutable_list()

    result = remove_shorter_than(charters, 2)

    # A comprehension would return a new list: the same instance proves the removal happened
    # in place.
    assert result is charters
    assert result == [two_days, three_days]


def test_remove_shorter_than_with_adjacent_matches_removes_all() -> None:
    charters = [one_day, one_day_again, three_days]

    assert remove_shorter_than(charters, 2) == [three_days]


def test_remove_shorter_than_with_no_match_leaves_list_unchanged() -> None:
    charters = mutable_list()

    assert len(remove_shorter_than(charters, 1)) == 4


def test_remove_shorter_than_with_all_matching_returns_empty_list() -> None:
    assert remove_shorter_than(mutable_list(), 10) == []


def test_remove_shorter_than_with_empty_list_returns_empty_list() -> None:
    assert remove_shorter_than([], 2) == []
