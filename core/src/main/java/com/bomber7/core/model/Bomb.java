package src.main.java.com.bomber7.core.model;

import com.bomber7.core.model.LevelMap;
import com.bomber7.core.model.square.Square;
import com.bomber7.core.model.square.UnbreakableWall;
import com.bomber7.core.model.square.BreakableWall;

public class Bomb extends Square {

    private int power;

    public Bomb(int p, String spriteFilePath){
        super(spriteFilePath);
        this.power = p;
    }

    public int getPower(){
        return this.power;
    }

    public void onExplosion(LevelMap m, int x, int y){
        Square sq = m.getSquare(x,y);
        // rajout effet d'explosion
    }

    public void activateBomb(LevelMap m) {

        int bombX;
        int bombY;

        // Explosion à la position de la bombe
        onExplosion(m,bombX,bombY);

        // Propagation : haut, bas, gauche, droite
        int[][] directions = { {0, 1}, {0, -1}, {-1,0}, {1,0}};

        for (int[] direction : directions) {

            for (int i = 1; i <= power; i++) {
                int newX = bombX + direction[0] * i;
                int newY = bombY + direction[1] * i;

                Square potentialSquare = m.getSquare(newX, newY);

                // Hors map ou Mur incassable - arrêter la propagation
                if (potentialSquare == null || potentialSquare.getMapElement() instanceof UnbreakableWall) {
                    break; // va pas plus loin
                }

                if (potentialSquare.getMapElement() instanceof BreakableWall) {
                    onExplosion(m,newX,newY);
                    break; // va pas plus loin -> car pas explosable les prochains
                }
                onExplosion(m,newX,newY);
            }
        }
    }

    public void setPower(int p){
        if (p > 0) {
            this.power = p;
        } else {
            throw new IllegalArgumentException("Power must be positive");
        }
    }
}
