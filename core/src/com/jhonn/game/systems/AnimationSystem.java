package com.jhonn.game.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.jhonn.game.Components.AnimationComponent;
import com.jhonn.game.Components.SpriteComponent;


public class AnimationSystem extends IteratingSystem {

    public AnimationSystem() {
        super(Family.all(AnimationComponent.class, SpriteComponent.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        AnimationComponent animationComponent = entity.getComponent(AnimationComponent.class);
        SpriteComponent spriteComponent = entity.getComponent(SpriteComponent.class);

        if (!animationComponent.textureLoaded() && spriteComponent.getTexture() != null) {
            animationComponent.setTexture(spriteComponent.getTexture());
        }
        if (animationComponent.getCurrentAnimationKey() != null) {
            animationComponent.update(deltaTime);
            spriteComponent.setRegion(animationComponent.getFrame(animationComponent.getCurrentAnimationKey()));
            spriteComponent.setFlip(animationComponent.isFlip(), false);

        }


    }
}

