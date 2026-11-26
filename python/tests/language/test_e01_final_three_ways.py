from typing import Final, get_origin, get_type_hints

import pytest

import katas.language.e01_final_three_ways as e01
from katas.language.e01_final_three_ways import hold


def test_hold_with_frozen_field_freezes_reference_not_contents() -> None:
    source = ["EC-A"]
    holder = hold(source)

    # The field cannot be rebound, yet the very same list it points to still grows.
    with pytest.raises(AttributeError):
        holder.registrations = []
    holder.registrations.append("EC-B")

    assert holder.registrations is source
    assert holder.registrations == ["EC-A", "EC-B"]
    assert type(holder).__dataclass_params__.frozen is True


def test_hold_module_declares_capacity_as_final_but_runtime_does_not_enforce_it() -> None:
    hold([])

    hints = get_type_hints(e01)
    assert get_origin(hints["CAPACITY"]) is Final
    assert isinstance(e01.CAPACITY, int)

    # Final is a promise to the type checker: the interpreter happily rebinds the name.
    original = e01.CAPACITY
    try:
        e01.CAPACITY = original + 1
        assert e01.CAPACITY == original + 1  # noqa: SIM300
    finally:
        e01.CAPACITY = original


def test_snapshot_returns_tuple_copy_that_neither_grows_nor_follows_the_list() -> None:
    holder = hold(["EC-A"])

    snapshot = holder.snapshot()
    holder.registrations.append("EC-B")

    assert isinstance(snapshot, tuple)
    assert snapshot == ("EC-A",)
    with pytest.raises(AttributeError):
        snapshot.append("EC-C")
