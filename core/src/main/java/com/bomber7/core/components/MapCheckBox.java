package com.bomber7.core.components;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.bomber7.core.ResourceManager;
import com.bomber7.core.model.Observer;
import com.bomber7.core.model.Subject;
 import com.bomber7.utils.ComponentsUtils;
import com.bomber7.utils.Dimensions;
import com.bomber7.utils.MVCComponent;
import com.bomber7.utils.GameMap;

import java.util.ArrayList;
import java.util.List;

/**
 * A checkbox component used in MapSelection screen to allow the user select the map(s) he wants to play on.
 */
public class MapCheckBox extends BomberTable implements MVCComponent, Subject {
    /**
     * The list of this subject's observers.
     */
    private final List<Observer> observers = new ArrayList<>();
    /**
     * Resource manager instance.
     */
    private final ResourceManager resources;
    /**
     * The map this checkbox represents.
     */
    private final GameMap map;

    /**
     * Checkbox container which represents the borders of the checkbox that change accordingly to its state.
     */
    private Container<Image> checkbox;
    /**
     * The clickable image of the map.
     * <p>
     *     It was decided not to use a ImageButton because of sizing issues.
     * </p>
     */
    private Image mapImage;
    /**
     * State of the checkbox.
     */
    private boolean checked = false;
    /**
     * Drawable for checked state, not hovered.
     */
    private final Drawable checkedUp;
    /**
     * Drawable for checked state, hovered.
     */
    private final Drawable checkedOver;
    /**
     * Drawable for unchecked state, not hovered.
     */
    private final Drawable uncheckedUp;
    /**
     * Drawable for unchecked state, hovered.
     */
    private final Drawable uncheckedOver;

    /**
     * Creates a {@code MapCheckBox} for the given map with appropriate resources.
     * Initializes the checkbox visuals and input handling.
     *
     * @param map       the map associated with this checkbox
     * @param resources the resource manager for loading styles and assets
     */
    public MapCheckBox(GameMap map, ResourceManager resources) {
        this.map = map;
        this.resources = resources;

        checkedUp = ComponentsUtils.getTintedDrawable(resources, "lightGreen");
        checkedOver = ComponentsUtils.getTintedDrawable(resources, "green");
        uncheckedUp = ComponentsUtils.getTintedDrawable(resources, "lightGold");
        uncheckedOver = ComponentsUtils.getTintedDrawable(resources, "gold");

        initView();
        initController();
    }

    @Override
    public void initView() {
        this.setTitle(new Label(resources.getString(map.getAssetName()), resources.getSkin()), 1);

        mapImage = new Image(new Texture(Gdx.files.internal("images/" + map.getAssetName() + ".9.png")));

        checkbox = new Container<>(mapImage);
        checkbox.setBackground(uncheckedUp);

        this.add(checkbox)
            .width(Dimensions.MAP_CHECK_BOX_SIZE)
            .height(Dimensions.MAP_CHECK_BOX_SIZE);
    }

    @Override
    public void initController() {
        mapImage.addListener(ComponentsUtils.addSoundEffect(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                    checked = !checked;
                    notifyObservers();
            }

            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                updateBackground(true);
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                updateBackground(false);
            }
        }, resources));
    }

    /**
     * Unchecks the checkbox.
     * Updates the background and notifies the observers.
     */
    public void uncheck() {
        this.checked = false;
        updateBackground(false);
        notifyObservers();
    }

    /**
     * Updates the checkbox background based on its checked state and whether it is being hovered.
     *
     * @param hover {@code true} if the mouse is hovering over the checkbox; {@code false} otherwise
     */
    private void updateBackground(boolean hover) {
        if (hover) {
            if (checked) {
                checkbox.setBackground(checkedOver);
            } else {
                checkbox.setBackground(uncheckedOver);
            }
        } else {
            if (checked) {
                checkbox.setBackground(checkedUp);
            } else {
                checkbox.setBackground(uncheckedUp);
            }
        }
    }

    /**
     * Returns the map this checkbox represents.
     * @return the GameMap that this checkbox represents
     */
    public GameMap getMap() {
        return map;
    }

    /**
     * Returns whether the checkbox is checked or not.
     * @return {@code true} if checked, {@code false} otherwise.
     */
    public boolean isChecked() {
        return checked;
    }

    @Override
    public List<Observer> getObservers() {
        return this.observers;
    }
}
