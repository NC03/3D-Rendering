import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.Graphics;
import java.awt.Color;
import java.util.PriorityQueue;

/**
 * Environment
 * 
 * @author NC03
 * @version 1.2.5
 * 
 */
public class Environment {
    public Camera camera;
    public int width = 1920;
    public int height = 1080;
    private double maxX = 2;
    private List<Shape> shapes;
    private Color backgroundColor = new Color(255, 255, 255);

    public static void main(String[] args) {
        animation();
    }

    public static void animation() {
        double r = 3;
        Environment e = new Environment(new Camera(3 * Math.PI / 2, 0, new Vector(0, -1 * r, 0), 1));
        Vector left = new Vector(-.5, 1, 0);
        Vector right = new Vector(.5, 1, 0);
        Vector top = new Vector(0, 1, 0.75);
        Vector O = new Vector(0, 0, 0);
        Vector i = new Vector(1, 0, 0);
        Vector j = new Vector(0, 1, 0);
        Vector k = new Vector(0, 0, 1);
        e.add(new Point(left));
        e.add(new Point(right));
        e.add(new Point(top));
        e.add(new Point(O, new Color(0, 0, 0)));
        e.add(new Line(O, i, new Color(255, 0, 0)));
        e.add(new Line(O, j, new Color(0, 255, 0)));
        e.add(new Line(O, k, new Color(0, 0, 255)));
        e.add(new Triangle(O, i, j, new Color(200, 128, 0)));
        double duration = 10;
        double dt = 1.0 / 30;
        int num = (int) (duration / dt);
        double dtheta = Math.PI * 2 / num;
        for (int a = 0; a < num; a++) {
            System.out.println(a + "/" + num);
            double theta = dtheta * a + 3 * Math.PI / 2;
            e.camera.setTheta(theta);
            e.camera.setPosition(O.add(new Vector(r * Math.cos(theta), r * Math.sin(theta), 0)));
            try {
                ImageIO.write(e.draw(), "png", new File("preview/video/" + formatNum(a, num) + ".png"));
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public static String formatNum(int a, int max) {
        String out = a + "";
        while (out.length() < ("" + max).length()) {
            out = "0" + out;
        }
        return out;
    }

    public Environment(Camera c) {
        this.camera = c;
        shapes = new ArrayList<Shape>();
    }

    public void add(Shape s) {
        shapes.add(s);
    }

    public void remove(Shape s)
    {
        shapes.remove(s);
    }

    public int[] convertToCanvas(Vector position) {
        double scalar = 1.0 / maxX * width / 2; // Scaling maintaing ratio to fill width
        double[] coords = camera.projection(position);
        int x = (int) (coords[0] * scalar);
        int y = (int) (coords[1] * scalar);
        x *= -1;
        y *= -1;
        x = width / 2 + x;
        y = height / 2 - y;
        return new int[] { x, y };
    }

    public BufferedImage draw() {
        BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics g = bi.getGraphics();
        g.setColor(backgroundColor);
        g.fillRect(0, 0, bi.getWidth(), bi.getHeight());
        PriorityQueue<Shape> pq = new PriorityQueue<Shape>();
        for (Shape s : shapes) {
            s.setOrigin(camera.getPosition());
            if (s.validate(camera)) {
                pq.add(s);
            }
        }
        Shape s = pq.poll();
        while (s != null) {
            s.draw(bi, this);
            s = pq.poll();
        }
        return bi;
    }

    public String toString() {
        String out = "";
        out += "Camera: " + camera + "\n";
        for (Shape s : shapes) {
            out += s + "\n";
        }
        return out;
    }

}