package com.bomber7.core;

import com.bomber7.core.screens.BomberScreen;
import com.bomber7.utils.ScreenType;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * Singleton class responsible for managing screen transitions in the game.
 *
 * <p>This class holds a reference to the main {@link BomberGame} instance and provides
 * functionality to initialize and switch between different screens based on
 * the {@link ScreenType} enum.</p>
 *
 * <p>The screen transition approach is inspired by the guidelines available at:<br>
 * <a href="https://www.pixnbgames.com/blog/libgdx/how-to-manage-screens-in-libgdx/">
 * https://www.pixnbgames.com/blog/libgdx/how-to-manage-screens-in-libgdx/
 * </a></p>
 */
public final class ScreenManager {

    /**
     * The singleton instance of the ScreenManager.
     */
    private static ScreenManager instance;

    private ScreenType currentScreenType = null;

    private final Map<ScreenType, BomberScreen> screens = new HashMap<>();
    private final Stack<BomberScreen> screenStack = new Stack<>();

    /**
     * Reference to the LibGDX Game object.
     */
    private BomberGame game;

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

        for (ScreenType screenType : ScreenType.values()) {
            screens.put(screenType, null);
        }
    }

    /**
     * Displays a new screen based on the specified {@link ScreenType}.
     * <p>
     * If a screen is currently active, it will be disposed before switching to the new screen.
     * You can control whether the current screen should be remembered as the previous screen
     * using the {@code updatePreviousScreen} flag.
     * </p>
     *
     * @param screenType            the type of screen to be shown
     * @param params                optional parameters used to configure the new screen (e.g., level ID, player settings)
     */
    public void showScreen(ScreenType screenType, boolean pushScreenToStack, boolean saveScreen, Object... params) {
        BomberScreen oldScreen = (BomberScreen) game.getScreen();
        BomberScreen newScreen;

        if (screens.get(screenType) == null || !saveScreen) {
//            System.out.println("initializing screen " + screenType);
            newScreen = screenType.getScreen(game, params);
            screens.put(screenType, newScreen);
        } else {
//            System.out.println("screen already initialized. retreiving it from the stack");
            newScreen = screens.get(screenType);
        }

        if (currentScreenType != null) {
//            System.out.println("current screen is not null");
            if (pushScreenToStack) {
                if (saveScreen) {
//                    System.out.println("saving " + currentScreenType + " " + "screen to stack");
                    screenStack.push(oldScreen);
                } else {
//                    System.out.println("creating a new " + currentScreenType + " " + "and adding it to the stack");
                    screenStack.push(currentScreenType.getScreen(game));
                }
            }
        }

        currentScreenType = screenType;

        game.setScreen(newScreen);
    }


    public void showPreviousScreen(boolean pushScreenToStack, boolean saveScreen) {
//        System.out.println("trying to come back to previous screen");
        if (!screenStack.isEmpty()) {
            BomberScreen poppedScreen = screenStack.pop();

            if (game.getScreen() != null && !screens.containsValue(game.getScreen())) {
                game.getScreen().dispose();
            }

            showScreen(poppedScreen.getScreenType(), pushScreenToStack, saveScreen);
        }
    }
}
