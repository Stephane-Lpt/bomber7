package com.bomber7.core.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Subject in the Observer design pattern.
 * Maintains a list of observers and notifies them of state changes.
 */
public interface Subject {

    /**
     * Returns the list of registered observers.
     * Implementing classes must manage and return the actual observer list.
     *
     * @return the list of observers
     */
    List<Observer> getObservers();

    /**
     * Registers an observer to receive updates.
     *
     * @param observer the observer to be added
     */
    default void registerObserver(Observer observer) {
        getObservers().add(observer);
    }

    /**
     * Unregisters an observer so it no longer receives updates.
     *
     * @param observer the observer to be removed
     */
    default void deleteObserver(Observer observer) {
        getObservers().remove(observer);
    }

    /**
     * Notifies all registered observers of a state change.
     */
    default void notifyObservers() {
        for (Observer observer : getObservers()) {
            observer.refresh(Subject.this);
        }
    }
}
