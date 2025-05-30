package com.bomber7.utils;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public final class ComponentsUtils {
    private ComponentsUtils() { }

    public static ClickListener addSoundEffect(ClickListener original, ResourceManager resources) {
        return new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                original.clicked(event, x, y);
            }

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                resources.getSound().play(SoundType.CLICK);
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
