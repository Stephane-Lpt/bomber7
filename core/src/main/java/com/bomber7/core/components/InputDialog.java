package com.bomber7.core.components;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import com.bomber7.core.ConfigManager;
import com.bomber7.core.ResourceManager;
import com.bomber7.utils.Controls;
import com.bomber7.utils.Dimensions;

/**
 * A dialog that listens for user key input to rebind controls for a specific player.
 * <p>
 * This dialog captures a single key press and sets it as the binding for a given control.
 * It closes either when a key is pressed or when the escape key is pressed to cancel.
 * </p>
 */
public class InputDialog extends Dialog {

    /**
     * The key used to close the InputDialog without modifying the input key.
     */
    private final int closeKey = Input.Keys.ESCAPE;

    /**
     * The index of the player this InputDialog is associated with.
     */
    private final int playerIndex;

    /**
     * Creates a new InputDialog for rebinding controls of a given player.
     *
     * @param resources the ResourceManager
     * @param playerIndex the index of the player whose control is being rebound
     */
    public InputDialog(ResourceManager resources, int playerIndex) {
        super(resources.getString("press_any_key"), resources.getSkin());

        this.playerIndex = playerIndex;

        Label titleLabel = getTitleLabel();
        getTitleTable().getCell(titleLabel)
            .padTop(Dimensions.COMPONENT_SPACING);
        titleLabel.setStyle(resources.getSkin().get("medium", Label.LabelStyle.class));
        titleLabel.setAlignment(Align.center);

        text(resources.getString("or_press_esc"));

        getContentTable().padTop(Dimensions.COMPONENT_SPACING);
    }

    /**
     * This function sets a custom InputProcessor to listen for keyDown actions as soon as the dialog is shown.
     * <p>
     *     If the {@code closeKey} is pressed, the dialog is hidden without modifying the playerConfig.
     *     If any other key is pressed, this key is set as the action for the control associated with this dialog.
     *     Then, the dialog is hidden.
     * </p>
     * @param control
     */
    private void startListening(Controls control) {
        Gdx.input.setInputProcessor(new InputAdapter() {
            @Override
            public boolean keyDown(int keycode) {
                Gdx.app.debug("InputDialog", Input.Keys.toString(keycode) + " (" + keycode + ") pressed.");
                if (keycode == closeKey) {
                    Gdx.app.debug(
                        "InputDialog",
                        "Closing dialog without modifying the binding."
                    );
                } else {
                    Gdx.app.debug(
                        "InputDialog",
                        "Setting it as " + control + " control for player " + playerIndex
                    );
                    ConfigManager.getInstance().getConfig().setPlayerControl(playerIndex, control, keycode);
                }
                InputDialog.this.hide();
                return true;
            }
        });
    }

    /**
     * A custom show function that, additionally to calling the super show method, calls
     * startListening and makes the dialog appear instantly instead of having a fade in.
     * @param stage the stage this dialog is bound to
     * @param control the control this dialog is supposed to modify
     * @return the Dialog
     */
    public Dialog show(Stage stage, Controls control) {
        super.show(stage);
        startListening(control);
        getColor().a = 1f;
        clearActions();
        return this;
    }
}
