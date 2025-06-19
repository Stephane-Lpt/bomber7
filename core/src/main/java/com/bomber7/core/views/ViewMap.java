package com.bomber7.core.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.bomber7.core.ResourceManager;
import com.bomber7.core.model.map.LevelMap;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.bomber7.core.model.square.Bomb;
import com.bomber7.core.model.square.Bonus;
import com.bomber7.core.model.square.Square;
import com.bomber7.core.model.square.TimeBomb;
import com.bomber7.core.model.square.Wall;
import com.bomber7.utils.Constants;

import java.util.ArrayList;
import java.util.List;

/**
 * The view that shows the game map in GameScreen.
 */
public class ViewMap extends Actor {

    /** The Grid ({@link <a href="https://www.youtube.com/watch?v=lILHEnz8fTk">YouTube video</a>}). */
    private final LevelMap levelMap;

    /** The resourceManager needed for sprites. */
    private final ResourceManager resources;

    /** The size of the scaled texture origin. */
    private float scaledTextureOrigin;

    /** The width of the total map. */
    private float totalWidth;
    /** The height of the total map. */
    private float totalHeight;
    /** The size of the scaled texture. */
    private float scaledTextureSize;
    /** The X coordinate of the origin point for drawing the map. */
    private float originX;
    /** The Y coordinate of the origin point for drawing the map. */
    private float originY;

    /**
     * List of characterViews (used to show the caracters on the map).
     */
    private final List<ViewCharacter> characterViews;

    /**
     * List of viewEffects (used to show the effects on the map, such as explosions).
     */
    private final List<ViewEffect> effectViews;

    /**
     * Constructs a new ViewMap with the specified map grid and resource manager.
     * @param levelMap the 2D array of Square objects representing the map
     * @param characterViews list of characters views to draw
     * @param resources the ResourceManager to manage textures and resources
     */
    public ViewMap(LevelMap levelMap, List<ViewCharacter> characterViews, ResourceManager resources) {
        this.levelMap = levelMap;
        this.resources = resources;
        this.characterViews = characterViews;
        this.effectViews = new ArrayList<>();

        updateDimensions();
    }

    /**
     * Method called by libGDX to draw the map.
     * It draws each map square by iterating through each square in the grid.
     * @param batch the Batch used for drawing
     * @param parentAlpha the alpha value of the parent actor
     */
    @Override
    public void draw(Batch batch, float parentAlpha) {
        updateDimensions();

        drawMap(batch);
        drawCharacters(batch, parentAlpha);
        drawEffects(batch);
    }

    private void drawCharacters(Batch batch, float parentAlpha) {
        for(ViewCharacter character : characterViews) {
            character.draw(batch, parentAlpha);
        }
    }

    /**
     * Draws the map.
     * @param batch the Batch used for drawing
     */
    private void drawMap(Batch batch) {
        for (int col = 0; col < levelMap.getHeight(); col++) {
            for (int row = 0; row < levelMap.getWidth(); row++) {
                Square square = levelMap.getSquare(row, col);

                // Background
                TextureRegion squareTextureRegion = resources.getMapSkin().getAtlas().findRegion(square.getTextureName());
                drawTextureRegion(batch, squareTextureRegion, row, col, square.computeRotation());

                // MapElement
                if (square.hasMapElement()) {
                    TextureRegion mapElementTextureRegion = null;

                    // Wall
                    if (square.getMapElement() instanceof Wall) {
                        mapElementTextureRegion = resources.getMapSkin().getAtlas().findRegion(square.getMapElement().getTextureName());
                    }
                    // Bonus
                    else if (square.getMapElement() instanceof Bonus) {
                        mapElementTextureRegion = resources.getMapSkin().getAtlas().findRegion(square.getMapElement().getTextureName());
                    }
                    // Bomb
                    else if (square.getMapElement() instanceof Bomb) {
                        // Bomb tick
                        if (square.getMapElement() instanceof TimeBomb) {
                            ((TimeBomb) square.getMapElement()).tick(levelMap, Gdx.graphics.getDeltaTime());
                        }

                        if (square.hasMapElement()) {
                            Gdx.app.debug("ViewMap", "Drawing bomb + " + (square.getMapElement().getTextureName()));
                            mapElementTextureRegion = resources.getMapSkin().getAtlas().findRegion(square.getMapElement().getTextureName());
                        }
                    }

                    // Actually drawing the mapElement
                    if (mapElementTextureRegion != null && square.hasMapElement()) {
                        drawTextureRegion(batch, mapElementTextureRegion, row, col, square.getMapElement().computeRotation());
                    }
                }
            }
        }
    }

    /**
     * Draws / updates all the queued effects
     * @param batch the Batch used for drawing
     */
    private void drawEffects(Batch batch) {
        float delta = Gdx.graphics.getDeltaTime();

        while (!levelMap.getEffectsQueue().empty()) {
            effectViews.add(new ViewEffect(levelMap.getEffectsQueue().pop()));
        }

        // Iterating from end to start so that removing elements doesn't cause IndexOutOfBounds
        for (int i = effectViews.size() - 1; i >= 0; i--) {
            ViewEffect effect = effectViews.get(i);

            // Updating animation & removing it if finished
            boolean isActive = effect.update(delta);
            if (!isActive) {
                effectViews.remove(i);
                continue;
            }

            // Drawing
            TextureRegion effectTexture = resources.getMapSkin()
                .getAtlas()
                .findRegion(effect.getCurrentTextureName());

            if (effectTexture != null) {
                drawTextureRegion(batch, effectTexture, (int) effect.getX(), (int) effect.getY(), 0f, 2f);
            }
        }
    }

    /**
     * Updates the dimensions / coordinates used to draw the map.
     */
    private void updateDimensions() {
        int centerX = Gdx.graphics.getWidth() / 2;
        int centerY = Gdx.graphics.getHeight() / 2;

        scaledTextureSize = Constants.TEXTURE_SIZE * Constants.SCALE;
        scaledTextureOrigin = scaledTextureSize / 2f;
        totalWidth = Constants.TEXTURE_SIZE * levelMap.getWidth() * Constants.SCALE;
        totalHeight = Constants.TEXTURE_SIZE * levelMap.getHeight() * Constants.SCALE;

        originX = centerX - totalWidth / 2;
        originY = centerY - totalHeight / 2;
    }

    private void drawTextureRegion(Batch batch, TextureRegion textureRegion, int row, int col, float rotation, float scale) {
        batch.draw(
            textureRegion,
            originX + (row * scaledTextureSize),
            originY + (col * scaledTextureSize),
            scaledTextureOrigin,
            scaledTextureOrigin,
            scaledTextureSize,
            scaledTextureSize,
            scale,
            scale,
            rotation
        );
    }

    private void drawTextureRegion(Batch batch, TextureRegion textureRegion, int row, int col, float rotation) {
        drawTextureRegion(batch, textureRegion, row, col, rotation, 1f);
    }
}
