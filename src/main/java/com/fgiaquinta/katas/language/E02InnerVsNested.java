package com.fgiaquinta.katas.language;

import java.util.List;

/**
 * Exercise E02 — Static nested versus inner class.
 *
 * <h2>Statement</h2>
 * Given a class, return a {@link Shape} saying whether it holds a hidden reference to an enclosing
 * instance, and the parameter types of its first declared constructor. Use {@link Nested} and
 * {@link Inner} to see the two cases.
 *
 * <h2>Concepts</h2>
 * A static nested class is a plain class in a namespace; an inner class carries a synthetic field {@code this$0} and a constructor that takes the outer instance; {@code Field.isSynthetic}, {@code Class.getDeclaredFields}, {@code Class.getDeclaredConstructors}; since JDK 18 javac drops {@code this$0} when the inner class never uses the outer instance, which is why {@link Inner} reads {@code airline}.
 *
 * <h2>Internals</h2>
 * TODO: fill after solving.
 * <h2>Anchor</h2>
 * TODO: your own image, written by you.
 */
public final class E02InnerVsNested {

    private final String airline = "Charter Air";

    E02InnerVsNested() {
    }

    public static class Nested {
        public String name() {
            return "nested";
        }
    }

    public class Inner {
        public String airline() {
            return airline;
        }
    }

    public record Shape(boolean hasOuterReference, List<Class<?>> constructorParameterTypes) {
    }

    public static Shape inspect(Class<?> type) {
        throw new UnsupportedOperationException("Not solved yet");
    }
}
