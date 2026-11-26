from katas.methodref.m02_largest_reading import largest_reading


def test_largest_reading_with_several_readings_returns_maximum():
    # max(12_000, 45_000, 800) = 45_000.
    assert largest_reading([12_000, 45_000, 800]) == 45_000


def test_largest_reading_with_empty_list_returns_none():
    assert largest_reading([]) is None
