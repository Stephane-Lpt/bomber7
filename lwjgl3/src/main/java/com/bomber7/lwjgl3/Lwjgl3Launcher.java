package com.bomber7.lwjgl3;

import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.bomber7.core.BomberGame;

/** Launches the desktop (LWJGL3) application. */
public class Lwjgl3Launcher {
    private static final int DESKTOP_MIN_WIDTH = 1920;
    private static final int DESKTOP_MIN_HEIGHT = 1080;
    private static final String APPLICATION_NAME = "Bomber7";

    /**
     * The main method to launch the Bomber7 game on desktop platforms.
     * It checks if a new JVM instance is required (for macOS support) and
     * creates the application with the default configuration.
     *
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {
        if (StartupHelper.startNewJvmIfRequired()) return; // This handles macOS support and helps on Windows.
        createApplication();
    }

    private static Lwjgl3Application createApplication() {
        return new Lwjgl3Application(new BomberGame(), getDefaultConfiguration());
    }

    private static Lwjgl3ApplicationConfiguration getDefaultConfiguration() {
        Lwjgl3ApplicationConfiguration configuration = new Lwjgl3ApplicationConfiguration();
        Graphics.DisplayMode displayMode = Lwjgl3ApplicationConfiguration.getDisplayMode();

        configuration.setTitle(APPLICATION_NAME);

        //// Vsync limits the frames per second to what your hardware can display, and helps eliminate
        //// screen tearing. This setting doesn't always work on Linux, so the line after is a safeguard.
        configuration.useVsync(true);

        //// Limits FPS to the refresh rate of the currently active monitor, plus 1 to try to match fractional
        //// refresh rates. The Vsync setting above should limit the actual FPS to match the monitor.
        configuration.setForegroundFPS(Lwjgl3ApplicationConfiguration.getDisplayMode().refreshRate + 1);
        //// If you remove the above line and set Vsync to false, you can get unlimited FPS, which can be
        //// useful for testing performance, but can also be very stressful to some hardware.
        //// You may also need to configure GPU drivers to fully disable Vsync; this can cause screen tearing.

//        configuration.setWindowedMode(640, 480);
        configuration.setWindowedMode(displayMode.width * 2/3, displayMode.height * 2/3);
        configuration.setWindowSizeLimits(
            DESKTOP_MIN_WIDTH,
            DESKTOP_MIN_HEIGHT,
                displayMode.width,
                displayMode.height
        );
//        configuration.setWindowIcon();

        return configuration;
    }
}
