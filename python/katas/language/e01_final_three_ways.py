"""Exercise E01 (Python twin), Final three ways.

Statement: Design ``Holder`` and implement ``hold``: the holder keeps the given
list, as it is and not wrapped, in a frozen field named ``registrations``, and
offers ``snapshot()`` returning the registrations as a tuple. Declare a module
constant ``CAPACITY`` typed ``Final[int]``. The test knows which of the three
"finals" is enforced at runtime and which is only a promise to the type checker.

Concepts: ``typing.Final`` on a module constant is a type-checker contract with
no runtime effect, the name can still be rebound; a ``frozen=True`` dataclass
field freezes the binding (``FrozenInstanceError``, a subclass of
``AttributeError``), not the object it points to, so the list still grows; a
tuple is the sequence that cannot grow, and it is a copy of the list at the
moment it is built.

Internals: TODO: fill after solving.

Anchor: TODO: your own image, written by you.
"""


class Holder:
    """Stub: the shape (dataclass options, fields) is yours to design; snapshot() is fixed by the test."""

    def snapshot(self) -> tuple[str, ...]:
        raise NotImplementedError("Not solved yet")


def hold(registrations: list[str]) -> Holder:
    raise NotImplementedError("Not solved yet")
