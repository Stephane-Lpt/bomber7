public abstract class Character {
    private String name;
    private int x_coord;
    private int y_coord;
    private int life;
    private int speed;
    private String spriteFilePath;
    private LevelMap gameMap;

    public Character(String name, int x_coord, int y_coord, int life, int speed, String spriteFilePath, LevelMap gameMap) {
        this.name = name;
        this.x_coord = x_coord;
        this.y_coord = y_coord;
        this.life = life;
        this.speed = speed;
        this.spriteFilePath = spriteFilePath;
        this.gameMap = gameMap;
        loadSprite();
    }

    public void move(int x, int y) {
        if(checkMove(x, y, this.gameMap)) {
            this.x_coord += x;
            this.y_coord += y;
        }
    }


    private boolean checkMove(int x, int y, LevelMap map){
        //Check if the move is valid
        return true;
    }

    public String getName() {
        return name;
    }

    public int getX() {
        return x_coord;
    }

    public int getY() {
        return y_coord;
    }

    public int getLife() {
        return life;
    }

    public int getSpeed() {
        return speed;
    }

    private void loadSprite() {
        // Load the sprite from the file
        // This could be done using a library like JavaFX or Swing
    }
}
