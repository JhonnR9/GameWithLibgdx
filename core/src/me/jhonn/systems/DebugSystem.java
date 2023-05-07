package me.jhonn.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;
import me.jhonn.Components.DebugComponent;
import me.jhonn.Components.SpriteComponent;
import me.jhonn.Components.TransformComponent;

public class DebugSystem extends IteratingSystem {
    private final ShapeRenderer shapeRenderer;

    public DebugSystem(ShapeRenderer shapeRenderer) {
        super(Family.all(TransformComponent.class, DebugComponent.class, SpriteComponent.class).get());
        this.shapeRenderer = shapeRenderer;
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        DebugComponent debug = entity.getComponent(DebugComponent.class);

        if (debug.isDebug()) {

            TransformComponent transform = entity.getComponent(TransformComponent.class);
            SpriteComponent sprite = entity.getComponent(SpriteComponent.class);

            Rectangle rect = debug.getRectangle(transform, sprite);

            Polygon polygon = new Polygon(getRotateVertices(rect, sprite.getRotation()));

            shapeRenderer.polygon(polygon.getVertices());

        }
    }

    /**
     * Returns the coordinates of the vertices of a rectangle after it has been rotated
     * by the specified angle around its center.
     *
     * @param rect  the rectangle to rotate
     * @param angle the angle to rotate the rectangle by, in degrees
     * @return an array containing the coordinates of the vertices of the rotated rectangle,
     * in the order x1, y1, x2, y2, x3, y3, x4, y4
     */
    public float[] getRotateVertices(Rectangle rect, float angle) {
        // Get the coordinates of the center of the rectangle
        float centerX = rect.x + rect.width / 2f;
        float centerY = rect.y + rect.height / 2f;

        // Convert the angle to radians
        float radians = MathUtils.degreesToRadians * angle;

        // Calculate the sine and cosine of the angle in radians
        float cos = MathUtils.cos(radians);
        float sin = MathUtils.sin(radians);

        // Calculate the coordinates of the vertices of the rotated rectangle
        float x1 = rect.x - centerX;
        float y1 = rect.y - centerY;
        float x2 = rect.x + rect.width - centerX;
        float y2 = rect.y - centerY;
        float x3 = rect.x + rect.width - centerX;
        float y3 = rect.y + rect.height - centerY;
        float x4 = rect.x - centerX;
        float y4 = rect.y + rect.height - centerY;

        // Rotate each vertex around the center of the rectangle
        float tempX = x1 * cos - y1 * sin;
        float tempY = x1 * sin + y1 * cos;
        x1 = tempX + centerX;
        y1 = tempY + centerY;

        tempX = x2 * cos - y2 * sin;
        tempY = x2 * sin + y2 * cos;
        x2 = tempX + centerX;
        y2 = tempY + centerY;

        tempX = x3 * cos - y3 * sin;
        tempY = x3 * sin + y3 * cos;
        x3 = tempX + centerX;
        y3 = tempY + centerY;

        tempX = x4 * cos - y4 * sin;
        tempY = x4 * sin + y4 * cos;
        x4 = tempX + centerX;
        y4 = tempY + centerY;

        // Return the coordinates of the vertices of the rotated rectangle
        return new float[]{x1, y1, x2, y2, x3, y3, x4, y4};
    }




}
