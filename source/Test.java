import java.awt.image.*;
import java.awt.Color;
import javax.imageio.*;
import java.io.*;
import java.awt.Graphics;

/**
 * Sphere
 * 
 * @author NC03
 * @version 1.2.3
 * @deprecated
 * 
 */
@Deprecated
public class Test {
    public static void main(String[] args) {
        BufferedImage bi = new BufferedImage(1920, 1080, BufferedImage.TYPE_INT_RGB);
        Graphics g = bi.getGraphics();
        g.setColor(new Color(255, 255, 255));
        g.fillRect(0, 0, bi.getWidth(), bi.getHeight());
        g.setColor(new Color(255, 0, 0));
        g.drawLine(bi.getWidth() / 2, bi.getHeight() / 2, bi.getWidth() * 3 / 2, bi.getHeight() * 3 / 4);
        try {
            ImageIO.write(bi, "png", new File("testOut.png"));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}