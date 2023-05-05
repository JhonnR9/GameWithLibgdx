package me.jhonn.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import me.jhonn.ResourceManager;

public class Object extends Actor {
    private ResourceManager resManager;
    private Texture texture;
    private float ppm = 16;

    public float getPpm() {
        return ppm;
    }

    public void setPpm(float ppm) {
        this.ppm = ppm;
    }

    public TextureRegion getTextureRegion() {
        return textureRegion;
    }

    public void setTextureRegion(TextureRegion textureRegion) {
        float w = textureRegion.getRegionWidth();
        float h = textureRegion.getRegionHeight();
        this.setWidth(w / ppm);
        this.setHeight(h / ppm);
        this.setOrigin((w / 2f), h / 2f);
        this.textureRegion = textureRegion;
    }

    private TextureRegion textureRegion;


    public Object(ResourceManager resManager) {
        this.resManager = resManager;


    }

    public void setTexture(Texture texture) {
        this.setWidth(texture.getWidth() / ppm);
        this.setHeight(texture.getHeight() / ppm);
        this.setOrigin(texture.getWidth() / 2f, texture.getHeight() / 2f);
        this.texture = texture;
        textureRegion = new TextureRegion(texture);

    }

    public void setPosition(Vector2 position) {
        this.setPosition(position.x, position.y);
    }
    public void setScale(Vector2 scale) {
        this.setScale(scale.x, scale.y);
    }

    public Texture getTexture() {
        return texture;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        if (isVisible() && textureRegion != null) {
            batch.draw(textureRegion, getX(), getY(), getOriginX(), getOriginY(),
                    getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());
        }
    }


}
