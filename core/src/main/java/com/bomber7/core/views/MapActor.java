package com.bomber7.core.views;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * MapActor is an Actor that draws the map using drawing of each square.
 * draw() is called by libGDX for each frame.
 */
public class MapActor extends Actor {
    private ViewMap viewMap;

    public MapActor(ViewMap viewMap) {
        this.viewMap = viewMap;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        viewMap.updateMapTextures();
    }
}
