package src.main.java.com.bomber7.core.model;

public class TriggerBomb extends Bomb {

    public TriggerBomb(int p, String spriteFilePath){
        super(p, spriteFilePath);
    }

    public void trigger(Map map) {
        activateBomb(map);
    }
}

