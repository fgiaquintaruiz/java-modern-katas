package com.fgiaquinta.katas.functional.methodref;

import com.fgiaquinta.katas.domain.Branch;
import com.fgiaquinta.katas.domain.Vehicle;

import java.util.List;

/**
 * Exercise M06 — Vehicles in branch fleet.
 *
 * <h2>Statement</h2>
 * Keep only the candidates that belong to the branch fleet, in input order. A bound instance method
 * reference on the branch fleet is REQUIRED as the predicate; a lambda is not accepted.
 *
 * <h2>Concepts</h2>
 * bound instance method reference on an expression result ({@code branch.fleet()::contains}).
 *
 * <h2>Internals</h2>
 * TODO after solving: explain what happens inside the pipeline, not what the code does.
 * <h2>Anchor</h2>
 * TODO after solving: one sentence, your own image from a world you know, that ties this operation to its mechanism.
 */
public final class M06VehiclesInBranchFleet {

    private M06VehiclesInBranchFleet() {
    }

    public static List<Vehicle> inFleet(List<Vehicle> candidates, Branch branch) {
        throw new UnsupportedOperationException("Not solved yet");
    }
}
