package me.jhonn;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.async.AsyncExecutor;

public class JavaGame extends ApplicationAdapter {
    private ResourceManager resourceManager;
    private boolean isStartedGame = false;

    SpriteBatch batch;
    Texture img;

    @Override
    public void create() {
        resourceManager = new ResourceManager();
        resourceManager.loadResources();


    }

    public void startGame() {
        batch = new SpriteBatch();
        img = resourceManager.getTexture("badlogic.jpg");
    }


    @Override
    public void render() {
        if (!resourceManager.isLoadingComplete()) {
            float progress = resourceManager.getLoadingProgress();
            System.out.println("Carregando... Progresso: " + (int) (progress * 100) + "%");

        } else {
            if (!isStartedGame) {
                startGame();
                float progress = resourceManager.getLoadingProgress();
                System.out.println("Carregando... Progresso: " + (int) (progress * 100) + "%");
                isStartedGame = true;
            }
            Gdx.gl.glClearColor(1, 0, 0, 1);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
            batch.begin();
            batch.draw(img, 0, 0);
            batch.end();
        }

    }


    @Override
    public void dispose() {
        batch.dispose();
        img.dispose();
    }

}
