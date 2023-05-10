package com.jhonn.game.systems;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.jhonn.game.Components.input.InputComponent;

public class InputSystem extends IteratingSystem {
    private final ComponentMapper<InputComponent> inputMapper;
    private final InputMultiplexer inputMultiplexer;

    public InputSystem() {
        super(Family.all(InputComponent.class).get());
        inputMapper = ComponentMapper.getFor(InputComponent.class);
        inputMultiplexer = (InputMultiplexer) Gdx.input.getInputProcessor();
    }


    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        InputComponent inputComponent = inputMapper.get(entity);
        if (!inputComponent.isAdd()){
            inputMultiplexer.addProcessor(inputComponent.getInputProcessor());
            inputComponent.setAdd(true);
        }

    }


}

