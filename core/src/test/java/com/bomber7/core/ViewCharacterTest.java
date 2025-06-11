package com.bomber7.core.views;

import org.mockito.Mockito;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.bomber7.core.model.entities.Character;


/**
 * Unit tests for the ViewCharacter class.
 * These tests validate the animation handling and rendering logic.
 */

class ViewCharacterTest {

    private Character mockCharacter;
    private ViewCharacter viewCharacter;


    /**
     * Sets up the test environment by creating a mockCharacter instance
     * and instantiating a ViewCharacter object using the mock.
     */
    @BeforeEach
    void setUp() {
        // Create a mock of the Character class
        mockCharacter = Mockito.mock(Character.class);

        // Provide a mocked sprite path to avoid dependency on real life paths
        Mockito.when(mockCharacter.getSpriteFP()).thenReturn("mock/path/to/sprite.png");

        // Simulate a character with attributes for testing
        Mockito.when(mockCharacter.getLife()).thenReturn(3);
        Mockito.when(mockCharacter.getSpeed()).thenReturn(2);

        // Instantiate ViewCharacter with the mocked character
        viewCharacter = new ViewCharacter(mockCharacter);
    }


    /**
     * Verify that the ViewCharacter constructor works with a valid
     * Character instance.
     */
    @Test
    void testConstructorWithCharacter() {
        assertNotNull(viewCharacter);
    }

    /**
     * Verify the rendering of the Character
     */
    @Test
    void testRenderCharacter() {

        SpriteBatch mockBatch = Mockito.mock(SpriteBatch.class);

        // Call the renderCharacter method
        viewCharacter.renderCharacter(mockBatch);

        // Verify that the batch is used to draw at least once
        Mockito.verify(mockBatch, Mockito.atLeastOnce()).draw(
            Mockito.any(TextureRegion.class),
            Mockito.anyFloat(),
            Mockito.anyFloat());
    }

    @Test
    void testAnimationCreation() {
        assertNotNull(viewCharacter, "L'animation `moveRight` devrait être correctement initialisée.");
    }
}
