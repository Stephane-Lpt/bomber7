package com.bomber7.core;

import com.bomber7.core.model.LevelMap;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LevelMapTest {

    // TODO: Make tests for LevelMap
    @Test
    void test_LevelMapCreation() {
        Path backgroundCsvFilePath = Paths.get("../assets/maps/foy/le_foy_Background.csv");
        Path breakableCsvFilePath = Paths.get("../assets/maps/foy/le_foy_Breakable.csv");
        Path unbreakableCsvFilePath = Paths.get("../assets/maps/foy/le_foy_Unbreakable.csv");
        LevelMap levelMap = new LevelMap(
            backgroundCsvFilePath.toAbsolutePath().toString(),
            breakableCsvFilePath.toAbsolutePath().toString(),
            unbreakableCsvFilePath.toAbsolutePath().toString());

        System.out.println(levelMap.toString());
    }
}
