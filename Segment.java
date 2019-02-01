
/**
 * The things that make up the body
 * 
 * @author Antonio Montes
 * @version 2014-12-2
 */

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.Color;

public class Segment
{
    private Rectangle location;
    private BufferedImage image;
    
    public static final int width = 25;
    public static final int height = 25;
    
    public Segment() {
        location = new Rectangle();
        image = Segment.defaultImage();
    }
    
    public Segment(int x, int y) {
        location = new Rectangle(x, y, width, height);
        image = Segment.defaultImage();
    }
    
    public Rectangle getLocation() {
        return location;
    }
    
    public BufferedImage getImage() {
        return image;
    }
    
    public void setLocation(int x, int y) {
        location.setLocation(x, y);
    }
    
    public void setImage(BufferedImage image) {
        if (image != null)
            this.image = image;
    }
    
    public static BufferedImage defaultImage() {
        BufferedImage temp = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics g = temp.createGraphics();
        
        //draw the background
        g.setColor(Color.black);
        g.fillRect(0,0,width,height);
        
        //draw the inner rectangle
        int scale = 20; //controls the size of the inner rectangle
        g.setColor(Color.green);
        g.fillRect(0 + width / scale , 0 + height / scale, width - ((width / scale) * 2),  height - ((height / scale) * 2));
        //draws the rectangle directly in the center of the image
        
        return temp;
    }
}
