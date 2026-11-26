from katas.methodref.m07_sorted_unique_registrations import sorted_unique_registrations


def test_sorted_unique_registrations_with_duplicates_and_disorder_returns_distinct_in_natural_order() -> None:  # fmt: skip
    # Natural order: "EC-AAA" < "EC-BBB" < "EC-CCC"; the repeated "EC-BBB" collapses.
    result = sorted_unique_registrations(["EC-CCC", "EC-BBB", "EC-AAA", "EC-BBB"])

    assert result == ["EC-AAA", "EC-BBB", "EC-CCC"]


def test_sorted_unique_registrations_with_empty_list_returns_empty_list() -> None:
    assert sorted_unique_registrations([]) == []
