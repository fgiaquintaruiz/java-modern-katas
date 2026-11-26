"""Exercise E08 (Python twin), Identity versus equality pitfalls.

Statement: Fill a ``Results`` by actually comparing, with ``is`` unless the name
says equal, ints parsed at runtime with ``int("256")``, the literal ``"MAD"``
against ``"".join(["M", "A", "D"])``, a ``float("nan")``, a ``RouteKey`` and two
Decimals with different scales. The test knows which comparisons are true.

Concepts: CPython caches the ints from -5 to 256, ``is`` compares identity;
identifier-like literals are interned by the compiler, strings built at runtime
are not, ``sys.intern`` returns the pooled instance; NaN is not equal to itself
but ``in`` checks identity before equality; defining ``__eq__`` without
``__hash__`` sets ``__hash__`` to ``None``, so the instances are unhashable;
``Decimal("1.0") == Decimal("1.00")`` is true while their text differs.

Internals: TODO: fill after solving.

Anchor: TODO: your own image, written by you.
"""

from dataclasses import dataclass


class RouteKey:
    """Equality by code, no ``__hash__``: Python then sets ``__hash__ = None``."""

    def __init__(self, code: str) -> None:
        self.code = code

    def __eq__(self, other: object) -> bool:
        return isinstance(other, RouteKey) and other.code == self.code


@dataclass(frozen=True)
class Results:
    int_256_identical: bool
    int_minus_5_identical: bool
    int_257_identical: bool
    int_minus_6_identical: bool
    int_257_equal: bool
    literals_identical: bool
    built_string_identical_to_literal: bool
    built_string_equal_to_literal: bool
    interned_identical_to_literal: bool
    nan_equal_to_itself: bool
    nan_found_in_list: bool
    eq_only_instance_hashable: bool
    decimal_scales_equal: bool
    decimal_scales_same_text: bool


def compare() -> Results:
    raise NotImplementedError("Not solved yet")
