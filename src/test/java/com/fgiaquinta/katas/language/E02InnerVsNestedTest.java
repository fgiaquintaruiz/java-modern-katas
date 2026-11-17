package com.fgiaquinta.katas.language;

import com.fgiaquinta.katas.language.E02InnerVsNested.Inner;
import com.fgiaquinta.katas.language.E02InnerVsNested.Nested;
import com.fgiaquinta.katas.language.E02InnerVsNested.Shape;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Modifier;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class E02InnerVsNestedTest {

    @Test
    @DisplayName("an inner class holds a synthetic outer reference and its constructor takes the outer instance")
    void inspect_withInnerClass_reportsOuterReferenceAndOuterConstructorParameter() {
        Shape shape = E02InnerVsNested.inspect(Inner.class);

        assertThat(shape.hasOuterReference()).isTrue();
        assertThat(shape.constructorParameterTypes()).containsExactly(E02InnerVsNested.class);
    }

    @Test
    @DisplayName("a static nested class holds no outer reference and has a no-argument constructor")
    void inspect_withStaticNestedClass_reportsNoOuterReference() {
        Shape shape = E02InnerVsNested.inspect(Nested.class);

        assertThat(shape.hasOuterReference()).isFalse();
        assertThat(shape.constructorParameterTypes()).isEmpty();
    }

    @Test
    @DisplayName("demonstration: an inner class cannot be built without an outer instance")
    void inner_withoutOuterInstance_hasNoNoArgConstructor() {
        assertThatThrownBy(() -> Inner.class.getDeclaredConstructor()).isInstanceOf(NoSuchMethodException.class);
        assertThat(new E02InnerVsNested().new Inner().airline()).isEqualTo("Charter Air");
        assertThat(Modifier.isStatic(Nested.class.getModifiers())).isTrue();
        assertThat(Modifier.isStatic(Inner.class.getModifiers())).isFalse();
    }
}
