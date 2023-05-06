import  java.awt.*;
import  java.awt.event.*;
import  java.util.*;
import javax.swing.*;

public class Paddles extends Rectangle{
    
    int id;
    int yVolocity;
    int speed = 10;

    Paddles(int x, int y, int PADDLE_WIDTH, int PADDLE_HEIGHT, int id) {
        super(x,y,PADDLE_WIDTH,PADDLE_HEIGHT);
        this.id = id;
    }
    public void keyPressed(KeyEvent e){
            switch(id){
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
            }
        }
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
    public void move(){
        y = y + yVolocity;
    }
    public void draw(Graphics g){
        if(id == 1)
            g.setColor(new Color(204,0, 255));
        else
           g.setColor(new Color(66,242,7)); 
        g.fillRect(x, y, width, height);
    }
}
