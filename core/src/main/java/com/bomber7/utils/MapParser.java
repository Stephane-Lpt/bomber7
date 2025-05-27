package com.bomber7.utils;

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
    public static List<List<String>> createTextureMatrix(String csvPath, String jsonPath) {
        Map<Integer, String> textureMap = parseTextureMap(jsonPath);
        return new LayerParser().parseCsv(csvPath, textureMap);
    }

    /**
     * Parses the JSON file to create a mapping of texture IDs to their file paths.
     *
     * @param jsonPath Path to the JSON file containing texture mappings.
     * @return A map where keys are texture IDs and values are their corresponding file paths.
     */
    private static Map<Integer, String> parseTextureMap(String jsonPath) {
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
    private static class LayerParser {
        public List<List<String>> parseCsv(String csvPath, Map<Integer, String> textureMap) {
            try (CSVReader reader = new CSVReader(new FileReader(csvPath))) {
                return reader.readAll().stream()
                    .map(row -> parseRow(row, textureMap))
                    .collect(Collectors.toList());
            } catch (Exception e) {
                throw new RuntimeException("Error parsing CSV: " + csvPath, e);
            }
        }

        private List<String> parseRow(String[] row, Map<Integer, String> textureMap) {
            return Arrays.stream(row)
                .map(String::trim)
                .map(Integer::parseInt)
                .map(id -> textureMap.getOrDefault(id, "missing_texture.png"))
                .collect(Collectors.toList());
        }
    }
    
// --- Test Snippet ---
    public static void main(String[] args) {
        // Adjust these paths if your execution directory is different
        // or if files are located elsewhere.
        // Assuming execution from the project root "ProjetLong"
        String csvFilePath = "core/src/main/java/com/bomber7/utils/le_foy_Background.csv";
        String jsonFilePath = "core/src/main/java/com/bomber7/utils/texture_map.json";

        System.out.println("Attempting to parse CSV: " + new File(csvFilePath).getAbsolutePath());
        System.out.println("Attempting to parse JSON: " + new File(jsonFilePath).getAbsolutePath());


        try {
            List<List<String>> textureMatrix = createTextureMatrix(csvFilePath, jsonFilePath);
            
            System.out.println("\nSuccessfully parsed texture matrix!");
            System.out.println("Matrix dimensions: " + textureMatrix.size() + " rows, " + (textureMatrix.isEmpty() ? 0 : textureMatrix.get(0).size()) + " cols.");
            
            // Print a small part of the matrix to verify
            System.out.println("\nSample of the texture matrix (first 5x5 or less):");
            for (int i = 0; i < Math.min(5, textureMatrix.size()); i++) {
                List<String> row = textureMatrix.get(i);
                for (int j = 0; j < Math.min(5, row.size()); j++) {
                    System.out.printf("%-30s ", row.get(j)); // Adjust formatting as needed
                }
                System.out.println();
            }

        } catch (RuntimeException e) {
            System.err.println("\nError during map parsing:");
            e.printStackTrace();
        }
    }
}