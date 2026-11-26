from katas.language.e03_initialisation_order import initialisation_order


def test_initialisation_order_with_two_instances_runs_module_and_class_body_once_at_import_then_instance_parts_twice():
    # "before" comes third: the module body and the class body already ran when the module was imported.
    assert initialisation_order() == [
        "module", "class-body",
        "before",
        "field-default", "post-init",
        "field-default", "post-init",
    ]
