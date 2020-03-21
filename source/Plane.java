
/**
 * Plane
 * 
 * @author NC03
 * @version 1.0.0
 * 
 */
public final class Plane
{
    private final Vector point;
    private final Vector orthogonal;

    
    public Plane(Vector point, Vector orthogonal)
    {
        this.point = point;
        this.orthogonal = orthogonal;
    }

    public String toString()
    {
        return ""+point+","+orthogonal;
    }
}