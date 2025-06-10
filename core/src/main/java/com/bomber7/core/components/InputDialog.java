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

public class InputDialog extends Dialog {

    public final static int CLOSE_KEY = Input.Keys.ESCAPE;

    public final int playerIndex;

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

    private void startListening(Controls control) {
        Gdx.input.setInputProcessor(new InputAdapter() {
            @Override
            public boolean keyDown(int keycode) {
                Gdx.app.debug("InputDialog", Input.Keys.toString(keycode) + " (" + keycode + ") pressed.");
                if (keycode == CLOSE_KEY) {
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

    public Dialog show(Stage stage, Controls control) {
        super.show(stage);
        startListening(control);
        getColor().a = 1f;
        clearActions();
        return this;
    }
}
