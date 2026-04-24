import java.awt.*;

public class Kunai {

    //todo: make this a pickup weapon for the Ninja
    //todo: get the picture for this ✅
    //todo: think if this would be possible for the array-generated ninja's to also pick up and use

    public int xpos;
    public int ypos;
    public int dx;
    public int dy;
    public int width;
    public int height;
    public Rectangle hitbox;
    public int angle_facing;
    public int target_x;
    public int target_y;

    public int y_step;
    public int x_step;

    public Kunai(int pXpos, int pYpos) {

        xpos = pXpos;
        ypos = pYpos;
        dx = 15;
        dy = 15;
        width = 60;
        height = 60;
        hitbox = new Rectangle(xpos, ypos, 60, 80);
    }

    public void move() {
        //ths freeze buff also wraps around the map
        if(ypos > 700){
            ypos = 0;
        }
        if(ypos < 0){
            ypos = 700;
        }

        if(xpos > 850){
            xpos = 0;
        }
        if(xpos < 0){
            xpos = 850;
        }
        xpos = xpos + dx;
        ypos = ypos + dy;
        hitbox = new Rectangle(xpos, ypos, 60, 80);
    }
    public void starting_angle(int xpos, int ypos){
        target_x = xpos;
        target_y = ypos;

        angle_facing = (int) Math.atan(Math.toIntExact(target_x/target_y));
        y_step = (int) Math.sin(Math.toRadians(angle_facing));
        x_step = (int) Math.cos(Math.toRadians(angle_facing));

    }
}

