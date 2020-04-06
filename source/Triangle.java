import java.awt.image.BufferedImage;
import java.awt.Color;
import java.awt.Graphics;

/**
 * Sphere
 * 
 * @author NC03
 * @version 1.2.1
 * 
 */
public class Triangle extends Shape {

    private Vector a;
    private Vector b;
    private Vector c;
    private Color color;

    public Triangle(Vector a, Vector b, Vector c, Color d) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.color = d;
    }

    public Triangle(Vector a, Vector b, Vector c) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.color = new Color(128, 128, 128);
    }

    @Override
    public void draw(BufferedImage bi, Environment e) {
        if (e.camera.validate(a) && e.camera.validate(b) && e.camera.validate(c)) {
            Graphics g = bi.getGraphics();
            g.setColor(color);
            int[] points = e.convertToCanvas(a);
            int x1 = points[0];
            int y1 = points[1];
            points = e.convertToCanvas(b);
            int x2 = points[0];
            int y2 = points[1];
            points = e.convertToCanvas(c);
            int x3 = points[0];
            int y3 = points[1];
            g.fillPolygon(new int[] { x1, x2, x3 }, new int[] { y1, y2, y3 }, 3);
        }
    }

}