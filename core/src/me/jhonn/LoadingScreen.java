package me.jhonn;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;

import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.utils.viewport.FillViewport;

public class LoadingScreen implements Screen {
    private ResourceManager resourceManager;
    private Game parent;
    private boolean isStartedGame = false;
    private Stage stage;
    private Group group;
    private  ProgressBar progressBar;


    public LoadingScreen(ResourceManager resourceManager, Game parent) {
        this.resourceManager = resourceManager;
        this.parent = parent;

        stage = new Stage(new FillViewport(160,90));


        group = new Group();
        group.setTransform(true);
        group.setDebug(true);

        stage.addActor(group);
    }

    @Override
    public void show() {
        progressBar  = new ProgressBar(0.0f, 10.0f, 2f, false, resourceManager.getDefaultSkin());
        progressBar.setValue(10f);
        progressBar.setColor(Color.RED);
        progressBar.setY(5);
        progressBar.setDebug(true);

        group.addActor(progressBar);
    }

    @Override
    public void render(float delta) {
        float progress = resourceManager.getLoadingProgress();


        progressBar.setValue(progress);
        if (!resourceManager.isLoadingComplete()) {
            System.out.println("Carregando... Progresso: " + (int) (progress * 100) + "%");

        } else {
            if (!isStartedGame) {
                System.out.println("Carregando... Progresso: " + (int) (progress * 100) + "%");
                isStartedGame = true;
            }
            Gdx.gl.glClearColor(.3f, .3f, .4f, 1);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
            stage.act();
            stage.draw();

        }

    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
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
