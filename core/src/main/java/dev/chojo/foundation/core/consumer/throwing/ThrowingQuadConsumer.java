/*
 *     SPDX-License-Identifier: AGPL-3.0-only
 *
 *     Copyright (C) RainbowDashLabs and Contributor
 */

package dev.chojo.foundation.core.consumer.throwing;

/**
 * Represents an operation that accepts four input arguments and returns no
 * result. Unlike most other functional interfaces, {@code Consumer} is expected
 * to operate via side-effects.
 *
 * <p>This is a <a href="package-summary.html">functional interface</a>
 * whose functional method is {@link #accept(Object, Object, Object, Object)}.
 *
 * @param <T> the type of the first input to the operation
 * @param <U> the type of the second input to the operation
 * @param <V> the type of the third input to the operation
 * @param <W> the type of the fourth input to the operation
 * @param <Exception> the type of the exception that might be thrown
 * @since 1.0
 */
@FunctionalInterface
public interface ThrowingQuadConsumer<T, U, V, W, Exception extends Throwable> {

    /**
     * Performs this operation on the given arguments.
     *
     * @param t first input argument
     * @param u second input argument
     * @param v third input argument
     * @param w fourth input argument
     * @throws Exception exception that might be thrown
     */
    void accept(T t, U u, V v, W w) throws Exception;
}
