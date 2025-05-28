package com.bomber7.core;

import com.bomber7.core.model.map.LevelMap;
import com.bomber7.core.model.map.LevelMapFactory;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LevelMapTest {

    private final Path jsonPath = Paths.get("../assets/textures/tileset.tsj");
    private final LevelMapFactory levelMapFactory = new LevelMapFactory(jsonPath);


    // TODO: Make tests for LevelMap
    @Test
    void test_LevelMapCreation() {
        LevelMap levelMap = levelMapFactory.createLevelMap("foy");
        System.out.println(levelMap.toString());
    }
}
