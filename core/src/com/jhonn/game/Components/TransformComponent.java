package com.jhonn.game.Components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool;

public class TransformComponent implements Component, Pool.Poolable {
    private Vector2 position;
    private Vector2 scale;
    private float rotation;



    public TransformComponent() {
        this.position = new Vector2();
        this.scale = new Vector2(1,1);
        this.rotation = 0;
    }

    /**
     *
     * @param position position of the image fpr draw
     * @param scale scale of image for draw don't set this to zero
     * @param rotation rotation of image for draw
     */
    public TransformComponent(Vector2 position, Vector2 scale, float rotation) {
        this.position = position;
        this.scale = scale;
        this.rotation = rotation;
    }

    /**
     * @return Vector2 of position
     */
    public Vector2 getPosition() {
        return position;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }
    public void setPosition(float x, float y) {
        this.position.x = x;
        this.position.y = y;
    }

    /**
     * @return Vector2 of scale
     */
    public Vector2 getScale() {
        return scale;
    }

    public void setScale(Vector2 scale) {
        this.scale = scale;
    }
    public void setScale(float x, float y) {
        this.scale.x = x;
        this.scale.y = y;
    }
    public void setScale(float xy) {
        this.scale.x = xy;
        this.scale.y = xy;
    }
    public float getRotation() {
        return rotation;
    }

    public void setRotation(float rotation) {
        this.rotation = rotation;
    }


    /**
     * don't call this if you its using this object
     */
    @Override
    public void reset() {
        position.setZero();
        scale.setZero();
        rotation = 0f;
    }
}
