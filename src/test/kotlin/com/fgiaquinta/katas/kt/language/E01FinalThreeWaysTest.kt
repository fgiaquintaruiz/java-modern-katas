package com.fgiaquinta.katas.kt.language

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import java.lang.reflect.Modifier

open class Extensible {
    open fun greet(): String = "hello"
}

class E01FinalThreeWaysTest {

    @Test
    @DisplayName("hold_withValProperty_freezesReferenceNotContents")
    fun hold_withValProperty_freezesReferenceNotContents() {
        val holder = hold(mutableListOf("EC-A"))

        val field = Holder::class.java.getDeclaredField("registrations")
        field.isAccessible = true
        @Suppress("UNCHECKED_CAST")
        (field.get(holder) as MutableList<String>).add("EC-B")

        assertThat(Modifier.isFinal(field.modifiers)).isTrue()
        assertThat(field.get(holder) as List<*>).containsExactly("EC-A", "EC-B")
    }

    @Test
    @DisplayName("hold_returnsHolderThatIsFinalByDefault")
    fun hold_returnsHolderThatIsFinalByDefault() {
        val holder = hold(mutableListOf())

        // No modifier was written: Kotlin classes and methods are final unless declared open.
        assertThat(Modifier.isFinal(holder.javaClass.modifiers)).isTrue()
        assertThat(Modifier.isFinal(holder.javaClass.getDeclaredMethod("describe").modifiers)).isTrue()
    }

    @Test
    @DisplayName("hold_withConstInCompanion_exposesPublicStaticFinalField")
    fun hold_withConstInCompanion_exposesPublicStaticFinalField() {
        val holder = hold(mutableListOf())

        val field = holder.javaClass.getField("CAPACITY")

        assertThat(Modifier.isStatic(field.modifiers)).isTrue()
        assertThat(Modifier.isFinal(field.modifiers)).isTrue()
    }

    @Test
    @DisplayName("open_optsIntoInheritance")
    fun open_optsIntoInheritance() {
        assertThat(Modifier.isFinal(Extensible::class.java.modifiers)).isFalse()
        assertThat(Modifier.isFinal(Extensible::class.java.getDeclaredMethod("greet").modifiers)).isFalse()
    }
}
