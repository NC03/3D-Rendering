public class Camera {
    private double theta;
    private double phi;
    private Vector position;
    private Plane plane;
    private Vector orthogonal;
    private double length;

    public Camera(double theta, double phi, Vector position, double dist) {
        setTheta(theta);
        setPhi(phi);
        setPosition(position);
        setLength(dist);
        updatePlane();
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

    public void updatePlane() {
        this.orthogonal = new Vector(Math.cos(theta) * Math.cos(phi), Math.sin(theta) * Math.cos(phi), Math.sin(phi))
                .scalar(length);
        this.plane = new Plane(position.add(orthogonal), orthogonal.unitVector());
    }

    public Vector getPosition()
    {
        return position;
    }
    public Vector getOrthogonal()
    {
        return orthogonal;
    }
    public Plane getPlane()
    {
        return plane;
    }
}