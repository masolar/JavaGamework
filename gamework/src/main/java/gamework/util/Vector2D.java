package gamework.util;

public class Vector2D {
    private double x;
    private double y;

    /**
     * Creates a new vector given its x and y components
     * @param x The length of the x component of the vector
     * @param y The length of the y component of the vector
     */
    public Vector2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Creates a new unit vector in the given direction.
     * @param orientation The direction of the vector in degrees.
     */
    public Vector2D(double orientation) {
        this.x = Math.cos(Math.toRadians(orientation));
        this.y = Math.sin(Math.toRadians(orientation));
    }

    /**
     * Adds two vectors together tail to head.
     * @param other The vector to be added to this one.
     * @return A new vector that is the sum of the two vectors
     */
    public Vector2D add(Vector2D other) {
        return new Vector2D(this.x + other.x, this.y + other.y);
    }

    /**
     * Scales a vector by a certain amount.
     * @param scalar The amount to scale the vector's length
     * @return A new vector that has been scaled by the given amount
     */
    public Vector2D scale(double scalar) {
        return new Vector2D(this.x * scalar, this.y * scalar);
    }

    /**
     * Takes the dot product between two vectors.
     * @param other The other vector that is part of the dot product.
     * @return A single number representing the dot product between two vectors.
     */
    public double dot(Vector2D other) {
        return this.x * other.x + this.y * other.y;
    }

    /**
     * Gets the x component of the vector.
     * @return A double representing the length of the x component of the vector.
     */
    public double getX() {
        // cos expects our orientation in radians, so it needs to be converted
        return this.x;
    }

    /**
     * Gets the y component of the vector.
     * @return A double representing the length of the y component of the vector.
     */
    public double getY() {
        // sin expects our orientation in radians, so it needs to be converted
        return this.y;
    }

    /**
     * Returns the magnitude (length) of the vector.
     * @return A number representing the length of a vector.
     */
    public double getLength() {
        return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
    }
    
    /**
     * Gets the direction of the vector in degrees.
     * @return The direction of the vector in degrees.
     */
    public double getDirection() {
        return Math.toDegrees(Math.atan2(this.y, this.x));
    }

    /**
     * Returns the unit representation of the given vector
     * @return A new vector that has length 1 and the same orientation
     */
    public Vector2D unit() {
        double length = this.getLength();
        return new Vector2D(this.x / length, this.y / length);
    }

    public boolean equals(Object other) {
        // Check if other is null
        if (other == null) {
            return false;
        }

        // Self check
        if (this == other) {
            return true;
        }

        // Make sure these are of the samem class
        if (getClass() != other.getClass()) {
            return false;
        }

        // Finally check if the objects match
        Vector2D vec = (Vector2D)other;

        // Double equality is tricky, so we'll check that the difference is small enough to not matter
        return this.x - vec.x < .0001 && this.y - vec.y < .0001;
    }
}
