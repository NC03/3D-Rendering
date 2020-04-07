/**
 * Vector
 * 
 * @author NC03
 * @version 1.2.4
 * 
 */
public final class Vector {
    private final double x, y, z;

    // Constructor
    public Vector(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    // Fundamental Qualities
    public double getX() {
        return this.x;
    }

    public double getY() {
        return this.y;
    }

    public double getZ() {
        return this.z;
    }

    public double magnitude() {
        return Math.sqrt(getX() * getX() + getY() * getY() + getZ() * getZ());
    }

    public String toString() {
        return getX() + "," + getY() + "," + getZ();
    }

    // Advanced Operations
    public Vector scalar(double a) {
        return new Vector(getX() * a, getY() * a, getZ() * a);
    }

    public Vector add(Vector a) {
        return new Vector(getX() + a.getX(), getY() + a.getY(), getZ() + a.getZ());
    }

    public double dotProduct(Vector a) {
        return getX() * a.getX() + getY() * a.getY() + getZ() * a.getZ();
    }

    public Vector crossProduct(Vector a) {
        return new Vector(getY() * a.getZ() - getZ() * a.getY(), getZ() * a.getX() - getX() * a.getZ(),
                getX() * a.getY() - getY() * a.getX());
    }

    public Vector unitVector() {
        return scalar(1 / magnitude());
    }

    public Vector subtract(Vector a) {
        return add(a.scalar(-1));
    }

    public Vector projectOnto(Vector b) {
        return b.scalar(dotProduct(b) / b.magnitude() / b.magnitude());
    }

    // Class Methods
    public static Vector add(Vector a, Vector b) {
        return a.add(b);
    }

    public static double dotProduct(Vector a, Vector b) {
        return a.dotProduct(b);
    }

    public static Vector crossProduct(Vector a, Vector b) {
        return a.crossProduct(b);
    }

    public static Vector subtract(Vector a, Vector b) {
        return a.subtract(b);
    }

    public static Vector project(Vector a, Vector b) {
        return a.projectOnto(b);
    }

}