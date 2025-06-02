package com.bomber7.core.components;

import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.bomber7.utils.Dimensions;

public class BomberTable extends Table {

    public void setupDoubleButtons(Button b1, Button b2, int colspan) {
        Table nestedTable = new Table();

        nestedTable.add(b1)
            .width(Dimensions.BUTTON_WIDTH)
            .height(Dimensions.BUTTON_HEIGHT)
            .padRight(Dimensions.COMPONENT_SPACING_LG);

        nestedTable.add(b2)
            .width(Dimensions.BUTTON_WIDTH)
            .height(Dimensions.BUTTON_HEIGHT)
            .padLeft(Dimensions.COMPONENT_SPACING_LG);

        this.add(nestedTable)
            .padTop(Dimensions.COMPONENT_SPACING)
            .colspan(colspan)
            .center();
    }
    public void setTitle(Label label, int colspan) {
        this.add(label)
            .colspan(colspan)
            .spaceBottom(Dimensions.COMPONENT_SPACING_LG)
            .row();
    }
}

