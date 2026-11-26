"""Exercise E03 (Python twin), Initialisation order.

Statement: Write, in this module, a module-level statement and a small dataclass
with a field ``default_factory`` and a ``__post_init__``, each recording its
name in a shared log. ``initialisation_order`` logs "before" first, then creates
two instances and returns the log. The test fixes the names and the order. Call
it once per interpreter: the module imports once.

Concepts: a module body runs top to bottom once, on first import, and the class
statement runs the class body right there, so both have logged before any
function in the module is called; the generated ``__init__`` runs on every
instance, calls each ``default_factory`` for the fields that were not passed and
then ``__post_init__``; ``sys.modules`` is the cache that makes the second
import a no-op.

Internals: TODO: fill after solving.

Anchor: TODO: your own image, written by you.
"""


def initialisation_order() -> list[str]:
    raise NotImplementedError("Not solved yet")
