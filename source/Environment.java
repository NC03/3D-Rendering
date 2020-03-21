import java.util.*;
import java.io.*;
import java.awt.image.*;
import java.awt.color.*;
import javax.imageio.*;
import java.awt.Graphics;
import java.awt.Color;

public class Environment {
    private Camera camera;
    public int width = 1920;
    public int height = 1080;
    private double maxX = 1;
    private List<Shape> shapes;
    private BufferedImage bi;

    public static void main(String[] args) {
        Environment e = new Environment(new Camera(3 * Math.PI / 2, 0, new Vector(0, -1, 0), 1));
        // Vector position = new Vector(0, 1, 0);
        // System.out.println(e.camera.planarVector(position));
        // System.out.println(Arrays.toString(e.camera.projection(position)));
        Vector left = new Vector(-.5, 1, 0);
        Vector right = new Vector(.5, 1, 0);
        Vector top = new Vector(0, 1, 0.75);
        e.add(new Point(left));
        e.add(new Point(right));
        e.add(new Point(top));
        e.add(new Point(new Vector(0,0,0), new Color(0,0,0)));
        e.draw();
    }

    public Environment(Camera c) {
        this.camera = c;
        shapes = new ArrayList<Shape>();
    }

    public void add(Shape s) {
        shapes.add(s);
    }

    public int[] convert(Vector position) {
        double scalar = 1.0 / maxX * width / 2;
        double[] coords = camera.projection(position);
        int x = (int) (-1 * coords[0] * scalar + bi.getWidth() / 2);
        int y = (int) (1 * coords[1] * scalar + bi.getHeight() / 2);
        return new int[]{x,y};
    }

    public void draw() {
        bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics g = bi.getGraphics();
        g.setColor(new Color(255,255,255));
        g.fillRect(0,0,bi.getWidth(),bi.getHeight());
        for (Shape s : shapes) {
            s.draw(bi,this);
        }
        try {
            ImageIO.write(bi, "png", new File("out.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Vector planarVector(Vector point) {
        Vector n = camera.getOrthogonal();
        Vector b = camera.getPosition();
        Vector p = b.add(n);
        Vector k = p.subtract(point);
        Vector v = b.subtract(point);
        double t = k.dotProduct(n) / v.dotProduct(n);

        Vector planar = point.add(v.scalar(t)).subtract(camera.getPosition().add(n));
        return planar;

        // Vector p = camera.getPosition().add(camera.getOrthogonal());
        // Vector c = camera.getPosition();
        // Vector n = camera.getOrthogonal();
        // double scalar = p.dotProduct(n) / (Vector.subtract(c, point).dotProduct(n));
        // Vector planar = Vector.add(point, Vector.subtract(c, point).scalar(scalar))
        // .subtract(p);
        // return planar;
    }

    public String toString() {
        return "" + camera;
    }

}