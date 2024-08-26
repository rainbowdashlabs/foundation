package de.chojo.foundation.system.exception;

public class UnknownVariableException extends RuntimeException {
    public UnknownVariableException(String message) {
        super(message);
    }

    public static UnknownVariableException forProp(String prop) {
        return new UnknownVariableException("Property %s is not set".formatted(prop));
    }

    public static UnknownVariableException forEnv(String env) {
        return new UnknownVariableException("Environment variable %s is not set".formatted(env));
    }

    public static UnknownVariableException forEnvOrProp(String env, String prop) {
        return new UnknownVariableException("Neither environment variable %s nor prop %s are set".formatted(env, prop));
    }
}
