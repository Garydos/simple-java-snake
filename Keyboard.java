
/**
 * Write a description of class Keyboard here.
 * 
 * @author Antonio Montes
 * @version 2014-12-2
 */

import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

public class Keyboard implements KeyListener
{
    private Snake simpleSnake;
    
    public Keyboard() {
    }
    
    public Keyboard(Snake snake) {
        this.simpleSnake = snake;
    }
    
    public void keyPressed(KeyEvent e) {
        
        String key = KeyEvent.getKeyText(e.getKeyCode());
        switch (key) {
            case "Up":
                if (simpleSnake.getDirection() != Direction.DOWN)
                    simpleSnake.setDirection(Direction.UP);
                break;
            case "Down":
                if (simpleSnake.getDirection() != Direction.UP)
                    simpleSnake.setDirection(Direction.DOWN);
                break;
            case "Left":
                if (simpleSnake.getDirection() != Direction.RIGHT)
                    simpleSnake.setDirection(Direction.LEFT);
                break;
            case "Right":
                if (simpleSnake.getDirection() != Direction.LEFT)
                    simpleSnake.setDirection(Direction.RIGHT);
                break;
            case "Enter":
                simpleSnake.setRunning(true);
                break;
            case "P":
                simpleSnake.togglePause();
                break;
            case "R":
                simpleSnake.setResetFlag(true);
                break;
        }
    }
    
    public void keyReleased(KeyEvent e) {
    }
    
    public void keyTyped(KeyEvent e) {
    }
    
    public void setSnake(Snake simpleSnake) {
        this.simpleSnake = simpleSnake;
    }
}
