package linearball;

//Author: Joshua D Moynihan
//Email: jdmoynihan19@csu.fullerton.edu
//Course: CPSC223J
//Usage: This program creates a ball that moves at a defined speed and refresh
// time and it ricochets off the walls of the panel that it moves within. 
//File name: Ricochetball.java

//Date last tested: 2015-10-26
//Purpose for this file: The main driver for a program showing a small object in motion.


import javax.swing.JFrame;

public class Ricochetball
{    public static void main(String[] args)
         {JFrame myframe = new Ricochetinterface();
          myframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
          myframe.setSize(1250,700);
          myframe.setVisible(true);
         }//End of main
}//End of class Pong