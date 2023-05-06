import  java.awt.*;
import  java.awt.event.*;
import  java.util.*;
import javax.swing.*;

public class GamePanel extends JPanel implements Runnable{
    
    static final int GAME_WIDTH = 1000;
    static final int GAME_HEIGHT = (int)(GAME_WIDTH * (0.5555));//PingPong table dimentions
    static final Dimension SCREEN_SIZE = new Dimension(GAME_WIDTH, GAME_HEIGHT);
    static final int BALL_DIAMETER = 20;
    static final int PADDLE_WIDTH = 25;
    static final int PADDLE_HEIGHT = 100;
    
    Thread gameThread;
    Image image;
    Graphics graphics;
    Random random;
    Paddles paddles1;
    Paddles paddles2;
    Ball ball;
    Score score;

    GamePanel() {
        newPaddles();
        newBall();
        score = new Score(GAME_WIDTH,GAME_HEIGHT);
        this.setFocusable(true);
        this.addKeyListener(new AL());
        this.setPreferredSize(SCREEN_SIZE);
        
        gameThread = new Thread(this);
        gameThread.start();
    }
    
    public void newBall(){
        //random = new Random();
        ball = new Ball((GAME_WIDTH / 2) - (BALL_DIAMETER / 2),(GAME_HEIGHT / 2) - (BALL_DIAMETER / 2),BALL_DIAMETER,BALL_DIAMETER);
                
    }
    public void newPaddles(){
        paddles1 = new Paddles(0,(GAME_HEIGHT / 2)-(PADDLE_HEIGHT / 2),PADDLE_WIDTH,PADDLE_HEIGHT, 1);
        paddles2 = new Paddles(GAME_WIDTH-PADDLE_WIDTH,(GAME_HEIGHT / 2)-(PADDLE_HEIGHT / 2),PADDLE_WIDTH,PADDLE_HEIGHT, 2);
    }
    public void paint(Graphics g){
        image = createImage(getWidth(),getHeight());
        graphics = image.getGraphics();
        draw(graphics);
        g.drawImage(image, 0, 0, this);
    }
    public void draw(Graphics g){
        paddles1.draw(g);
        paddles2.draw(g);
        ball.draw(g);
        
    }
    public void move(){
        paddles1.move();
        paddles2.move();
        ball.move();
    }
    public void checkCollision(){
        //allows ball to stay within frame and bounce
        if(ball.y <= 0){
            ball.setYDirection(-ball.xVolocity);
        }
        if(ball.y >= GAME_HEIGHT - BALL_DIAMETER){
            ball.setYDirection(-ball.xVolocity);
        }
        
        //Stops paddles from going over frame window edge
        if(paddles1.y <= 0)
            paddles1.y = 0;
        if(paddles1.y >= (GAME_HEIGHT - PADDLE_HEIGHT))
            paddles1.y = GAME_HEIGHT - PADDLE_HEIGHT;
        
        if(paddles2.y <= 0)
            paddles2.y = 0;
        if(paddles2.y >= (GAME_HEIGHT - PADDLE_HEIGHT))
            paddles2.y = GAME_HEIGHT - PADDLE_HEIGHT;
    }
    public void run(){
        //basic game loop
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        
        while(true){
            long now = System.nanoTime();
            delta += (now -lastTime) / ns;
            lastTime = now;
            
            if(delta >= 1){
                move();
                checkCollision();
                repaint();
                delta--;
//                System.out.println("TEST");
            }
        }
        
    }
    public class AL extends KeyAdapter{ //AL = Actual Listener
        public void keyPressed(KeyEvent e){
            paddles1.keyPressed(e);
            paddles2.keyPressed(e);
        }
        public void keyReleased(KeyEvent e){
            paddles1.keyReleased(e);
            paddles2.keyReleased(e);
        }
    }
    
    
}
