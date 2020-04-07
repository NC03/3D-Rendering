import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Scanner;

import java.io.*;
import javax.imageio.*;

/**
 * TUI
 * 
 * @author NC03
 * @version 1.2.5
 * 
 */
public class TUI {
    private BufferedImage image;
    public Environment e;

    public double dp = 0.125;
    public double dt = Math.PI / 32;
    public double dl = 0.0625;
    public int width = 80;
    public int height = 24;

    public static void main(String[] args) {
        new TUI().run();
    }

    public void run() {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        boolean run = true;
        while (run) {
            // System.out.println(s);
            if (s.length() > 0) {
                s = s.substring(0, 1);
            }
            switch (s) {
                case "w":
                    moveForward();
                    break;
                case "a":
                    moveLeft();
                    break;
                case "s":
                    moveBackward();
                    break;
                case "d":
                    moveRight();
                    break;
                    case "p":
                    zoomIn();
                    break;
                    case "o":
                    zoomOut();
                    break;
                    case " ":
                    moveUp();
                    break;
                    case "z":
                    moveDown();
                    break;
                    case "i":
                    turnDown();
                    break;
                    case "j":
                    turnLeft();
                    break;
                    case "k":
                    turnUp();
                    break;
                    case "l":
                    turnRight();
                    break;
                case "q":
                    run = false;
                    break;
                default:

                    break;
            }
            System.out.println(board());
            s = sc.nextLine();
        }
        // while(sc.nextLine())
        // {

        // }
        // String key = "";
        // if (ke.getKeyCode() == 16) {
        // key = "shift";
        // moveDown();

        // } else if (ke.getKeyCode() == 32) {
        // key = "space";
        // moveUp();

        // } else if (ke.getKeyCode() == 37) {
        // key = "left";
        // turnLeft();

        // } else if (ke.getKeyCode() == 38) {
        // key = "up";
        // turnDown();

        // } else if (ke.getKeyCode() == 39) {
        // key = "right";
        // turnRight();

        // } else if (ke.getKeyCode() == 40) {
        // key = "down";
        // turnUp();

        // } else if (ke.getKeyCode() == 87) {
        // key = "w";
        // moveForward();

        // } else if (ke.getKeyCode() == 65) {
        // key = "a";
        // moveLeft();

        // } else if (ke.getKeyCode() == 83) {
        // key = "s";
        // moveBackward();

        // } else if (ke.getKeyCode() == 68) {
        // key = "d";
        // moveRight();
        // } else if (ke.getKeyCode() == 73) {
        // key = "i";
        // zoomIn();
        // } else if (ke.getKeyCode() == 79) {
        // key = "o";
        // zoomOut();
        // } else {
        // System.out.println(ke.getKeyChar() + ", " + ke.getKeyCode());
        // }
        // System.out.println(key);
        // genImage();
        // repaint();
    }

    public TUI() {

        Vector O = new Vector(0, 0, 0);
        Vector i = new Vector(1, 0, 0);
        Vector j = new Vector(0, 1, 0);
        Vector k = new Vector(0, 0, 1);

        e = new Environment(new Camera(3 * Math.PI / 2.0, 0, new Vector(0, -1, 0), 1));
        e.add(new Line(O, i, new Color(255, 0, 0)));
        e.add(new Line(O, j, new Color(0, 255, 0)));
        e.add(new Line(O, k, new Color(0, 0, 255)));
        e.add(new Point(O, new Color(0, 0, 0)));
        e.add(new Point(i, new Color(255, 0, 0)));
        e.add(new Point(j, new Color(0, 255, 0)));
        e.add(new Point(k, new Color(0, 0, 255)));
        // e.add(new Triangle(O, i, j));
        // e.add(new Triangle(O, j, k, new Color(128, 128, 0)));

        // try {

        // e.add(new ASCIISTL(O, new Color(0, 128, 255), new
        // File("/home/nc/Desktop/logo.stl")));
        // } catch (Exception ex) {
        // ex.printStackTrace();
        // }

        int len = 24;
        for (int l = 0; l < len; l++) {
            double dist = 0.25 * l;
            e.add(new Triangle(new Vector(0, dist, 0), new Vector(1, dist, 0), new Vector(0.5, dist, Math.sqrt(3)),
                    new Color(Color.HSBtoRGB(1.0F * l / len, 1.0F, 1.0F))));
        }
        image = e.draw();
    }

    /**
     * Zooms out on the image
     */
    public void zoomOut() {
        e.camera.setLength(Math.max(dl, e.camera.getLength() - dl));
    }

    /**
     * Zooms in on the image
     */
    public void zoomIn() {
        e.camera.setLength(e.camera.getLength() + dl);
    }

    /**
     * Translates the image to the left
     */
    public void moveLeft() {
        e.camera.setPosition(e.camera.getPosition().add(e.camera.thetaHat().scalar(-dp)));
    }

    /**
     * Translates the image to the right
     */
    public void moveRight() {
        e.camera.setPosition(e.camera.getPosition().add(e.camera.thetaHat().scalar(+dp)));
    }

    /**
     * Translates the image up
     */
    public void moveUp() {
        e.camera.setPosition(e.camera.getPosition().add(new Vector(0, 0, dp)));
    }

    /**
     * Translates the image down
     */
    public void moveDown() {
        e.camera.setPosition(e.camera.getPosition().add(new Vector(0, 0, -dp)));
    }

    public void moveForward() {
        Vector n = e.camera.getOrthogonal();
        e.camera.setPosition(e.camera.getPosition().subtract(n.scalar(dp / n.magnitude())));
    }

    public void moveBackward() {
        Vector n = e.camera.getOrthogonal();
        e.camera.setPosition(e.camera.getPosition().add(n.scalar(dp / n.magnitude())));
    }

    public void turnUp() {
        e.camera.setPhi(e.camera.getPhi() - dt);
    }

    public void turnDown() {
        e.camera.setPhi(e.camera.getPhi() + dt);
    }

    public void turnLeft() {
        e.camera.setTheta(e.camera.getTheta() + dt);
    }

    public void turnRight() {
        e.camera.setTheta(e.camera.getTheta() - dt);
    }

    /**
     * Generates the image based on the current arguments
     */
    public void genImage() {
        image = e.draw();
        try {
            ImageIO.write(image, "png", new File("testViewing.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String board() {
        genImage();
        System.out.println("image done");
        Color[][] colors = new Color[height][width];
        double w = (double) e.width / width;
        double h = (double) e.height / height;
        System.out.println(w);
        System.out.println(h);
        BufferedImage temp = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        for (int i = 0; i < colors.length; i++) {
            for (int j = 0; j < colors[i].length; j++) {
                colors[i][j] = average(image, (int) (j * w), (int) (i * h), (int) (j * w + w), (int) (i * h + h));
                temp.setRGB(j, i, colors[i][j].getRGB());
            }
        }
        try {
            ImageIO.write(temp,"png",new File("testOtherViewing.png"));
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        String out = "";// "\\e["+height+"A \r";
        for (int i = 0; i < colors.length; i++) {
            for (int j = 0; j < colors[i].length; j++) {
                // out += (intensity(colors[i][j]) > 128 ? "#" : " ");
                out += "\033[48;2;" + colors[i][j].getRed() + ";" + colors[i][j].getGreen() + ";"
                        + colors[i][j].getBlue() + "m#";
            }
            out += "\n";
        }
        return out;
    }

    public int intensity(Color c) {
        return (c.getRed() + c.getGreen() + c.getBlue()) / 3;
    }

    public Color average(BufferedImage bi, int x1, int y1, int x2, int y2) {
        y2 = Math.min(bi.getHeight(), y2);
        x2 = Math.min(bi.getWidth(), x2);
        double count = (x2 - x1) * (y2 - y1);
        double red = 0;
        double green = 0;
        double blue = 0;
        for (int i = x1; i < x2; i++) {
            for (int j = y1; j < y2; j++) {
                Color c = new Color(bi.getRGB(i, j));
                red += c.getRed() / count;
                green += c.getGreen() / count;
                blue += c.getBlue() / count;
            }
        }
        return new Color((int) Math.round(red), (int) Math.round(green), (int) Math.round(blue));
    }
}