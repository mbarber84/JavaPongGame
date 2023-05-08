import  java.awt.*;
import  java.awt.event.*;
import  java.util.*;
import javax.swing.*;

//Score class that represents the score display in the Pong game. It includes a method for drawing the score on the screen, using a specific font, color, and position. The score values for player 1 and player 2 are displayed as two digits.

public class Score extends Rectangle{ //The class extends the Rectangle class, which represents a rectangle. As with the Paddles and Ball classes
    //instance variables
    static int GAME_WIDTH;
    static int GAME_HEIGHT;
    int player1;
    int player2;

    Score(int GAME_WIDTH, int GAME_HEIGHT) {//constructor with parameters
        //The GAME_WIDTH and GAME_HEIGHT parameters are assigned to the corresponding static variables of the class, allowing them to be accessed by other methods.
        Score.GAME_WIDTH = GAME_WIDTH;
        Score.GAME_HEIGHT = GAME_HEIGHT;
    }
    //This method is responsible for drawing the score display on the screen.
    public void draw(Graphics g){
        g.setColor(Color.red);//Score colour
        g.setFont(new Font("Consolas", Font.PLAIN,60));//Font style and size
        g.drawLine(GAME_WIDTH / 2, 0, GAME_WIDTH /2, GAME_HEIGHT);//It draws a vertical line at the center of the game window
        //The score values are obtained by dividing player1 and player2 by 10 and getting the remainder using %. This is done to display the score as two digits.
        g.drawString(String.valueOf(player1/10) + String.valueOf(player1%10), (GAME_WIDTH/2)-95,50);
        g.drawString(String.valueOf(player2/10) + String.valueOf(player2%10), (GAME_WIDTH/2)+25,50);
        
    }
}
