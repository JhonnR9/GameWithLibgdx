package com.jhonn.game.Components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class SpriteComponent extends Sprite implements Component {
    private final float PPM = 16f;

    public SpriteComponent(Texture texture) {
        super(texture);

        this.setSize(texture.getWidth() / PPM, texture.getHeight() / PPM);
        this.setOriginCenter();
    }

    @Override
    public void setRegion(TextureRegion region) {
        this.setSize(region.getRegionWidth() / PPM, region.getRegionHeight() / PPM);
        this.setOriginCenter();
        super.setRegion(region);
    }

    /**
     * Returns a new position Vector2 object that represents the position of the
     * texture with transformations applied.
     *
     * @param transform The transform component that contains the position and scale
     *                  of the texture.
     * @return A new position Vector2 object that represents the position of the
     * texture with transformations applied.
     */
    public Vector2 getDebugPosition(TransformComponent transform) {
        float newWidth = this.getWidth() * transform.getScale().x;
        float newHeight = this.getHeight() * transform.getScale().y;

        Vector2 origin = new Vector2(transform.getPosition()).sub(getWidth() / 2, getHeight() / 2);
        Vector2 offset = new Vector2(newWidth / 2, newHeight / 2);

        return origin.add(offset);
    }


}
