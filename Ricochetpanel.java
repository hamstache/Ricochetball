package linearball;

//Author: Joshua D Moynihan
//Email:  jdmoynihan19@csu.fullerton.edu
//Course: CPSC223J
//
//File name: Ricochetpanel.java
//Date last tested: 2015-10-26
//Description: This is one module in the Ricochet ball project that defines
// the functions for the speed and calculate the coordinates as the panel
// refreshes every time.


import java.awt.Graphics;
import javax.swing.JPanel;
import java.awt.Color;

public class Ricochetpanel extends JPanel
{    int widthofgraphicalarea;
     int heightofgraphicalarea;
     final int ballradius = 15;
     final int balldiameter = 2*ballradius;
     double startx;
     double starty;
     double ballrealxcoordinate;
     double ballrealycoordinate;
     double realy;

     //The following negative values assure that the ball remains out of view
     //until the start button is clicked.
     int ballintxcoordinate = - balldiameter;
     int ballintycoordinate = - balldiameter;
     double deltax;
     double deltay;

     Ricochetpanel(double directionoftravel, double ballvelocity, double animationrate)  //constructor
     {//directionoftravel is a real number showing the number of degrees in the angle between the positive x-axix and the path of the ball.
      //ballvelocity is a real number measuring the number of pixels traveled per second.
      //animationrate is a real number giving the number of times the coordinates of the ball are updated each second.
      deltax = ballvelocity * Math.sin(Math.toRadians(directionoftravel)) / animationrate;
      deltay = ballvelocity * Math.cos(Math.toRadians(directionoftravel)) / animationrate;
     }

     public void paintComponent(Graphics graphicarea)
     {    super.paintComponent(graphicarea);
          this.setBackground(Color.BLACK);
          widthofgraphicalarea = getWidth();
          heightofgraphicalarea = getHeight();
          //Give the ball a red color
          graphicarea.setColor(Color.YELLOW);
          graphicarea.fillOval(ballintxcoordinate,ballintycoordinate,balldiameter,balldiameter);
     }//End of method paintComponent

     //The next method places the ball in its starting position, namely: top and center
     public void initializeball()
     {    //ballrealxcoordinate = (double)(widthofgraphicalarea/2 - ballradius);
          ballrealxcoordinate = 600.0;
          ballrealycoordinate = 275.0;
          ballintxcoordinate = (int)ballrealxcoordinate;
          ballintycoordinate = (int)ballrealycoordinate;
     }//End of initializeball
     public void setNewParameters(double directionoftravel, double ballvelocity, double animationrate){
        deltax = ballvelocity * Math.sin(Math.toRadians(directionoftravel)) / animationrate;
        deltay = ballvelocity * Math.cos(Math.toRadians(directionoftravel)) / animationrate;
     }

     //The next method advances the ball by adding suitable increments to the ball's x and y real coordinates.
     //
     public boolean moveball()
     {    boolean successfulmove = false; //Assume no move unless proven otherwise.
          //if(ballintycoordinate+2*ballradius < heightofgraphicalarea && ballintxcoordinate+2*ballradius < widthofgraphicalarea && ballintycoordinate+2*ballradius > 30 && ballintxcoordinate+2*ballradius > 30)
          //{
            if (ballintycoordinate+2*ballradius < heightofgraphicalarea){
             deltay = -1*deltay;
          }
          if (ballintxcoordinate+2*ballradius < widthofgraphicalarea){
             deltax = -1*deltax;
          }
          if (ballintycoordinate+2*ballradius > 30){
             deltay = -1*deltay;
          }
          if (ballintxcoordinate+2*ballradius > 30) {
             deltax = -1*deltax;
          }
            ballrealxcoordinate = ballrealxcoordinate + deltax;
            ballrealycoordinate = ballrealycoordinate + deltay;
            ballintxcoordinate = (int)Math.round(ballrealxcoordinate);
            ballintycoordinate = (int)Math.round(ballrealycoordinate);
          
            successfulmove = true;
          //}
          return successfulmove;
     }//End of moveball
    
     public int sendXCoord(){
         return (int) (ballrealxcoordinate+2*ballradius);
     }
     public int sendYCoord(){
         return (int) (ballrealycoordinate+2*ballradius);
     }
}//End of Graphicalpanel
