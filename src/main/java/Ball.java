import  java.awt.*;
import  java.awt.event.*;
import  java.util.*;
import javax.swing.*;

public class Ball extends Rectangle{//The class extends the Rectangle class, which represents a rectangle. Same as in the Paddles class.
    //instance variables
    Random random;
    int xVolocity;
    int yVolocity;
    int initialSpeed = 2;
    
    Ball(int x, int y, int width, int height){//The constructor is called when an instance of the Ball class is created. It takes parameters x, y, width, and height.
        super(x,y,width,height);//The super() method is used to call on the constructor of the Rectangle class 
        random = new Random(); // instance of the Random class is created and assigned to the random variable.
        int randomXDirection = random.nextInt(2); //2 values, randomXDirection and randomYDirection, are generated
        if(randomXDirection == 0) //set as -1 or 1 using conditional statements.
            randomXDirection--;
        setXDirection(randomXDirection * initialSpeed);   
        int randomYDirection = random.nextInt(2);
        if(randomYDirection == 0)//Set as -1 or 1 using conditional statements.
            randomYDirection--;
        setYDirection(randomYDirection * initialSpeed);
    }
    //This method sets the horizontal velocity (xVolocity) of the ball based on the given randomXDirection value.
    public void setXDirection(int randomXDirection){
        xVolocity = randomXDirection;
    }
    //This method sets the vertical velocity (yVolocity) of the ball based on the given randomYDirection value.
    public void setYDirection(int randomYDirection){
        yVolocity = randomYDirection;
    }
    //This method updates the position of the ball by adding the horizontal and vertical velocities to the current position
    public void move(){
        x += xVolocity;
        y += yVolocity;
    }
    //This method is responsible for drawing the ball on the screen.
    public void draw(Graphics g){
        g.setColor(new Color(212,175,55));
        g.fillOval(x, y, width, height);//It fills an oval shape on the screen with the specified position, size, and color using the fillOval() method of the Graphics object.
    }
}
