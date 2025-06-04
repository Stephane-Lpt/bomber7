package com.bomber7.utils;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Utility class for managing project paths.
 */
public final class ProjectPaths {
    /**
     * The root path of the project, determined by the current working directory.
     * This is used to construct paths to various resources within the project.
     */
    private static final Path PROJECT_ROOT = Paths.get(System.getProperty("user.dir"));

    private ProjectPaths() {
        throw new UnsupportedOperationException("Utility class for project paths, cannot be instantiated.");
    }

    /**
     * Returns the path to the assets directory.
     * If the project is running from the core module, it adjusts the path accordingly.
     *
     * @return Path to the assets directory
     */
    public static Path getAssetsPath() {
        if (PROJECT_ROOT.toString().contains("core")) {
            // If running from the core module, we need to go up one level to find the assets
            return Paths.get(PROJECT_ROOT + "/../assets");
        } else {
            return Paths.get(PROJECT_ROOT + "/assets");
        }
    }

    /**
     * Returns the path to the maps directory within the assets.
     *
     * @return Path to the maps directory
     */
    public static Path getMapDir() {
        System.out.println("Assets path: " + getAssetsPath());
        return Paths.get(getAssetsPath() + "/maps");
    }

    /**
     * Returns the path to a texture image by its name.
     * The texture is expected to be in the "textures/images" directory within the assets.
     *
     * @param name The name of the texture (without file extension)
     * @return Path to the texture image
     */
    public static Path getTexture(String name) {
        System.out.println("Assets path: " + getAssetsPath());
        return Paths.get(getAssetsPath() + "/textures/images/" + name + ".png");
    }

    /**
     * Returns the path to the tileset texture file.
     * The tileset is expected to be in the "textures" directory within the assets.
     *
     * @return Path to the tileset texture json file
     */
    public static Path getTileset() {
        return Paths.get(getAssetsPath() + "/textures/tileset.tsj");
    }
}
