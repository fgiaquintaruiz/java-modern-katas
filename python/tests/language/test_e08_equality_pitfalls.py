from katas.language.e08_equality_pitfalls import RouteKey, compare


def test_compare_ints_are_identical_only_inside_the_small_int_cache() -> None:
    results = compare()

    assert results.int_256_identical is True
    assert results.int_minus_5_identical is True
    assert results.int_257_identical is False
    assert results.int_minus_6_identical is False
    assert results.int_257_equal is True


def test_compare_strings_identity_follows_interning() -> None:
    results = compare()

    assert results.literals_identical is True
    assert results.built_string_identical_to_literal is False
    assert results.built_string_equal_to_literal is True
    assert results.interned_identical_to_literal is True


def test_compare_nan_is_not_equal_to_itself_yet_in_finds_it_by_identity() -> None:
    results = compare()

    assert results.nan_equal_to_itself is False
    assert results.nan_found_in_list is True


def test_compare_eq_without_hash_makes_instances_unhashable() -> None:
    results = compare()

    assert results.eq_only_instance_hashable is False
    assert RouteKey.__hash__ is None


def test_compare_decimals_with_different_scales_are_equal_but_print_differently() -> None:
    results = compare()

    assert results.decimal_scales_equal is True
    assert results.decimal_scales_same_text is False
