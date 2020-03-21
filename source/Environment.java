public class Environment {
    private Camera camera;

    public static void main(String[] args) {
        Environment e = new Environment(new Camera(3*Math.PI/2, 0, new Vector(0,-1,0), 1));
        Vector position = new Vector(0, 1, 0);
        System.out.println(e.planarVector(position));

    }

    public Environment(Camera c) {
        this.camera = c;

    }

    public Vector planarVector(Vector point) {
        double scalar = camera.getPosition().add(camera.getOrthogonal()).dotProduct(camera.getOrthogonal())
                / (Vector.subtract(camera.getPosition(), point).dotProduct(camera.getOrthogonal()));
        Vector planar = Vector.add(point, Vector.subtract(camera.getPosition(), point).scalar(scalar))
                .subtract(camera.getPosition().add(camera.getOrthogonal()));
        return planar;
    }

}