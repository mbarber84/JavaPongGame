import  java.awt.*;
import  java.awt.event.*;
import  java.util.*;
import javax.swing.*;

public class GamePanel extends JPanel implements Runnable{
    //Class variables
    private static GamePanel instance; // Singleton instance variable
    private GameState gameState;
    static final int GAME_WIDTH = 1000;
    static final int GAME_HEIGHT = (int)(GAME_WIDTH * (0.5555));//PingPong table dimentions
    static final Dimension SCREEN_SIZE = new Dimension(GAME_WIDTH, GAME_HEIGHT); //the Dimension object is representing the size of the game window.
    static final int BALL_DIAMETER = 20;// A Constant
    static final int PADDLE_WIDTH = 25;// A Constant
    static final int PADDLE_HEIGHT = 100;// A Constant
    boolean paused = false;
    
    Thread gameThread;//object that will run the game loop.
    Image image;//object used for double buffering
    Graphics graphics;
    Random random;//An instance of the Random class for generating random values.
    //Instances of the Paddles class representing the two paddles in the game.
    Paddles paddles1;
    Paddles paddles2;
    Ball ball;//An instance of the Ball class representing the ball in the game
    Score score;//An instance of the Score class representing the score display.

    private GamePanel() { //constructor
        //initialises the paddles, ball, and score objects
        newPaddles();
        newBall();
        gameState = GameState.PLAYING; // set the initial state to "playing"
        score = new Score(GAME_WIDTH,GAME_HEIGHT);
        this.setFocusable(true);//It sets the focusable to true, enabling keyboard input for the panel.
        this.addKeyListener(new AL());
        this.setPreferredSize(SCREEN_SIZE);
        //new thread starts the game loop.
        gameThread = new Thread(this);
        gameThread.start();
    }
    public static GamePanel getInstance() {
        if (instance == null) {
            instance = new GamePanel();
        }
        return instance;
    }
    //method generates a new ball object with a random position within the game window.
    public void newBall(){
        random = new Random();
        ball = new Ball((GAME_WIDTH / 2) - (BALL_DIAMETER / 2),random.nextInt(GAME_HEIGHT - BALL_DIAMETER),BALL_DIAMETER,BALL_DIAMETER);
                
    }
    //The method generates new paddle objects for both players and their start positions.
    public void newPaddles(){
        paddles1 = new Paddles(0,(GAME_HEIGHT / 2)-(PADDLE_HEIGHT / 2),PADDLE_WIDTH,PADDLE_HEIGHT, 1);
        paddles2 = new Paddles(GAME_WIDTH-PADDLE_WIDTH,(GAME_HEIGHT / 2)-(PADDLE_HEIGHT / 2),PADDLE_WIDTH,PADDLE_HEIGHT, 2);
    }
    //This method is called when the panel needs to be painted or repainted.
    public void paint(Graphics g){
        image = createImage(getWidth(),getHeight());//Starts with object the size of the panel using the createImage() method.
        graphics = image.getGraphics();
        draw(graphics);// passing the graphics object to draw the paddles, ball, and score.
        g.drawImage(image, 0, 0, this);//draws the image on the panel
    }
    //This method is responsible for drawing the paddles, ball, and score on the screen.
    public void draw(Graphics g){
        paddles1.draw(g);
        paddles2.draw(g);
        ball.draw(g);
        score.draw(g);  
    }
    //This method is responsible for moving the paddles and the ball.
    public void move(){
        paddles1.move();
        paddles2.move();
        ball.move();
    }
    public void checkCollision(){
        //allows ball to stay within frame and bounce
        ////If the ball's y-coordinate is less than or equal to 0, it means the ball has hit the top boundary.
        if(ball.y <= 0){ 
            ball.setYDirection(-ball.yVolocity);
        }
        //If the ball's y-coordinate is greater than or equal to GAME_HEIGHT - BALL_DIAMETER, it means the ball has hit the bottom boundary.
        if(ball.y >= GAME_HEIGHT - BALL_DIAMETER){
            ball.setYDirection(-ball.yVolocity);
        }
        //In both cases it makes the ball bounce back in the opposite direction.
        
        //allows paddles and ball to collide
        if(ball.intersects(paddles1)){//check to see if the ball intersects (hits) with paddles1 using the intersects() method of the Rectangle class.
            ball.xVolocity = Math.abs(ball.xVolocity); //updates the x-direction(horizontal)of the ball to the absolute value of its current x-velocity, to make it bounce back in the opposite direction.
            ball.xVolocity++; //speed up ball
            if(ball.yVolocity > 0)
                ball.yVolocity++;//steeper angle.
            else
                ball.yVolocity--;
            ball.setXDirection(ball.xVolocity);
            ball.setYDirection(ball.yVolocity);
        }
        //See comments above for paddles1
        if(ball.intersects(paddles2)){//check to see if the ball intersects (hits) with paddles2 using the intersects() method of the Rectangle class.
            ball.xVolocity = Math.abs(ball.xVolocity);
            ball.xVolocity++; //speed up ball
            if(ball.yVolocity > 0)
                ball.yVolocity++;//speed up ball
            else
                ball.yVolocity--;
            ball.setXDirection(-ball.xVolocity);
            ball.setYDirection(ball.yVolocity);
        }
        
        //Stops paddles from going over frame window edge
        if(paddles1.y <= 0)
            paddles1.y = 0;
        // Same for paddle1 and paddles2: If the y-coordinate of paddles1 is less than or equal to 0, it means the paddle has reached or exceeded the top boundary. if so, they are set to 0 stoping it from going higher.
        if(paddles1.y >= (GAME_HEIGHT - PADDLE_HEIGHT))
            paddles1.y = GAME_HEIGHT - PADDLE_HEIGHT;
        // Same for paddle1 and paddles2: If the y-coordinate is greater than or equal to GAME_HEIGHT - PADDLE_HEIGHT, it means they has reached the bottom boundary. in this case y-coordinate is set to GAME_HEIGHT - PADDLE_HEIGHT
        if(paddles2.y <= 0)
            paddles2.y = 0;
        if(paddles2.y >= (GAME_HEIGHT - PADDLE_HEIGHT))
            paddles2.y = GAME_HEIGHT - PADDLE_HEIGHT;
        
        //Method for points and reset of game paddles and ball
        if(ball.x <= 0){//If the x-coordinate of the ball is less than or equal to 0, it means the ball has crossed the left boundary scoring a point for player 2.
            score.player2++; //incrementation to show a point was scored
            newPaddles();//reset the positions of the paddles.
            newBall();//new ball with a random start position along the centre horizontal line
            System.out.println("Player 2" + score.player2); //displays the updated score in console
        }
        // Same as above but if the x-coordinate is is greater than or equal to GAME_WIDTH - BALL_DIAMETER, it means player 1 has scored.
        if(ball.x >= GAME_WIDTH - BALL_DIAMETER){
            score.player1++;//incrementation to show a point was scored
            newPaddles();//reset the positions of the paddles.
            newBall();//new ball with a random start position along the centre horizontal line
            System.out.println("Player 1" + score.player1);//displays the updated score in console
        }
        
    }
    public void run(){
        //basic game loop
        long lastTime = System.nanoTime();//represents the timestamp of the previous loop iteration.
        double amountOfTicks = 60.0;//desired number of game updates per second.
        double ns = 1000000000 / amountOfTicks;//ns is calculated by dividing 1 billion nanoseconds by the amountOfTicks, representing the time interval between each tick.
        double delta = 0; // delta represents the accumulated time since the last tick.
        
        while(true){//infinite loop
            long now = System.nanoTime();// current time is stored in the now variable.
            delta += (now -lastTime) / ns; //This calculates the number of ticks that should have happened since the last loop.
            lastTime = now;
            
            if(delta >= 1){//if delta is equal or greater than 1, time to do a game update.
                 if (gameState == GameState.PLAYING) { // execute game logic only when in "playing" state
                move();//update the positions of the paddles and the ball
                checkCollision();//handle collision detection and response.
                 }
                repaint();
                delta--;//indicating that a tick has been processed
//                System.out.println("TEST");
            }
        }  
    }
    public class AL extends KeyAdapter{ //AL = Actual Listener //a convenience class for receiving keyboard events
        public void keyPressed(KeyEvent e){
            //This delegates the responsibility of handling the key press event to the corresponding paddle objects.
            paddles1.keyPressed(e);
            paddles2.keyPressed(e);
            
            if (e.getKeyCode() == KeyEvent.VK_P) {
            toggleGameState();
        }
        }
        public void keyReleased(KeyEvent e){
            paddles1.keyReleased(e);
            paddles2.keyReleased(e);
        }
    }
    private void toggleGameState() {
        if (gameState == GameState.PLAYING) {
            gameState = GameState.PAUSED;
        } else if (gameState == GameState.PAUSED) {
            gameState = GameState.PLAYING;
        }
    }
}
