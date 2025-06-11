package com.bomber7.core;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.bomber7.utils.Config;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * A singleton manager responsible for loading, saving, and managing the user's configuration.
 * <p>
 * The configuration is saved locally using LibGDX's {@link FileHandle}, and is serialized via Java's
 * {@link ObjectOutputStream}. This manager provides access to both the persistent config and a
 * modifiable version that can be saved or discarded.
 * </p>
 */
public final class ConfigManager {
    /**
     * Path to the configuration file stored locally.
     */
    private static final String CONFIG_PATH = "config.dat"; // or config.json if using Json
    /**
     * Reference to the local file where the configuration is saved.
     * <p>
     * Uses LibGDX's {@link com.badlogic.gdx.files.FileHandle} to access the file
     * in a platform-independent way.
     * </p>
     */
    private static final FileHandle CONFIG_FILE = Gdx.files.local(CONFIG_PATH);

    /**
     * The singleton instance of the ConfigManager.
     */
    private static ConfigManager instance;
    /**
     * Config instance that isn't supposed to be modified (unless saved).
     */
    private Config config;
    /**
     *
     */
    private Config modifiableConfig;

    /**
     * Private constructor to enforce singleton pattern.
     */
    private ConfigManager() {
        super();
    }

    /**
     * Retrieves the singleton instance of the {@code ConfigManager}.
     *
     * @return the single {@code ConfigManager} instance.
     */
    public static ConfigManager getInstance() {
        if (instance == null) {
            instance = new ConfigManager();
        }
        return instance;
    }

    /**
     * Initializes the ConfigManager manager.
     * <p>
     * This method must be called before any config access occurs.
     * </p>
     *
     */
    public void initialize() {
        load();
        resetModifications();
    }

    /**
     * Setups the config from a CONFIG_FILE or with a default config.
     * <p>
     *     If a CONFIG_FILE already exists on user's device, it loads it the config.
     *     If an error occurs while parsing the CONFIG_FILE, it falls back to the default config.
     *     If no CONFIG_FILE exists, we set up the default config.
     * </p>
     */
    private void load() {
        if (CONFIG_FILE.exists()) {
            Gdx.app.debug("ConfigManager", "Loading config from " + CONFIG_FILE);
            try (ObjectInputStream in = new ObjectInputStream(CONFIG_FILE.read())) {
                config = (Config) in.readObject();
            } catch (IOException | ClassNotFoundException e) {
                Gdx.app.debug("ConfigManager", "Failed loading config from " + CONFIG_FILE + ". Falling back to default config.");
                config = new Config();
            }
        } else {
            Gdx.app.debug("ConfigManager", "No " + CONFIG_FILE + " found. Falling back to default config.");
            config = new Config();
        }
    }

    /**
     * Saves the current config into CONFIG_FILE.
     */
    public void save() {
        try (ObjectOutputStream out = new ObjectOutputStream(CONFIG_FILE.write(false))) {
            out.writeObject(config);
            Gdx.app.debug("ConfigManager", "Saving current config to " + CONFIG_FILE);
        } catch (IOException e) {
            Gdx.app.error("ConfigManager", "Could not save the config", e);
        }
    }

    /**
     * A getter for the {@code modifiableConfig} attribute.
     * @return the current modifiableConfig attribute.
     */
    public Config getConfig() {
        return modifiableConfig;
    }

    /**
     * Saves the modifications made to {@code modifiableConfig} into {@code config}.
     * Saves the config file to user's local directory and resets the modifications made to {@code modifiableConfig}.
     */
    public void saveModifications() {
        this.config = new Config(modifiableConfig);
        save();
        resetModifications();
    }

    /**
     * Discard the changes made in the unsavedConfig.
     */
    public void resetModifications() {
        this.modifiableConfig = new Config(config);
    }

    /**
     * Indicates whether there is unsaved changes.
     * @return {@code true} if {@code modifiableConfig} does not have the same config as {@code config},
     * {@code false} if they are the same.
     */
    public boolean isUnsaved() {
        return !config.equals(modifiableConfig);
    }
}
