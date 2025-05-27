package com.bomber7.core.model.square;

public class TimeBomb extends Bomb {
    private int timer;

    public TimeBomb(int p, String spriteFilePath, int t) {
        super(p, spriteFilePath);
        this.timer = t;
    }

    public void tick(Map map){
        if (timer > 0) {
            timer--;
            if (timer == 0) {
                activateBomb(map);
            }
        }
    }

    public int getTimer(){
        return this.timer;
    }

    public void setTimer(int t){
        this.timer = t;
    }

}
