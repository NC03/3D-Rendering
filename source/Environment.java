import java.util.*;
import java.io.*;
import java.awt.image.*;
import java.awt.color.*;
import javax.imageio.*;
import java.awt.Graphics;
import java.awt.Color;

/**
 * Environment
 * 
 * @author NC03
 * @version 1.0.0
 * 
 */
public class Environment {
    public Camera camera;
    public int width = 1920;
    public int height = 1080;
    private double maxX = 2;
    private List<Shape> shapes;
    private BufferedImage bi;

    public static void main(String[] args) {
        // Environment e = new Environment(new Camera(3 * Math.PI / 2, 0, new Vector(0, -1, 0), 1));
        // // Vector position = new Vector(0, 1, 0);
        // // System.out.println(e.camera.planarVector(position));
        // // System.out.println(Arrays.toString(e.camera.projection(position)));
        // Vector left = new Vector(-.5, 1, 0);
        // Vector right = new Vector(.5, 1, 0);
        // Vector top = new Vector(0, 1, 0.75);
        // e.add(new Point(left));
        // e.add(new Point(right));
        // e.add(new Point(top));
        // e.add(new Point(new Vector(0,0,0), new Color(0,0,0)));
        // e.draw();
        animation();
    }

    public static void animation()
    {
        double r = 3;
        Environment e = new Environment(new Camera(3 * Math.PI / 2, 0, new Vector(0, -1*r, 0), 1));
        Vector left = new Vector(-.5, 1, 0);
        Vector right = new Vector(.5, 1, 0);
        Vector top = new Vector(0, 1, 0.75);
        Vector O = new Vector(0,0,0);
        Vector i = new Vector(1,0,0);
        Vector j = new Vector(0,1,0);
        Vector k = new Vector(0,0,1);
        e.add(new Point(left));
        e.add(new Point(right));
        e.add(new Point(top));
        e.add(new Point(O, new Color(0,0,0)));
        e.add(new Line(O,i,new Color(255,0,0)));
        e.add(new Line(O,j,new Color(0,255,0)));
        e.add(new Line(O,k,new Color(0,0,255)));
        e.add(new Triangle(O,i,j,new Color(200,128,0)));
        double duration = 10;
        double dt = 1.0 / 30;
        int num = (int)(duration/dt);
        double dtheta = Math.PI * 2 / num;
        for(int a = 0; a <num; a++)
        {
            System.out.println(a+"/"+num);
            double theta = dtheta * a + 3*Math.PI/2;
            e.camera.setTheta(theta);
            e.camera.setPosition(O.add(new Vector(r*Math.cos(theta),r*Math.sin(theta),0)));
            // System.out.println(e);
            try {
                ImageIO.write(e.draw(),"png",new File("../preview/video/"+formatNum(a,num)+".png"));
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

    }

    public static String formatNum(int a, int max)
    {
        String out = a+"";
        while(out.length() < (""+max).length())
        {
            out = "0"+out;
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

    public int[] convert(Vector position) {
        double scalar = 1.0 / maxX * width / 2;
        double[] coords = camera.projection(position);
        int x = (int) (-1 * coords[0] * scalar + bi.getWidth() / 2);
        int y = (int) (1 * coords[1] * scalar + bi.getHeight() / 2);
        return new int[]{x,y};
    }

    public BufferedImage draw() {
        bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics g = bi.getGraphics();
        g.setColor(new Color(255,255,255));
        g.fillRect(0,0,bi.getWidth(),bi.getHeight());
        for (Shape s : shapes) {
            s.draw(bi,this);
        }
        return bi;
    }

    // public Vector planarVector(Vector point) {
    //     Vector n = camera.getOrthogonal();
    //     Vector b = camera.getPosition();
    //     Vector p = b.add(n);
    //     Vector k = p.subtract(point);
    //     Vector v = b.subtract(point);
    //     double t = k.dotProduct(n) / v.dotProduct(n);

    //     Vector planar = point.add(v.scalar(t)).subtract(camera.getPosition().add(n));
    //     return planar;

    //     // Vector p = camera.getPosition().add(camera.getOrthogonal());
    //     // Vector c = camera.getPosition();
    //     // Vector n = camera.getOrthogonal();
    //     // double scalar = p.dotProduct(n) / (Vector.subtract(c, point).dotProduct(n));
    //     // Vector planar = Vector.add(point, Vector.subtract(c, point).scalar(scalar))
    //     // .subtract(p);
    //     // return planar;
    // }

    public String toString() {
        String out = "";
        out += "Camera: "+camera+"\n";
        for(Shape s : shapes)
        {
            out += s+"\n";
        }
        return out;
    }

}