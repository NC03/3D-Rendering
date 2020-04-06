import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseEvent;
import java.awt.event.KeyEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.KeyListener;


public class Interactive extends JFrame{
    
    private static final long serialVersionUID = 1L;
    private BufferedImage image;
    public Environment e;

    public double dp = 0.125;
    public double dt = Math.PI/32;
    public double dl = 0.0625;



    public static void main(String[] args) {
        new Interactive();
    }

    public Interactive() {
        super("Mandelbrot Viewer");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
        setSize(600, 400);


        Vector O = new Vector(0, 0, 0);
        Vector i = new Vector(1, 0, 0);
        Vector j = new Vector(0, 1, 0);
        Vector k = new Vector(0, 0, 1);

        e = new Environment(new Camera(Math.PI / 2.0, 0, new Vector(0, -1, 0), 1));
        e.add(new Line(O, i, new Color(255, 0, 0)));
        e.add(new Line(O, j, new Color(0, 255, 0)));
        e.add(new Line(O, k, new Color(0, 0, 255)));
        e.add(new Point(O,new Color(0,0,0)));
        e.add(new Point(i,new Color(255,0,0)));
        e.add(new Point(j,new Color(0,255,0)));
        e.add(new Point(k,new Color(0,0,255)));
        e.add(new Triangle(O,i,j));
        e.add(new Triangle(O,j,k,new Color(128,128,0)));
        image = e.draw();

        addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent evt) {
                genImage();
                repaint();
            }
        });
        addMouseListener(new MouseListener() {
            public void mouseExited(MouseEvent me) {

            }

            public void mouseEntered(MouseEvent me) {

            }

            public void mouseReleased(MouseEvent me) {

            }

            public void mousePressed(MouseEvent me) {
                
            }

            public void mouseClicked(MouseEvent me) {

            }
        });
        addMouseMotionListener(new MouseMotionListener() {
            public void mouseMoved(MouseEvent me) {

            }

            public void mouseDragged(MouseEvent me) {
                
            }

            public void mouseEntered(MouseEvent me) {

            }

            public void mouseExited(MouseEvent me) {

            }
        });
        addKeyListener(new KeyListener() {
            public void keyReleased(KeyEvent ke) {

            }

            public void keyPressed(KeyEvent ke) {
                String key = "";
                if(ke.getKeyCode() == 16)
                {
                    key = "shift";
                    moveDown();

                }else if(ke.getKeyCode() == 32)
                {
                    key = "space";
                    moveUp();

                }else if(ke.getKeyCode() == 37)
                {
                    key = "left";
                    turnLeft();

                }else if(ke.getKeyCode() == 38)
                {
                    key = "up";
                    turnDown();

                }else if(ke.getKeyCode() == 39)
                {
                    key = "right";
                    turnRight();

                }else if(ke.getKeyCode() == 40)
                {
                    key = "down";
                    turnUp();

                }else if(ke.getKeyCode() == 87)
                {
                    key = "w";
                    moveForward();

                }else if(ke.getKeyCode() == 65)
                {
                    key = "a";
                    moveLeft();

                }else if(ke.getKeyCode() == 83)
                {
                    key = "s";
                    moveBackward();

                }else if(ke.getKeyCode() == 68)
                {
                    key = "d";
                    moveRight();
                }else if(ke.getKeyCode() == 73)
                {
                    key = "i";
                    zoomIn();
                }else if(ke.getKeyCode() == 79)
                {
                    key = "o";
                    zoomOut();
                }else{
                    System.out.println(ke.getKeyChar()+", "+ke.getKeyCode());
                }
                System.out.println(key);
                genImage();
                repaint();
            }

            public void keyTyped(KeyEvent ke) {
                
            }
        });
    }

    /**
     * Paints the JFrame
     * 
     * @param g The JFrame Graphics
     */
    @Override
    public void paint(Graphics g) {
        g.drawImage(image, 0, 0,getWidth(),getHeight(),new Color(255,0,0), null);
    }

    /**
     * Zooms out on the image
     */
    public void zoomOut() {
        e.camera.setLength(Math.max(dl,e.camera.getLength()-dl));
    }

    /**
     * Zooms in on the image
     */
    public void zoomIn() {
        e.camera.setLength(e.camera.getLength()+dl);
    }

    /**
     * Translates the image to the left
     */
    public void moveLeft() {
        // double cp = Math.cos(e.camera.getPhi());
        // double sp = Math.sin(e.camera.getPhi());
        // double ct = Math.cos(e.camera.getTheta()+Math.PI/2);
        // double st = Math.sin(e.camera.getTheta()+Math.PI/2);
        // e.camera.setPosition(e.camera.getPosition().add(new Vector(dp*cp*ct,dp*cp*st,dp*sp)));
        e.camera.setPosition(e.camera.getPosition().add(e.camera.thetaHat().scalar(dp)));
    }

    /**
     * Translates the image to the right
     */
    public void moveRight() {
        // double cp = Math.cos(e.camera.getPhi());
        // double sp = Math.sin(e.camera.getPhi());
        // double ct = Math.cos(e.camera.getTheta()-Math.PI/2);
        // double st = Math.sin(e.camera.getTheta()-Math.PI/2);
        // e.camera.setPosition(e.camera.getPosition().add(new Vector(dp*cp*ct,dp*cp*st,dp*sp)));
        e.camera.setPosition(e.camera.getPosition().add(e.camera.thetaHat().scalar(-dp)));
    }

    /**
     * Translates the image up
     */
    public void moveUp() {
        e.camera.setPosition(e.camera.getPosition().add(new Vector(0,0,dp)));
    }

    /**
     * Translates the image down
     */
    public void moveDown() {
        e.camera.setPosition(e.camera.getPosition().add(new Vector(0,0,-dp)));
    }

    public void moveForward()
    {
        double cp = Math.cos(e.camera.getPhi());
        double sp = Math.sin(e.camera.getPhi());
        double ct = Math.cos(e.camera.getTheta());
        double st = Math.sin(e.camera.getTheta());
        e.camera.setPosition(e.camera.getPosition().add(new Vector(dp*cp*ct,dp*cp*st,dp*sp)));
    }
    public void moveBackward()
    {
        double cp = Math.cos(e.camera.getPhi());
        double sp = Math.sin(e.camera.getPhi());
        double ct = Math.cos(e.camera.getTheta());
        double st = Math.sin(e.camera.getTheta());
        e.camera.setPosition(e.camera.getPosition().subtract(new Vector(dp*cp*ct,dp*cp*st,dp*sp)));
    }
    public void turnUp()
    {
        e.camera.setPhi(e.camera.getPhi() - dt);
    }
    public void turnDown()
    {
        e.camera.setPhi(e.camera.getPhi() + dt);
    }
    public void turnLeft()
    {
        e.camera.setTheta(e.camera.getTheta() + dt);
    }
    public void turnRight()
    {
        e.camera.setTheta(e.camera.getTheta() - dt);
    }

    /**
     * Generates the image based on the current arguments
     */
    public void genImage() {
        
        // Vector O = new Vector(0, 0, 0);
        // Vector i = new Vector(1, 0, 0);
        // Vector j = new Vector(0, 1, 0);
        // Vector k = new Vector(0, 0, 1);

        // Environment e = new Environment(new Camera(Math.PI / 2.0, 0, new Vector(0, -1, 0), 1));
        // e.add(new Line(O, i, new Color(255, 0, 0)));
        // e.add(new Line(O, j, new Color(0, 255, 0)));
        // e.add(new Line(O, k, new Color(0, 0, 255)));
        // e.add(new Point(new Vector(1, 0, 1)));
        // e.add(new Point(new Vector(-1, 0, 1)));
        // e.add(new Point(new Vector(1, -2, 1)));
        // e.add(new Point(new Vector(0.25,-0.25,0),new Color(0,0,255)));
        image = e.draw();
    }
}