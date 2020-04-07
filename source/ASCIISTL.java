import java.awt.image.BufferedImage;
import java.awt.Color;
import java.util.List;
import java.util.ArrayList;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * 
 * ASCII STL
 * 
 * @author NC03
 * @version 1.2.5
 * 
 */
public class ASCIISTL extends Shape {
    private Vector position;
    private Color color;
    private List<Triangle> triangles;
    private List<Triangle> original;
    private Vector origin;
    // private double theta;
    // private double phi;

    public ASCIISTL(Vector position, Color c, File f) throws FileNotFoundException, IOException{
        this.position = position;
        this.color = c;
        triangles = new ArrayList<Triangle>();
        original = new ArrayList<Triangle>();

        BufferedReader br = new BufferedReader(new FileReader(f));
        String s = br.readLine();
        List<String> lines = new ArrayList<String>();
        while(s != null)
        {
            lines.add(s);
            s = br.readLine();
        }
        br.close();
        
        // System.out.println("start match");
        // Pattern p = Pattern.compile("facet normal.+?(vertex [\\d\\.e+-] [\\d\\.e+-] [\\d\\.e+-]).+?(vertex [\\d\\.e+-] [\\d\\.e+-] [\\d\\.e+-]).+?(vertex [\\d\\.e+-] [\\d\\.e+-] [\\d\\.e+-]).+?endfacet",Pattern.DOTALL);
        // Matcher m = p.matcher(read);
        // int count = 0;
        // System.out.println("startfind");
        // while(m.find() && count < 5)
        // {
        //     System.out.println("iter");
        //     System.out.println(m.group());
        //     count++;
        // }
        // System.out.println("done");
        for(int i = 0; i < lines.size() / 7; i++)
        {
            System.out.println(i);
            int idx = i*7 + 1 + 2;
            Pattern p = Pattern.compile("vertex ([\\d\\.e+-]+) ([\\d\\.e+-]+) ([\\d\\.e+-]+)");
            Matcher m = p.matcher(lines.get(idx));
            System.out.println(lines.get(idx));
            m.find();
            Vector v1 = new Vector(Double.parseDouble(m.group(1)),Double.parseDouble(m.group(2)),Double.parseDouble(m.group(3)));
            m = p.matcher(lines.get(idx+1));
            m.find();
            Vector v2 = new Vector(Double.parseDouble(m.group(1)),Double.parseDouble(m.group(2)),Double.parseDouble(m.group(3)));
            m = p.matcher(lines.get(idx+2));
            m.find();
            Vector v3 = new Vector(Double.parseDouble(m.group(1)),Double.parseDouble(m.group(2)),Double.parseDouble(m.group(3)));
            original.add(new Triangle(v1, v2, v3, color));
        }
        generateTriangles();
        

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
        this.origin = o;
    }

    @Override
    public Vector getOrigin() {
        return origin;
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


    public void generateTriangles()
    {
        triangles.clear();
        for(Triangle t : original)
        {
            triangles.add(t);
        }
    }

}