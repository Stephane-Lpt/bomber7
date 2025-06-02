package com.bomber7.core.components;

import java.util.Observable;
import java.util.Observer;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Scaling;
import com.bomber7.utils.Dimensions;
import com.bomber7.utils.Constants;
import com.bomber7.utils.ComponentsUtils;
import com.bomber7.utils.PlayerBlueprintObservable;
import com.bomber7.utils.MVCComponent;
import com.bomber7.core.ResourceManager;

/**
 * A component used in {@link com.bomber7.core.screens.PlayerSelectionScreen} to add / remove / setup
 * players in a game.
 * It observes the {@link PlayerBlueprintObservable} that is linked to it and also modifies it
 * based on user's interactions (changing the name, skin, strategy...)
 */
public class PlayerSelector extends Table implements MVCComponent, Observer {
    /**
     * The index of the player this selector represents.
     */
    private final int playerIndex;

    /**
     * Manages access to localized strings and graphical resources.
     * */
    private final ResourceManager resources;

    /**
     * Observable blueprint containing player configuration and strategy.
     */
    private final PlayerBlueprintObservable playerBlueprint;

    /**
     Table containing strategyLabel and changePlayerStrategyButtons.
     */
    private Table strategyTable;

    /**
     * TextField to edit the player's name.
     */
    private TextField nameTextField;

    /**
     * Button that appears when a player is not added.
     */
    private TextButton addPlayerButton;

    /**
     * Label displaying the current player's strategy.
     */
    private Label strategyLabel;

    /**
     * ImageButton that shows the character's skin background (when the player is added).
     */
    private ImageButton characterBackgroundButton;

    /**
     * ImageButton that shows the character's skin (when the player is added).
     */
    private Image characterImage;

    /**
     * Button to cycle to the previous character skin.
     */
    private TextButton changePlayerSkinLeftButton;

    /**
     * Button to cycle to the next character skin.
     */
    private TextButton changePlayerSkinRightButton;

    /**
     * Button to switch to the previous strategy.
     */
    private TextButton changePlayerStrategyLeftButton;

    /**
     * Button to switch to the next strategy.
     */
    private TextButton changePlayerStrategyRightButton;


    /**
     * Constructs a new {@code PlayerSelector} which provides a UI component for configuring a player's settings.
     *
     * @param r the {@link ResourceManager} the game resources.
     * @param o the {@link PlayerBlueprintObservable} the player blueprint that the selector will be linked to.
     * @param i the index of the player.
     * <p>
     * Initializes the view and controller components, registers this selector as an observer of the player blueprint,
     * and triggers an initial update to sync the UI with the player's current state.
     * </p>
     */
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
        final int cols = 3;

        strategyTable = new Table();
        nameTextField = new TextField("", resources.getSkin());
        nameTextField.setAlignment(Align.center);
        nameTextField.setMaxLength(Constants.MAX_PLAYER_NAME_LENGTH);
        strategyLabel = new Label("", resources.getSkin());

        addPlayerButton = new TextButton(resources.getString("add"), resources.getSkin(), "player-add");
        characterBackgroundButton = new ImageButton(resources.getSkin(), "character-selector-valid");
        characterImage = new Image(resources.getSkin().getDrawable("transparent-bg"));
        characterImage.setTouchable(Touchable.disabled);

        addPlayerButton.setWidth(Dimensions.PLAYER_SELECTOR_WIDTH);
        addPlayerButton.setHeight(Dimensions.PLAYER_SELECTOR_HEIGHT);
        characterImage.setWidth(Dimensions.PLAYER_SELECTOR_WIDTH);
        characterImage.setScaling(Scaling.fit);
        characterBackgroundButton.setWidth(Dimensions.PLAYER_SELECTOR_WIDTH);
        characterBackgroundButton.setHeight(Dimensions.PLAYER_SELECTOR_HEIGHT);

        Stack stack = new Stack();
        stack.add(addPlayerButton);
        stack.add(characterBackgroundButton);
        stack.add(characterImage);

        changePlayerSkinLeftButton = new TextButton("<", resources.getSkin(), "transparent-lg");
        changePlayerSkinRightButton = new TextButton(">", resources.getSkin(), "transparent-lg");
        changePlayerStrategyLeftButton = new TextButton("<", resources.getSkin(), "transparent-sm");
        changePlayerStrategyRightButton = new TextButton(">", resources.getSkin(), "transparent-sm");

        this.add(nameTextField)
            .colspan(cols)
            .fillX();
        this.row();

        this.add(changePlayerSkinLeftButton);
        this.add(stack)
            .width(Dimensions.PLAYER_SELECTOR_WIDTH)
            .height(Dimensions.PLAYER_SELECTOR_HEIGHT)
            .pad(
                Dimensions.COMPONENT_SPACING_SM,
                Dimensions.COMPONENT_SPACING_XS,
                Dimensions.COMPONENT_SPACING_SM,
                Dimensions.COMPONENT_SPACING_XS
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
            .colspan(cols)
            .fillX();

        this.row();
    }

    @Override
    public void initController() {
        nameTextField.setTextFieldListener((textField, c) -> playerBlueprint.setName(textField.getText()));

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
        updateCharacterBackground();
        updateNameTextField();
        updatePlayerStrategyTable();
    }

    /**
     * Updates the character background accordingly to the linked {@link PlayerBlueprintObservable} state.
     * <p>
     *     If the player blueprint is disposed, the button "add" is shown and other buttons are hidden.
     *     If it is not, the current skin of the player appears, as well as two buttons to cycle through
     *     existing skins. If the selected skin has yet to be unlocked, the background appears in red.
     * </p>
     */
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

            characterImage.setDrawable(resources.getSkin().getDrawable(playerBlueprint.getCharacter().getDrawableName()));

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

    /**
     * Updates the player name field accordingly to the linked {@link PlayerBlueprintObservable} state.
     * <p>
     *     If the player blueprint is disposed, the name field is hidden. If not, is it visible.
     * </p>
     */
    private void updateNameTextField() {
        if (playerBlueprint.isDisposed()) {
            nameTextField.setVisible(false);
            nameTextField.setText("");
        } else {
            nameTextField.setVisible(true);
            nameTextField.setText(playerBlueprint.getName());
        }
    }

    /**
     * Updates the player strategy table accordingly to the linked {@link PlayerBlueprintObservable} state.
     * <p>
     *     If the player blueprint is disposed, the table is hidden.
     *     If it is not disposed, the table is visible and strategyLabel shows the current strategy.
     * </p>
     */
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
                default:
                    strategyLabel.setText(resources.getString("error"));
            }
        }
    }
}
