package com.fgiaquinta.katas.kt.language

class FleetOffice {
    val airline: String = "Charter Air"

    class Nested {
        fun name(): String = "nested"
    }

    inner class Inner {
        fun airline(): String = airline
    }
}

data class Shape(val hasOuterReference: Boolean, val constructorParameterTypes: List<Class<*>>)

/**
 * Exercise E02 (Kotlin twin) — Nested versus inner class.
 *
 * Statement: Given a class, return a [Shape] saying whether it holds a hidden reference to an enclosing
 * instance, and the parameter types of its first declared constructor. Use [FleetOffice.Nested] and
 * [FleetOffice.Inner] to see the two cases.
 *
 * Concepts: in Kotlin a nested class is static by default and `inner` opts in, the opposite of Java;
 * the compiled inner class still carries the synthetic `this$0` field; Class.declaredFields,
 * Field.isSynthetic and Class.declaredConstructors work on Kotlin classes too.
 *
 * Internals: TODO: fill after solving.
 *
 * Anchor: TODO: your own image, written by you.
 */
fun inspect(type: Class<*>): Shape = TODO("Not solved yet")
