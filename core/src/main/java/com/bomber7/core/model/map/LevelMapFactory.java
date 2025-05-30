package com.bomber7.core.model.map;

import com.bomber7.core.model.square.BreakableWall;
import com.bomber7.core.model.square.Square;
import com.bomber7.core.model.square.UnbreakableWall;
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
public class LevelMapFactory {

    private final Map<Integer, String> textureMap;


    public LevelMapFactory(Path tilesetJsonPath) {
        this.textureMap = LevelMapFactory.parseTextureMap(tilesetJsonPath);

    }

    public LevelMap createLevelMap(String mapName){
        File mapRootDirectory = searchMapFilesRootDirectory(mapName);
        if (mapRootDirectory == null || mapRootDirectory.listFiles() == null || mapRootDirectory.listFiles().length == 0) {
            throw new IllegalArgumentException("Map directory not found or is empty for: " + mapName + " (cwd: " + Paths.get("./").toAbsolutePath() + ")");
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
                                throw new IllegalArgumentException("Multiple background CSV files found in: " + mapName);
                            }
                            backgroundCsvFile = file;
                        } else if (name.contains("unbreakable")) {
                            if (unbreakableCsvFile != null) {
                                throw new IllegalArgumentException("Multiple unbreakable CSV files found in: " + mapName);
                            }
                            unbreakableCsvFile = file;
                        } else if (name.contains("breakable")) {
                            if (breakableCsvFile != null) {
                                throw new IllegalArgumentException("Multiple breakable CSV files found in: " + mapName);
                            }
                            breakableCsvFile = file;
                        }
                    }
                }
            }
        }
        List<List<Square>> checkerboard = LevelMapFactory.parseCsv(backgroundCsvFile, breakableCsvFile, unbreakableCsvFile, this.textureMap);
        return new LevelMap(checkerboard);
    }

    /**
     * Searches for a subdirectory inside "assets/maps" that contains a file with the specified name.
     *
     * @param filename The name of the file to search for.
     * @return The path of the subdirectory containing the file, or null if not found.
     */
    public static File searchMapFilesRootDirectory(String filename) {
        File mapsRoot = new File("../assets/maps");

        File[] subdirs = mapsRoot.listFiles(File::isDirectory);
        if (subdirs == null) return null;

        for (File dir : subdirs) {
            if(dir.getName().equals(filename)) {
                return dir;
            }
        }


        return null; // not found
    }

    /**
     * Parses the JSON file to create a mapping of texture IDs to their file paths.
     *
     * @param jsonPath Path to the JSON file containing texture mappings.
     * @return A map where keys are texture IDs and values are their corresponding file paths.
     */
    public static Map<Integer, String> parseTextureMap(Path jsonPath) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode root = objectMapper.readTree(new File(jsonPath.toAbsolutePath().toString()));
            Map<Integer, String> textureMap = new HashMap<>();

            for (JsonNode tile : root.path("tiles")) {
                int id = tile.path("id").asInt();
                String path = tile.path("image").asText().replace("\\/", "/");
                textureMap.put(id, path);
            }
            return textureMap;

        } catch (IOException e) {
            throw new IllegalArgumentException("Failed to parse texture JSON. Wrong filepath or wrong format/json content.", e);
        }
    }

    public static List<List<Square>> parseCsv(File backgroundCsvPath, File breakableCsvPath, File unbreakableCsvPath, Map<Integer, String> textureMap) {
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

            for (int i = 0; i < backgroundRows.size(); i++) {

                int backgroundCols = backgroundRows.get(i).length;
                int breakableCols = breakableRows.get(i).length;
                int unbreakableCols = unbreakableRows.get(i).length;

                // Check if all CSV files have the same number of columns in the current row
                if (backgroundCols != breakableCols || backgroundCols != unbreakableCols) {
                    throw new IllegalArgumentException("Row " + i + " has mismatched column counts between CSV files.");
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

                    if(!textureMap.containsKey(backgroundTextureId) && !textureMap.containsKey(breakableTextureId) && !textureMap.containsKey(unbreakableTextureId)) {
                        throw new IllegalArgumentException("textureMap doesnt have all the required textures.");
                    }

                    String backgroundTexture = textureMap.get(backgroundTextureId);

                    if(breakableTextureId != -1){
                        String breakableTexture = textureMap.get(breakableTextureId);
                        squareRow.add(new Square(backgroundTexture, backgroundTextureId, new BreakableWall(breakableTexture, breakableTextureId)));
                    }
                    else if(unbreakableTextureId != -1){
                        String unbreakableTexture = textureMap.get(unbreakableTextureId);
                        squareRow.add(new Square(backgroundTexture, backgroundTextureId, new UnbreakableWall(unbreakableTexture, unbreakableTextureId)));
                    }
                    else if (backgroundTextureId != -1) {
                        squareRow.add(new Square(backgroundTexture, backgroundTextureId));
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
