package me.jhonn;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import me.jhonn.objects.BadlogicObject;
import me.jhonn.objects.Object;

public class GameScreen implements Screen {
    private ResourceManager resourceManager;
    private Game parent;
    private BadlogicObject badlogic;

    Stage mainStage;

    public GameScreen(ResourceManager resourceManager, Game parent) {
        this.resourceManager = resourceManager;
        this.parent = parent;

        mainStage = new Stage(new FitViewport(800,600));
    }

    @Override
    public void show() {
        mainStage.clear();

        badlogic = new BadlogicObject(resourceManager);
        badlogic.setScale(.5f);

        mainStage.addActor(badlogic);

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(.3f, .2f, .4f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        mainStage.act();
        mainStage.draw();
    }

    @Override
    public void resize(int width, int height) {

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
