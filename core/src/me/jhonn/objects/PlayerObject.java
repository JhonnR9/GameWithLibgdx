package me.jhonn.objects;

import com.badlogic.gdx.graphics.g2d.Animation;
import me.jhonn.ResourceManager;
import me.jhonn.SpriteAnimation;

public class PlayerObject extends Object {
    float velocity = 4f;
    private boolean isBack = false;
    private SpriteAnimation spriteAnimation;

    public PlayerObject(ResourceManager resManager) {
        super(resManager);
        setTexture(resManager.getTexture("character.png"));
        spriteAnimation = new SpriteAnimation(
                getTexture(),
                4,1,
                16,16,
                Animation.PlayMode.LOOP);
        spriteAnimation.setFrameDuration(.9f);
    }

    @Override
    public void act(float delta) {
        spriteAnimation.update(delta);
        this.setTextureRegion(spriteAnimation.getCurrentFrame());
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
