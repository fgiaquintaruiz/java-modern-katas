package com.fgiaquinta.katas.collections;

import com.fgiaquinta.katas.collections.D09ViewsVersusCopies.Views;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class D09ViewsVersusCopiesTest {

    private final List<String> source = new ArrayList<>(List.of("EC-A", "EC-B", "EC-C"));

    @Test
    @DisplayName("the unmodifiable wrapper reflects later changes of the source but rejects its own writes")
    void build_unmodifiableView_followsSourceAndRejectsWrites() {
        Views views = D09ViewsVersusCopies.build(source);

        source.set(0, "EC-Z");
        source.add("EC-D");

        assertThat(views.unmodifiableView()).containsExactly("EC-Z", "EC-B", "EC-C", "EC-D");
        assertThatThrownBy(() -> views.unmodifiableView().add("X")).isInstanceOf(UnsupportedOperationException.class);
    }

    @Test
    @DisplayName("the copy is a snapshot: later changes are not visible and writes are rejected")
    void build_copy_isIndependentAndImmutable() {
        Views views = D09ViewsVersusCopies.build(source);

        source.set(0, "EC-Z");
        source.add("EC-D");

        assertThat(views.copy()).containsExactly("EC-A", "EC-B", "EC-C");
        assertThatThrownBy(() -> views.copy().add("X")).isInstanceOf(UnsupportedOperationException.class);
    }

    @Test
    @DisplayName("the sublist is a write-through view of the first two elements")
    void build_firstTwoView_writesThroughToSource() {
        Views views = D09ViewsVersusCopies.build(source);

        views.firstTwoView().set(1, "EC-Y");
        source.set(0, "EC-Z");

        assertThat(views.firstTwoView()).containsExactly("EC-Z", "EC-Y");
        assertThat(source).containsExactly("EC-Z", "EC-Y", "EC-C");
    }

    @Test
    @DisplayName("the sublist fails fast after a structural change of the source")
    void build_firstTwoView_afterSourceAdd_throwsConcurrentModification() {
        Views views = D09ViewsVersusCopies.build(source);

        source.add("EC-D");

        assertThatThrownBy(() -> views.firstTwoView().get(0)).isInstanceOf(ConcurrentModificationException.class);
    }

    @Test
    @DisplayName("the Arrays.asList list writes through to its array in both directions but cannot grow")
    void build_asListOfArray_isFixedSizeAndWriteThrough() {
        Views views = D09ViewsVersusCopies.build(source);

        views.asListOfArray().set(1, "EC-Q");
        views.backingArray()[2] = "EC-R";

        assertThat(views.backingArray()[1]).isEqualTo("EC-Q");
        assertThat(views.asListOfArray()).containsExactly("EC-A", "EC-Q", "EC-R");
        assertThatThrownBy(() -> views.asListOfArray().add("X")).isInstanceOf(UnsupportedOperationException.class);
    }

    @Test
    @DisplayName("the Arrays.asList list is detached from the source list")
    void build_asListOfArray_isIndependentOfSource() {
        Views views = D09ViewsVersusCopies.build(source);

        source.set(0, "EC-Z");

        assertThat(views.asListOfArray()).containsExactly("EC-A", "EC-B", "EC-C");
    }
}
