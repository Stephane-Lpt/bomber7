package com.bomber7.core.components;

import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

/**
 * A {@link TextButton} that supports being enabled or disabled.
 * When disabled, it changes its style to reflect its inactive state.
 */
public class DisableableTextButton extends TextButton {

    /**
     * Creates a new DisableableTextButton with the specified text, skin, and style.
     *
     * @param text      the button label
     * @param skin      the skin used to style the button
     * @param styleName the name of the style within the skin
     */
    public DisableableTextButton(String text, Skin skin, String styleName) {
        super(text, skin, styleName);
    }

    /**
     * Disabled the button and updates its style accordingly.
     */
    public void disable() {
        this.setTouchable(Touchable.disabled);
        this.setStyle(
            this.getSkin().get("inactive", TextButton.TextButtonStyle.class
            ));

    }


    /**
     * Enabled the button and updates its style accordingly.
     */
    public void enable() {
        this.setTouchable(Touchable.enabled);
        this.setStyle(
            this.getSkin().get("default", TextButton.TextButtonStyle.class
            ));
    }

    /**
     * Enables or disables the component based on the given expression.
     *
     * @param expression {@code true} to enable; {@code false} to disable
     */
    public void toggle(boolean expression) {
        if (expression) {
            enable();
        } else {
            disable();
        }
    }
}
