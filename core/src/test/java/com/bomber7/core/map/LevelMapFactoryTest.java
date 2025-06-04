package com.bomber7.core.map;

import com.bomber7.core.model.map.LevelMap;
import com.bomber7.core.model.map.LevelMapFactory;
import com.bomber7.core.model.square.MapElement;
import com.bomber7.core.model.square.Square;
import com.bomber7.utils.ProjectPaths;
import com.opencsv.CSVReader;
import org.junit.jupiter.api.Test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * Test class for LevelMapFactory.
 */
public class LevelMapFactoryTest {

    /** Map filename. */
    private final String foyMapFileName = "foy";
    /** Tileset JSON file path. */
    private final Path tilesetJsonPath = ProjectPaths.getTileset();
    /** Texture Map. */
    private final Map<Integer, String> textureMap = LevelMapFactory.parseTextureMap(tilesetJsonPath);
    /** Map name. */
    private final String foyMapName = "foy";
    /** Directory for Foy map. */
    private final File foyDirectory = new File(ProjectPaths.getMapDir() + "/" + foyMapName);
    /** New factory for a level map. */
    private final LevelMapFactory levelMapFactory = new LevelMapFactory(tilesetJsonPath);

    @Test
    void testSearchMapFilesRootDirectoryFound() {
        File result = LevelMapFactory.searchMapFilesRootDirectory(foyMapFileName);
        assertNotNull(result);
        assertEquals(foyMapFileName, result.getName());
        assertTrue(result.isDirectory());
    }

    @Test
    void testSearchMapFilesRootDirectoryNotFound() {
        File result = LevelMapFactory.searchMapFilesRootDirectory("nonexistent_dir_1234");
        assertNull(result);
    }

    @Test
    void testCreateLevelMapOK() {
        LevelMap levelMap = levelMapFactory.createLevelMap("foy");
        System.out.println(levelMap.toString());
    }

    @Test
    void testCreateLevelMapNonExistentDirectory() {
        assertThrows(IllegalArgumentException.class, () -> {
            levelMapFactory.createLevelMap("non_existent_map");
        });
    }

    @Test
    void testCreateLevelMapEmptyDirectory() {
        String dirName = "emptyDirectory";
        String dirPathName = ProjectPaths.getMapDir() + dirName;
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
    void testCreateLevelMapMoreThanOneBackgroundCsv() throws IOException {
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
    void testCreateLevelMapMoreThanOneUnbreakableCsv() throws IOException {
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
    void testCreateLevelMapMoreThanOneBreakableCsv() throws IOException {
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
    void testCreateLevelMapMoreThan3CsvsButDifferentBackBreakUnBreakOK() throws IOException {

        File dummyFile = new File(foyDirectory, "Test.csv");
        assertTrue(dummyFile.createNewFile(), "Failed to create dummy background CSV");

        try {
            LevelMap levelMap = levelMapFactory.createLevelMap(foyMapName);
            Square square = levelMap.getSquare(5, 2);
            // id = 33
            assertEquals("spruce-planks", square.getTextureName());
            // id = 44
            assertEquals("pressure-plate", square.getMapElement().getTextureName());
        } finally {
            assertTrue(dummyFile.delete(), "Failed to delete dummy file");
        }
    }

    @Test
    void testParseTextureMapSuccess() throws IOException {
        // Create a temporary JSON file with tile data
        String jsonContent = "{\n"
        + "  \"tiles\": [\n"
        + "                 { \"id\": 0, \"image\": \"tiles/grass.png\" },\n"
        + "                 { \"id\": 1, \"image\": \"tiles/wall.png\" }\n"
        + "             ]\n"
        + "}";

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
    void testParseTextureMapFileNotFound() {
        Path fakePath = Paths.get("nonexistent/path/to/file.json");

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            LevelMapFactory.parseTextureMap(fakePath);
        });

        assertTrue(exception.getMessage().contains("Failed to parse texture JSON"));
    }

    @Test
    void testParseTextureMapMalFormedJson() throws IOException {
        Path tempFile = Files.createTempFile("badjson", ".json");
        Files.writeString(tempFile, "{ invalid json ]");

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            LevelMapFactory.parseTextureMap(tempFile);
        });

        assertTrue(exception.getMessage().contains("Failed to parse texture JSON"));

        Files.deleteIfExists(tempFile); // cleanup
    }

    @Test
    void testParseCsvThrowsOnCsvNotRectangular() throws IOException {
        String[][] background = {{"1", "2"}, {"3", "4"}};
        String[][] breakable = {{"-1", "-1"}}; // Fewer rows
        String[][] unbreakable = {{"-1", "-1"}, {"-1", "-1"}};

        File f1 = writeCsv("bga", background);
        File f2 = writeCsv("bra", breakable);
        File f3 = writeCsv("uba", unbreakable);

        assertThrows(IllegalArgumentException.class, () ->
            LevelMapFactory.parseCsv(f1, f2, f3, Map.of())
        );
    }

    @Test
    void testParseCsvThrowsOnColumnMismatch() throws IOException {
        String[][] background = {{"1", "2"}, {"3", "4"}};
        String[][] breakable = {{"-1", "-1"}, {"5"}}; // Row has fewer columns
        String[][] unbreakable = {{"-1", "-1"}, {"-1", "-1"}};

        File f1 = writeCsv("bga", background);
        File f2 = writeCsv("bra", breakable);
        File f3 = writeCsv("uba", unbreakable);

        assertThrows(IllegalArgumentException.class, () ->
            LevelMapFactory.parseCsv(f1, f2, f3, Map.of())
        );
    }

    @Test
    void testParseCsvMissingTextureForBackgroundInTileset() throws IOException {
        String[][] background = {{"33"}};
        String[][] breakable = {{"-1"}};
        String[][] unbreakable = {{"-1"}};

        File f1 = writeCsv("bga", background);
        File f2 = writeCsv("bra", breakable);
        File f3 = writeCsv("uba", unbreakable);

        // Create levelMap with an Empty texture map (Map.of()), should throw an error
        assertThrows(IllegalArgumentException.class, () ->
            LevelMapFactory.parseCsv(f1, f2, f3, Map.of())
        );
    }

    @Test
    void testParseCsvBackgroundWithTextureOK() throws IOException {

        String[][] background = {{"33"}};
        String[][] breakable = {{"-1"}};
        String[][] unbreakable = {{"-1"}};

        File f1 = writeCsv("bga", background);
        File f2 = writeCsv("bra", breakable);
        File f3 = writeCsv("uba", unbreakable);

        List<List<Square>> levelMap = LevelMapFactory.parseCsv(f1, f2, f3, this.textureMap);

        System.out.println(levelMap);
        assertEquals("spruce-planks", levelMap.get(0).get(0).getTextureName());
    }

    @Test
    void testParseCsvBackgroundTextureIdsMatch() throws Exception {
        String mapName = "foy";
        LevelMap levelMap = levelMapFactory.createLevelMap(mapName);

        File backgroundCsv = new File(ProjectPaths.getMapDir() + "/" + mapName + "/le_foy_Background.csv");
        File breakableCsv = new File(ProjectPaths.getMapDir() + "/" + mapName + "/le_foy_Breakable.csv");
        File unbreakableCsv = new File(ProjectPaths.getMapDir() + "/" + mapName + "/le_foy_Unbreakable.csv");
        assertTrue(backgroundCsv.exists(), "Background CSV does not exist!");

        try (
                CSVReader backgroundReader = new CSVReader(new FileReader(backgroundCsv));
                CSVReader breakableReader = new CSVReader(new FileReader(breakableCsv));
                CSVReader unbreakableReader = new CSVReader(new FileReader(unbreakableCsv))
        ) {
            List<String[]> backgroundRows = backgroundReader.readAll();
            List<String[]> breakableRows = breakableReader.readAll();
            List<String[]> unbreakableRows = unbreakableReader.readAll();

            assertEquals(backgroundRows.size(), levelMap.getHeight(), "Number of back rows mismatch");
            assertEquals(breakableRows.size(), levelMap.getHeight(), "Number of break rows mismatch");
            assertEquals(unbreakableRows.size(), levelMap.getHeight(), "Number of unbreak rows mismatch");

            for (int i = 0; i < backgroundRows.size(); i++) {
                String[] backgroundCols = backgroundRows.get(i);
                String[] breakableCols = breakableRows.get(i);
                String[] unbreakableCols = unbreakableRows.get(i);
                assertEquals(backgroundCols.length, levelMap.getWidth(), "Number of columns mismatch at row " + i);
                assertEquals(breakableCols.length, levelMap.getWidth(), "Number of columns mismatch at row " + i);
                assertEquals(unbreakableCols.length, levelMap.getWidth(), "Number of columns mismatch at row " + i);
            }

            Square square = levelMap.getSquare(4, 11);
            System.out.println("Square at (4, 11): " + square);
            MapElement mapElement = square.getMapElement();
            System.out.println("MapElement at (4, 11): " + mapElement);

            System.out.println("Flip Horizontal: " + mapElement.isHorizontalFlip());
            System.out.println("Flip Vertical: " + mapElement.isVerticalFlip());
            System.out.println("Flip Diagonal: " + mapElement.isDiagonalFlip());

            System.out.println(levelMap);

            System.out.println("Square at (12,13): " + levelMap.getSquare(12, 13));

            assertTrue(levelMap.getSquare(4, 11).getMapElement().isHorizontalFlip());
        }
    }

//    @Test
//    public void test_debug() {
//        int breakableTextureId = 53;
//        System.out.print("FLIP_V: ");
//        System.out.println(breakableTextureId | ElementTexture.FLIP_V);
//        System.out.print("FLIP_H: ");
//        System.out.println(breakableTextureId | ElementTexture.FLIP_H);
//        System.out.print("FLIP_D: ");
//        System.out.println(breakableTextureId | ElementTexture.FLIP_D);
//        System.out.print("FLIP_VH: ");
//        System.out.println(breakableTextureId | ElementTexture.FLIP_V | ElementTexture.FLIP_H);
//        System.out.print("FLIP_VD: ");
//        System.out.println(breakableTextureId | ElementTexture.FLIP_V | ElementTexture.FLIP_D);
//        System.out.print("FLIP_HD: ");
//        System.out.println(breakableTextureId | ElementTexture.FLIP_H | ElementTexture.FLIP_D);
//        System.out.print("FLIP_VHD: ");
//        System.out.println(breakableTextureId | ElementTexture.FLIP_V | ElementTexture.FLIP_H | ElementTexture.FLIP_D);
//    }




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
