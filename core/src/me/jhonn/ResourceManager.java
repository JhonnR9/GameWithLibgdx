package me.jhonn;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetErrorListener;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.TextureLoader;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Logger;

import java.security.spec.RSAPrivateKeySpec;

public class ResourceManager implements AssetErrorListener {
    private AssetManager assetManager;
    private static final Logger log = new Logger(ResourceManager.class.getName(), Logger.DEBUG);
    private String defaultSkin;

    public ResourceManager() {
        assetManager = new AssetManager();
        assetManager.setErrorListener(this);
    }

    /**
     * Read all resources and load them at once so any new resources
     * must be placed in the method implementation
     */
    public void loadResources() {
        /**
         * define texture parameters
         */
        TextureLoader.TextureParameter textureParams = new TextureLoader.TextureParameter();
        textureParams.minFilter = Texture.TextureFilter.Linear;
        textureParams.magFilter = Texture.TextureFilter.Linear;

        TextureLoader.TextureParameter texturePixelParams = new TextureLoader.TextureParameter();
        textureParams.minFilter = Texture.TextureFilter.Nearest;
        textureParams.magFilter = Texture.TextureFilter.Nearest;

        assetManager.load(defaultSkin, Skin.class);
        assetManager.load("badlogic.jpg", Texture.class, textureParams);
        assetManager.load("character.png", Texture.class, texturePixelParams);
        assetManager.finishLoading();

    }

    /**
     *
     * @param path Path to the file with the extension for example "badlogic.jpg"
     * @return A reference to an instance of a Texture previously loaded with loadResources()
     */
    public Texture getTexture(String path) {
        if (assetManager.isLoaded(path, Texture.class)) {
            return assetManager.get(path, Texture.class);
        } else {
            throw new RuntimeException("Texture '" + path + "' not loaded.");
        }
    }

    public void setDefaultSkin(String defaultSkin) {
        this.defaultSkin = defaultSkin;
    }

    /**
     *
     * @return default skin of the game and must be defined before with /setDefaultSkin();
     */
    public Skin getDefaultSkin(){
        if (assetManager.isLoaded(defaultSkin, Skin.class)) {
            return assetManager.get(defaultSkin, Skin.class);
        } else {
            throw new RuntimeException("Skin '" + defaultSkin + "' not loaded.");
        }
    }

    /**
     *
     * @param path Path to the file with the extension for example "graphics.atlas"
     * @return A reference to an instance of a TextureAtlas previously loaded with loadResources()
     */
    public TextureAtlas getTextureAtlas(String path) {
        if (assetManager.isLoaded(path, TextureAtlas.class)) {
            return assetManager.get(path, TextureAtlas.class);
        } else {
            throw new RuntimeException("TextureAtlas '" + path + "' not loaded.");
        }
    }

    /**
     *
     * @return current load progress
     */
    public float getLoadingProgress() {
        return assetManager.getProgress();
    }

    /**
     *
     * @return true if it completed the load successfully
     */
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
