import java.awt.image.BufferedImage;

/**
 * Shape
 * 
 * @author NC03
 * @version 1.2.4
 * 
 */
public abstract class Shape implements Comparable<Shape> {
    public abstract void draw(BufferedImage bi, Environment e);

    public abstract void setOrigin(Vector o);

    public abstract Vector getOrigin();

    public double distance(){
        return centroid().subtract(getOrigin()).magnitude();
    }

    public abstract Vector centroid();

    @Override
    public int compareTo(Shape s){
        double diff = distance() - s.distance();
        if(diff > 0)
        {//Further distnace renders first so ascending order priority queue reverses output
            return -1;
        }else if(diff < 0){
            return 1;
        }else{
            return 0;
        }
    }

    public abstract boolean validate(Camera c);
}