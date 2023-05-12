import  java.awt.*;
import  java.awt.event.*;
import  java.util.*;
import javax.swing.*;

//This code defines a GameFrame class that represents the main window of the Pong game. It creates a game panel, sets up the frame properties, and displays the frame with the game panel.

public class GameFrame extends JFrame{
    
    GamePanel panel; //This variable will hold the game panel where the actual gameplay will be displayed.

    GameFrame() { //This constructor is called when an instance of the GameFrame class is created.
        panel = GamePanel.getInstance();//The GamePanel class acts for the panel where the game graphics and logic will be put into effect.
        this.add(panel); //This adds the game panel to the content pane of the frame.
        this.setTitle("Java Pong Game");//The title of the frame is set to "Java Pong Game" using the setTitle() method.
        this.setResizable(false);//The frame is set to be non-resizable using the setResizable() method.
        this.setBackground(Color.black);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//The default close operation of the frame is set to exit the application using the setDefaultCloseOperation() method with the JFrame.EXIT_ON_CLOSE constant.
        this.pack(); //The frame is packed to ensure that all components are laid out correctly
        this.setVisible(true); //The frame is set to be visible
        this.setLocationRelativeTo(null); //The frame is positioned at the center of the screen
    } 
}
