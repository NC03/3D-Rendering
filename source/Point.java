import java.awt.image.*;
import java.awt.Graphics;

public class Point extends Shape {
    private Vector position;

    public Point(Vector position) {
        this.position = position;
    }

    @Override
    public void draw(BufferedImage bi, Environment e) {
        Graphics g = bi.getGraphics();
        int[] points = e.convert(position);
        int x = points[0];
        int y = points[1];
        if(x >= 0 && y >= 0 && x < bi.getWidth() && y < bi.getHeight())
        {
            g.fillOval(x, y, 20, 20);
        }
    }


}