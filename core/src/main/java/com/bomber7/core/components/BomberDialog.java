package com.bomber7.core.components;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.bomber7.utils.Dimensions;

import java.util.function.Consumer;

public class BomberDialog extends Dialog {
    private Consumer<Boolean> onResult;

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
