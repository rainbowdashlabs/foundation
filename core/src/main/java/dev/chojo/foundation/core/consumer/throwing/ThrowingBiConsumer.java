/*
 *     SPDX-License-Identifier: AGPL-3.0-only
 *
 *     Copyright (C) RainbowDashLabs and Contributor
 */

package dev.chojo.foundation.core.consumer.throwing;

/**
 * Represents an operation that accepts a single input argument and returns no
 * result. Unlike most other functional interfaces, {@code Consumer} is expected
 * to operate via side-effects.
 *
 * <p>This is a <a href="package-summary.html">functional interface</a>
 * whose functional method is {@link #accept(Object, Object)}.
 *
 * @param <T> the type of the first input to the operation
 * @param <U> the type of the second input to the operation
 * @param <Exception> the type of the exception that might be thrown
 * @since 1.0
 */
@FunctionalInterface
public interface ThrowingBiConsumer<T, U, Exception extends Throwable> {

    /**
     * Performs this operation on the given arguments.
     *
     * @param t first input argument
     * @param u second input argument
     */
    void accept(T t, U u) throws Exception;
}
