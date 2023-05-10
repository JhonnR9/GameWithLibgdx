package com.jhonn.game;

import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.physics.box2d.*;
import com.jhonn.game.systems.InputSystem;
import com.jhonn.game.utils.ResourceManager;
import com.jhonn.game.systems.AnimationSystem;
import com.jhonn.game.systems.DebugRendererSystem;
import com.jhonn.game.systems.SpriteRendererSystem;
import com.jhonn.game.entities.Player;


public class GameEngine extends ApplicationAdapter {
    private ResourceManager resourceManager;
    private SpriteBatch bath;
    private ShapeRenderer shapeRenderer;
    private OrthographicCamera camera;
    private Engine engine;

    @Override
    public void create() {
        Box2D.init();
        startGame();
        addSystems();
        Player player0 = new Player(1, 1, resourceManager);
        engine.addEntity(player0);

    }



    private void addSystems() {
        engine.addSystem(new SpriteRendererSystem(bath));
        engine.addSystem(new DebugRendererSystem(shapeRenderer));
        engine.addSystem(new AnimationSystem());
        engine.addSystem(new InputSystem());
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
        InputMultiplexer inputMultiplexer = new InputMultiplexer();
        Gdx.input.setInputProcessor(inputMultiplexer);
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
