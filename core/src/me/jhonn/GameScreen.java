package me.jhonn;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FillViewport;
import me.jhonn.objects.PlayerObject;

public class GameScreen implements Screen {
    private final ResourceManager resManager;

    Stage mainStage;

    public GameScreen(ResourceManager resManager, Game parent) {
        this.resManager = resManager;

        float worldScale = .8f;
        float worldWidth = 16f * worldScale;
        float worldHeight = 9f * worldScale;
        mainStage = new Stage(new FillViewport(worldWidth, worldHeight));
    }

    @Override
    public void show() {
        mainStage.clear();

        PlayerObject player0 = new PlayerObject(resManager);
        player0.setDebug(true);
        player0.setPosition(new Vector2(1,1));
        player0.setColor(Color.BLUE);

        PlayerObject player1 = new PlayerObject(resManager);
        player1.setDebug(true);
        player1.setPosition(new Vector2(3,1));
        player1.setColor(Color.RED);

        PlayerObject player2 = new PlayerObject(resManager);
        player2.setDebug(true);
        player2.setPosition(new Vector2(5,1));
        player2.setColor(Color.GREEN);

        PlayerObject player3 = new PlayerObject(resManager);
        player3.setDebug(true);
        player3.setPosition(new Vector2(7,1));
        player3.setColor(Color.CYAN);

        PlayerObject player4 = new PlayerObject(resManager);
        player4.setDebug(true);
        player4.setPosition(new Vector2(9,1));
        player4.setColor(Color.YELLOW);

        PlayerObject player5 = new PlayerObject(resManager);
        player5.setDebug(true);
        player5.setPosition(new Vector2(11,1));
        player5.setColor(Color.MAGENTA);

        mainStage.addActor(player0);
        mainStage.addActor(player1);
        mainStage.addActor(player2);
        mainStage.addActor(player3);
        mainStage.addActor(player4);
        mainStage.addActor(player5);

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
