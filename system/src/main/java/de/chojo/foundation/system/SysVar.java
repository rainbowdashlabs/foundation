/*
 *     SPDX-License-Identifier: AGPL-3.0-only
 *
 *     Copyright (C) RainbowDashLabs and Contributor
 */

package de.chojo.foundation.system;

import de.chojo.foundation.system.exception.UnknownVariableException;

import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

public class SysVar {
    public static <T extends Exception> String envOrPropOrThrow(String env, String prop, Supplier<T> throwableSupplier) throws T {
        return envOrProp(env, prop).orElseThrow(throwableSupplier);
    }

    public static <T extends Exception> String propOrEnvOrThrow(String property, String environment, Supplier<T> throwableSupplier) throws T {
        return propOrEnv(property, environment).orElseThrow(throwableSupplier);
    }

    public static <T extends Exception> String envOrPropOrThrow(String property, String environment) throws T {
        return envOrProp(property, environment).orElseThrow(() -> UnknownVariableException.forEnvOrProp(environment, property));
    }

    public static <T extends Exception> String propOrEnvOrThrow(String property, String environment) throws T {
        return propOrEnv(property, environment).orElseThrow(() -> UnknownVariableException.forEnvOrProp(environment, property));
    }

    public static String envOrProp(String env, String prop, String def) {
        return envOrProp(env, prop).orElse(def);
    }

    public static String propOrEnv(String prop, String env, String def) {
        return propOrEnv(prop, env).orElse(def);
    }

    public static Optional<String> envOrProp(String env, String prop) {
        return env(env).or(() -> prop(prop));
    }

    public static Optional<String> propOrEnv(String prop, String env) {
        return prop(prop).or(() -> env(env));
    }

    private static Optional<String> prop(String name) {
        return Optional.ofNullable(System.getProperty(name));
    }

    private static Optional<String> env(String name) {
        return Optional.ofNullable(System.getenv(name));
    }

    public static Optional<String> get(List<Supplier<Optional<String>>> suppliers) {
        for (Supplier<Optional<String>> supplier : suppliers) {
            Optional<String> opt = supplier.get();
            if (opt.isPresent()) return opt;
        }
        return Optional.empty();
    }

}
