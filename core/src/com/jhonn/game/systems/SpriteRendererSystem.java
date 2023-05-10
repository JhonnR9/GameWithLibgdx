package com.jhonn.game.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.jhonn.game.Components.SpriteComponent;
import com.jhonn.game.Components.TransformComponent;

public class SpriteRendererSystem extends IteratingSystem {
    private final SpriteBatch batch;

    public SpriteRendererSystem(SpriteBatch batch) {
        super(Family.all(TransformComponent.class, SpriteComponent.class).get());
        this.batch = batch;
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        TransformComponent transform = entity.getComponent(TransformComponent.class);
        SpriteComponent spriteComponent = entity.getComponent(SpriteComponent.class);
        updateSpritePosition(spriteComponent, transform);

        spriteComponent.draw(batch);

    }

    private void updateSpritePosition(SpriteComponent spriteComponent, TransformComponent transform) {
        Vector2 newPosition = spriteComponent.getDebugPosition(transform);
        spriteComponent.setPosition(newPosition.x, newPosition.y);
        spriteComponent.setScale(transform.getScale().x, transform.getScale().y);
    }


}

