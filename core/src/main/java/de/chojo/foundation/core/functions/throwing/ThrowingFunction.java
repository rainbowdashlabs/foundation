/*
 *     SPDX-License-Identifier: AGPL-3.0-only
 *
 *     Copyright (C) RainbowDashLabs and Contributor
 */

package de.chojo.foundation.core.functions.throwing;

import java.util.function.Function;

public interface ThrowingFunction<T, R, E extends Throwable> {
    R apply(T t) throws E;
}
