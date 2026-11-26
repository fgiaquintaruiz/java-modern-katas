from katas.collections.d08_mutable_key_lookup import MutableRoute, contains_after_rename


def test_set_with_mutated_key_fails_to_find_it_but_still_iterates():
    # Demonstration: a mutable key renamed after insertion is lost to lookups, though still iterated.
    broken = set()
    route = MutableRoute("MAD-BCN")
    broken.add(route)

    route.code = "MAD-SEV"

    assert route not in broken
    assert len(broken) == 1
    assert next(iter(broken)) is route


def test_contains_after_rename_with_rename_returns_true():
    route = MutableRoute("MAD-BCN")

    assert contains_after_rename(route, "MAD-SEV") is True


def test_contains_after_rename_with_rename_changes_the_route():
    route = MutableRoute("MAD-BCN")

    contains_after_rename(route, "MAD-SEV")

    assert route.code == "MAD-SEV"


def test_contains_after_rename_with_same_code_returns_true():
    assert contains_after_rename(MutableRoute("MAD-BCN"), "MAD-BCN") is True
