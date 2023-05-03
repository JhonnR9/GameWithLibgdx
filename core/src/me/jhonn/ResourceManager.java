package me.jhonn;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetErrorListener;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.TextureLoader;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.Logger;

public class ResourceManager implements AssetErrorListener {
    private AssetManager assetManager;
    private static final Logger log = new Logger(ResourceManager.class.getName(), Logger.DEBUG);
    private boolean loadingComplete;

    public ResourceManager() {
        assetManager = new AssetManager();
        assetManager.setErrorListener(this);
    }

    public void loadResources() {
        // Configura o carregamento para texturas
        TextureLoader.TextureParameter textureParams = new TextureLoader.TextureParameter();
        textureParams.minFilter = Texture.TextureFilter.Linear;
        textureParams.magFilter = Texture.TextureFilter.Linear;

        // Carrega texturas
        assetManager.load("badlogic.jpg", Texture.class, textureParams);
        //assetManager.load("character.png", Texture.class, textureParams);

        // Carrega atlas de texturas
      //  assetManager.load("sprites.atlas", TextureAtlas.class);

        // Aguarda o carregamento de todos os recursos
       // assetManager.finishLoading();
    }

    public Texture getTexture(String path) {
        if (assetManager.isLoaded(path, Texture.class)) {
            return assetManager.get(path, Texture.class);
        } else {
            throw new RuntimeException("Texture '" + path + "' not loaded.");
        }
    }

    public TextureAtlas getTextureAtlas(String path) {
        if (assetManager.isLoaded(path, TextureAtlas.class)) {
            return assetManager.get(path, TextureAtlas.class);
        } else {
            throw new RuntimeException("TextureAtlas '" + path + "' not loaded.");
        }
    }

    public float getLoadingProgress() {
        return assetManager.getProgress();
    }

    public boolean isLoadingComplete() {
        return assetManager.update();
    }

    public void dispose() {
        assetManager.dispose();
    }

    @Override
    public void error(AssetDescriptor asset, Throwable throwable) {
        log.error("Error loading asset: " + asset.fileName, throwable);
        throwable.printStackTrace();
    }
}
