package com.bomber7.core.model.square;

public class TriggerBomb extends Bomb {

    public TriggerBomb(int p, String spriteFilePath){
        super(p, spriteFilePath);
    }

    public void trigger(Map map) {
        activateBomb(map);
    }
}

