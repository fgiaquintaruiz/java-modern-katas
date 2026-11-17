package com.fgiaquinta.katas.kt.language

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import java.lang.reflect.Modifier

class E02InnerVsNestedTest {

    @Test
    @DisplayName("inspect_withInnerClass_reportsOuterReferenceAndOuterConstructorParameter")
    fun inspect_withInnerClass_reportsOuterReferenceAndOuterConstructorParameter() {
        val shape = inspect(FleetOffice.Inner::class.java)

        assertThat(shape.hasOuterReference).isTrue()
        assertThat(shape.constructorParameterTypes).containsExactly(FleetOffice::class.java)
    }

    @Test
    @DisplayName("inspect_withNestedClassWithoutInner_reportsNoOuterReference")
    fun inspect_withNestedClassWithoutInner_reportsNoOuterReference() {
        val shape = inspect(FleetOffice.Nested::class.java)

        assertThat(shape.hasOuterReference).isFalse()
        assertThat(shape.constructorParameterTypes).isEmpty()
    }

    @Test
    @DisplayName("nested_isStaticByDefaultAndInnerOptsIn")
    fun nested_isStaticByDefaultAndInnerOptsIn() {
        // No static keyword exists in Kotlin: the plain nested class compiles to a static nested class.
        assertThat(Modifier.isStatic(FleetOffice.Nested::class.java.modifiers)).isTrue()
        assertThat(Modifier.isStatic(FleetOffice.Inner::class.java.modifiers)).isFalse()
        assertThat(FleetOffice().Inner().airline()).isEqualTo("Charter Air")
    }
}
