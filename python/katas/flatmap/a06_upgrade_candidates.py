"""Exercise A06 (Python twin), Upgrade candidates.

Statement: Given a list of Charter, for each charter consider every aircraft in
the origin airport's fleet and collect those whose AircraftCategory tier is
strictly higher than the tier of the aircraft actually chartered, wrapped as
Upgrade. Tier is the enum's declaration order (e.g. WIDE_BODY ranks above
NARROW_BODY).

Concepts: a nested comprehension whose ``if`` clause closes over the outer
``charter``; ``>`` between AircraftCategory members follows declaration order
(the twin of Java's enum ``compareTo``); cartesian-style expansion of one
charter against a whole fleet; a frozen dataclass as the twin of a record,
with equality by value.

Internals: TODO: fill after solving.

Anchor: TODO: your own image, written by you.
"""

from dataclasses import dataclass

from katas.domain import Charter


@dataclass(frozen=True)
class Upgrade:
    """A candidate upgrade: the charter that could be upgraded, and the
    registration of the origin-airport aircraft offered as the upgrade.

    charter_id: id of the charter being considered for an upgrade
    registration: registration of the candidate aircraft in the origin fleet
    """

    charter_id: str
    registration: str


def upgrade_candidates(charters: list[Charter]) -> list[Upgrade]:
    raise NotImplementedError("Not solved yet")
