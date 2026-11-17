package com.fgiaquinta.katas.collections;

import java.util.Objects;

/**
 * Exercise D08 — Lookup after a key mutates.
 *
 * <h2>Statement</h2>
 * Register a {@link MutableRoute} in a hash-based set, then rename the route to {@code newCode}. Return
 * whether the registry still finds the route after the rename. The route must end up renamed, and the
 * answer must be {@code true}.
 *
 * <h2>Concepts</h2>
 * {@code HashSet}/{@code HashMap} place an entry in a bucket by the hash at insertion time; the equals/hashCode contract; mutating a field used by {@code hashCode} strands the entry; defensive immutable keys (a {@code record}).
 *
 * <h2>Internals</h2>
 * TODO: fill after solving.
 * <h2>Anchor</h2>
 * TODO: your own image, written by you.
 */
public final class D08MutableKeyLookup {

    private D08MutableKeyLookup() {
    }

    /** Mutable on purpose: equals and hashCode depend on a field that has a setter. */
    public static final class MutableRoute {
        private String code;

        public MutableRoute(String code) {
            this.code = code;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        @Override
        public boolean equals(Object other) {
            return other instanceof MutableRoute route && Objects.equals(code, route.code);
        }

        @Override
        public int hashCode() {
            return Objects.hash(code);
        }
    }

    public static boolean containsAfterRename(MutableRoute route, String newCode) {
        throw new UnsupportedOperationException("Not solved yet");
    }
}
