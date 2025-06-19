package com.bomber7.core.model.map;

import com.bomber7.core.model.square.BonusLife;
import com.bomber7.core.model.square.BonusSpeed;
import com.bomber7.core.model.square.BonusTriggerBomb;
import com.bomber7.core.model.square.BreakableWall;
import com.bomber7.core.model.square.Square;
import com.bomber7.core.model.square.TriggerBomb;
import com.bomber7.core.model.square.UnbreakableWall;
import com.bomber7.core.model.texture.ElementTexture;
import com.bomber7.utils.BonusType;
import com.bomber7.utils.ProjectPaths;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Factory class for creating LevelMap instances.
 * Responsible for parsing texture mappings and CSV files to create a map of squares.
 */
public final class LevelMapFactory {
    /**
     * A map that associates texture IDs with their corresponding file paths.
     * The keys are texture IDs, and the values are the paths to the texture files.
     */
    private static final Map<Integer, String> TEXTURE_MAP =
        LevelMapFactory.parseTextureMap(ProjectPaths.getTileset());

    private LevelMapFactory() {
        // Private constructor to prevent instantiation
    }

    /**
     * Creates a LevelMap instance based on the specified map name.
     * Searches for the map files in the "assets/maps" directory and parses the related CSV files to create the map.
     *
     * @param mapName The name of the map to create.
     * @param windowWidth The width of the game window.
     * @param windowHeight The height of the game window.
     * @return A LevelMap instance representing the specified map.
     * @throws IllegalArgumentException if the map directory is not found, is empty, or same type multiple CSV files.
     */
    public static LevelMap createLevelMap(String mapName, int windowWidth, int windowHeight) {
        File mapRootDirectory = searchMapFilesRootDirectory(mapName);
        if (mapRootDirectory == null || mapRootDirectory.listFiles() == null || mapRootDirectory.listFiles().length == 0) {
            throw new IllegalArgumentException(
                "Map directory not found or is empty for: "
                +
                mapName
                +
                " (cwd: "
                +
                Paths.get("./").toAbsolutePath()
                +
                ")"
            );
        }


        File backgroundCsvFile = null;
        File breakableCsvFile = null;
        File unbreakableCsvFile = null;

        if (mapRootDirectory.isDirectory()) {
            File[] files = mapRootDirectory.listFiles();
            if (files == null || files.length == 0) {
                throw new IllegalArgumentException("Map directory is empty for: " + mapName);
            }

            if (files != null) {
                for (File file : files) {
                    if (file.isFile() && file.getName().toLowerCase().endsWith(".csv")) {
                        String name = file.getName().toLowerCase();

                        if (name.contains("background")) {
                            if (backgroundCsvFile != null) {
                                throw new IllegalArgumentException(
                                    "Multiple background CSV files found in: " + mapName
                                );
                            }
                            backgroundCsvFile = file;
                        } else if (name.contains("unbreakable")) {
                            if (unbreakableCsvFile != null) {
                                throw new IllegalArgumentException(
                                    "Multiple unbreakable CSV files found in: " + mapName
                                );
                            }
                            unbreakableCsvFile = file;
                        } else if (name.contains("breakable")) {
                            if (breakableCsvFile != null) {
                                throw new IllegalArgumentException(
                                    "Multiple breakable CSV files found in: " + mapName
                                );
                            }
                            breakableCsvFile = file;
                        }
                    }
                }
            }
        }
        List<List<Square>> checkerboard = LevelMapFactory.parseCsv(
            backgroundCsvFile, breakableCsvFile, unbreakableCsvFile, LevelMapFactory.TEXTURE_MAP
        );
        return new LevelMap(mapName, checkerboard, windowWidth, windowHeight);
    }

    /**
     * Searches for a subdirectory inside "assets/maps" that contains a file with the specified name.
     *
     * @param filename The name of the file to search for.
     * @return The path of the subdirectory containing the file, or null if not found.
     */
    public static File searchMapFilesRootDirectory(String filename) {
        File mapsRoot = new File(ProjectPaths.getAssetsPath() + "/maps");

        File[] subdirs = mapsRoot.listFiles(File::isDirectory);
        if (subdirs == null) {
            return null;
        }

        for (File dir : subdirs) {
            if (dir.getName().equals(filename)) {
                return dir;
            }
        }


        return null; // not found
    }

    /**
     * Parses the JSON file to create a mapping of texture IDs to their names.
     *
     * @param jsonPath Path to the JSON file containing texture mappings.
     * @return A map where keys are texture IDs and values are their corresponding names.
     */
    public static Map<Integer, String> parseTextureMap(Path jsonPath) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode root = objectMapper.readTree(new File(jsonPath.toAbsolutePath().toString()));
            Map<Integer, String> textureMap = new HashMap<>();

            for (JsonNode tile : root.path("tiles")) {
                int id = tile.path("id").asInt();
                String textureName = tile.path("image").asText().replace("\\/", "/");
                textureMap.put(id, textureName);
            }
            return textureMap;

        } catch (IOException e) {
            throw new IllegalArgumentException(
                "Failed to parse texture JSON. Wrong filepath or wrong format/json content:" + jsonPath, e
            );
        }
    }

    /**
     * Parses the CSV files to create a list of lists of Square objects (the checkerboard).
     *
     * @param backgroundCsvPath Path to the background CSV file.
     * @param breakableCsvPath Path to the breakable walls CSV file.
     * @param unbreakableCsvPath Path to the unbreakable walls CSV file.
     * @param textureMap A map of texture IDs to their file paths.
     * @return A list of lists of Square objects representing the map.
     */
    public static List<List<Square>> parseCsv(
        File backgroundCsvPath,
        File breakableCsvPath,
        File unbreakableCsvPath,
        Map<Integer, String> textureMap
    ) {
        List<List<Square>> result = new ArrayList<>();

        try (
            CSVReader backgroundReader = new CSVReader(new FileReader(backgroundCsvPath.getAbsolutePath()));
            CSVReader breakableReader = new CSVReader(new FileReader(breakableCsvPath.getAbsolutePath()));
            CSVReader unbreakableReader = new CSVReader(new FileReader(unbreakableCsvPath.getAbsolutePath()))
        ) {
            List<String[]> backgroundRows = backgroundReader.readAll();
            List<String[]> breakableRows = breakableReader.readAll();
            List<String[]> unbreakableRows = unbreakableReader.readAll();

            // Verify that all CSV files have the same number of rows
            int numRows = backgroundRows.size();
            if (breakableRows.size() != numRows || unbreakableRows.size() != numRows) {
                throw new IllegalArgumentException("CSV files do not have the same number of rows.");
            }

            for (int i = backgroundRows.size() - 1; i >= 0; i--) {
                int backgroundCols = backgroundRows.get(i).length;
                int breakableCols = breakableRows.get(i).length;
                int unbreakableCols = unbreakableRows.get(i).length;

                // Check if all CSV files have the same number of columns in the current row
                if (backgroundCols != breakableCols || backgroundCols != unbreakableCols) {
                    throw new IllegalArgumentException(
                        "Row " + i + " has mismatched column counts between CSV files."
                    );
                }
                // Check if all rows are rectangular (comparing with the first row)
                if (backgroundCols != backgroundRows.get(0).length) {
                    throw new IllegalArgumentException("Background CSV is not rectangular at row " + i);
                }
                if (breakableCols != breakableRows.get(0).length) {
                    throw new IllegalArgumentException("Breakable CSV is not rectangular at row " + i);
                }
                if (unbreakableCols != unbreakableRows.get(0).length) {
                    throw new IllegalArgumentException("Unbreakable CSV is not rectangular at row " + i);
                }

                String[] backgroundRow = backgroundRows.get(i);
                String[] breakableRow = breakableRows.get(i);
                String[] unbreakableRow = unbreakableRows.get(i);
                List<Square> squareRow = new ArrayList<>();

                for (int j = 0; j < backgroundRow.length; j++) {
                    int backgroundTextureId = Integer.parseInt(backgroundRow[j].trim());
                    int breakableTextureId = Integer.parseInt(breakableRow[j].trim());
                    int unbreakableTextureId = Integer.parseInt(unbreakableRow[j].trim());
                    boolean backgroundVerticalFlip = false;
                    boolean backgroundHorizontalFlip = false;
                    boolean backgroundDiagonalFlip = false;

                    if (backgroundTextureId != -1) {
                        // The 3 high bits are used for flipping:
                        // 0x80000000 → Bit 31: Horizontal flip → 1000 0000 0000 0000 0000 0000 0000 0000
                        // 0x40000000 → Bit 30: Vertical flip → 0100 0000 0000 0000 0000 0000 0000 0000
                        // 0x20000000 → Bit 29: Diagonal flip → 0010 0000 0000 0000 0000 0000 0000 0000
                        backgroundVerticalFlip = (backgroundTextureId & ElementTexture.FLIP_V) != 0;
                        backgroundHorizontalFlip = (backgroundTextureId & ElementTexture.FLIP_H) != 0;
                        backgroundDiagonalFlip = (backgroundTextureId & ElementTexture.FLIP_D) != 0;
                        backgroundTextureId = backgroundTextureId & ElementTexture.ID_MASK;
                    }

                    if (!textureMap.containsKey(backgroundTextureId)) {
                        throw new IllegalArgumentException(
                            "textureMap doesnt have all the required textures: back:" + backgroundTextureId
                        );
                    }

                    String backgroundTextureName = textureMap.get(backgroundTextureId);

                    if (breakableTextureId != -1) {
                        boolean breakableVerticalFlip = (breakableTextureId & ElementTexture.FLIP_V) != 0;
                        boolean breakableHorizontalFlip   = (breakableTextureId & ElementTexture.FLIP_H) != 0;
                        boolean breakableDiagonalFlip   = (breakableTextureId & ElementTexture.FLIP_D) != 0;
                        breakableTextureId = breakableTextureId & ElementTexture.ID_MASK;

                        if (!textureMap.containsKey(breakableTextureId)) {
                            throw new IllegalArgumentException(
                                "textureMap doesnt have all the required textures: break:" + breakableTextureId
                                +
                                    " row " + i + " col " + j
                            );
                        }

                        String breakableTextureName = textureMap.get(breakableTextureId);

                        squareRow.add(
                            new Square(backgroundTextureName,
                                new BreakableWall(
                                    breakableTextureName, breakableVerticalFlip,
                                    breakableHorizontalFlip, breakableDiagonalFlip
                                ),
                                backgroundVerticalFlip, backgroundHorizontalFlip, backgroundDiagonalFlip
                            )
                        );
                    } else if (unbreakableTextureId != -1) {
                        boolean unbreakableVerticalFlip = (unbreakableTextureId & ElementTexture.FLIP_V) != 0;
                        boolean unbreakableHorizontalFlip   = (unbreakableTextureId & ElementTexture.FLIP_H) != 0;
                        boolean unbreakableDiagonalFlip   = (unbreakableTextureId & ElementTexture.FLIP_D) != 0;
                        unbreakableTextureId = unbreakableTextureId & ElementTexture.ID_MASK;

                        if (!textureMap.containsKey(unbreakableTextureId)) {
                            throw new IllegalArgumentException(
                                "textureMap doesnt have all the required textures: unbreak:" + unbreakableTextureId
                            );
                        }

                        String unbreakableTextureName = textureMap.get(unbreakableTextureId);

                        squareRow.add(
                            new Square(backgroundTextureName,
                                new UnbreakableWall(
                                    unbreakableTextureName,
                                    unbreakableVerticalFlip,
                                    unbreakableHorizontalFlip,
                                    unbreakableDiagonalFlip),
                                backgroundVerticalFlip,
                                backgroundHorizontalFlip,
                                backgroundDiagonalFlip
                            )
                        );
                    } else {
                        squareRow.add(
                            new Square(
                                backgroundTextureName,
                                backgroundVerticalFlip,
                                backgroundHorizontalFlip,
                                backgroundDiagonalFlip
                            )
                        );
                    }
                }
                result.add(squareRow);
            }
            return result;
        } catch (FileNotFoundException e) {
            throw new IllegalArgumentException("Invalid CSV filepath.", e);
        } catch (IOException e) {
            throw new RuntimeException("Unable to read specified files, maybe lack of ressources", e);
        } catch (CsvException e) {
            throw new IllegalArgumentException("Unable to read the CSV file. Wrong format");
        }
    }
}
