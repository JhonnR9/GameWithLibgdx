package me.jhonn.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import me.jhonn.ResourceManager;

public class Object extends Actor {
    private ResourceManager resManager;
    private Texture texture;
    private Sprite sprite;

    public Object(ResourceManager resManager) {
        this.resManager = resManager;


    }

    public void setTexture(Texture texture) {
        this.setWidth(texture.getWidth());
        this.setHeight(texture.getHeight());
        this.setOrigin(texture.getWidth()/2, texture.getHeight()/2);
        this.texture = texture;
        if (texture!= null){
            sprite = new Sprite(texture);
        }
    }

    public Texture getTexture() {
        return texture;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        if (isVisible() && sprite !=null){
            batch.draw(sprite,getX(),getY(),getOriginX(),getOriginY(),
                 getWidth(),getHeight(), getScaleX(),getScaleY(),getRotation());
        }
    }

}
