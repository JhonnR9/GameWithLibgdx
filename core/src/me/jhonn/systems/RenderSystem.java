package me.jhonn.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import me.jhonn.Components.SpriteComponent;
import me.jhonn.Components.TransformComponent;

public class RenderSystem extends IteratingSystem {
    private final SpriteBatch batch;

    public RenderSystem(SpriteBatch batch) {
        super(Family.all(TransformComponent.class, SpriteComponent.class).get());
        this.batch = batch;
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        TransformComponent transform = entity.getComponent(TransformComponent.class);
        SpriteComponent spriteComponent = entity.getComponent(SpriteComponent.class);


        Vector2 newPosition = spriteComponent.getDebugPosition(transform);
        spriteComponent.setPosition(newPosition.x, newPosition.y);
        spriteComponent.setScale(transform.getScale().x, transform.getScale().y);
        spriteComponent.setRotation(spriteComponent.getRotation() + deltaTime * 20);

        spriteComponent.draw(batch);

    }


}

