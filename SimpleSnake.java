/* 
 * Example code for Think Java (http://thinkapjava.com)
 *
 * Adapted from http://en.wikibooks.org/wiki/Java_Programming/Canvas
 *
 * Copyright(c) 2011 Allen B. Downey
 * GNU General Public License v3.0 (http://www.gnu.org/copyleft/gpl.html)
 *
 * @author Antonio Montes
 * @version 2014-12-2
 */

import java.awt.Color;
import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Polygon;
import javax.swing.JFrame;
import java.util.Scanner;
import javax.swing.Timer;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class SimpleSnake extends Canvas implements ActionListener {   
    public static final int SCREEN_WIDTH = 50 * 10;
    public static final int SCREEN_HEIGHT = 50 * 10;
    public static final int gameOverWait = 10;
    
    private boolean menu;
    private boolean paused;
    private boolean gameOver;
    private Rectangle test;
    private Timer timer;
    private BufferedImage screen;
    private int xvel;
    private int yvel;
    public Snake snake;
    private Apple apple;
    private int gameOverWaitCounter;
    
    public Keyboard keyboard;
    
    public SimpleSnake() {
        test = new Rectangle(50,50,50,50);
        timer = new Timer(1000 / 10 , this);
        timer.start();
        xvel = 5;
        yvel = 5;
        
        initialize();
    }
    
    public void initialize() {
        menu = true;
        paused = false;
        gameOver = false;
        gameOverWaitCounter = 0;
        
        snake = new Snake(50, 50);
        apple = new Apple(50 * randomInt(0, SCREEN_WIDTH/ 50), 50 * randomInt(0, SCREEN_HEIGHT / 50));
        screen = new BufferedImage(SCREEN_WIDTH, SCREEN_HEIGHT, BufferedImage.TYPE_INT_ARGB);
    }
    
    public static void main(String[] args) {
        // Create a JFrame object, which is a window that can contain the canvas,
        // buttons, menus, and other window components;
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // add the canvas
        // Canvas class is used to create an area in a frame to be 
        //   used for displaying graphics.
        SimpleSnake game = new SimpleSnake();
        Canvas canvas = game; // Create a Canvas object called canvas
        canvas.setSize(SCREEN_WIDTH, SCREEN_HEIGHT); // Set the size of our canvas
        frame.getContentPane().add(canvas);// Add our canvas to the JFrame object
        
        Keyboard keys = new Keyboard(game.snake);
        game.keyboard = keys;
        canvas.addKeyListener(keys);
        
        // Display the frame on the screen.
        frame.pack();
        frame.setVisible(true);
    }
    
    public void paint(Graphics G) {
        Graphics2D g = (Graphics2D)screen.createGraphics();
        g.setBackground(new Color(255, 255, 255, 0));
        g.clearRect(0,0,SCREEN_WIDTH,SCREEN_HEIGHT);
        
        g.setColor(Color.BLACK);
        if (menu) {
            g.drawString("PRESS ENTER TO BEGIN", SCREEN_WIDTH / 2 - 80, SCREEN_HEIGHT / 2 - 10);
        }
        else if (paused) {
            g.drawString("PAUSED", SCREEN_WIDTH / 2 - 25, SCREEN_HEIGHT / 2 - 10);
        }
        else if (gameOver) {
            g.clearRect(0,0,SCREEN_WIDTH,SCREEN_HEIGHT);
            g.drawString("GAME OVER", SCREEN_WIDTH / 2 - 35, SCREEN_HEIGHT / 2 - 10);
            g.drawString("PRESS R TO RESTART", SCREEN_WIDTH / 2 - 70, SCREEN_HEIGHT / 2 + 5);
        }
        else {
            ArrayList<Segment> snakeBody = snake.getBody();
            
            for (Segment segment : snakeBody) {
                g.drawImage(segment.getImage(), segment.getLocation().x, segment.getLocation().y, null);
            }
            
            if (snakeBody.get(0).getLocation().equals(apple.getLocation())) {
                do {
                    apple.setLocation(50 * randomInt(0, SCREEN_WIDTH/ 50), 50 * randomInt(0, SCREEN_HEIGHT / 50));
                }while(snake.isTouching(apple.getLocation()));
                snake.appendSegment();
            }
            g.drawImage(apple.getImage(), apple.getLocation().x, apple.getLocation().y, null);
            
            if (snake.hasCollided(SCREEN_WIDTH, SCREEN_HEIGHT)) {
                gameOver = true;
                snake.setRunning(false);
            }
        }
        
        //G.setColor(new Color(255, 255, 255, 0));
        //G.clearRect(0,0,SCREEN_WIDTH,SCREEN_HEIGHT);
        //G.drawImage(screen,0,0,null);
        G.drawImage(screen,0,0,null);
    }
    
    public void actionPerformed(ActionEvent e) {      
        if (snake.getResetFlag()) {
            initialize();
            keyboard.setSnake(snake);
        }
        
        if (snake.isRunning() && !gameOver)
            menu = false;
            
        if (snake.isRunning()) {
            paused = false;
            snake.move();
        }
        else if (gameOver){
            paused = false;
        }
        else {
            paused = true;
        }
        
        if (!gameOver || (gameOver && gameOverWaitCounter >= gameOverWait))
            repaint();
        else if (gameOverWaitCounter <= gameOverWait) {
            gameOverWaitCounter++;
        }
    }
    
    public static int randomInt(int lower, int upper) {
        return (int)(Math.random() * (upper - lower)) + lower;
    }

}