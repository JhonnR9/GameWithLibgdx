package me.jhonn;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;


public class SpriteAnimation {
    private Animation<TextureRegion> animation;

    float frameDuration = 0.2f;

    public void setFrameDuration(float frameDuration) {
        this.frameDuration = frameDuration;
    }

    private float elapsedTime;

    public SpriteAnimation(Texture texture, int columns, int rows, int frameWidth,
                           int frameHeight, int startX, int startY, Animation.PlayMode playMode) {
        TextureRegion[] sprites = createTexturesRegions(texture, frameWidth, frameHeight, startX, startY, columns, rows);
        animation = createAnimation(sprites, frameDuration, playMode);
    }
    public SpriteAnimation(Texture texture, int columns, int rows, int frameWidth,
                           int frameHeight, Animation.PlayMode playMode) {
        TextureRegion[] sprites = createTexturesRegions(texture, frameWidth, frameHeight, 0, 0, columns, rows);
        animation = createAnimation(sprites, frameDuration, playMode);
    }

    public SpriteAnimation(Texture texture, int columns, int rows, Animation.PlayMode playMode) {
        TextureRegion[] sprites = createTexturesRegions(texture, 16, 16, 0, 0, columns, rows);
        animation = createAnimation(sprites, frameDuration, playMode);
    }

    public SpriteAnimation(Texture texture, int columns, int rows) {
        TextureRegion[] sprites = createTexturesRegions(texture, 16, 16, 0, 0, columns, rows);
        animation = createAnimation(sprites, frameDuration, Animation.PlayMode.LOOP);
    }


    private TextureRegion[] createTexturesRegions(Texture texture, int frameWidth, int frameHeight, int startX, int startY, int columns, int rows) {
        TextureRegion[][] frames = TextureRegion.split(texture, frameWidth, frameHeight);

        TextureRegion[] textureRegions = new TextureRegion[columns * rows];

        int index = 0;
        for (int row = startY; row < startY + rows; row++) {
            for (int col = startX; col < startX + columns; col++) {
                textureRegions[index++] = frames[row][col];
            }
        }

        return textureRegions;
    }

    private Animation<TextureRegion> createAnimation(TextureRegion[] frames, float frameDuration, Animation.PlayMode playMode) {
        Animation<TextureRegion> animation = new Animation<>(frameDuration, frames);
        animation.setPlayMode(playMode);
        return animation;
    }


    public void update(float deltaTime) {
        elapsedTime += deltaTime;
    }

    public TextureRegion getCurrentFrame() {
        return animation.getKeyFrame(elapsedTime);
    }

}
