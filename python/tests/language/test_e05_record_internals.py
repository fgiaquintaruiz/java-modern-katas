from dataclasses import FrozenInstanceError, is_dataclass
from decimal import Decimal

import pytest

from katas.language.e05_record_internals import quote


def test_quote_with_negative_price_raises_value_error():
    with pytest.raises(ValueError):
        quote("MAD-BCN", Decimal("-0.01"))


def test_quote_with_zero_price_is_accepted():
    assert quote("MAD-BCN", Decimal("0")).price == 0


def test_quote_with_padded_route_stores_stripped_route():
    assert quote("  MAD-BCN ", Decimal("100.00")).route == "MAD-BCN"


def test_quote_with_equal_fields_are_equal_with_same_hash():
    first = quote("MAD-BCN", Decimal("100.00"))
    second = quote("MAD-BCN", Decimal("100.00"))

    assert first == second
    assert hash(first) == hash(second)
    assert len({first, second}) == 1
    assert first != quote("MAD-SEV", Decimal("100.00"))


def test_quote_repr_lists_fields_by_name():
    assert repr(quote("MAD-BCN", Decimal("100.00"))) == "Quote(route='MAD-BCN', price=Decimal('100.00'))"


def test_quote_type_is_frozen_dataclass():
    q = quote("MAD-BCN", Decimal("10"))

    assert is_dataclass(q)
    assert type(q).__dataclass_params__.frozen is True
    with pytest.raises(FrozenInstanceError):
        q.route = "MAD-SEV"


def test_quote_with_order_compares_field_by_field_in_declaration_order():
    cheap_sev = quote("MAD-SEV", Decimal("50.00"))
    dear_bcn = quote("MAD-BCN", Decimal("100.00"))
    cheaper_bcn = quote("MAD-BCN", Decimal("90.00"))

    # Route first, then price: the field tuple is compared lexicographically.
    assert dear_bcn < cheap_sev
    assert sorted([cheap_sev, dear_bcn, cheaper_bcn]) == [cheaper_bcn, dear_bcn, cheap_sev]
