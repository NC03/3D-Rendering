import java.awt.image.BufferedImage;
import java.awt.Color;
import java.util.List;
import java.util.ArrayList;

/**
 * 
 * Sphere
 * 
 * @author NC03
 * @version 1.2.3
 * @deprecated
 * 
 */
@Deprecated
public class Sphere extends Shape {
    private boolean start;
    private Vector position;
    private double radius;
    private Color color;
    private List<Triangle> triangles;

    public Sphere(Vector position, double radius, Color c) {
        updatePosition(position);
        this.color = c;
        this.radius = radius;
        this.start = true;
    }

    public Sphere(Vector position, double radius) {
        updatePosition(position);
        this.color = new Color(0,128,255);
        this.radius = radius;
        this.start = true;
    }

    public Sphere(Vector position) {
        updatePosition(position);
        this.color = new Color(0,128,255);
        this.radius = 1;
        this.start = true;
    }

    @Override
    public void draw(BufferedImage bi, Environment e) {
        for(Triangle t : triangles){
            if(t.validate(e.camera))
            {
                t.draw(bi,e);
            }
        }
    }

    @Override
    public void setOrigin(Vector o) {
        // TODO Auto-generated method stub

    }

    @Override
    public Vector getOrigin() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Vector centroid() {
        return position;
    }

    @Override
    public boolean validate(Camera c) {
        for(Triangle t : triangles)
        {
            if(t.validate(c))
            {
                return true;
            }
        }
        return false;
    }

    public void updatePosition(Vector pos)
    {
        this.position = pos;
        generateTriangles();
    }

    public void generateTriangles()
    {
        triangles.clear();

    }

}