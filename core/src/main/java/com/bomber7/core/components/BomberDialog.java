package com.bomber7.core.components;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.bomber7.utils.Dimensions;

import java.util.function.Consumer;

/**
 * A custom dialog used in the game to confirm user actions, with styled buttons and title.
 * <p>
 * This dialog includes a title, a message, and two buttons for positive and negative responses.
 * It also allows setting a callback through {@link #setOnResult(Consumer)} to handle the user's choice.
 */
public class BomberDialog extends Dialog {
    /**
     * A callback function that consumes the result of the dialog.
     * If the user clicks the "yes" button, {@code true} is passed; if "no", {@code false}.
     */
    private Consumer<Boolean> onResult;

    /**
     * Creates a new {@code BomberDialog} with a title, a message, and two styled buttons.
     *
     * @param title      the title
     * @param text       the message displayed in the content area
     * @param yesBtnText the label for the positive/confirmation button
     * @param noBtnText  the label for the negative/cancel button
     * @param skin       the skin
     */
    public BomberDialog(String title, String text, String yesBtnText, String noBtnText, Skin skin) {
        super(title, skin);

        Label titleLabel = getTitleLabel();
        titleLabel.setStyle(skin.get("medium", Label.LabelStyle.class));
        getTitleTable().getCell(titleLabel).padTop(Dimensions.COMPONENT_SPACING);

        text(text);

        button(
            noBtnText,
            false,
            skin.get("red", TextButton.TextButtonStyle.class)
        );

        button(
            yesBtnText,
            true,
            skin.get("green", TextButton.TextButtonStyle.class)
        );


        getContentTable().padTop(Dimensions.COMPONENT_SPACING);
        getContentTable().padBottom(Dimensions.COMPONENT_SPACING);

        getButtonTable().getCells().forEach(cell -> {
            cell.padLeft(Dimensions.COMPONENT_SPACING_LG);
            cell.padRight(Dimensions.COMPONENT_SPACING_LG);
        });

    }

    @Override
    public Dialog show(Stage stage) {
        super.show(stage);
        getColor().a = 1f; // force fully visible immediately
        clearActions();    // clear any fadeIn actions
        return this;
    }

    /**
     * Sets a callback that will be triggered with the result of the dialog.
     *
     * @param onResult a consumer that accepts {@code true} for "yes" and {@code false} for "no"
     */
    public void setOnResult(Consumer<Boolean> onResult) {
        this.onResult = onResult;
    }

    @Override
    protected void result(Object object) {
        if (onResult != null && object instanceof Boolean) {
            onResult.accept((Boolean) object);
        }
    }
}
