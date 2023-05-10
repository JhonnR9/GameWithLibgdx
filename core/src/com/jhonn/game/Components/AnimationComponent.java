package com.jhonn.game.Components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

import com.jhonn.game.utils.AnimationConfig;

import java.util.HashMap;
import java.util.Map;

/**
 * This component contains animations for entities in the game.
 * It holds a collection of animations created from a single texture and a set of animation configs.
 * The current animation and flip state of the texture can be set and retrieved from this component.
 *
 * @author Jhones Rocha
 */
public class AnimationComponent implements Component {

    private final Map<String, Animation<TextureRegion>> animations = new HashMap<>();
    private Texture texture;
    private boolean isFlip;
    private float elapsedTime;
    private String currentAnimationKey;

    /**
     * Checks if texture is loaded.
     *
     * @return true if texture is loaded, false otherwise.
     */
    public boolean textureLoaded() {
        return texture != null;
    }


    public boolean isFlip() {
        return isFlip;
    }


    public void setFlip(boolean flip) {
        isFlip = flip;
    }

    /**
     * Gets the key of the current animation.
     *
     * @return the string key of the current animation.
     */
    public String getCurrentAnimationKey() {
        return currentAnimationKey;
    }

    /**
     * Sets the key of the current animation.
     *
     * @param currentAnimationKey the string key of the animation to be set as current.
     */
    public void setCurrentAnimationKey(String currentAnimationKey) {
        this.currentAnimationKey = currentAnimationKey;
    }

    /**
     * Gets the current frame of the specified animation.
     *
     * @param animationName the string key of the animation.
     * @return the current texture region of the specified animation.
     */
    public TextureRegion getFrame(String animationName) {
        return animations.get(animationName).getKeyFrame(elapsedTime);
    }

    /**
     * Creates an array of texture regions from a specified animation config.
     *
     * @param ac the animation config containing information about the animation.
     * @return an array of texture regions created from the specified animation config.
     */
    private TextureRegion[] createTexturesRegions(AnimationConfig ac) {
        TextureRegion[][] frames = TextureRegion.split(texture, ac.getFrameWidth(), ac.getFrameHeight());
        TextureRegion[] textureRegions = new TextureRegion[ac.getColumns() * ac.getRows()];
        int index = 0;
        for (int row = ac.getStartY(); row < ac.getStartY() + ac.getRows(); row++) {
            for (int col = ac.getStartX(); col < ac.getStartX() + ac.getColumns(); col++) {
                textureRegions[index++] = frames[row][col];
            }
        }
        return textureRegions;
    }

    /**
     * Creates an animation from the given {@link AnimationConfig} and adds it to the map of animations.
     *
     * @param ac           the {@link AnimationConfig} containing the animation parameters
     * @param animationKey the key to associate with the animation
     */
    public void createAnimation(AnimationConfig ac, String animationKey) {
        Animation<TextureRegion> animation = new Animation<>(ac.getFrameDuration(), createTexturesRegions(ac));
        if (ac.isLoop()) {
            animation.setPlayMode(Animation.PlayMode.LOOP);
        } else {
            animation.setPlayMode(Animation.PlayMode.NORMAL);
        }
        animations.put(animationKey, animation);
        if (currentAnimationKey == null) {
            currentAnimationKey = animationKey;
        }
    }

    public void update(float deltaTime) {
        elapsedTime += deltaTime;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }
}
