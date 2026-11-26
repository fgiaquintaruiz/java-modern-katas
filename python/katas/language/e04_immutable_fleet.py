"""Exercise E04 (Python twin), Immutable class by hand.

Statement: Make ``Fleet`` immutable and implement ``create``. Changing the list
passed to the constructor must never change the fleet; ``aircraft`` is a tuple,
``by_registration`` is a read-only mapping from registration to aircraft, and
assigning any attribute fails.

Concepts: a tuple of frozen dataclasses versus a list; ``frozen=True`` plus
``__post_init__`` with ``object.__setattr__`` to convert on the way in;
``types.MappingProxyType`` as a read-only view of a dict, not a copy; the trap
of an immutable container holding mutable items: ``Aircraft`` is frozen but its
``features`` is a list, so the fleet is neither deeply immutable nor hashable.

Internals: TODO: fill after solving.

Anchor: TODO: your own image, written by you.
"""

from collections.abc import Mapping

from katas.domain import Aircraft


class Fleet:
    """Stub: the API is fixed by the test, the storage and the freezing are yours."""

    def __init__(self, owner: str, aircraft: list[Aircraft]) -> None:
        raise NotImplementedError("Not solved yet")

    @property
    def owner(self) -> str:
        raise NotImplementedError("Not solved yet")

    @property
    def aircraft(self) -> tuple[Aircraft, ...]:
        raise NotImplementedError("Not solved yet")

    @property
    def by_registration(self) -> Mapping[str, Aircraft]:
        raise NotImplementedError("Not solved yet")


def create(owner: str, aircraft: list[Aircraft]) -> Fleet:
    raise NotImplementedError("Not solved yet")
