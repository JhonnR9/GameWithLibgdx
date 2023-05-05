package me.jhonn;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import me.jhonn.objects.PlayerObject;

public class GameScreen implements Screen {
    private ResourceManager resourceManager;
    private Game parent;
    private PlayerObject badlogic;

    Stage mainStage;

    public GameScreen(ResourceManager resourceManager, Game parent) {
        this.resourceManager = resourceManager;
        this.parent = parent;

        float worldScale = .8f;
        float worldWidth = 16f * worldScale;
        float worldHeight = 9f * worldScale;
        mainStage = new Stage(new FillViewport(worldWidth, worldHeight));
    }

    @Override
    public void show() {
        mainStage.clear();

        badlogic = new PlayerObject(resourceManager);
        badlogic.setDebug(true);
        badlogic.setPosition(new Vector2(1,1));
        mainStage.addActor(badlogic);

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(.1f, .1f, .1f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        mainStage.act();
        mainStage.draw();
    }

    @Override
    public void resize(int width, int height) {
        mainStage.getViewport().update(width, height, true);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
