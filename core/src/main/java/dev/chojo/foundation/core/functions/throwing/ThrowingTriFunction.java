/*
 *     SPDX-License-Identifier: AGPL-3.0-only
 *
 *     Copyright (C) RainbowDashLabs and Contributor
 */

package dev.chojo.foundation.core.functions.throwing;

public interface ThrowingTriFunction<T, U, V, R, E extends Throwable> {
    R apply(T t, U u, V v) throws E;
}
