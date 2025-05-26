package com.bomber7.core.components;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.bomber7.core.BomberResources;

public class BomberTextButton extends TextButton {
    private BomberResources resources;

    public BomberTextButton(String text, BomberResources resources) {
        super(text, resources.skin);
        this.resources = resources;
    }

    @Override
    public boolean addListener(EventListener listener) {
        // TODO : refactor? not a clean solution
        if (listener instanceof ClickListener) {
            listener = withSoundEffects((ClickListener) listener);
        }
        return super.addListener(listener);
    }

    private ClickListener withSoundEffects(ClickListener original) {
        return new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                original.clicked(event, x, y);
            }

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                resources.clickSound.play();
                return original.touchDown(event, x, y, pointer, button);
            }

            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                original.enter(event, x, y, pointer, fromActor);
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                original.exit(event, x, y, pointer, toActor);
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                original.touchUp(event, x, y, pointer, button);
            }
        };
    }
}
