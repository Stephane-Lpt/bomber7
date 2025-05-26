package com.bomber7.core.model;

/**
 * Observer in the Observer design pattern.
 * Implementing classes receive updates from a {@link Subject} when its state changes.
 */
public interface Observer {

    /**
     * Called by the subject to notify this observer of a state change.
     *
     * @param subject the subject whose state has changed
     */
    void refresh(Subject subject);
}
