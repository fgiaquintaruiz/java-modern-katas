from collections import deque

from katas.collections.d01_boarding_queue import last_boarded_first


def test_last_boarded_first_with_four_passengers_and_two_returns_last_two_reversed() -> None:
    boarding = ["Ana", "Bea", "Carlos", "Dana"]

    result = last_boarded_first(boarding, 2)

    assert isinstance(result, deque)
    assert list(result) == ["Dana", "Carlos"]


def test_last_boarded_first_with_n_larger_than_queue_returns_all_reversed() -> None:
    boarding = ["Ana", "Bea", "Carlos"]

    assert list(last_boarded_first(boarding, 10)) == ["Carlos", "Bea", "Ana"]


def test_last_boarded_first_with_zero_returns_empty() -> None:
    assert list(last_boarded_first(["Ana", "Bea"], 0)) == []


def test_last_boarded_first_with_empty_list_returns_empty() -> None:
    assert list(last_boarded_first([], 3)) == []


def test_last_boarded_first_with_duplicate_names_keeps_both() -> None:
    boarding = ["Ana", "Bea", "Ana"]

    result = last_boarded_first(boarding, 3)

    # Duplicated names are separate passengers, and the input list is left untouched.
    assert list(result) == ["Ana", "Bea", "Ana"]
    assert boarding == ["Ana", "Bea", "Ana"]
