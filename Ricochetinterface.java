package linearball;
//Author: Joshua D Moynihan
//Email: jdmoynihan19@csu.fullerton.edu
//Course: CPSC223J
//
//File name: Ricochetinterface.java
//Date last tested: 2015-10-26
//Description: This is one module in the Ricochet ball program.  This module
//defines the user interface.
//
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.Timer;


public class Ricochetinterface extends JFrame
{    
     JPanel titlepanel;
     JLabel titlelabel;
     
     Ricochetpanel linearpanel;
     
     JPanel buttonpanel;
     JButton gobutton;
     JButton newbutton;
     JButton quitbutton;
     
     JLabel speedlabel;
     JTextField speedinput;
     String speedstring;
     double speed;
     
     
     JLabel refreshlabel;
     JTextField refreshinput;
     String refreshstring;
     double refresh;
     
     JLabel directionlabel;
     JTextField directioninput;
     String directionstring;
     double direction;
     
     JLabel xcoord;
     JTextField xoutput;
     private int x;
     String xstring;
     JLabel ycoord;
     JTextField youtput;
     private int y;
     String ystring;
     
     
     Timer refreshclock;
     Timer animationclock;
     Buttonhandlerclass buttonhandler;
     Refreshclass refreshhandler;
     Animationclass animationhandler;
     final double millisecondpersecond = 1000.0;
     double directionoftravel = 90.0; //degrees.  The ball will move into the fourth traditional quadrant of the Cartesian plane.
     double directionoftravelcorrected = 90.0 + directionoftravel; //Correction because computer's y-axis is upside down.
     double speedofball = 48.18;  //This is the number of pixels traveled by the ball in one second.
     double rateofanimation = 22.5;  //This is the number of times the ball's coordinates are updated each second.
     int animationinterval = (int)Math.round(millisecondpersecond/rateofanimation);
     double rateofrefresh = 40.333;  //This is the number of times the graphical area is repainted each second.
     int refreshinterval = (int)Math.round(millisecondpersecond/rateofrefresh);

     public Ricochetinterface()
         {super("Ricochet Ball");
          //Do not use FlowLayout when working with Graphical objects.
          //Make 3 panels and place them from top to bottom onto any object of type Linearinterface.
          //Make the 1st panel
          titlelabel = new JLabel("CPSC 223J Assignment 4 By Josh Moynihan");
          titlepanel = new JPanel();
          titlepanel.add(titlelabel);
          titlepanel.setPreferredSize(new Dimension(1200,25));
          add(titlepanel,BorderLayout.NORTH);
          
          //Make the 2nd panel
          linearpanel = new Ricochetpanel(directionoftravelcorrected,speedofball,rateofanimation);
          linearpanel.setBackground(Color.BLUE);
          linearpanel.setPreferredSize(new Dimension(1200, 600));
          add(linearpanel,BorderLayout.CENTER);
          
          //Make the 3rd panel
          buttonhandler = new Buttonhandlerclass();
          
          newbutton = new JButton("New");
          gobutton = new JButton("Start");
          quitbutton = new JButton("Quit");
          
          refreshlabel = new JLabel("Refresh rate (Hz)");
          refreshinput = new JTextField(10);
          refreshinput.setText("40");
          speedlabel = new JLabel("Speed (px/sec)");
          speedinput = new JTextField(10);
          speedinput.setText("40");
          directionlabel = new JLabel("Direction (deg)");
          directioninput = new JTextField(10);
          directioninput.setText("0");
          
          xcoord = new JLabel("X = ");
          xoutput = new JTextField(5);
          xoutput.setText("0");
          ycoord = new JLabel("Y = ");
          youtput = new JTextField(5);
          youtput.setText("0");
          
          newbutton.addActionListener(buttonhandler);
          gobutton.addActionListener(buttonhandler);
          quitbutton.addActionListener(buttonhandler);
          
          buttonpanel = new JPanel();
          buttonpanel.add(newbutton);
          buttonpanel.add(gobutton);
          buttonpanel.add(quitbutton);
          buttonpanel.add(refreshlabel);
          buttonpanel.add(refreshinput);
          buttonpanel.add(speedlabel);
          buttonpanel.add(speedinput);
          buttonpanel.add(directionlabel);
          buttonpanel.add(directioninput);
          buttonpanel.add(xcoord);
          buttonpanel.add(xoutput);
          buttonpanel.add(ycoord);
          buttonpanel.add(youtput);
          buttonpanel.setPreferredSize(new Dimension(1200,50));
          add(buttonpanel,BorderLayout.SOUTH);

          //Make a clock that controls the graphical refresh rate.
          refreshhandler = new Refreshclass();
          refreshclock = new Timer(refreshinterval,refreshhandler);

          //Make a clock that controls the animation rate.
          animationhandler = new Animationclass();
          animationclock = new Timer(animationinterval,animationhandler);

         }//End of Gameinterface constructor.

     private class Buttonhandlerclass implements ActionListener
     {    public void actionPerformed(ActionEvent event)
              {
                if(event.getSource() == newbutton){
                    linearpanel.initializeball();
                    linearpanel.repaint();
                    refreshclock.stop();
                    animationclock.stop();
                    xoutput.setText(Integer.toString(linearpanel.sendXCoord()));
                    youtput.setText(Integer.toString(linearpanel.sendYCoord()));
                    directioninput.setText(Integer.toString(0));
                    speedinput.setText(Integer.toString(40));
                    refreshinput.setText(Integer.toString(40));
                      
                }
                else if(event.getSource() == gobutton)
                {
                   //read in direction degree and update directionoftravel angle
                   directionstring = directioninput.getText();
                   direction = Double.parseDouble(directionstring);
                   directionoftravel = direction;
                   directionoftravelcorrected = 90.0+direction;
                   
                   //read in speed input and update speed of animationclock
                   speedstring = speedinput.getText();
                   speed = Double.parseDouble(speedstring);
                   //speed= 1000/speed;
                   rateofanimation = 1000/speed;
                   animationinterval = (int)Math.round(millisecondpersecond/rateofanimation);
                   //animationclock.setDelay((int) speed);
                   
                   //read in refresh rate and update refresh of refreshclock
                   refreshstring = refreshinput.getText();
                   refresh = Double.parseDouble(refreshstring);
                   rateofrefresh = refresh;
                   refreshinterval = (int)Math.round(millisecondpersecond/rateofrefresh);
                   
                   refreshclock = new Timer(refreshinterval,refreshhandler);
                   //animationclock = new Timer(animationinterval,animationhandler);
                   //rateofrefresh refreshinterval
                   
                   //originallinearpanel.setNewParameters(directionoftravelcorrected, speedofball,rateofanimation);
                   linearpanel.setNewParameters(directionoftravelcorrected, speed, rateofanimation);
                   linearpanel.initializeball();
                   linearpanel.repaint();
                   refreshclock.start();
                   animationclock.start();
                  }
                else if (event.getSource() == quitbutton) {
                    System.exit(0);
                    //message.setText("Message: this program will now close");
                    //exitbutton.setEnabled(false);
                    //delayedclosing.start();//delays closing of program
                }
                else
                  System.out.printf("%s\n","An error ocurred in a button.");
              }
     }//End of Buttonhandlerclass

     private class Refreshclass implements ActionListener
     {    public void actionPerformed(ActionEvent event)
              {if(event.getSource() == refreshclock){
                  linearpanel.repaint();
                  x = linearpanel.sendXCoord();
                  y = linearpanel.sendYCoord();
                  xstring = Integer.toString(x);
                  ystring = Integer.toString(y);
                  //xstring = String.format("%.2f", xstring);
                  //ystring = String.format("%.2f", ystring);
                  xoutput.setText(xstring);
                  youtput.setText(ystring);
              }
               else
                  System.out.printf("%s\n","Error in the refresh clock.");
              }
     }//end of Refreshclass

     private class Animationclass implements ActionListener
     {    public void actionPerformed(ActionEvent event)
              {if(event.getSource() == animationclock){
                  linearpanel.moveball();
                  /*x = linearpanel.sendXCoord();
                  y = linearpanel.sendYCoord();
                  xoutput.setText(Double.toString(x));
                  youtput.setText(Double.toString(y));*/
              }
               else
                  System.out.printf("%s\n","Error in the animation clock.");
              }
     }//End of Animationclass

}//End of Gameinterface
              
