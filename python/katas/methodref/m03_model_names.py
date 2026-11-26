"""Exercise M03 (Python twin), Model names.

Statement: Return the model of each aircraft, in input order.
``operator.attrgetter("model")`` as the mapping function is REQUIRED; a lambda
is not accepted.

Concepts: ``attrgetter`` builds a callable that reads one attribute of
whatever it is handed, the twin of the unbound ``Aircraft::model`` where the
first argument becomes the receiver; dataclass fields are attributes, not
accessor methods, so there is no ``Aircraft.model`` function to pass;
``itemgetter`` and ``methodcaller`` are the siblings for indexing and for
calling a method by name.

Internals: TODO: fill after solving.

Anchor: TODO: your own image, written by you.
"""

from katas.domain import Aircraft


def model_names(aircraft: list[Aircraft]) -> list[str]:
    raise NotImplementedError("Not solved yet")
