import java.awt.image.*;
import java.awt.Graphics;
import java.awt.Color;

/**
 * Point
 * 
 * @author NC03
 * @version 1.0.0
 * 
 */
public class Point extends Shape {
    private Vector position;
    private Color color;

    public Point(Vector position, Color c) {
        this.position = position;
        this.color = c;
    }

    public Point(Vector position)
    {
        this.position = position;
        this.color = new Color(255,0,0);
    }

    @Override
    public void draw(BufferedImage bi, Environment e) {
        Graphics g = bi.getGraphics();
        g.setColor(color);
        int[] points = e.convert(position);
        int x = points[0];
        int y = points[1];
        if(x >= 0 && y >= 0 && x < bi.getWidth() && y < bi.getHeight())
        {
            g.fillOval(x, y, 20, 20);
        }
    }


}