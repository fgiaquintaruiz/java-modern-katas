package com.fgiaquinta.katas.language;

import com.fgiaquinta.katas.language.E01FinalThreeWays.Holder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class E01FinalThreeWaysTest {

    @Test
    @DisplayName("the registrations field is final, yet the list it points to can still grow")
    @SuppressWarnings("unchecked")
    void hold_withFinalField_freezesReferenceNotContents() throws Exception {
        List<String> source = new ArrayList<>(List.of("EC-A"));
        Holder holder = E01FinalThreeWays.hold(source);

        Field field = Holder.class.getDeclaredField("registrations");
        field.setAccessible(true);
        List<String> inside = (List<String>) field.get(holder);
        inside.add("EC-B");

        assertThat(Modifier.isFinal(field.getModifiers())).isTrue();
        assertThat((List<String>) field.get(holder)).containsExactly("EC-A", "EC-B");
    }

    @Test
    @DisplayName("the holder class is final, so it cannot be extended")
    void hold_returnsHolderOfFinalClass() {
        Holder holder = E01FinalThreeWays.hold(new ArrayList<>());

        assertThat(Modifier.isFinal(holder.getClass().getModifiers())).isTrue();
    }

    @Test
    @DisplayName("describe is a final method, so it cannot be overridden")
    void hold_returnsHolderWithFinalDescribeMethod() throws Exception {
        Holder holder = E01FinalThreeWays.hold(new ArrayList<>());

        Method describe = holder.getClass().getDeclaredMethod("describe");

        assertThat(Modifier.isFinal(describe.getModifiers())).isTrue();
        assertThat(describe.getReturnType()).isEqualTo(String.class);
    }
}
