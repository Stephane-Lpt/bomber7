package com.bomber7.utils;

/**
 * Enum containing all the maps that exist in the game.
 */
public enum Map {
    /**
     * ENSEEIHT's schoolyard map.
     */
    ENSEEIHT(
        "enseeiht",
        1000
        ),
    /**
     * Student's restaurant map.
     */
    CROUS(
        "crous",
        1000
        ),
    /**
     * Student's foyer map.
     */
    FOY(
        "foy",
        1000
        ),
    /**
     * Halle aux grains map.
     */
    HALLE_AUX_GRAINS(
        "halle_aux_grains",
        1000
        );

    /**
     * The asset name of the map (found in bundles, images, etc...).
     */
    private final String assetName;
    /**
     * The number of points needed to unlock the map.
     */
    private final int pointsToUnlock;

    /**
     * Getter of assetName.
     *
     * @return the name of the libGSX asset associated with this map.
     */
    public String getAssetName() {
        return assetName;
    }

    /**
     * Getter of pointsToUnlock.
     *
     * @return the unlock threshold in points.
     */
    public int getPointsToUnlock() {
        return pointsToUnlock;
    }

    /**
     * Initializes the map.
     * @param assetName the name of the map asset.
     * @param pointsToUnlock the number of points needed to unclock the map.
     */
    Map(String assetName, int pointsToUnlock) {
        this.assetName = assetName;
        this.pointsToUnlock = pointsToUnlock;
    }
}
