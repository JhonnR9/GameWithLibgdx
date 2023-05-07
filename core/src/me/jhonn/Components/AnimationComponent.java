package me.jhonn.Components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

import me.jhonn.utils.AnimationConfig;

import java.util.HashMap;
import java.util.Map;

public class AnimationComponent implements Component {

    private Texture texture;

    public void setTexture(Texture texture) {
        this.texture = texture;
    }

    private TextureRegion frame;
   private final Map<String, Animation<TextureRegion>> animations = new HashMap<>();

    private float elapsedTime;
    private  String currentAnimationKey;

    public String getCurrentAnimationKey() {
        return currentAnimationKey;
    }

    public void setCurrentAnimationKey(String currentAnimationKey) {
        this.currentAnimationKey = currentAnimationKey;
    }

    public AnimationComponent() {

    }

    public TextureRegion getFrame(String animationName) {
        frame = animations.get(animationName).getKeyFrame(elapsedTime);
        return frame;
    }



    private TextureRegion[] createTexturesRegions(AnimationConfig ac) {
        TextureRegion[][] frames = TextureRegion.split(texture, ac.getFrameWidth(), ac.getFrameHeight());

        TextureRegion[] textureRegions = new TextureRegion[ac.getColumns() * ac.getRows()];

        int index = 0;
        for (int row = ac.getStartY(); row < ac.getStartY() + ac.getRows(); row++) {
            for (int col = ac.getStartX(); col < ac.getStartX() + ac.getColumns(); col++) {
                textureRegions[index++] = frames[row][col];
            }
        }

        return textureRegions;
    }

    public void createAnimation(AnimationConfig ac, String animationKey) {
        Animation<TextureRegion> animation = new Animation<>(ac.getFrameDuration(), createTexturesRegions(ac));
        if (ac.isLoop()) {
            animation.setPlayMode(Animation.PlayMode.LOOP);
        } else {
            animation.setPlayMode(Animation.PlayMode.NORMAL);
        }

        animations.put(animationKey, animation) ;

    }

    public void update(float deltaTime) {
        elapsedTime += deltaTime;

    }


}
