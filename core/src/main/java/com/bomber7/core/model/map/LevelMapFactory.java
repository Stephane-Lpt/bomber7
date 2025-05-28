package com.bomber7.core.model.map;

import com.bomber7.core.model.square.BreakableWall;
import com.bomber7.core.model.square.Square;
import com.bomber7.core.model.square.UnbreakableWall;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.opencsv.CSVReader;

import java.io.File;
import java.io.FileReader;
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

    private Path tilesetJsonPath;

    public LevelMapFactory(Path tilesetJsonPath) {
        this.tilesetJsonPath = tilesetJsonPath;
    }

    public LevelMap createLevelMap(String mapName){
        File mapRootDirectory = searchMapFilesRootDirectory(mapName);
        if (mapRootDirectory == null) {
            throw new IllegalArgumentException("Map directory not found for file: " + mapName + Paths.get("./").toAbsolutePath());
        }

        File backgroundCsvFile = null;
        File breakableCsvFile = null;
        File unbreakableCsvFile = null;

        if (mapRootDirectory.isDirectory()) {
            File[] files = mapRootDirectory.listFiles();

            if (files != null) {
                for (File file : files) {
                    if (file.isFile() && file.getName().toLowerCase().endsWith(".csv")) {
                        String name = file.getName().toLowerCase();

                        if (name.contains("background") && backgroundCsvFile == null) {
                            backgroundCsvFile = file;
                        } else if (name.contains("unbreakable") && unbreakableCsvFile == null) {
                            unbreakableCsvFile = file;
                        } else if (name.contains("breakable") && breakableCsvFile == null) {
                            breakableCsvFile = file;
                        }
                    }
                }
            }
        }

        Map<Integer, String> textureMap = LevelMapFactory.parseTextureMap(this.tilesetJsonPath);
        List<List<Square>> checkerboard = LevelMapFactory.parseCsv(backgroundCsvFile, breakableCsvFile, unbreakableCsvFile, textureMap);
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

        } catch (Exception e) {
            throw new RuntimeException("Failed to parse texture JSON", e);
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

            for (int i = 0; i < backgroundRows.size(); i++) {
                String[] backgroundRow = backgroundRows.get(i);
                String[] breakableRow = breakableRows.get(i);
                String[] unbreakableRow = unbreakableRows.get(i);

                List<Square> squareRow = new ArrayList<>();

                for (int j = 0; j < backgroundRow.length; j++) {
                    int backgroundTextureId = Integer.parseInt(backgroundRow[j].trim());
                    int breakableTextureId = Integer.parseInt(breakableRow[j].trim());
                    int unbreakableTextureId = Integer.parseInt(unbreakableRow[j].trim());

                    final String defaultValue = "missing_texture.png";
                    String backgroundTexture = textureMap.getOrDefault(backgroundTextureId, defaultValue);

                    if(breakableTextureId != -1){
                        String breakableTexture = textureMap.getOrDefault(breakableTextureId, defaultValue);
                        squareRow.add(new Square(backgroundTexture, new BreakableWall(breakableTexture, j, i)));
                    }
                    else if(unbreakableTextureId != -1){
                        String unbreakableTexture = textureMap.getOrDefault(unbreakableTextureId, defaultValue);
                        squareRow.add(new Square(backgroundTexture, new UnbreakableWall(unbreakableTexture, j, i)));
                    }
                    else {
                        squareRow.add(new Square(backgroundTexture));
                    }

                }

                result.add(squareRow);
            }

            return result;
        } catch (Exception e) {
            throw new RuntimeException("Error parsing CSVs. backPath: " + backgroundCsvPath + " breakPath: " + breakableCsvPath + " unbreakPath: " + unbreakableCsvPath, e);
        }
    }
}
