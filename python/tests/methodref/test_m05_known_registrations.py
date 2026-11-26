from katas.methodref.m05_known_registrations import known_registrations


def test_known_registrations_with_mixed_registrations_keeps_only_known_in_input_order() -> None:
    # "EC-CCC" is not in the set; the others keep their input order.
    result = known_registrations(["EC-BBB", "EC-CCC", "EC-AAA"], {"EC-AAA", "EC-BBB"})

    assert result == ["EC-BBB", "EC-AAA"]


def test_known_registrations_with_empty_known_set_returns_empty_list() -> None:
    assert known_registrations(["EC-AAA"], set()) == []
