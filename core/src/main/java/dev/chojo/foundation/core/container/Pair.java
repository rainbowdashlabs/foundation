/*
 *     SPDX-License-Identifier: AGPL-3.0-only
 *
 *     Copyright (C) RainbowDashLabs and Contributor
 */

package dev.chojo.foundation.core.container;

/**
 * Contains an immutable pair of two values
 *
 * @param <A> value A
 * @param <B> value B
 * @since 1.0.0
 */
public record Pair<A, B>(A first, B second) {
    /**
     * Create a new pair.
     *
     * @param x   first value
     * @param y   second value
     * @param <X> type of first value
     * @param <Y> type of second value
     * @return new pair
     */
    public static <X, Y> Pair<X, Y> of(X x, Y y) {
        return new Pair<>(x, y);
    }

}
