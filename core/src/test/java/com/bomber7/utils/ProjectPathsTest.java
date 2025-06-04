package com.bomber7.utils;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Disabled("Ne fonctionne pas en CI")
public class ProjectPathsTest {

    /**
     * The original user directory before any tests are run.
     * This is used to restore the user directory after each test.
     */
    private static String originalUserDir;

    @BeforeAll
    static void saveOriginalUserDir() {
        originalUserDir = System.getProperty("user.dir");
    }

    @AfterEach
    void restoreUserDir() {
        System.setProperty("user.dir", originalUserDir);
    }

    @Test
    void testGetAssetsPathFromRoot() {
        Path projectRoot = Paths.get(System.getProperty("user.dir"));
        Path expected = projectRoot.resolve("../assets");
        Path actual = ProjectPaths.getAssetsPath();

        assertEquals(expected.normalize(), actual.normalize());
    }

    @Test
    void testGetAssetsPathFromCore() {
        System.setProperty("user.dir", "/home/aug/code/bomber7/core");

        Path expected = Paths.get("/home/aug/code/bomber7/assets");
        Path actual = ProjectPaths.getAssetsPath();

        assertEquals(expected.normalize(), actual.normalize());
    }

    @Test
    void testGetMapDir() {
        System.setProperty("user.dir", "/home/aug/code/bomber7");

        Path expected = Paths.get("/home/aug/code/bomber7/assets/maps");
        Path actual = ProjectPaths.getMapDir();

        assertEquals(expected.normalize(), actual.normalize());
    }

    @Test
    void testGetTexture() {
        System.setProperty("user.dir", "/home/aug/code/bomber7");

        Path expected = Paths.get("/home/aug/code/bomber7/assets/textures/images/bomb.png");
        Path actual = ProjectPaths.getTexture("bomb");

        assertEquals(expected.normalize(), actual.normalize());
    }
}
