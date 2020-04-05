import java.awt.image.*;
import java.awt.Graphics;
import java.awt.Color;

/**
 * Line
 * 
 * @author NC03
 * @version 1.0.0
 * 
 */
public class Line extends Shape {
    private Vector pos1;
    private Vector pos2;
    private Color color;

    public Line(Vector pos1, Vector pos2, Color c) {
        this.pos1 = pos1;
        this.pos2 = pos2;
        this.color = c;
    }

    public Line(Vector pos1, Vector pos2) {
        this.pos1 = pos1;
        this.pos2 = pos2;
        this.color = new Color(255, 0, 0);
    }

    @Override
    public void draw(BufferedImage bi, Environment e) {
        Graphics g = bi.getGraphics();
        g.setColor(color);
        int[] points = e.graphicsCoordinates(pos1);
        int x1 = points[0];
        int y1 = points[1];
        points = e.graphicsCoordinates(pos2);
        int x2 = points[0];
        int y2 = points[1];
        g.drawLine(x1,y1,x2,y2);
    }

}