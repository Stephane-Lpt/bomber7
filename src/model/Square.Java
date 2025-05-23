package model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Square {
    private static final float SIZE = 64F;

    private String name; // Identificateur du carré (herbe, mur, etc.)
    private String spriteFilePath; // Chemin vers l'image du sprite
    
    private float x,y;
    private Texture texture;

    public Square(String name, float x, float y, String spriteFilePath) {
        this.x = x;
        this.y = y;
        this.name = name;
        this.texture = new Texture(Gdx.files.internal(spriteFilePath));
        this.spriteFilePath = spriteFilePath;
    }

    // Setters & Getters

    public String getName() {
        return name;
    }
    
    public float getX() {
        return x;
    }
    public float getY() {
        return y;
    }

    public String getSpriteFilePath() {
        return spriteFilePath;
    }
    
    public void setTexture(Texture texture) {
        if (this.texture != null) {
            this.texture.dispose();
        } 
        this.texture = texture;
    }
}