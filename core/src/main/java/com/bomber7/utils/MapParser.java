package com.bomber7.utils;

import com.bomber7.core.model.square.BreakableWall;
import com.bomber7.core.model.square.MapElement;
import com.bomber7.core.model.square.Square;
import com.bomber7.core.model.square.UnbreakableWall;
import com.opencsv.CSVReader;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import java.io.File;
import java.io.FileReader;
import java.util.*;
import java.util.stream.Collectors;

public class MapParser {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * Parses a CSV file and a JSON file to create a texture matrix.
     * The CSV contains IDs that map to texture paths defined in the JSON.
     *
     * @param csvPath  Path to the CSV file containing texture IDs.
     * @param jsonPath Path to the JSON file mapping IDs to texture paths.
     * @return A list of lists representing the texture matrix.
     */
    public static List<List<Square>> createTextureMatrix(String backgroundCsvPath, String breakableCsvPath, String unbreakableCsvPath, String jsonPath) {
        Map<Integer, String> textureMap = parseTextureMap(jsonPath);
        return new LayerParser().parseCsv(backgroundCsvPath, breakableCsvPath, unbreakableCsvPath, textureMap);
    }

    /**
     * Parses the JSON file to create a mapping of texture IDs to their file paths.
     *
     * @param jsonPath Path to the JSON file containing texture mappings.
     * @return A map where keys are texture IDs and values are their corresponding file paths.
     */
    public static Map<Integer, String> parseTextureMap(String jsonPath) {
        try {
            JsonNode root = objectMapper.readTree(new File(jsonPath));
            Map<Integer, String> map = new HashMap<>();

            for (JsonNode tile : root.path("tiles")) {
                int id = tile.path("id").asInt();
                String path = tile.path("image").asText().replace("\\/", "/");
                map.put(id, path);
            }
            return map;

        } catch (Exception e) {
            throw new RuntimeException("Failed to parse texture JSON", e);
        }
    }

    /**
     * Generic inner parser class for handling CSV parsing.
     */
    public static class LayerParser {
        public List<List<Square>> parseCsv(String backgroundCsvPath, String breakableCsvPath, String unbreakableCsvPath, Map<Integer, String> textureMap) {
            List<List<Square>> result = new ArrayList<>();

            try (
                    CSVReader backgroundReader = new CSVReader(new FileReader(backgroundCsvPath));
                    CSVReader breakableReader = new CSVReader(new FileReader(breakableCsvPath));
                    CSVReader unbreakableReader = new CSVReader(new FileReader(unbreakableCsvPath))
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
                throw new RuntimeException("Error parsing CSVs: " + backgroundCsvPath + breakableCsvPath + unbreakableCsvPath, e);
            }
        }
    }


    // --- Test Snippet ---
    public static void main(String[] args) {
        // Adjust these paths if your execution directory is different
        // or if files are located elsewhere.
        // Assuming execution from the project root "ProjetLong"
        String backgroundCsvFilePath = "assets/maps/foy/le_foy_Background.csv";
        String breakableCsvFilePath = "assets/maps/foy/le_foy_Breakable.csv";
        String unbreakableCsvFilePath = "assets/maps/foy/le_foy_Unbreakable.csv";
        String jsonFilePath = "assets/textures/tileset.tsj";

        System.out.println("Attempting to parse CSV: " + new File(backgroundCsvFilePath).getAbsolutePath());
        System.out.println("Attempting to parse CSV: " + new File(breakableCsvFilePath).getAbsolutePath());
        System.out.println("Attempting to parse CSV: " + new File(unbreakableCsvFilePath).getAbsolutePath());
        System.out.println("Attempting to parse JSON: " + new File(jsonFilePath).getAbsolutePath());


        try {
            List<List<Square>> textureMatrix = createTextureMatrix(backgroundCsvFilePath, breakableCsvFilePath, unbreakableCsvFilePath, jsonFilePath);

            System.out.println("\nSuccessfully parsed texture matrix!");
            System.out.println("Matrix dimensions: " + textureMatrix.size() + " rows, " + (textureMatrix.isEmpty() ? 0 : textureMatrix.get(0).size()) + " cols.");

            // Print a small part of the matrix to verify
            System.out.println("\nSample of the texture matrix (first 5x5 or less):");
            for (int i = 0; i < Math.min(5, textureMatrix.size()); i++) {
                List<Square> row = textureMatrix.get(i);
                for (int j = 0; j < Math.min(5, row.size()); j++) {
                    System.out.printf("%-30s ", row.get(j).getSpriteFilePath()); // Adjust formatting as needed
                }
                System.out.println();
            }

        } catch (RuntimeException e) {
            System.err.println("\nError during map parsing:");
            e.printStackTrace();
        }
    }
}
