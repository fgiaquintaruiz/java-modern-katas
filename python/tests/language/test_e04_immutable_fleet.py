from types import MappingProxyType

import pytest

from katas.domain import AircraftCategory, EngineType
from katas.language.e04_immutable_fleet import create
from tests.testdata import aircraft

a1 = aircraft("EC-001", AircraftCategory.REGIONAL, EngineType.PISTON, 11, 1000, "30.00")
a2 = aircraft("EC-002", AircraftCategory.NARROW_BODY, EngineType.TURBOFAN, 180, 1200, "35.00")


def test_create_then_mutating_source_list_does_not_affect_fleet() -> None:
    source = [a1]
    fleet = create("Charter Air", source)

    source.append(a2)
    source[0] = a2

    assert fleet.aircraft == (a1,)


def test_aircraft_returns_tuple_that_cannot_grow_and_owner_is_kept() -> None:
    fleet = create("Charter Air", [a1])

    returned = fleet.aircraft
    with pytest.raises(AttributeError):
        returned.append(a2)  # type: ignore[attr-defined]

    assert fleet.aircraft == (a1,)
    assert fleet.owner == "Charter Air"


def test_by_registration_is_read_only_view_keyed_by_registration() -> None:
    fleet = create("Charter Air", [a1, a2])

    view = fleet.by_registration

    assert isinstance(view, MappingProxyType)
    assert view["EC-002"] is a2
    assert list(view) == ["EC-001", "EC-002"]
    with pytest.raises(TypeError):
        view["EC-003"] = a1  # type: ignore[index]


def test_create_returns_fleet_whose_attributes_cannot_be_assigned() -> None:
    fleet = create("Charter Air", [a1])

    # No setters: every attribute assignment is refused, including a brand-new attribute.
    with pytest.raises(AttributeError):
        fleet.owner = "Other Air"  # type: ignore[misc]
    with pytest.raises(AttributeError):
        fleet.aircraft = ()  # type: ignore[misc]
    # A frozen dataclass raises FrozenInstanceError (an AttributeError); with slots=True CPython
    # 3.12 raises TypeError instead for an unknown name. Either way the assignment is refused.
    with pytest.raises((AttributeError, TypeError)):
        fleet.extra = "not allowed"  # type: ignore[attr-defined]


def test_immutable_container_of_mutable_items_is_the_trap() -> None:
    fleet = create(
        "Charter Air",
        [aircraft("EC-001", AircraftCategory.REGIONAL, EngineType.PISTON, 11, 1000, "30.00")],
    )

    # Aircraft is frozen and the tuple cannot grow, yet features is a list and it changes in place.
    fleet.aircraft[0].features.append("WIFI")

    assert fleet.aircraft[0].features == ["WIFI"]
    # The list inside also makes the "frozen" aircraft, and so the tuple, unhashable.
    with pytest.raises(TypeError):
        hash(fleet.aircraft)
