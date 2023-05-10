package com.jhonn.game.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.jhonn.game.Components.PhysicsComponent;
import com.jhonn.game.Components.TransformComponent;

public class PhysicsSystem extends IteratingSystem {
    private final World world;

    public PhysicsSystem(World world) {
        super(Family.all(PhysicsComponent.class, TransformComponent.class).get());
        this.world = world;
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        PhysicsComponent physics = entity.getComponent(PhysicsComponent.class);
        TransformComponent transform = entity.getComponent(TransformComponent.class);

       if (physics.getBody() != null){
           Vector2 position = physics.getBody().getPosition();
           transform.getPosition().x = position.x;
           transform.getPosition().y = position.y;

           float angle = physics.getBody().getAngle();
           transform.setRotation(angle);
       }
    }


    public void update(float deltaTime) {
        world.step(deltaTime, 6, 2);
        super.update(deltaTime);
    }
}
