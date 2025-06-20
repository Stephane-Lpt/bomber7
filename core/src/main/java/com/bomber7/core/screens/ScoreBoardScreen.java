package com.bomber7.core.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.bomber7.core.ScreenManager;
import com.bomber7.core.components.BomberTable;
import com.bomber7.core.components.DisableableTextButton;
import com.bomber7.utils.ComponentsUtils;
import com.bomber7.core.model.entities.Character;
import com.bomber7.utils.Dimensions;
import com.bomber7.utils.ScreenType;
import com.bomber7.utils.SoundManager;
import com.bomber7.utils.SoundType;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Represents the scoreboard screen in the Bomber7 game.
 * This screen displays the scores of all players after a round ends.
 */
public class ScoreBoardScreen extends BomberScreen {

    /**
     * Button to stop the game and go to main menu.
     */
    private TextButton goToMainMenuButton;

    /**
     * Button to advance to the next round.
     */
    private DisableableTextButton nextRoundButton;

    /**
     * Initializes the screen.
     *
     * @param game the Game instance this screen belongs to
     */
    public ScoreBoardScreen(Game game) {
        super(game);

        initView();
        initController();
    }

    @Override
    public void initView() {
        final int cols = 2;

        BomberTable table = new BomberTable();
        table.setFillParent(true);

        Label scoreboardLabel = new Label(resources.getString("scoreboard"), resources.getSkin(), "large");
        goToMainMenuButton = new TextButton(resources.getString("go_back_to_main_menu"), resources.getSkin());
        nextRoundButton = new DisableableTextButton(resources.getString("next_round"), resources.getSkin(), "default");

        table.setTitle(scoreboardLabel, cols);

        List<Character> sortedCharacters = new ArrayList<>(game.getCurrentMap().getCharacters());
        Collections.sort(sortedCharacters);

        for (Character character : sortedCharacters) {
            Image coinImage = new Image(resources.getSkin().getDrawable("coin"));
            Label scoreLabel = new Label(Integer.toString(character.getScore()), resources.getSkin(), "medium");
            Label playerNameLabel = new Label(character.getName(), resources.getSkin(), "medium");

            Table scoreTable = new Table();

            scoreTable.add(coinImage);
            scoreTable.add(scoreLabel)
                .spaceLeft(Dimensions.COMPONENT_SPACING_LG);

            table.add(scoreTable)
                .left()
                .spaceBottom(Dimensions.COMPONENT_SPACING_LG)
                .padLeft(Dimensions.COMPONENT_SPACING_XL);
            table.add(playerNameLabel)
                .right()
                .spaceBottom(Dimensions.COMPONENT_SPACING_LG)
                .padRight(Dimensions.COMPONENT_SPACING_XL);
            table.row();
        }

        table.setupDoubleButtons(goToMainMenuButton, nextRoundButton, cols);

        if (game.isLastRound()) {
            SoundManager.getInstance().resetFightMusic();
            SoundManager.getInstance().play(SoundType.GAME_OVER);
            nextRoundButton.disable();
        }

        super.addActor(table);
    }

    @Override
    public void initController() {
        goToMainMenuButton.addListener(ComponentsUtils.addSoundEffect(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ScreenManager.getInstance().showScreen(ScreenType.MAIN_MENU, false, false);
                game.stop();
            }
        }));

        if (!game.isLastRound()) {
            nextRoundButton.addListener(ComponentsUtils.addSoundEffect(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    game.nextRound();
                }
            }));
        }
    }

    @Override
    public ScreenType getScreenType() {
        return ScreenType.SCOREBOARD;
    }
}
