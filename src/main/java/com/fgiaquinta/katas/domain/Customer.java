package com.fgiaquinta.katas.domain;

/**
 * A customer of the rental business.
 *
 * @param id             unique customer identifier
 * @param name            full name
 * @param country         customer's home country
 * @param loyaltyMember   whether the customer is enrolled in the loyalty program
 */
public record Customer(
        String id,
        String name,
        String country,
        boolean loyaltyMember
) {
}
