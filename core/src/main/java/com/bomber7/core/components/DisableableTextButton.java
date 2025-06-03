package com.bomber7.core.components;

import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

public class DisableableTextButton extends TextButton {
    public DisableableTextButton(String text, Skin skin, String styleName) {
        super(text, skin, styleName);
    }

    public void disable() {
        this.setTouchable(Touchable.disabled);
        this.setStyle(
            this.getSkin().get("inactive", TextButton.TextButtonStyle.class
            ));

    }

    public void enable() {
        this.setTouchable(Touchable.enabled);
        this.setStyle(
            this.getSkin().get("default", TextButton.TextButtonStyle.class
            ));
    }

    public void toggle(boolean expression) {
        if (expression) {
            enable();
        } else {
            disable();
        }
    }
}
