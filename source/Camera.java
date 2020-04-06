import java.util.*;

/**
 * Camera
 * 
 * @author NC03
 * @version 1.0.0
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

    public Plane getPlane() {
        return new Plane(position.add(getOrthogonal()), getOrthogonal().unitVector());
    }

    public Vector getPosition() {
        return position;
    }

    public Vector getOrthogonal() {
        return new Vector(Math.cos(theta) * Math.cos(phi), Math.sin(theta) * Math.cos(phi), Math.sin(phi))
                .scalar(length);
    }

    public double getTheta()
    {
        return theta;
    }
    public double getPhi()
    {
        return phi;
    }
    public double getLength()
    {
        return length;
    }

    public String toString() {
        // String out = "";
        // out += theta + "," + phi + "," + getPosition() + "," + (getPosition().add(getOrthogonal())) + "," + getPlane();
        // return out;
        return "position: "+getPosition()+", angle: "+theta+", orthogonal: "+getOrthogonal();
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

        Vector nul = new Vector(0,0,0);

        Vector planar = point.add(v.scalar(t)).subtract(p);

        
        if(v.dotProduct(n) > 0)
        {
            return nul;
        }
        return planar;
    }

    public double[] projection(Vector point) {
        Vector planar = planarVector(point);
        // double a1 = planar.getX();
        // double a2 = planar.getY();
        // double a3 = planar.getZ();
        // double b3 = sec(phi) / (cot(phi) - tan(phi))
        // * (cot(phi) / (tan(theta) - cot(theta)) * (a2 * sec(theta) - a1 * csc(theta))
        // - a3);
        // double b2 = sec(phi)
        // * (a3 + (a3 - (a2 * sec(theta) - a1 * csc(theta)) / (tan(theta) -
        // cot(theta))) / (cot(phi) - tan(phi)));
        // double b1 = Math.abs(cos(phi)) * csc(theta) * (a1 * sec(phi) - cos(theta) *
        // tan(phi) * b2 - cos(theta) * b3);
        // return new double[]{b1,b2};



        // double[][] data = { { thetaHat().getX(), phiHat().getX(), rHat().getX(), planar.getX() },
        //         { thetaHat().getY(), phiHat().getY(), rHat().getY(), planar.getY() },
        //         { thetaHat().getZ(), phiHat().getZ(), rHat().getZ(), planar.getZ() } };
        // Matrix m = new Matrix(data);

        // // System.out.println(m);

        // Matrix temp = m.rref();

        // // System.out.println(temp);

        // double[] out = new double[temp.getData().length];
        // for (int i = 0; i < temp.getData().length; i++) {
        //     out[i] = temp.getData()[i][temp.getData()[i].length - 1];
        // }
        // return out;
        return new double[]{thetaHat().dotProduct(planar), phiHat().dotProduct(planar)};
    }

    boolean validate(Vector point)
    {
        Vector n = getOrthogonal();
        Vector b = getPosition();
        Vector p = b.add(n);
        Vector k = p.subtract(point);
        Vector v = b.subtract(point);
        double t = k.dotProduct(n) / v.dotProduct(n);

        Vector nul = new Vector(0,0,0);

        Vector planar = point.add(v.scalar(t)).subtract(p);

        return !(v.dotProduct(n) > 0);
    }

    private double sin(double ang) {
        return Math.sin(ang);
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