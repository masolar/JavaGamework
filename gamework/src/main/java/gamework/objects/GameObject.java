package gamework.objects;

import gamework.rendering.Renderable;
import gamework.util.Vector2D;

/**
 * An abstract class to represent an object that can be
 * updated and rendered in the game world. 
 */
public abstract class GameObject implements Renderable {
    protected double x;
    protected double y;
    protected Vector2D velocity;
    protected Vector2D acceleration;

    protected GameObject(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public Vector2D getVelocity() {
        return velocity;
    }

    public Vector2D getAcceleration() {
        return acceleration;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setVelocity(double x, double y) {
        this.velocity = new Vector2D(x, y);
    }

    public void setAcceleration(double x, double y) {
        this.acceleration = new Vector2D(x, y);
    }

    /**
     * A function called once per frame to perform the game logic.
     */
    public abstract void update();
}
