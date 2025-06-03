package com.bomber7.core;

import com.badlogic.gdx.Screen;
import com.bomber7.core.screens.BomberScreen;
import com.bomber7.utils.ScreenType;

/**
 * Singleton class responsible for managing screen transitions in the game.
 * <p>
 * This class holds a reference to the main {@link BomberGame} instance and provides
 * functionality to initialize and switch between different screens based on
 * the {@link ScreenType} enum.
 * </p>
 * <p>
 * The screen transition approach is inspired by the guidelines available at
 * <url>https://www.pixnbgames.com/blog/libgdx/how-to-manage-screens-in-libgdx/</url>
 * </p>
 */
public final class ScreenManager {

    /**
     * The singleton instance of the ScreenManager.
     */
    private static ScreenManager instance;

    /**
     * Reference to the LibGDX Game object.
     */
    private BomberGame game;

    /**
     * Type of current screen.
     */
    private ScreenType currentScreenType = null;
    /**
     * Type of previous screen.
     */
    private ScreenType previousScreenType = null;

    /**
     * Private constructor to enforce singleton pattern.
     */
    private ScreenManager() {
        super();
    }

    /**
     * Retrieves the singleton instance of the {@code ScreenManager}.
     *
     * @return the single {@code ScreenManager} instance.
     */
    public static ScreenManager getInstance() {
        if (instance == null) {
            instance = new ScreenManager();
        }
        return instance;
    }

    /**
     * Initializes the screen manager with the main {@link BomberGame} instance.
     * <p>
     * This method must be called before any screen transitions can occur.
     * </p>
     *
     * @param bomberGame the {@code Game} instance used to manage screens.
     */
    public void initialize(BomberGame bomberGame) {
        this.game = bomberGame;
    }

    /**
     * Displays a new screen based on the specified {@link ScreenType}.
     * <p>
     * If a screen is currently active, it will be disposed before switching to the new screen.
     * </p>
     *
     * @param screenType the type of screen to be shown.
     */
    public void showScreen(ScreenType screenType) {
        Screen currentScreen = game.getScreen();

        previousScreenType = currentScreenType;
        currentScreenType = screenType;

        BomberScreen newScreen = screenType.getScreen(game);

        game.setScreen(newScreen);

        if (currentScreen != null) {
            currentScreen.dispose();
        }
    }

    /**
     * Sets the previous screen as the current screen (if not null).
     */
    public void showPreviousScreen() {
        if (previousScreenType != null) {
            showScreen(previousScreenType);
        }
    }
}
