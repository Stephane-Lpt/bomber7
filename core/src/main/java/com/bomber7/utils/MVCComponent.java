package com.bomber7.utils;

/**
 * An interface that all UI classes should implement to separate the View and the Controller.
 * Used to make the code structure MVC look alike
 */
public interface MVCComponent {
    /**
     * The method where all the view components should be instantiated and placed.
     */
    void initView();

    /**
     * The method where all the controller logic should be created (such as binding buttons, etc...).
     */
    void initController();
}
