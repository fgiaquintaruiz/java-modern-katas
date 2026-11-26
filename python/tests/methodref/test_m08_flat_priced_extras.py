from katas.methodref.m08_flat_priced_extras import flat_priced_extras
from tests.testdata import extra, money


def test_flat_priced_extras_with_two_names_returns_extras_with_same_price_in_input_order() -> None:
    # Two names sharing one price of 5.00 -> two Extra, input order kept.
    result = flat_priced_extras(["Catering", "Ground transfer"], money("5.00"))

    assert result == [extra("Catering", "5.00"), extra("Ground transfer", "5.00")]


def test_flat_priced_extras_with_no_names_returns_empty_list() -> None:
    assert flat_priced_extras([], money("5.00")) == []
