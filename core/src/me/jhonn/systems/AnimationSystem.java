package me.jhonn.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import me.jhonn.Components.AnimationComponent;
import me.jhonn.Components.SpriteComponent;


public class AnimationSystem extends IteratingSystem {

    public AnimationSystem() {
        super(Family.all(AnimationComponent.class, SpriteComponent.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        AnimationComponent animationComponent = entity.getComponent(AnimationComponent.class);
        SpriteComponent spriteComponent = entity.getComponent(SpriteComponent.class);

        if (animationComponent.getCurrentAnimationKey() != null) {
            animationComponent.update(deltaTime);
            spriteComponent.setRegion(animationComponent.getFrame(animationComponent.getCurrentAnimationKey()));
        }


    }
}

