package com.bomber7.utils;

import java.nio.file.Path;
import java.nio.file.Paths;

public class ProjectPaths {
    private static final Path PROJECT_ROOT = Paths.get(System.getProperty("user.dir"));

    public static Path getAssetsPath() {
        if (PROJECT_ROOT.toString().contains("core")) {
            // If running from the core module, we need to go up one level to find the assets
            return Paths.get(PROJECT_ROOT + "/../assets");
        }
        else {
            return Paths.get(PROJECT_ROOT + "/assets");
        }
    }

    public static Path getMapDir() {
        System.out.println("Assets path: " + getAssetsPath());
        return Paths.get(getAssetsPath() + "/maps");
    }

    public static Path getTexture(String name) {
        System.out.println("Assets path: " + getAssetsPath());
        return Paths.get(getAssetsPath() + "/textures/images/" + name + ".png");
    }

    public static Path getTileset() {
        return Paths.get(getAssetsPath() + "/textures/tileset.tsj");
    }
}
