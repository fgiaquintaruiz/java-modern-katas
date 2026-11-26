from katas.methodref.m01_parse_all_numbers import parse_all


def test_parse_all_with_numeric_strings_returns_integers_in_order():
    # "12" -> 12, "-3" -> -3, "007" -> 7, input order preserved.
    assert parse_all(["12", "-3", "007"]) == [12, -3, 7]


def test_parse_all_with_empty_list_returns_empty_list():
    assert parse_all([]) == []
