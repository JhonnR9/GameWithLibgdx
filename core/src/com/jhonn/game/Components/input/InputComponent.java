package com.jhonn.game.Components.input;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.InputProcessor;

public class InputComponent implements Component {
    private InputProcessor inputProcessor;
    private boolean isAdd = false;

    public InputComponent(InputProcessor inputProcessor) {
        this.inputProcessor = inputProcessor;
    }
    public InputProcessor getInputProcessor() {
        return inputProcessor;
    }

    public boolean isAdd() {
        return isAdd;
    }

    public void setAdd(boolean add) {
        isAdd = add;
    }




}