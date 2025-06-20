package com.bomber7.core.components;

import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.bomber7.core.ResourceManager;
import com.bomber7.core.model.entities.Player;
import com.bomber7.utils.Dimensions;
import com.bomber7.utils.MVCComponent;

/**
 * Represents a scoreboard for a player in the Bomber7 game.
 * This scoreboard displays the player's score, health, and number of bombs.
 */
public class PlayerScoreBoard extends Table implements MVCComponent {

    /** The player associated with this scoreboard. */
    private Player player;
    /** The label displaying the player's score. */
    private Label scoreLabel;
    /** The label displaying the player's health. */
    private Label healthLabel;
    /** The label displaying the player's bombs. */
    private Label bombLabel;
    /** The image displaying the player's life. */
    private Image heartImage;
    /** The image displaying the player's bombs. */
    private Image bombImage;

    /**
     * The player's scoreboard.
     * @param player the player to which this scoreboard belongs
     */
    public PlayerScoreBoard(Player player) {
        this.player = player;

        initView();
        initController();
    }

    @Override
    public void initView() {
//        setDebug(true);
        final int cols = 6;
        Label nameLabel = new Label(player.getName(), ResourceManager.getInstance().getSkin());
        nameLabel.setAlignment(Align.center);
        scoreLabel = new Label("", ResourceManager.getInstance().getSkin());
        healthLabel = new Label("", ResourceManager.getInstance().getSkin());
        bombLabel = new Label("", ResourceManager.getInstance().getSkin());

        Image coinImage = new Image(ResourceManager.getInstance().getSkin().getDrawable("coin"));
        bombImage = new Image(ResourceManager.getInstance().getSkin().getDrawable("bomb"));
        heartImage = new Image(ResourceManager.getInstance().getSkin().getDrawable("heart_full.9"));

        // Name
        this.add(nameLabel)
            .fillX()
            .center()
            .colspan(cols)
            .spaceBottom(Dimensions.COMPONENT_SPACING_XS)
            .row();

        // Score
        this.add(coinImage)
            .spaceRight(Dimensions.COMPONENT_SPACING_XXS);
        this.add(scoreLabel)
            .spaceRight(Dimensions.COMPONENT_SPACING_XS);

        // Health
        this.add(heartImage)
            .spaceRight(Dimensions.COMPONENT_SPACING_XXS);
        this.add(healthLabel)
            .spaceRight(Dimensions.COMPONENT_SPACING_XS);

        // Bomb
        this.add(bombImage)
            .spaceRight(Dimensions.COMPONENT_SPACING_XXS);
        this.add(bombLabel)
            .spaceRight(Dimensions.COMPONENT_SPACING_XS);
    }

    @Override
    public void initController() {

    }

    /**
     * Update view accordingly to current player state.
     */
    public void refresh() {
        refreshScore();
        refreshHealth();
        refreshBomb();
    }

    /**
     * Refreshes all the view elements related to player's health.
     */
    private void refreshHealth() {
        if (player.isAlive()) {
            healthLabel.setColor(ResourceManager.getInstance().getSkin().getColor("white"));
            heartImage.setDrawable(ResourceManager.getInstance().getSkin().getDrawable("heart_full.9"));
        } else {
            healthLabel.setColor(ResourceManager.getInstance().getSkin().getColor("darkRed"));
            heartImage.setDrawable(ResourceManager.getInstance().getSkin().getDrawable("heart_none.9"));
        }
        healthLabel.setText(player.getLife());
    }

    /**
     * Refreshes all the view elements related to player's score.
     */
    private void refreshScore() {
        scoreLabel.setText(player.getScore());
    }


    /**
     * Refreshes all the view elements related to player's bombs.
     */
    private void refreshBomb() {
        bombLabel.setText(player.getNbBomb());
    }
}
