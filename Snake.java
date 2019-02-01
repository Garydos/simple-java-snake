
/**
 * The main snake that holds the segments and moves around
 * the world
 * 
 * @author Antonio Montes
 * @version 2014-12-2
 */

import java.util.ArrayList;
import java.awt.Rectangle;

public class Snake
{
    private ArrayList<Segment> body;
    private Direction direction;
    private Direction currentDirection;
    private Rectangle previousLocation;
    private boolean running;
    private boolean reset;
    
    public Snake() {
        body = new ArrayList<Segment>();
        direction = Direction.RIGHT;
        currentDirection = direction;
        running = false;
        reset = false;
    }
    
    public Snake(int x, int y) {
        body = new ArrayList<Segment>();
        body.add(new Segment(x, y));
        body.add(new Segment(x - Segment.width * 1, y));
        body.add(new Segment(x - Segment.width * 2, y));
        direction = Direction.RIGHT;
        currentDirection = direction;
        running = false;
        reset = false;
    }
    
    public Snake(int x, int y, Direction direction) {
        body = new ArrayList<Segment>();
        body.add(new Segment(x, y));
        body.add(new Segment(x - Segment.width * 1, y));
        body.add(new Segment(x - Segment.width * 2, y));
        this.direction = direction;
        running = false;
        reset = false;
    }
    
    /**
     * uses the currentDirection to determine where to move unit to
     * by taking the tail and moving it to the new direction
     */
    public void move() {
        switch (currentDirection) {
            case UP:
                if (direction != Direction.DOWN)
                    direction = currentDirection;
                break;
            case DOWN:
                if (direction != Direction.UP)
                    direction = currentDirection;
                break;
            case LEFT:
                if (direction != Direction.RIGHT)
                    direction = currentDirection;
                break;
            case RIGHT:
                if (direction != Direction.LEFT)
                    direction = currentDirection;
                break;
        }
        
        previousLocation = new Rectangle();
        Segment head = body.get(0);
        Segment tail = body.get(body.size() - 1);
        previousLocation = new Rectangle(tail.getLocation());
        switch (direction) {
            case UP :
                tail.setLocation(head.getLocation().x, head.getLocation().y - Segment.height);
                break;
            case DOWN:
                tail.setLocation(head.getLocation().x, head.getLocation().y + Segment.height);
                break;
            case LEFT:
                tail.setLocation(head.getLocation().x - Segment.width, head.getLocation().y);
                break;
            case RIGHT:
                tail.setLocation(head.getLocation().x + Segment.width, head.getLocation().y);
                break;
        }
        body.remove(body.size() - 1);
        body.add(0, tail);
        //body.remove(body.size() - 1);
        
    }
    
    /**
     * makes sure that the snake has moved at least once, then appends
     * a segment to the end of the snake, which becomes the new tail
     */
    public void appendSegment() {
        if (previousLocation != null)
            body.add(new Segment(previousLocation.x, previousLocation.y));
    }
    
    public void setDirection(Direction direction) {
        this.currentDirection = direction;
    }
    
    public Direction getDirection() {
        return direction;
    }
    
    public ArrayList<Segment> getBody() {
        return body;
    }
    
    /**
     * checks to see if any part of the snake
     * is touching the bounds in the Rectangle pos
     */
    public boolean isTouching(Rectangle pos) {
        for (Segment seg : body) {
            if (pos.equals(seg.getLocation()))
                return true;
        }
        return false;
    }
    
    /**
     * checks if the snake has collided with itself
     */
    public boolean hasCollided() {
        Segment head = body.get(0);
        for (int i = 1; i < body.size(); i++) {
            if (head.getLocation().equals(body.get(i).getLocation()))
                return true;
        }
        return false;
    }
    
    /**
     * checks if the snake has collided with itself or the outer bounds
     */
    public boolean hasCollided(int x, int y) {
        Segment head = body.get(0);
        return (head.getLocation().x + Segment.width > x || head.getLocation().x < 0)
                || (head.getLocation().y + Segment.height > y || head.getLocation().y < 0)
                || hasCollided();
    }
    
    //all of the following methods are used
    //for handling game states

    public boolean isRunning() {
        return running;
    }
    
    public void setRunning(boolean running) {
        this.running = running;
    }
    
    public void togglePause() {
        this.running = !running;
    }
    
    public void setResetFlag(boolean reset) {
        this.reset = reset;
    }
    
    public boolean getResetFlag() {
        return this.reset;
    }
}
