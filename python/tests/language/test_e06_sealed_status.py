from typing import get_args

import pytest

from katas.domain import AircraftCategory, EngineType
from katas.language.e06_sealed_status import Cancelled, Delayed, Scheduled, Status, describe
from tests.testdata import aircraft, airport, charter, customer

ana = customer("C1", "Ana", "Spain", True)
plane = aircraft("EC-001", AircraftCategory.REGIONAL, EngineType.PISTON, 11, 1000, "30.00")
madrid = airport("MAD", "Madrid", "Spain", plane)
r1 = charter("R1", ana, plane, madrid, madrid, "2024-01-01", "2024-01-02", "30.00")


def test_describe_with_scheduled_returns_scheduled_and_charter_id():
    assert describe(Scheduled(r1)) == "Scheduled R1"


def test_describe_with_short_delay_returns_delayed_minutes():
    assert describe(Delayed(15)) == "Delayed 15 min"
    assert describe(Delayed(59)) == "Delayed 59 min"


def test_describe_with_delay_from_sixty_minutes_returns_heavily_delayed():
    assert describe(Delayed(60)) == "Heavily delayed 60 min"
    assert describe(Delayed(90)) == "Heavily delayed 90 min"


def test_describe_with_cancelled_returns_cancelled_and_reason():
    assert describe(Cancelled("weather")) == "Cancelled: weather"


def test_describe_with_unknown_status_raises_from_the_fallback_case():
    # The union is the closed set; anything outside it must hit the assert_never fallback.
    assert get_args(Status) == (Scheduled, Delayed, Cancelled)
    with pytest.raises(AssertionError):
        describe("boarding")  # type: ignore[arg-type]
