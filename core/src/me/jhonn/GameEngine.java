package me.jhonn;

import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import me.jhonn.Components.SpriteComponent;
import me.jhonn.entities.Player;
import me.jhonn.systems.AnimationSystem;
import me.jhonn.systems.DebugSystem;
import me.jhonn.systems.RenderSystem;
import me.jhonn.utils.ResourceManager;

public class GameEngine extends ApplicationAdapter {
    private ResourceManager resourceManager;
    private SpriteBatch bath;
    private ShapeRenderer shapeRenderer;
    private OrthographicCamera camera;
    private Engine engine;


    @Override
    public void create() {
        startGame();
        addSystem();


        Player player0 = new Player(1,1,resourceManager);
        player0.getComponent(SpriteComponent.class).setColor(Color.CYAN);
        Player player1 = new Player(4,1,resourceManager);
        player1.getComponent(SpriteComponent.class).setColor(Color.MAGENTA);
        Player player2 = new Player(7,1,resourceManager);
        player2.getComponent(SpriteComponent.class).setColor(Color.YELLOW);
        Player player3 = new Player(10,1,resourceManager);
        player3.getComponent(SpriteComponent.class).setColor(Color.RED);
        Player player4 = new Player(13,1,resourceManager);
        player4.getComponent(SpriteComponent.class).setColor(Color.GREEN);

        engine.addEntity(player0);
        engine.addEntity(player1);
        engine.addEntity(player2);
        engine.addEntity(player3);
        engine.addEntity(player4);


    }

    private void addSystem() {
        engine.addSystem(new RenderSystem(bath));
        engine.addSystem(new DebugSystem(shapeRenderer));
        engine.addSystem(new AnimationSystem());
    }

    private void startGame() {
        resourceManager = new ResourceManager();
        resourceManager.setDefaultSkin("skin/cloud-form-ui.json");
        resourceManager.loadResources();
        bath = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 16, 9);
        engine = new Engine();
    }


    @Override
    public void render() {
        Gdx.gl.glClearColor(.1f, .1f, .1f, 1);
        Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);

        camera.update();

        bath.setProjectionMatrix(camera.combined);
        shapeRenderer.setProjectionMatrix(camera.combined);


        bath.begin();
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(Color.RED);

        engine.update(Gdx.graphics.getDeltaTime());
        bath.end();
        shapeRenderer.end();


    }

    @Override
    public void dispose() {
        resourceManager.dispose();
        shapeRenderer.dispose();
        bath.dispose();
    }
}
