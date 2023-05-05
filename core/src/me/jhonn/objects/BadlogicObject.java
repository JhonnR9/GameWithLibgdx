package me.jhonn.objects;

import com.badlogic.gdx.Gdx;
import me.jhonn.ResourceManager;

public class BadlogicObject extends Object {
    float velocity = 4f;
    private boolean isBack = false;

    public BadlogicObject(ResourceManager resManager) {
        super(resManager);
        setTexture(resManager.getTexture("badlogic.jpg"));
    }

    @Override
    public void act(float delta) {
        super.act(delta);

    }

    public void moveBy(float x, float y, float delta) {
        float speedMultiplier = 60.0f;

        float deltaX = x * speedMultiplier * delta;
        float deltaY = y * speedMultiplier * delta;

        float newX = getX() + deltaX;
        float newY = getY() + deltaY;

        setPosition(newX, newY);
    }

}
