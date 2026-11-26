from katas.collections.d05_registration_order import ordered_registrations
from katas.domain import Aircraft, AircraftCategory, EngineType
from tests.testdata import aircraft


def with_registration(registration: str) -> Aircraft:
    return aircraft(registration, AircraftCategory.REGIONAL, EngineType.PISTON, 11, 1000, "30.00")


fleet = [
    with_registration("EC-A350"),
    with_registration("EC-P12"),
    with_registration("EC-ATF"),
    with_registration("EC-AL"),
    with_registration("EC-ALI"),
]


def test_ordered_registrations_with_mixed_lengths_orders_by_length_then_alphabetically() -> None:
    assert ordered_registrations(fleet) == ["EC-AL", "EC-ALI", "EC-ATF", "EC-P12", "EC-A350"]


def test_ordered_registrations_navigation_follows_comparator_order() -> None:
    result = ordered_registrations(fleet)

    # first / last / higher / lower of a NavigableSet, expressed over the sorted list.
    assert result[0] == "EC-AL"
    assert result[-1] == "EC-A350"
    assert result[result.index("EC-ATF") + 1] == "EC-P12"
    assert result[result.index("EC-ATF") - 1] == "EC-ALI"
    assert result.index("EC-A350") == len(result) - 1
    assert result.index("EC-AL") == 0


def test_ordered_registrations_with_case_variant_collapses_to_first_inserted() -> None:
    with_variant = [
        with_registration("EC-ATF"),
        with_registration("ec-atf"),
        with_registration("EC-AL"),
    ]

    result = ordered_registrations(with_variant)

    assert result == ["EC-AL", "EC-ATF"]
    # The variant is "contained" in the comparator's sense: exactly one element matches it
    # ignoring case, and that element is the first inserted spelling.
    assert [r for r in result if r.casefold() == "ec-atf"] == ["EC-ATF"]


def test_ordered_registrations_with_empty_fleet_returns_empty_list() -> None:
    assert ordered_registrations([]) == []
