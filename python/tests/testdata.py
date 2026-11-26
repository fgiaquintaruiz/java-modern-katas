"""Factory helpers to build small, inline domain datasets in tests without
repeating constructor boilerplate. Twin of the Java TestData class; only
referenced from tests.
"""

from __future__ import annotations

from datetime import date
from decimal import Decimal

from katas.domain import Aircraft, AircraftCategory, Airport, Charter, Customer, EngineType, Extra


def money(amount: str) -> Decimal:
    return Decimal(amount).quantize(Decimal("0.01"))


def aircraft(
    registration: str,
    category: AircraftCategory,
    engine: EngineType,
    seats: int,
    flight_hours: int,
    daily_rate: str,
    *features: str,
) -> Aircraft:
    """Builds an aircraft whose model is a real type matching the (category, engine) pair."""
    return Aircraft(
        registration,
        _realistic_model(category, engine),
        category,
        engine,
        seats,
        flight_hours,
        money(daily_rate),
        list(features),
    )


def _realistic_model(category: AircraftCategory, engine: EngineType) -> str:
    match category:
        case AircraftCategory.REGIONAL:
            return {
                EngineType.TURBOPROP: "ATR 72",
                EngineType.PISTON: "Tecnam P2012",
                EngineType.ELECTRIC: "Eviation Alice",
                EngineType.TURBOFAN: "Embraer E175",
            }[engine]
        case AircraftCategory.NARROW_BODY:
            return _require_turbofan(category, engine, "A320neo")
        case AircraftCategory.WIDE_BODY:
            return _require_turbofan(category, engine, "A350-900")
        case AircraftCategory.CARGO:
            if engine is EngineType.TURBOFAN:
                return "Boeing 777F"
            if engine is EngineType.TURBOPROP:
                return "ATR 72F"
            raise _unrealistic(category, engine)
        case AircraftCategory.BUSINESS_JET:
            return {
                EngineType.TURBOFAN: "Gulfstream G650",
                EngineType.TURBOPROP: "Pilatus PC-12",
                EngineType.PISTON: "Piper M350",
                EngineType.ELECTRIC: "Beta ALIA",
            }[engine]
    raise _unrealistic(category, engine)


def _require_turbofan(category: AircraftCategory, engine: EngineType, model: str) -> str:
    if engine is not EngineType.TURBOFAN:
        raise _unrealistic(category, engine)
    return model


def _unrealistic(category: AircraftCategory, engine: EngineType) -> ValueError:
    return ValueError(f"No real aircraft for {category.name} + {engine.name}")


def airport(code: str, city: str, country: str, *fleet: Aircraft) -> Airport:
    return Airport(code, city, country, list(fleet))


def customer(id: str, name: str, country: str, loyalty: bool) -> Customer:
    return Customer(id, name, country, loyalty)


def extra(name: str, price: str) -> Extra:
    return Extra(name, money(price))


def charter(
    id: str,
    customer: Customer,
    aircraft: Aircraft,
    origin: Airport,
    destination: Airport,
    start_iso: str,
    end_iso: str,
    total_price: str,
    *extras: Extra,
) -> Charter:
    return Charter(
        id,
        customer,
        aircraft,
        origin,
        destination,
        date.fromisoformat(start_iso),
        date.fromisoformat(end_iso),
        money(total_price),
        list(extras),
    )
