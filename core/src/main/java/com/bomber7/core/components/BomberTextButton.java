package com.bomber7.core.components;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.bomber7.utils.ResourceManager;
import com.bomber7.utils.SoundType;

/**
 * A custom {@link TextButton} that plays a click sound effect when pressed.
 * <p>
 * This button wraps click listeners to add sound effects on touch events.
 * </p>
 */
public class BomberTextButton extends TextButton {

    /**
     * Resource manager used to access game assets like sounds and skins.
     */
    private ResourceManager resources;

    /**
     * Creates a new {@code BomberTextButton} with the
     * specified label text and resource manager.
     *
     * @param text      the text to display on the button
     * @param resources the resource manager to access skins and sounds
     */
    public BomberTextButton(String text, ResourceManager resources) {
        super(text, resources.getSkin());
        this.resources = resources;
    }

    /**
     * Adds an {@link EventListener} to this button.
     * <p>
     * If the listener is a {@link ClickListener}, it is wrapped to
     * add sound effects on touch down.
     * </p>
     *
     * @param listener the event listener to add
     * @return {@code true} if the listener was successfully added
     */
    @Override
    public boolean addListener(EventListener listener) {
        // TODO : refactor? not a clean solution
        if (listener instanceof ClickListener) {
            listener = withSoundEffects((ClickListener) listener);
        }
        return super.addListener(listener);
    }

    /**
     * Wraps the given {@link ClickListener} to play a click sound on touch down
     * while preserving the original listener's behavior.
     *
     * @param original the original click listener to wrap
     * @return a new click listener that plays a sound effect
     * and delegates to the original
     */
    private ClickListener withSoundEffects(ClickListener original) {
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

