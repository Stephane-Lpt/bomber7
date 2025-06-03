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
import com.bomber7.core.screens.MapSelectionScreen;
import com.bomber7.utils.ComponentsUtils;
import com.bomber7.utils.Dimensions;
import com.bomber7.utils.MVCComponent;
import com.bomber7.utils.Map;

import java.util.ArrayList;
import java.util.List;

public class MapCheckBox extends BomberTable implements MVCComponent, Subject {
    private final List<Observer> observers = new ArrayList<>();

    private final ResourceManager resources;
    private final Map map;

    private Container<Image> checkbox;
    private Image mapImage;

    private boolean checked = false;
    private boolean checkable = true;

    private final Drawable checkedUp;
    private final Drawable checkedOver;
    private final Drawable uncheckedUp;
    private final Drawable uncheckedOver;
    private final Drawable red;

    public MapCheckBox(Map map, ResourceManager resources) {
        this.map = map;
        this.resources = resources;

        checkedUp = ComponentsUtils.getTintedDrawable(resources, "lightGreen");
        checkedOver = ComponentsUtils.getTintedDrawable(resources, "green");
        uncheckedUp = ComponentsUtils.getTintedDrawable(resources, "lightGold");
        uncheckedOver = ComponentsUtils.getTintedDrawable(resources, "gold");
        red = ComponentsUtils.getTintedDrawable(resources, "red");

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
                if (checked) {
                    checkbox.setBackground(checkedOver);
                } else {
                    checkbox.setBackground(uncheckedOver);
                }
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                if (checked) {
                    checkbox.setBackground(checkedUp);
                } else {
                    checkbox.setBackground(uncheckedUp);
                }
            }
        }, resources));
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
        updateView();
        notifyObservers();
    }

    private void updateView() {
        if (checked) {
            checkbox.setBackground(checkedUp);
        } else {
            checkbox.setBackground(uncheckedUp);
        }
    }

    public boolean isChecked() {
        return checked;
    }

    @Override
    public List<Observer> getObservers() {
        return this.observers;
    }
}
