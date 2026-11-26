"""Exercise M05 (Python twin), Known registrations.

Statement: Keep only the registrations present in the given set, in input
order. The bound method ``known_registrations.__contains__`` as the predicate
of ``filter`` is REQUIRED; a lambda is not accepted.

Concepts: a bound method is a function value with its receiver already
attached, the twin of ``knownRegistrations::contains``; ``x in s`` is sugar
for ``s.__contains__(x)``, so the dunder is the callable to pass; ``filter``
is lazy and ``list`` drains it in input order; set membership is O(1) by hash.

Internals: TODO: fill after solving.

Anchor: TODO: your own image, written by you.
"""


def known_registrations(registrations: list[str], known_registrations: set[str]) -> list[str]:
    raise NotImplementedError("Not solved yet")
