"""Shared, immutable domain model for the katas: a small charter-airline
business (fleet, airports, customers and charters). Mirrors the Java records
one to one, same type names and same constructor argument order, so tests
port without changing shape.
"""

from __future__ import annotations

from dataclasses import dataclass
from datetime import date
from decimal import Decimal
from enum import Enum, auto
from functools import total_ordering


@total_ordering
class AircraftCategory(Enum):
    """Commercial tier of a charter aircraft, from the smallest to the most
    expensive segment. Declaration order is ascending by tier, so ``<`` and
    ``>`` between members reflect the business ranking of categories, the
    same way the Java enum's ``compareTo`` follows ordinal order.
    """

    REGIONAL = auto()
    NARROW_BODY = auto()
    WIDE_BODY = auto()
    CARGO = auto()
    BUSINESS_JET = auto()

    def __lt__(self, other: object) -> bool:
        if not isinstance(other, AircraftCategory):
            return NotImplemented
        return self.value < other.value


class EngineType(Enum):
    """Powerplant of a charter aircraft."""

    PISTON = auto()
    TURBOPROP = auto()
    TURBOFAN = auto()
    ELECTRIC = auto()


@dataclass(frozen=True)
class Aircraft:
    """A single aircraft in an airport's fleet.

    registration: aircraft registration, unique within the fleet
    model: commercial model name
    category: commercial tier, see AircraftCategory
    engine: powerplant, see EngineType
    seats: passenger seat count, excluding the crew
    flight_hours: accumulated airframe flight hours
    daily_rate: list price per charter day, before extras
    features: free-form equipment tags, e.g. "WIFI", "CATERING"
    """

    registration: str
    model: str
    category: AircraftCategory
    engine: EngineType
    seats: int
    flight_hours: int
    daily_rate: Decimal
    features: list[str]

    def __post_init__(self) -> None:
        # Defensive copy, like List.copyOf in the Java record.
        object.__setattr__(self, "features", list(self.features))


@dataclass(frozen=True)
class Airport:
    """A charter airline base (airport + its local fleet).

    code: short airport code, e.g. "MAD"
    city: city where the airport operates
    country: ISO-ish country name or code used across the katas
    fleet: aircraft currently assigned to this airport
    """

    code: str
    city: str
    country: str
    fleet: list[Aircraft]

    def __post_init__(self) -> None:
        # Defensive copy, like List.copyOf in the Java record.
        object.__setattr__(self, "fleet", list(self.fleet))


@dataclass(frozen=True)
class Customer:
    """A customer of the charter airline business.

    id: unique customer identifier
    name: full name
    country: customer's home country
    loyalty_member: whether the customer is enrolled in the loyalty program
    """

    id: str
    name: str
    country: str
    loyalty_member: bool


@dataclass(frozen=True)
class Extra:
    """An optional paid add-on attached to a charter, e.g. catering or
    additional insurance.

    name: human-readable name of the extra
    price: flat price charged for the whole charter
    """

    name: str
    price: Decimal


@dataclass(frozen=True)
class Charter:
    """A completed or booked charter agreement.

    id: unique charter identifier
    customer: the chartering customer
    aircraft: the chartered aircraft
    origin: airport where the aircraft departed
    destination: airport where the aircraft arrived (or will arrive)
    start: first day of the charter, inclusive
    end: last day of the charter, exclusive (see days())
    total_price: final price actually charged, including extras
    extras: paid add-ons attached to this charter
    """

    id: str
    customer: Customer
    aircraft: Aircraft
    origin: Airport
    destination: Airport
    start: date
    end: date
    total_price: Decimal
    extras: list[Extra]

    def __post_init__(self) -> None:
        if not self.end > self.start:
            raise ValueError(f"end ({self.end}) must be after start ({self.start})")
        # Defensive copy, like List.copyOf in the Java record.
        object.__setattr__(self, "extras", list(self.extras))

    def days(self) -> int:
        """Number of charter days, with ``end`` treated as exclusive: a charter
        from day 1 to day 4 lasts 3 days."""
        return (self.end - self.start).days
