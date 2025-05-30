package com.bomber7.core.components;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Scaling;
import com.bomber7.utils.*;

import java.util.Observable;
import java.util.Observer;

public class PlayerSelector extends Table implements MVCComponent, Observer {
    public static final float SELECTOR_WIDTH = 175f;
    public static final float SELECTOR_HEIGHT = 350;

    private final int playerIndex;

    private ResourceManager resources;

    private final PlayerBlueprintObservable playerBlueprint;

    private TextField nameTextField;
    private TextButton addPlayerButton;
    private Label strategyLabel;
    private ImageButton characterBackgroundButton;
    private Image characterImage;
    private Table strategyTable;
    private TextButton changePlayerSkinLeftButton;
    private TextButton changePlayerSkinRightButton;
    private TextButton changePlayerStrategyLeftButton;
    private TextButton changePlayerStrategyRightButton;

    public PlayerSelector(ResourceManager r, PlayerBlueprintObservable o, int i) {
//        this.setDebug(true);

        this.resources = r;
        this.playerBlueprint = o;
        this.playerIndex = i;

        initView();
        initController();

        playerBlueprint.addObserver(this);
        playerBlueprint.notifyChanged();
    }

    @Override
    public void initView() {
        strategyTable = new Table();
        nameTextField = new TextField("", resources.getSkin());
        nameTextField.setAlignment(Align.center);
        nameTextField.setMaxLength(Constants.MAX_PLAYER_NAME_LENGTH);
        strategyLabel = new Label("", resources.getSkin());

        addPlayerButton = new TextButton(resources.getString("add"), resources.getSkin(), "player-add");
        characterBackgroundButton = new ImageButton(resources.getSkin(), "character-selector-valid");
        characterImage = new Image(resources.getSkin().getDrawable("transparent-bg"));
        characterImage.setTouchable(Touchable.disabled);

        addPlayerButton.setWidth(SELECTOR_WIDTH);
        addPlayerButton.setHeight(SELECTOR_HEIGHT);
        characterImage.setWidth(SELECTOR_WIDTH);
        characterImage.setScaling(Scaling.fit);
        characterBackgroundButton.setWidth(SELECTOR_WIDTH);
        characterBackgroundButton.setHeight(SELECTOR_HEIGHT);

        Stack stack = new Stack();
        stack.add(addPlayerButton);
        stack.add(characterBackgroundButton);
        stack.add(characterImage);

        changePlayerSkinLeftButton = new TextButton("<", resources.getSkin(), "transparent-lg");
        changePlayerSkinRightButton = new TextButton(">", resources.getSkin(), "transparent-lg");
        changePlayerStrategyLeftButton = new TextButton("<", resources.getSkin(), "transparent-sm");
        changePlayerStrategyRightButton = new TextButton(">", resources.getSkin(), "transparent-sm");

        this.add(nameTextField)
            .colspan(3)
            .fillX();
        this.row();

        this.add(changePlayerSkinLeftButton);
        this.add(stack)
            .width(SELECTOR_WIDTH)
            .height(SELECTOR_HEIGHT)
            .pad(
                Dimensions.COMPONENT_SPACING / 4f,
                Dimensions.COMPONENT_SPACING / 3f,
                Dimensions.COMPONENT_SPACING / 4f,
                Dimensions.COMPONENT_SPACING / 3f
            );
        this.add(changePlayerSkinRightButton);
        this.row();

        strategyTable.add(changePlayerStrategyLeftButton)
            .expandX()
            .fillX();
        strategyTable.add(strategyLabel)
            .center();
        strategyTable.add(changePlayerStrategyRightButton)
            .expandX()
            .fillX();

        this.add(strategyTable)
            .colspan(3)
            .fillX();

        this.row();
    }

    @Override
    public void initController() {
        nameTextField.setTextFieldListener((textField, c) -> {
            playerBlueprint.setName(textField.getText());
        });

        // Buttons to change player's skin
        changePlayerSkinLeftButton.addListener(ComponentsUtils.addSoundEffect(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                playerBlueprint.setCharacter(playerBlueprint.getCharacter().previous());
            }
        }, resources));
        changePlayerSkinRightButton.addListener(ComponentsUtils.addSoundEffect(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                playerBlueprint.setCharacter(playerBlueprint.getCharacter().next());
            }
        }, resources));

        // Buttons to change AI difficulty (Peaceful, Beginner or Pro)
        changePlayerStrategyLeftButton.addListener(ComponentsUtils.addSoundEffect(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                playerBlueprint.setStrategy(playerBlueprint.getStrategy().previous());
            }
        }, resources));
        changePlayerStrategyRightButton.addListener(ComponentsUtils.addSoundEffect(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                playerBlueprint.setStrategy(playerBlueprint.getStrategy().next());
            }
        }, resources));

        addPlayerButton.addListener(ComponentsUtils.addSoundEffect(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                playerBlueprint.initialize(
                    resources.getString("player") + " " + (playerIndex + 1)
                );
                // TODO : is this notifyObserver necessary? Doesn't it notify on subscribe?
                // TODO : make sure that observers are unsubscribed at some point
                playerBlueprint.notifyObservers();
            }
        }, resources));

        characterBackgroundButton.addListener(ComponentsUtils.addSoundEffect(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                playerBlueprint.dispose();
            }
        }, resources));
    }

    @Override
    public void update(Observable observable, Object o) {
        System.out.println("updating playerselector");

        updateCharacterBackground();
        updateNameTextField();
        updatePlayerStrategyTable();
    }

    private void updateCharacterBackground() {
        if (playerBlueprint.isDisposed()) {
            addPlayerButton.setVisible(true);

            characterBackgroundButton.setVisible(false);
            characterImage.setVisible(false);
            changePlayerSkinLeftButton.setVisible(false);
            changePlayerSkinRightButton.setVisible(false);
        } else {
            addPlayerButton.setVisible(false);

            characterBackgroundButton.setVisible(true);
            characterImage.setVisible(true);
            changePlayerSkinLeftButton.setVisible(true);
            changePlayerSkinRightButton.setVisible(true);

            characterImage.setDrawable(resources.getSkin().getDrawable(playerBlueprint.getCharacter().drawableName()));

            if (playerBlueprint.isValid()) {
                characterBackgroundButton.setStyle(
                    resources.getSkin().get("character-selector-valid", ImageButton.ImageButtonStyle.class
                ));
            } else {
                characterBackgroundButton.setStyle(
                    resources.getSkin().get("character-selector-locked", ImageButton.ImageButtonStyle.class
                ));
            }
        }
    }

    private void updateNameTextField() {
        if (playerBlueprint.isDisposed()) {
            nameTextField.setVisible(false);
            nameTextField.setText("");
        } else {
            nameTextField.setVisible(true);
            nameTextField.setText(playerBlueprint.getName());
        }
    }

    private void updatePlayerStrategyTable() {
        if (playerBlueprint.isDisposed()) {
            strategyTable.setVisible(false);
        } else {
            strategyTable.setVisible(true);

            switch (playerBlueprint.getStrategy()) {
                case HUMAN:
                    strategyLabel.setText(resources.getString("strategy_human"));
                    break;
                case PEACEFUL:
                    strategyLabel.setText(resources.getString("strategy_peaceful"));
                    break;
                case BEGINNER:
                    strategyLabel.setText(resources.getString("strategy_beginner"));
                    break;
                case PRO:
                    strategyLabel.setText(resources.getString("strategy_pro"));
                    break;
            }
        }
    }
}
