public class Camera {
    private double theta;
    private double phi;
    private Vector position;

    public Camera(double theta, double phi, Vector position) {
        setTheta(theta);
        setPhi(phi);
        setPosition(position);
    }

    public void setTheta(double theta) {
        this.theta = theta;

    }

    public void setPhi(double phi) {
        this.phi = phi;

    }

    public void setPosition(Vector position) {
        this.position = position;
    }
}