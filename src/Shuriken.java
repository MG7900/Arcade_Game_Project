import java.awt.*;

public class Shuriken {

    //todo: make this a pickup weapon for the Ninja
    //todo: get the picture for this ✅
    //todo: think if this would be possible for the array-generated ninja's to also pick up and use

    public double xpos;
    public double ypos;
    public double dx;
    public double dy;
    public int width;
    public int height;
    public Rectangle hitbox;
    public int angle_facing;
    public int target_x;
    public int target_y;

    public int y_step;
    public int x_step;

    public Shuriken(int pXpos, int pYpos) {

        xpos = pXpos;
        ypos = pYpos;
        dx = 15;
        dy = 15;
        width = 60;
        height = 60;
        hitbox = new Rectangle((int)xpos, (int)ypos, 55, 77);
    }

    public void move_by_player() {
        //ths freeze buff also wraps around the map
        if (xpos < 0 || xpos > 950) {
            dx = -dx;
        }

        if (ypos < 0 || ypos > 620) {
            dy = -dy;
        }
        xpos = xpos + dx;
        ypos = ypos + dy;
        hitbox = new Rectangle((int)xpos, (int)ypos, 55, 60);

    }

    public void move_by_npc(){
        xpos = xpos + dx;
        ypos = ypos + dy;
        hitbox = new Rectangle((int)xpos, (int)ypos, 55, 60);
    }

    //the below version now actually calculates and uses the angle
    //it uses it by assigning the direction and speed of the shuriken
        public void starting_angle(int target_x, int target_y){
            double change_x = target_x - xpos;
            double change_y = target_y - ypos;

            double angle = Math.atan2(change_y, change_x);

            int velocity = 5;
            dx = (Math.cos(angle) * velocity);
            dy = (Math.sin(angle) * velocity);
    }
}



