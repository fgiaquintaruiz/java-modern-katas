from katas.domain import AircraftCategory, EngineType
from katas.methodref.m03_model_names import model_names
from tests.testdata import aircraft

REGIONAL = AircraftCategory.REGIONAL
WIDE_BODY = AircraftCategory.WIDE_BODY
PISTON = EngineType.PISTON
TURBOFAN = EngineType.TURBOFAN


def test_model_names_with_two_aircraft_returns_models_in_input_order() -> None:
    # testdata derives the model from category and engine: input order b, a gives
    # "Tecnam P2012", "A350-900".
    a = aircraft("EC-AAA", WIDE_BODY, TURBOFAN, 300, 10_000, "60.00")
    b = aircraft("EC-BBB", REGIONAL, PISTON, 11, 5_000, "30.00")

    assert model_names([b, a]) == ["Tecnam P2012", "A350-900"]


def test_model_names_with_empty_list_returns_empty_list() -> None:
    assert model_names([]) == []
