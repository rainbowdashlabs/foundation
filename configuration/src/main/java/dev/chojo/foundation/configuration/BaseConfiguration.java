/*
 *     SPDX-License-Identifier: AGPL-3.0-only
 *
 *     Copyright (C) RainbowDashLabs and Contributor
 */

package dev.chojo.foundation.configuration;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import dev.chojo.foundation.configuration.exception.ConfigurationException;
import dev.chojo.foundation.system.SysVar;
import org.slf4j.Logger;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.slf4j.LoggerFactory.getLogger;

public abstract class BaseConfiguration<T> {
    private static final Logger log = getLogger(Configuration.class);
    protected final ObjectMapper objectMapper;
    protected T config;

    protected BaseConfiguration(T config, ObjectMapper mapper) {
        objectMapper = mapper;
        this.config = config;
    }

    protected BaseConfiguration(T config) {
        this(config, JsonMapper.builder()
                .configure(MapperFeature.ALLOW_FINAL_FIELDS_AS_MUTATORS, true)
                .build()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                .setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY)
                .setVisibility(PropertyAccessor.GETTER, JsonAutoDetect.Visibility.NONE)
                .setDefaultPrettyPrinter(new DefaultPrettyPrinter()));
    }

    public void reload() {
        try {
            reloadFile();
        } catch (IOException e) {
            log.info("Could not load config", e);
            throw new ConfigurationException("Could not load config file", e);
        }
        try {
            save();
        } catch (IOException e) {
            log.error("Could not save config.", e);
        }
    }

    private void save() throws IOException {
        try (var writer = objectMapper.writerWithDefaultPrettyPrinter().writeValues(getConfig().toFile())) {
            writer.write(config);
        }
    }

    private void reloadFile() throws IOException {
        forceConsistency();
        config = (T) objectMapper.readValue(getConfig().toFile(), config.getClass());
    }

    private void forceConsistency() throws IOException {
        Files.createDirectories(getConfig().getParent());
        if (!getConfig().toFile().exists()) {
            if (getConfig().toFile().createNewFile()) {
                save();
                throw new ConfigurationException("Please configure the config.");
            }
        }
    }

    private Path getConfig() {
        var home = new File(".").getAbsoluteFile().getParentFile().toPath();
        var variable = SysVar.envOrPropOrThrow("FOUNDATION_CONFIG", "foundation.config",
                () -> new ConfigurationException("Set property -Dfoundation.config=<config path> or environment variable FOUNDATION_CONFIG."));
        log.info("Found variable for config file");
        return Paths.get(home.toString(), variable);
    }

    public T config() {
        return config;
    }
}
