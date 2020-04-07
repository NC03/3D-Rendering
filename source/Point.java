import java.awt.image.BufferedImage;
import java.awt.Graphics;
import java.awt.Color;

/**
 * Point
 * 
 * @author NC03
 * @version 1.2.5
 * 
 */
public class Point extends Shape {
    private Vector position;
    private Color color;
    private int dim = 20;
    private Vector origin;

    public Point(Vector position, Color c) {
        this.position = position;
        this.color = c;
    }

    public Point(Vector position) {
        this.position = position;
        this.color = new Color(255, 0, 0);
    }

    @Override
    public void draw(BufferedImage bi, Environment e) {
        if (e.camera.validate(position)) {
            Graphics g = bi.getGraphics();
            g.setColor(color);
            int[] points = e.convertToCanvas(position);
            int x = points[0];
            int y = points[1];
            g.fillOval(x - dim / 2, y - dim / 2, dim, dim);
        }
    }

    @Override
    public String toString() {
        String out = super.toString() + ": ";
        out += "position: " + position;
        return out;
    }

    @Override
    public void setOrigin(Vector o) {
        this.origin = o;
    }

    @Override
    public Vector getOrigin()
    {
        return this.origin;
    }

    @Override
    public Vector centroid() {
        return position;
    }

    @Override
    public boolean validate(Camera c) {
        return c.validate(position);
    }

}