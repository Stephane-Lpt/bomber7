package com.bomber7.utils;

import org.junit.jupiter.api.*;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

@Disabled("Ne fonctionne pas en CI")
class ProjectPathsTest {

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
