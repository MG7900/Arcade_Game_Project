//Basic Game Application
//Version 2
// Basic Object, Image, Movement
// Astronaut moves to the right.
// Threaded

//K. Chun 8/2018

//*******************************************************************************
//Import Section
//Add Java libraries needed for the game
//import java.awt.Canvas;

//Graphics Libraries
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferStrategy;
import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JPanel;

//ninja game regarding picking up weapons and throwing them in x and y directions to escape other ninjas
//*******************************************************************************
// Class Definition Section

public class BasicGameApp implements Runnable, MouseListener, KeyListener {
    //todo: add KeyListener

    //Variable Definition Section
    //Declare the variables used in the program
    //You can set their initial values too

    //Sets the width and height of the program window
    final int WIDTH = 1000;
    final int HEIGHT = 700;

    //Declare the variables needed for the graphics
    public JFrame frame;
    public Canvas canvas;
    public JPanel panel;

    public Rectangle startHitbox;

    public BufferStrategy bufferStrategy;

    public Image ninjaPic;
    public Image flippedninjaPic;
    public Image Freeze_BuffPic;
    public Image KunaiPic;
    public Image ShurikenPic;

    //Declare the objects used in the program
    //These are things that are made up of more than one variable type
    public Ninja ninja;
    public Kunai kunai;
    public Shuriken shuriken;
    public Freeze_Buff freeze_buff;

    public Rebel_Ninjas[] rebel_ninjas;

    public boolean start_game;

    public boolean mouseheld;

    // Main method definition
    // This is the code that runs first and automatically
    public static void main(String[] args) {
        BasicGameApp ex = new BasicGameApp();   //creates a new instance of the game
        new Thread(ex).start();                 //creates a threads & starts up the code in the run( ) method
    }


    // Constructor Method
    // This has the same name as the class
    // This section is the setup portion of the program
    // Initialize your variables and construct your program objects here.
    public BasicGameApp() {

        setUpGraphics();

        //variable and objects
        //create (construct) the objects needed for the game and load up
        ninjaPic = Toolkit.getDefaultToolkit().getImage("Ninja.png"); //load the picture
        flippedninjaPic = Toolkit.getDefaultToolkit().getImage("NinjaFlipped.png");
        ShurikenPic = Toolkit.getDefaultToolkit().getImage("Shuriken.png");
        KunaiPic = Toolkit.getDefaultToolkit().getImage("Kunai.png");
        Freeze_BuffPic = Toolkit.getDefaultToolkit().getImage("Freeze_Buff.png");

        start_game = false;

        startHitbox = new Rectangle(400, 400, 200, 50);

        mouseheld = false;

        ninja = new Ninja(10, 100);
        //todo: do the same for other game elements

        kunai = new Kunai(20,500);

        shuriken = new Shuriken((int)(Math.random()*1000),(int)(Math.random())*700);

        //testing
        rebel_ninjas = new Rebel_Ninjas[3];

        for(int i = 0; i < rebel_ninjas.length; i++){
            rebel_ninjas[i] = new Rebel_Ninjas((int)(Math.random()*1000),(int)(Math.random())*700);
            rebel_ninjas[i].dx = (int)(Math.random()*3)-5;
            rebel_ninjas[i].dy = (int)(Math.random()*3)-2;
        }

    }// BasicGameApp()


//*******************************************************************************
//User Method Section
//
// put your code to do things here.

    // main thread
    // this is the code that plays the game after you set things up
    public void run() {

        //for the moment we will loop things forever.
        while (true) {

            moveThings();  //move all the game objects
            render();  // paint the graphics
            pause(20); // sleep for 10 ms
        }
    }


    public void moveThings() {
        //calls the move( ) code in the objects
        if (start_game == true) {
            ninja.move();
            shuriken.move();
            kunai.move();

            for(int m = 0; m < rebel_ninjas.length; m++){
                rebel_ninjas[m].move();
            }
        }
    }

    //Pauses or sleeps the computer for the amount specified in milliseconds
    public void pause(int time) {
        //sleep
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {

        }
    }

    //Graphics setup method
    private void setUpGraphics() {
        frame = new JFrame("Application Template");   //Create the program window or frame.  Names it.

        panel = (JPanel) frame.getContentPane();  //sets up a JPanel which is what goes in the frame
        panel.setPreferredSize(new Dimension(WIDTH, HEIGHT));  //sizes the JPanel
        panel.setLayout(null);   //set the layout

        // creates a canvas which is a blank rectangular area of the screen onto which the application can draw
        // and trap input events (Mouse and Keyboard events)
        canvas = new Canvas();

        canvas.addKeyListener(this);
        canvas.addMouseListener(this);
        //todo: why does the above have to be there?

        canvas.setBounds(0, 0, WIDTH, HEIGHT);
        canvas.setIgnoreRepaint(true);

        panel.add(canvas);  // adds the canvas to the panel.

        // frame operations
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //makes the frame close and exit nicely
        frame.pack();  //adjusts the frame and its contents so the sizes are at their default or larger
        frame.setResizable(false);   //makes it so the frame cannot be resized
        frame.setVisible(true);      //IMPORTANT!!!  if the frame is not set to visible it will not appear on the screen!

        // sets up things so the screen displays images nicely.
        canvas.createBufferStrategy(2);
        bufferStrategy = canvas.getBufferStrategy();
        canvas.requestFocus();
        System.out.println("DONE graphic setup");

    }


    //paints things on the screen using bufferStrategy
    private void render() {
        //todo: add keylistener and mouselistener etc✅
        //todo: render other elements

        Graphics2D g = (Graphics2D) bufferStrategy.getDrawGraphics();
        g.clearRect(0, 0, WIDTH, HEIGHT);

        //todo: make a better start button

        if (start_game == true) {
            //draw the image of the rebel ninjas as well as the rectangles
            for(int i = 0; i < rebel_ninjas.length; i++){
                g.drawImage(ninjaPic, rebel_ninjas[i].xpos, rebel_ninjas[i].ypos, rebel_ninjas[i].width, rebel_ninjas[i].height, null);
                g.drawRect(rebel_ninjas[i].xpos, rebel_ninjas[i].ypos, rebel_ninjas[i].width, rebel_ninjas[i].height);
            }

            /*todo: make this conditional
            Apr 17, it instantly displays the flipped and the normal picture simultaneously, and the two versions
            just stay together for the remainder of the code
             */
            for(int z = 0; z < rebel_ninjas.length; z++){
                if(ninja.flip == true) {

                    g.drawImage(flippedninjaPic, rebel_ninjas[z].xpos, rebel_ninjas[z].ypos, rebel_ninjas[z].width, rebel_ninjas[z].height, null);

                } else {

                    g.drawImage(ninjaPic, rebel_ninjas[z].xpos, rebel_ninjas[z].ypos, rebel_ninjas[z].width, rebel_ninjas[z].height, null);
                }
            }


            //todo:why do we need to make the new objects below in order for the kunai.xpos for example to not be "null"
            g.drawImage(ninjaPic, ninja.xpos, ninja.ypos, ninja.width, ninja.height, null);
            g.drawRect(ninja.xpos, ninja.ypos, ninja.width, ninja.height);

            g.drawImage(KunaiPic, kunai.xpos, kunai.ypos, kunai.width, kunai.height, null);
            g.drawRect(kunai.xpos, kunai.ypos, kunai.dx, kunai.dy);

            g.drawImage(ShurikenPic, shuriken.xpos, shuriken.ypos, shuriken.width, shuriken.height, null);
            g.drawRect(shuriken.xpos, shuriken.ypos, shuriken.dx, shuriken.dy);

        }
        if(start_game == false) {
            g.setColor(Color.blue);
            g.fillRect(400, 400, 200, 50);
            //place holder for actual start button
        }
        g.dispose();

        bufferStrategy.show();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        mouseheld = true;
        System.out.println(e.getPoint());
        Rectangle mouseHitbox = new Rectangle(e.getX(), e.getY(), 1, 1);
        if (startHitbox.intersects(mouseHitbox)) {
            System.out.println("Game starts!");
            start_game = true;
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        System.out.println("Mouse is present!");
    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    //Key portion below
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println("I touched " + e.getKeyCode());
        //gets the letters that were pressed, not e.g. backspace or space
        if(e.getKeyCode() == 37){
            ninja.isLeft = true;
        }
        if(e.getKeyCode() == 38){
            ninja.isUp = true;
        }
        if(e.getKeyCode() == 39){
            ninja.isRight = true;
        }
        if(e.getKeyCode() == 40){
            ninja.isDown = true;
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {   //key released is triggered everytime we stop touching a key
        System.out.println("I stopped touching " + e.getKeyCode());

        if(e.getKeyCode() == 37){
            ninja.isLeft = false;
        }
        if(e.getKeyCode() == 38){
            ninja.isUp = false;
        }
        if(e.getKeyCode() == 39){
            ninja.isRight = false;
        }
        if(e.getKeyCode() == 40){
            ninja.isDown = false;
        }
    }
}