import java.awt.*;

/**
 * Created by chales on 11/6/2017.
 */
public class Ninja {

    //todo:add feature where the ninja's image would turn to the according side if the horizontal direction changes

    //VARIABLE DECLARATION SECTION
    //Here's where you state which variables you are going to use.
    public String name;                //holds the name of the hero
    public int xpos;                //the x position
    public int ypos;                //the y position
    public int dx;                    //the speed of the hero in the x direction
    public int dy;                    //the speed of the hero in the y direction
    public int width;
    public int height;
    public boolean isAlive;            //a boolean to denote if the hero is alive or dead.
    public Rectangle hitbox;
    public boolean flip;

    public boolean isUp;
    public boolean isDown;
    public boolean isLeft;
    public boolean isRight;


    // METHOD DEFINITION SECTION

    // Constructor Definition
    // A constructor builds the object when called and sets variable values.


    //This is a SECOND constructor that takes 3 parameters.  This allows us to specify the hero's name and position when we build it.
    // if you put in a String, an int and an int the program will use this constructor instead of the one above.
    public Ninja(int pXpos, int pYpos) {
        xpos = pXpos;
        ypos = pYpos;
        dx =10;
        dy =0;
        width = 55;
        height = 55;
        isAlive = true;
        hitbox = new Rectangle(xpos, ypos, width, height);
        flip = false;
    } // constructor

    //The move method.  Everytime this is run (or "called") the hero's x position and y position change by dx and dy
    public void move() {
        xpos = xpos + dx;
        ypos = ypos + dy;

        if(xpos < 0 || xpos > 950){
            dx = -dx;
        }

        if(ypos < 0 || ypos > 620){
            dy = -dy;
        }

        if(isUp = true){
            dy = dy - 2;
        }
        if(isDown = true){
            dy = dy + 2;
        }
        if(isLeft = true){
            flip = true;
            dx = dx - 2;
        }
        if(isRight = true){
            dx = dx + 2;
        }

    }
}






