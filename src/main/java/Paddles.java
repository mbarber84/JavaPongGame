import  java.awt.*;
import  java.awt.event.*;
import  java.util.*;
import javax.swing.*;

//This code is for the Paddles class that represents the paddles in the Pong game. It includes methods for handling keyboard input, updating the position of the paddles, and drawing them on the screen.

public class Paddles extends Rectangle{ //The class extends the Rectangle class, which represents a rectangle with properties like position and size.
    
    //instance variables
    int id;
    int yVolocity;
    int speed = 10;

    Paddles(int x, int y, int PADDLE_WIDTH, int PADDLE_HEIGHT, int id) {//constructor is called when an instance of the class is created.
        super(x,y,PADDLE_WIDTH,PADDLE_HEIGHT);//The super() method is called to invoke the constructor of the Rectangle class and set the initial position and size of the paddle.
        this.id = id;// parameter assigned to instance variable.
    }
    //
    public void keyPressed(KeyEvent e){
            switch(id){//switch statement to check the value of the id variable.
                case 1:
                    if(e.getKeyCode()==KeyEvent.VK_W){
                        setYDirection(-speed); //UP
                        move();
                    }
                    if(e.getKeyCode()==KeyEvent.VK_S){
                        setYDirection(speed); //DOWN
                        move();
                    }
                    break;
                    //For id = 1, it checks if the pressed buttons is W or S buttons and adjusts the paddles vertical direction accordingly using the setYDirection() method and moves the paddle using the move() method.
                case 2:
                    if(e.getKeyCode()==KeyEvent.VK_UP){
                        setYDirection(-speed); //UP
                        move();
                    }
                    if(e.getKeyCode()==KeyEvent.VK_DOWN){
                        setYDirection(speed); //DOWN
                        move();
                    }
                    break;
                    //For id = 2, it checks if the pressed buttons is the arrow up or down buttons and adjusts the paddle's vertical direction and moves it accordingly.
            }
        }
    //The method below, adjusts the paddle's vertical direction based on the released button and moves the paddle accordingly.
    public void keyReleased(KeyEvent e){
            switch(id){
                case 1:
                    if(e.getKeyCode()==KeyEvent.VK_W){
                        setYDirection(0);
                        move();
                    }
                    if(e.getKeyCode()==KeyEvent.VK_S){
                        setYDirection(0);
                        move();
                    }
                    break;
                case 2:
                    if(e.getKeyCode()==KeyEvent.VK_UP){
                        setYDirection(0);
                        move();
                    }
                    if(e.getKeyCode()==KeyEvent.VK_DOWN){
                        setYDirection(0);
                        move();
                    }
                    break;
            }
        }
    public void setYDirection(int yDirection){
        yVolocity = yDirection;
    }
    //This method updates the position of the paddle based on the current vertical velocity 
    public void move(){
        y = y + yVolocity;
    }
    //This method is responsible for drawing the paddle on the screen. Depending on the id of the paddle, it sets the color using the setColor() method.
    public void draw(Graphics g){
        if(id == 1)
            g.setColor(new Color(204,0, 255));
        else
           g.setColor(new Color(66,242,7)); 
        g.fillRect(x, y, width, height);
    }
}
