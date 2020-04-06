import java.awt.image.*;
import java.awt.Graphics;
import java.awt.Color;
import java.util.*;

/**
 * Point
 * 
 * @author NC03
 * @version 1.2.1
 * 
 */
public class Point extends Shape {
    private Vector position;
    private Color color;
    private int dim = 20;

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
            // if (x >= 0 && y >= 0 && x < bi.getWidth() && y < bi.getHeight()) {
                g.fillOval(x - dim / 2, y - dim / 2, dim, dim);
            // } else {
            //     System.out.println("what");
            // }
        }
    }

    @Override
    public String toString() {
        String out = super.toString() + ": ";
        out += "position: " + position;
        return out;
    }

}