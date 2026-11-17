package com.fgiaquinta.katas.kt.collections

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class D09ViewsVersusCopiesTest {

    private val source = mutableListOf("EC-A", "EC-B", "EC-C")

    @Test
    @DisplayName("build_readOnlyView_followsSourceAndIsOnlyReadOnlyByType")
    fun build_readOnlyView_followsSourceAndIsOnlyReadOnlyByType() {
        val views = build(source)

        source[0] = "EC-Z"
        source.add("EC-D")

        assertThat(views.readOnlyView).containsExactly("EC-Z", "EC-B", "EC-C", "EC-D")
        // Read-only is a compile-time type, not a runtime guarantee: the same object still accepts writes.
        @Suppress("UNCHECKED_CAST")
        (views.readOnlyView as MutableList<String>).add("EC-E")
        assertThat(source).endsWith("EC-E")
    }

    @Test
    @DisplayName("build_copy_isIndependentOfSource")
    fun build_copy_isIndependentOfSource() {
        val views = build(source)

        source[0] = "EC-Z"
        source.add("EC-D")

        assertThat(views.copy).containsExactly("EC-A", "EC-B", "EC-C")
    }

    @Test
    @DisplayName("build_javaImmutable_isSnapshotAndRejectsWrites")
    fun build_javaImmutable_isSnapshotAndRejectsWrites() {
        val views = build(source)

        source[0] = "EC-Z"

        assertThat(views.javaImmutable).containsExactly("EC-A", "EC-B", "EC-C")
        assertThatThrownBy { (views.javaImmutable as MutableList<String>).add("X") }
            .isInstanceOf(UnsupportedOperationException::class.java)
    }

    @Test
    @DisplayName("build_firstTwoView_writesThroughToSource")
    fun build_firstTwoView_writesThroughToSource() {
        val views = build(source)

        views.firstTwoView[1] = "EC-Y"
        source[0] = "EC-Z"

        assertThat(views.firstTwoView).containsExactly("EC-Z", "EC-Y")
        assertThat(source).containsExactly("EC-Z", "EC-Y", "EC-C")
    }

    @Test
    @DisplayName("build_firstTwoView_afterSourceAdd_throwsConcurrentModification")
    fun build_firstTwoView_afterSourceAdd_throwsConcurrentModification() {
        val views = build(source)

        source.add("EC-D")

        assertThatThrownBy { views.firstTwoView[0] }.isInstanceOf(ConcurrentModificationException::class.java)
    }

    @Test
    @DisplayName("build_asListOfArray_isFixedSizeAndWriteThrough")
    fun build_asListOfArray_isFixedSizeAndWriteThrough() {
        val views = build(source)

        views.asListOfArray[1] = "EC-Q"
        views.backingArray[2] = "EC-R"

        assertThat(views.backingArray[1]).isEqualTo("EC-Q")
        assertThat(views.asListOfArray).containsExactly("EC-A", "EC-Q", "EC-R")
        assertThatThrownBy { views.asListOfArray.add("X") }.isInstanceOf(UnsupportedOperationException::class.java)
    }

    @Test
    @DisplayName("build_asListOfArray_isIndependentOfSource")
    fun build_asListOfArray_isIndependentOfSource() {
        val views = build(source)

        source[0] = "EC-Z"

        assertThat(views.asListOfArray).containsExactly("EC-A", "EC-B", "EC-C")
    }
}
