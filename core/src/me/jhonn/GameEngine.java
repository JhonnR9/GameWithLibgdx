package me.jhonn;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;

public class GameEngine extends Game {
    private ResourceManager resourceManager;
    @Override
    public void create() {
        resourceManager = new ResourceManager();
        resourceManager.setDefaultSkin("skin/cloud-form-ui.json");
        resourceManager.loadResources();
        this.setScreen(new GameScreen(resourceManager,this));

    }
    @Override
    public void dispose() {
        resourceManager.dispose();
    }
}
