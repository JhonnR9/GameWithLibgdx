package me.jhonn.Components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class DebugComponent implements Component {
    private boolean isDebug = true;
    public Boolean isDebug() {
        return isDebug;
    }

    public void setDebug(Boolean debug) {
        isDebug = debug;
    }

    /**
     * Returns a Rectangle object that represents the area of the sprite rectangle
     * with transformations applied.
     *
     * @param transform The transform component that contains the position and scale
     * of the sprite.
     * @param sprite The sprite component that contains the position and dimensions
     * of the sprite.
     * @return A Rectangle object that represents the area of the sprite rectangle
     * with transformations applied.
     */
    public Rectangle getRectangle(TransformComponent transform, SpriteComponent sprite) {
        // Calculate the new position of the sprite based on the transform component
        Vector2 newPosition = sprite.getDebugPosition(transform);
        float worldOriginX = newPosition.x + sprite.getOriginX();
        float worldOriginY = newPosition.y + sprite.getOriginY();

        float x = -sprite.getOriginX();
        float y = -sprite.getOriginY();
        float width = sprite.getWidth();
        float height = sprite.getHeight();

        // Apply the scale transformation to the sprite if necessary
        Vector2 scale = transform.getScale();
        if (scale.x != 1 || scale.y != 1) {
            x *= scale.x;
            y *= scale.y;
            width *= scale.x;
            height *= scale.y;
        }

        // Return the rectangle object representing the transformed sprite
        return new Rectangle(worldOriginX + x, worldOriginY + y, width, height);
    }



}
