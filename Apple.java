
/**
 * Write a description of class Apple here.
 * 
 * @author Antonio Montes
 * @version 2014-12-2
 */

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.Color;

public class Apple extends Segment
{
    public Apple() {
        super();
        setImage(defaultImage());
    }
    
    public Apple(int x, int y) {
        super(x, y);
        setImage(defaultImage());
    }
    
    public static BufferedImage defaultImage() {
        BufferedImage temp = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics g = temp.createGraphics();

        g.setColor(Color.black);
        g.fillRect(0,0,width,height);
        
        return temp;
    }
}
