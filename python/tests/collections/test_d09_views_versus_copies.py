import pytest

from katas.collections.d09_views_versus_copies import build


def source_list() -> list[str]:
    return ["EC-A", "EC-B", "EC-C"]


def test_build_live_iterator_follows_source_and_rejects_writes() -> None:
    source = source_list()
    views = build(source)

    source[0] = "EC-Z"
    source.append("EC-D")

    assert list(views.live_iterator) == ["EC-Z", "EC-B", "EC-C", "EC-D"]
    with pytest.raises(TypeError):
        views.live_iterator[0] = "X"  # type: ignore[index]


def test_build_snapshot_is_independent_and_immutable() -> None:
    source = source_list()
    views = build(source)

    source[0] = "EC-Z"
    source.append("EC-D")

    assert views.snapshot == ("EC-A", "EC-B", "EC-C")
    with pytest.raises(TypeError):
        views.snapshot[0] = "X"  # type: ignore[index]


def test_build_first_two_is_a_copy_and_does_not_write_through_to_source() -> None:
    # Opposite of subList: slicing copies, so neither side sees the other's writes.
    source = source_list()
    views = build(source)

    views.first_two[1] = "EC-Y"
    source[0] = "EC-Z"

    assert views.first_two == ["EC-A", "EC-Y"]
    assert source == ["EC-Z", "EC-B", "EC-C"]


def test_build_first_two_after_source_append_keeps_reading() -> None:
    # Opposite of subList: no fail-fast check exists, the copy simply does not notice.
    source = source_list()
    views = build(source)

    source.append("EC-D")

    assert views.first_two[0] == "EC-A"
    assert len(views.first_two) == 2


def test_build_buffer_view_is_fixed_size_and_write_through() -> None:
    # "EC-A,EC-B,EC-C": byte 3 is the "A" of EC-A, byte 8 is the "B" of EC-B.
    views = build(source_list())

    views.buffer_view[3:4] = b"Q"
    views.backing_buffer[8:9] = b"R"

    assert views.backing_buffer[3:4] == b"Q"
    assert bytes(views.buffer_view) == b"EC-Q,EC-R,EC-C"
    with pytest.raises(BufferError):
        views.backing_buffer.extend(b",EC-X")


def test_build_buffer_view_is_independent_of_source() -> None:
    source = source_list()
    views = build(source)

    source[0] = "EC-Z"

    assert bytes(views.buffer_view) == b"EC-A,EC-B,EC-C"
