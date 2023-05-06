import  java.awt.*;
import  java.awt.event.*;
import  java.util.*;
import javax.swing.*;

public class Ball extends Rectangle{
    
    Random random;
    int xVolocity;
    int yVolocity;
    
    Ball(int x, int y, int width, int height){
        super(x,y,width,height);
        random = new Random();
        int randomXDirection = random.nextInt(2);
        if(randomXDirection == 0)
            randomXDirection--;
        setXDirection(randomXDirection);
               
        int randomYDirection = random.nextInt(2);
        if(randomYDirection == 0)
            randomYDirection--;
        setYDirection(randomYDirection);
    }
    public void setXDirection(int randomXDirection){
        xVolocity = randomXDirection;
    }
    public void setYDirection(int randomYDirection){
        yVolocity = randomYDirection;
    }
    public void move(){
        x += xVolocity;
        y += yVolocity;
    }
    public void draw(Graphics g){
        g.setColor(new Color(212,175,55));
        g.fillOval(x, y, width, height);
    }
}
