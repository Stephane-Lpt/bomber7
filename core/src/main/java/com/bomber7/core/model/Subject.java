package com.bomber7.core.model;

import java.util.List;

public interface Subject {

    List<Observer> getObservers();

    default void registerObserver(Observer observer){
        getObservers().add(observer);
    }

    default void deleteObserver(Observer observer){
        getObservers().remove(observer);
    }

    default void notifyObservers(){
        for (Observer observer : getObservers()) {
            observer.refresh(Subject.this);
        }
    }
}
