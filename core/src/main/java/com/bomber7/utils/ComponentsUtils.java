package com.bomber7.utils;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.bomber7.core.ResourceManager;

/**
 * Utility class for enhancing UI components with additional behaviors.
 * <p>
 * This class is non-instantiable and provides only static helper methods.
 */
public final class ComponentsUtils {
    /**
     * Private constructor to prevent instantiation of utility class.
     */
    private ComponentsUtils() { }

    /**
     * Wraps an existing {@link ClickListener} to add a click sound effect when the user presses down on a component.
     * <p>
     * The original listener's behavior is preserved, and the sound is played via the provided {@link ResourceManager}
     * using the {@link SoundType#CLICK} sound type.
     *
     * @param original the original {@code ClickListener} to wrap.
     * @return a new {@code ClickListener} that adds a sound effect to the {@code touchDown} event,
     *         while delegating all events to the original listener.
     */
    public static ClickListener addSoundEffect(ClickListener original) {
        return new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                original.clicked(event, x, y);
            }

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                SoundManager.getInstance().play(SoundType.CLICK);
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

    /**
     * Returns a new drawable tinted with the specified color from the skin.
     *
     * @param resources the resource manager that provides access to the skin
     * @param color     the name of the color defined in the skin
     * @return a new {@link Drawable} tinted with the specified color
     */
    public static Drawable getTintedDrawable(ResourceManager resources, String color) {
        return resources.getSkin().newDrawable(
            "white.9",
            resources.getSkin().getColor(color)
        );
    }
}
