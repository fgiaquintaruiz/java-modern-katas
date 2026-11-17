package com.fgiaquinta.katas.language;

import com.fgiaquinta.katas.domain.Aircraft;
import com.fgiaquinta.katas.domain.AircraftCategory;
import com.fgiaquinta.katas.domain.EngineType;
import com.fgiaquinta.katas.language.E04ImmutableFleet.Fleet;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.fgiaquinta.katas.domain.TestData.aircraft;
import static org.assertj.core.api.Assertions.assertThat;

class E04ImmutableFleetTest {

    private final Aircraft a1 = aircraft("EC-001", AircraftCategory.REGIONAL, EngineType.PISTON, 11, 1000, "30.00");
    private final Aircraft a2 = aircraft("EC-002", AircraftCategory.NARROW_BODY, EngineType.TURBOFAN, 180, 1200, "35.00");

    @Test
    @DisplayName("changing the list passed to the constructor does not change the fleet")
    void create_thenMutatingSourceList_doesNotAffectFleet() {
        List<Aircraft> source = new ArrayList<>(List.of(a1));
        Fleet fleet = E04ImmutableFleet.create("Charter Air", source);

        source.add(a2);
        source.set(0, a2);

        assertThat(fleet.aircraft()).containsExactly(a1);
    }

    @Test
    @DisplayName("changing the list returned by the fleet does not change the fleet")
    void aircraft_thenMutatingReturnedList_doesNotAffectFleet() {
        Fleet fleet = E04ImmutableFleet.create("Charter Air", new ArrayList<>(List.of(a1)));

        List<Aircraft> returned = fleet.aircraft();
        try {
            returned.add(a2);
        } catch (UnsupportedOperationException acceptedWayOfRefusing) {
            // An unmodifiable result is as good as a copy.
        }

        assertThat(fleet.aircraft()).containsExactly(a1);
        assertThat(fleet.owner()).isEqualTo("Charter Air");
    }

    @Test
    @DisplayName("the class is final, every instance field is private final and there are no setters")
    void create_returnsFleetWithFinalClassPrivateFinalFieldsAndNoSetters() {
        Fleet fleet = E04ImmutableFleet.create("Charter Air", List.of(a1));
        Class<?> type = fleet.getClass();

        List<Field> fields = Arrays.stream(type.getDeclaredFields())
                .filter(f -> !f.isSynthetic() && !Modifier.isStatic(f.getModifiers()))
                .toList();

        assertThat(Modifier.isFinal(type.getModifiers())).isTrue();
        assertThat(fields).isNotEmpty();
        assertThat(fields).allSatisfy(f -> {
            assertThat(Modifier.isPrivate(f.getModifiers())).isTrue();
            assertThat(Modifier.isFinal(f.getModifiers())).isTrue();
        });
        assertThat(Arrays.stream(type.getDeclaredMethods()).map(Method::getName)).noneMatch(n -> n.startsWith("set"));
    }
}
