package com.bomber7.core.map;

import com.bomber7.core.model.map.LevelMap;
import com.bomber7.core.model.map.LevelMapFactory;
import com.bomber7.core.model.square.Square;
import com.opencsv.CSVReader;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class LevelMapFactoryTest {

    private final String foyMapFileName = "foy";

    private final Path tilesetJsonPath = Paths.get("../assets/textures/tileset.tsj");
    private final Map<Integer, String> textureMap = LevelMapFactory.parseTextureMap(tilesetJsonPath);

    private final String foyMapName = "foy";
    private final File foyDirectory = new File("../assets/maps/" + foyMapName);
    private final LevelMapFactory levelMapFactory = new LevelMapFactory(tilesetJsonPath);

    @Test
    void testSearchMapFilesRootDirectory_Found() {
        File result = LevelMapFactory.searchMapFilesRootDirectory(foyMapFileName);
        assertNotNull(result);
        assertEquals(foyMapFileName, result.getName());
        assertTrue(result.isDirectory());
    }

    @Test
    void testSearchMapFilesRootDirectory_NotFound() {
        File result = LevelMapFactory.searchMapFilesRootDirectory("nonexistent_dir_1234");
        assertNull(result);
    }

    @Test
    void test_createLevelMap_OK() {
        LevelMap levelMap = levelMapFactory.createLevelMap("foy");
        System.out.println(levelMap.toString());
    }

    @Test
    void test_createLevelMap_nonExistentDirectory() {
        assertThrows(IllegalArgumentException.class, () -> {
            levelMapFactory.createLevelMap("non_existent_map");
        });
    }

    @Test
    void test_createLevelMap_emptyDirectory() {
        String dirName = "emptyDirectory";
        String dirPathName = "../assets/maps/" + dirName;
        File dir = new File(dirPathName);
        dir.mkdir();
        try {
            assertThrows(IllegalArgumentException.class, () -> {
                levelMapFactory.createLevelMap(dirName);
            });
        } finally {
            // Clean up the directory after the test
            if (dir.exists()) {
                dir.delete();
            }
        }
    }

    @Test
    void test_createLevelMap_MoreThanOneBackgroundCsv() throws IOException {
        File dummyFile = new File(foyDirectory, "SecondBackground.csv");
        assertTrue(dummyFile.createNewFile(), "Failed to create dummy background CSV");

        try {
            assertThrows(IllegalArgumentException.class, () -> {
                levelMapFactory.createLevelMap(foyMapName);
            });
        } finally {
            assertTrue(dummyFile.delete(), "Failed to delete dummy file");
        }
    }


    @Test
    void test_createLevelMap_MoreThanOneUnbreakableCsv() throws IOException {
        File dummyFile = new File(foyDirectory, "SecondUnbreakable.csv");
        assertTrue(dummyFile.createNewFile(), "Failed to create dummy background CSV");

        try {
            assertThrows(IllegalArgumentException.class, () -> {
                levelMapFactory.createLevelMap(foyMapName);
            });
        } finally {
            assertTrue(dummyFile.delete(), "Failed to delete dummy file");
        }
    }

    @Test
    void test_createLevelMap_MoreThanOneBreakableCsv() throws IOException {
        File dummyFile = new File(foyDirectory, "SecondBreakable.csv");
        assertTrue(dummyFile.createNewFile(), "Failed to create dummy background CSV");

        try {
            assertThrows(IllegalArgumentException.class, () -> {
                levelMapFactory.createLevelMap(foyMapName);
            });
        } finally {
            assertTrue(dummyFile.delete(), "Failed to delete dummy file");
        }
    }

    @Test
    void test_createLevelMap_MoreThan3CsvsButDifferentBackBreakUnBreak_OK() throws IOException {
        File dummyFile = new File(foyDirectory, "Test.csv");
        assertTrue(dummyFile.createNewFile(), "Failed to create dummy background CSV");

        try {
            LevelMap levelMap = levelMapFactory.createLevelMap(foyMapName);
            Square square = levelMap.getSquare(5,2);
            // id = 33
            assertEquals(Paths.get("assets/textures/images/spruce_planks.png"), square.getTextureFilePath());

            // id = 44
            assertEquals(Paths.get("assets/textures/images/pressure_plate.png"), square.getMapElement().getTextureFilePath());
        } finally {
            assertTrue(dummyFile.delete(), "Failed to delete dummy file");
        }
    }

    @Test
    void test_parseTextureMap_success() throws IOException {
        // Create a temporary JSON file with tile data
        String jsonContent = "{\n" +
            "  \"tiles\": [\n" +
            "    { \"id\": 0, \"image\": \"tiles/grass.png\" },\n" +
            "    { \"id\": 1, \"image\": \"tiles/wall.png\" }\n" +
            "  ]\n" +
            "}";

        Path tempFile = Files.createTempFile("tileset", ".json");
        Files.writeString(tempFile, jsonContent);

        // Act
        Map<Integer, String> result = LevelMapFactory.parseTextureMap(tempFile);

        // Assert
        assertEquals(2, result.size());
        assertEquals("tiles/grass.png", result.get(0));
        assertEquals("tiles/wall.png", result.get(1));

        Files.deleteIfExists(tempFile); // cleanup
    }

    @Test
    void test_parseTextureMap_fileNotFound() {
        Path fakePath = Paths.get("nonexistent/path/to/file.json");

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            LevelMapFactory.parseTextureMap(fakePath);
        });

        assertTrue(exception.getMessage().contains("Failed to parse texture JSON"));
    }

    @Test
    void test_parseTextureMap_malformedJson() throws IOException {
        Path tempFile = Files.createTempFile("badjson", ".json");
        Files.writeString(tempFile, "{ invalid json ]");

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            LevelMapFactory.parseTextureMap(tempFile);
        });

        assertTrue(exception.getMessage().contains("Failed to parse texture JSON"));

        Files.deleteIfExists(tempFile); // cleanup
    }

    @Test
    void test_parseCsv_throwsOnCsvNotRectangular() throws IOException {
        String[][] background = { {"1", "2"}, {"3", "4"} };
        String[][] breakable = { {"-1", "-1"} }; // Fewer rows
        String[][] unbreakable = { {"-1", "-1"}, {"-1", "-1"} };

        File f1 = writeCsv("bga", background);
        File f2 = writeCsv("bra", breakable);
        File f3 = writeCsv("uba", unbreakable);

        assertThrows(IllegalArgumentException.class, () ->
            LevelMapFactory.parseCsv(f1, f2, f3, Map.of())
        );
    }

    @Test
    void test_parseCsv_throwsOnColumnMismatch() throws IOException {
        String[][] background = { {"1", "2"}, {"3", "4"} };
        String[][] breakable = { {"-1", "-1"}, {"5"} }; // Row has fewer columns
        String[][] unbreakable = { {"-1", "-1"}, {"-1", "-1"} };

        File f1 = writeCsv("bga", background);
        File f2 = writeCsv("bra", breakable);
        File f3 = writeCsv("uba", unbreakable);

        assertThrows(IllegalArgumentException.class, () ->
            LevelMapFactory.parseCsv(f1, f2, f3, Map.of())
        );
    }

    @Test
    void test_parseCsv_missingTextureForBackgroundInTileset() throws IOException {
        String[][] background = { {"33"} };
        String[][] breakable = { {"-1"} };
        String[][] unbreakable = { {"-1"} };

        File f1 = writeCsv("bga", background);
        File f2 = writeCsv("bra", breakable);
        File f3 = writeCsv("uba", unbreakable);

        // Create levelMap with an Empty texture map (Map.of()), should throw an error
        assertThrows(IllegalArgumentException.class, () ->
            LevelMapFactory.parseCsv(f1, f2, f3, Map.of())
        );
    }

    @Test
    void test_parseCsv_BackgroundWithTextureOK() throws IOException {
        String[][] background = { {"33"} };
        String[][] breakable = { {"-1"} };
        String[][] unbreakable = { {"-1"} };

        File f1 = writeCsv("bga", background);
        File f2 = writeCsv("bra", breakable);
        File f3 = writeCsv("uba", unbreakable);

        List<List<Square>> levelMap = LevelMapFactory.parseCsv(f1, f2, f3, this.textureMap);

        System.out.println(levelMap);
        assertEquals(33, levelMap.get(0).get(0).getTextureId());
        assertEquals(Paths.get("assets/textures/images/spruce_planks.png"), levelMap.get(0).get(0).getTextureFilePath());
    }


    @Test
    void test_parseCsv_backgroundTextureIds_match() throws Exception {
        String mapName = "foy";
        LevelMap levelMap = levelMapFactory.createLevelMap(mapName);

        File backgroundCsv = new File("../assets/maps/" + mapName + "/le_foy_Background.csv");
        assertTrue(backgroundCsv.exists(), "Background CSV does not exist!");

        try (CSVReader reader = new CSVReader(new FileReader(backgroundCsv))) {
            List<String[]> rows = reader.readAll();

            assertEquals(rows.size(), levelMap.getHeight(), "Number of rows mismatch");

            for (int i = 0; i < rows.size(); i++) {
                String[] cols = rows.get(i);
                assertEquals(cols.length, levelMap.getWidth(), "Number of columns mismatch at row " + i);

                for (int j = 0; j < cols.length; j++) {
                    System.out.println("i[" + i + "] = " + "j[" + j + "]");
                    int expectedTextureId = Integer.parseInt(cols[j].trim());
                    System.err.println("Found texture id " + expectedTextureId);
                    Square actualSquare = levelMap.getSquare(j,i);
                    int actualTextureId = actualSquare.getTextureId();
                    System.err.println(actualSquare);
                    boolean verticalFlip = actualSquare.isVerticalFlip();
                    boolean horizontalFlip = actualSquare.isHorizontalFlip();
                    boolean diagonalFlip = actualSquare.isDiagonalFlip();

                    if (horizontalFlip) {
                        actualTextureId |= 0x80000000;
                    }
                    if (verticalFlip) {
                        actualTextureId |= 0x40000000;
                    }
                    if (diagonalFlip) {
                        actualTextureId |= 0x20000000;
                    }

                    assertEquals(expectedTextureId, actualTextureId,
                        String.format("Mismatch at [%d,%d]: expected %d but got %d", i, j, expectedTextureId, actualTextureId));
                }
            }
        }
    }















    private File writeCsv(String prefix, String[][] data) throws IOException {
        File file = File.createTempFile(prefix, ".csv");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (String[] row : data) {
                writer.write(String.join(",", row));
                writer.newLine();
            }
        }
        return file;
    }



}
