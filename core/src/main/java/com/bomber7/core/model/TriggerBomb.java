package src.main.java.com.bomber7.core.model;

public class TriggerBomb extends Bomb {

    public TriggerBomb(int power){
        super(power);
    }

    public void trigger(Map map) {
        activateBomb(map);
    }
}

