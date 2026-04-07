import java.awt.*;

public class Asteroid {
    //VARIABLE DECLARATION SECTION
    //Here's where you state which variables you are going to use.
    public int xbound;
    public int ybound;
    public double scale;
    public String name;                //holds the name of the hero
    public double xpos;                //the x position
    public double ypos;                //the y position
    public double dx;                    //the speed of the hero in the x direction
    public double dy;                    //the speed of the hero in the y direction
    public int width;
    public int height;
    public boolean isAlive;//a boolean to denote if the hero is alive or dead.
    public Rectangle hitbox;
    public boolean isCrashing;
    public double rand;
    double startxpos;
    double startypos;
    public double speedxrand;
    public double speedyrand;






    // METHOD DEFINITION SECTION

    // Constructor Definition
    // A constructor builds the object when called and sets variable values.


    //This is a SECOND constructor that takes 3 parameters.  This allows us to specify the hero's name and position when we build it.
    // if you put in a String, an int and an int the program will use this constructor instead of the one above.
    public Asteroid (int pXpos, int pYpos) {
        xpos = pXpos;
        ypos = pYpos;
        dx =-3;
        dy = -3;
        isCrashing = false;
        width = 100;
        height = 100;
        isAlive = true;
        hitbox = new Rectangle ((int)xpos, (int) ypos, width, height);
        rand = (Math.random()*4)+1;
        speedxrand = (Math.random()*200)-100;
        speedyrand = (Math.random()*200)-100;


    } // constructor

    //The move method.  Everytime this is run (or "called") the hero's x position and y position change by dx and dy
    public void move() {
        xbound = 1000-width;
        ybound = 700-height;
        xpos = xpos + dx;
        ypos = ypos + dy;
       if (xpos < 0){
           xpos = 1000;
       ypos = (int)(Math.random()*700)+1;

            }
        if (xpos > 1000){

            xpos = 0;
            ypos = (int)(Math.random()*700)+1;
            }
        if (ypos > 700){ ypos = 0;
            xpos = (int)(Math.random()*1000)+1;
        }
        if (ypos < 0){ ypos = 700;
            xpos = (int)(Math.random()*1000)+1;
        }


        hitbox = new Rectangle((int)xpos, (int) ypos, width, height);


    }
}
