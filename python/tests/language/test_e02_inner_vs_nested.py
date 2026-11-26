import gc
import weakref

from katas.language.e02_inner_vs_nested import FleetOffice, inspect


def test_inspect_with_closure_reports_outer_reference_and_self_as_free_variable() -> None:
    shape = inspect(FleetOffice().inner())

    assert shape.has_outer_reference is True
    assert shape.free_variables == ("self",)


def test_inspect_with_plain_nested_function_reports_no_outer_reference() -> None:
    shape = inspect(FleetOffice().nested())

    assert shape.has_outer_reference is False
    assert shape.free_variables == ()


def test_closure_cell_holds_the_office_and_keeps_it_alive() -> None:
    # Demonstration of the mechanism itself; it does not go through inspect.
    office = FleetOffice()
    airline = office.inner()
    name = office.nested()
    alive = weakref.ref(office)

    assert airline() == "Charter Air"
    assert airline.__closure__[0].cell_contents is office  # type: ignore[index]
    assert name.__closure__ is None

    del office
    gc.collect()
    # The closure is the Python counterpart of this$0: while it lives, so does the office.
    assert alive() is not None
    del airline
    gc.collect()
    assert alive() is None
