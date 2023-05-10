
package com.jhonn.game.entities;

import com.badlogic.ashley.core.Entity;
import com.jhonn.game.Components.AnimationComponent;
import com.jhonn.game.Components.DebugComponent;
import com.jhonn.game.Components.SpriteComponent;
import com.jhonn.game.Components.TransformComponent;
import com.jhonn.game.utils.AnimationConfig;
import com.jhonn.game.utils.ResourceManager;

/**
 * The Player class represents a player entity in the game.
 * It extends the Ashley Entity class and contains components
 * that define its behavior and appearance.
 */
public final class Player extends Entity {
    private final TransformComponent transformComponent;
    private final DebugComponent debugComponent;
    private final SpriteComponent spriteComponent;
    private final AnimationComponent animationComponent;
    private final float x, y;

    /**
     * Constructs a Player object with the specified position and resource manager.
     * @param x               the x-coordinate of the player's position
     * @param y               the y-coordinate of the player's position
     * @param resourceManager the resource manager used to load the player's sprite
     */
    public Player(float x, float y, ResourceManager resourceManager) {
        this.x = x;
        this.y = y;

        this.spriteComponent = createSpriteComponent(resourceManager);
        this.debugComponent = createDebugComponent();
        this.transformComponent = createTransformComponent();
        this.animationComponent = createAnimationComponent(spriteComponent);

        configComponents();
        addComponents();
    }

    private void configComponents() {
        configureAnimations();
        configureTransform();
        configureDebug();
    }

    private void addComponents() {
        this.add(this.transformComponent);
        this.add(this.spriteComponent);
        this.add(this.debugComponent);
        this.add(this.animationComponent);
    }

    private SpriteComponent createSpriteComponent(ResourceManager resourceManager) {
        return new SpriteComponent(resourceManager.getTexture("character.png"));
    }

    private DebugComponent createDebugComponent() {
        return new DebugComponent();
    }

    private TransformComponent createTransformComponent() {
        return new TransformComponent();
    }

    private AnimationComponent createAnimationComponent(SpriteComponent spriteComponent) {
        AnimationComponent animationComponent = new AnimationComponent();
        animationComponent.setTexture(spriteComponent.getTexture());
        return animationComponent;
    }

    private void configureAnimations() {
        AnimationConfig walk = new AnimationConfig(4, 1, 16, 16);
        walk.setFrameDuration(0.20f);

        AnimationConfig idle = new AnimationConfig(4, 1, 16, 16);
        idle.setStartY(1);
        idle.setFrameDuration(0.20f);

        animationComponent.createAnimation(walk, "walk");
        animationComponent.createAnimation(idle, "idle");
    }

    private void configureTransform() {
        transformComponent.setPosition(x, y);
        transformComponent.setScale(1.5f);
    }

    private void configureDebug() {
        debugComponent.setDebug(true);
    }
}
