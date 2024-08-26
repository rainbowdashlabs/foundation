/*
 *     SPDX-License-Identifier: AGPL-3.0-only
 *
 *     Copyright (C) RainbowDashLabs and Contributor
 */

package de.chojo.foundation.core.functions.throwing;

public interface ThrowingQuadFunction<T, U, V, W, R, E extends Throwable> {
    R apply(T t, U u, V v, W w) throws E;
}
