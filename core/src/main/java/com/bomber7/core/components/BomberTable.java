package com.bomber7.core.components;

import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.bomber7.utils.Dimensions;

/**
 * A {@link Table} extension that provides some helpful function for cleaner UI initialization.
 */
public class BomberTable extends Table {

    /**
     * Adds two buttons side by side to the table.
     *
     * @param b1       the first button to add
     * @param b2       the second button to add
     * @param totalCols  the total number of columns in the table.
     */
    public void setupDoubleButtons(Button b1, Button b2, int totalCols) {
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
            .colspan(totalCols)
            .center();
    }

    /**
     * Adds a title label to this table.
     * @param label the label to add.
     * @param totalCols  the total number of columns in the table.
     */
    public void setTitle(Label label, int totalCols) {
        this.add(label)
            .colspan(totalCols)
            .spaceBottom(Dimensions.COMPONENT_SPACING_LG)
            .padBottom(Dimensions.COMPONENT_SPACING_LG)
            .row();
    }
}

