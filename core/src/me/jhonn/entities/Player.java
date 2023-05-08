package me.jhonn.entities;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;
import me.jhonn.Components.AnimationComponent;
import me.jhonn.Components.DebugComponent;
import me.jhonn.Components.SpriteComponent;
import me.jhonn.Components.TransformComponent;
import me.jhonn.utils.AnimationConfig;
import me.jhonn.utils.ResourceManager;

public class Player extends Entity implements Component {
    public Player(float x, float y, ResourceManager resourceManager) {
        SpriteComponent spriteComponent;
        TransformComponent transformComponent;
        DebugComponent debugComponent;
        AnimationComponent animationComponent;

        debugComponent = new DebugComponent();
        spriteComponent = new SpriteComponent( resourceManager.getTexture("character.png"));
        transformComponent = new TransformComponent();
        animationComponent = new AnimationComponent();
        AnimationConfig ac = new AnimationConfig(4,1,16,16);

        animationComponent.setTexture(spriteComponent.getTexture());
        animationComponent.createAnimation(ac,"walk");
        animationComponent.setCurrentAnimationKey("walk");

        debugComponent.setDebug(true);
        transformComponent.setPosition(x,y);
        transformComponent.setScale(1.5f);

        add(this);
        add(spriteComponent);
        add(transformComponent);
        add(debugComponent);
        add(animationComponent);
    }
}
