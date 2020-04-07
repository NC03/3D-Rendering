/**
 * Camera
 * 
 * @author NC03
 * @version 1.2.5
 * 
 */
public class Camera {
    private double theta;
    private double phi;
    private Vector position;
    private Vector orthogonal;
    private double length;

    public Camera(double theta, double phi, Vector position, double dist) {
        setTheta(theta);
        setPhi(phi);
        setPosition(position);
        setLength(dist);
    }

    public void setTheta(double theta) {
        this.theta = theta;
    }

    public void setLength(double dist) {
        this.length = dist;
    }

    public void setPhi(double phi) {
        this.phi = phi;
    }

    public void setPosition(Vector position) {
        this.position = position;
    }

    public Vector getPosition() {
        return position;
    }

    public Vector getOrthogonal() {
        return new Vector(Math.cos(theta) * Math.cos(phi), Math.sin(theta) * Math.cos(phi), Math.sin(phi))
                .scalar(length);
    }

    public double getTheta() {
        return theta;
    }

    public double getPhi() {
        return phi;
    }

    public double getLength() {
        return length;
    }

    public String toString() {
        return "position: " + getPosition() + ", angle: " + theta + ", orthogonal: " + getOrthogonal();
    }

    public Vector thetaHat() {
        return new Vector(-Math.cos(phi) * Math.sin(theta), Math.cos(theta) * Math.cos(phi), 0)
                .scalar(1 / Math.abs(Math.cos(phi)));
    }

    public Vector phiHat() {
        return new Vector(-Math.cos(theta) * Math.sin(phi), Math.sin(theta) * Math.sin(phi), Math.cos(phi));
    }

    public Vector rHat() {
        return new Vector(Math.cos(theta) * Math.cos(phi), Math.sin(theta) * Math.cos(phi), Math.sin(phi));
    }

    public Vector planarVector(Vector point) {
        Vector n = getOrthogonal();
        Vector b = getPosition();
        Vector p = b.add(n);
        Vector k = p.subtract(point);
        Vector v = b.subtract(point);
        double t = k.dotProduct(n) / v.dotProduct(n);
        Vector planar = point.add(v.scalar(t)).subtract(p);
        return planar;
    }

    public boolean validate(Vector point) {
        Vector n = getOrthogonal();
        Vector b = getPosition();
        Vector v = b.subtract(point);
        return (v.dotProduct(n) > 0);
    }

    public boolean validate(Vector[] points)
    {
        for(Vector v : points)
        {
            if(!validate(v))
            {
                return false;
            }
        }
        return true;
    }

    private double sin(double ang) {
        return Math.sin(ang);
    }

    public double[] projection(Vector point) {
        Vector planar = planarVector(point);
        double[] out = { thetaHat().dotProduct(planar), phiHat().dotProduct(planar) };
        return out;
    }

    private double cos(double ang) {
        return Math.cos(ang);
    }

    private double tan(double ang) {
        return Math.tan(ang);
    }

    private double cot(double ang) {
        return 1.0 / tan(ang);
    }

    private double csc(double ang) {
        return 1.0 / sin(ang);
    }

    private double sec(double ang) {
        return 1.0 / cos(ang);
    }
}