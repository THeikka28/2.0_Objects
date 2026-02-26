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


//step 1 implement keylistener


//*******************************************************************************
// Class Definition Section

public class BasicGameApp implements Runnable, KeyListener, MouseListener{

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
   public long millitime;
   
	public BufferStrategy bufferStrategy;
	public Image astroPic;
    public Image gabepic;
    public Image gavinpic;
    public Image asteroidpic;
    public Image backgroundpic;

   //Declare the objects used in the program
   //These are things that are made up of more than one variable type
	private Astronaut astro;
    private Astronaut gabe;
    private Astronaut Gavin;
    private Asteroid asteroid1;
    private Asteroid asteroid2;
    private boolean up, down, left, right;
    public int speed;





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

        //setup for xom variable
        // first make int then do
      int randx = (int)(Math.random() * 400)+100;
      int randy = (int)(Math.random() * 300)+100;
        int randx2 = (int)(Math.random() * 400)+100;
        int randy2 = (int)(Math.random() * 300)+100;
        int randx3 = (int)(Math.random() * 400)+100;
        int randy3 = (int)(Math.random() * 300)+100;

      setUpGraphics();
       
      //variable and objects
      //create (construct) the objects needed for the game and load up 
		astroPic = Toolkit.getDefaultToolkit().getImage("Niam.png"); //load the picture
        gabepic = Toolkit.getDefaultToolkit().getImage("Gabe.png"); //load the picture
        gavinpic = Toolkit.getDefaultToolkit().getImage("Gavin.png"); //load the picture
        asteroidpic = Toolkit.getDefaultToolkit().getImage("Asteroid.png"); //load the picture
        backgroundpic = Toolkit.getDefaultToolkit().getImage("catbackground.jpeg"); //load the picture

        astro = new Astronaut(randx3,randy3);
        astro.ypos = 400;
        astro.xpos = 220;
        speed = 5;
        astro.dx = 0;
        astro.dy = 0;

        gabe = new Astronaut(randx,randy);
        Gavin = new Astronaut(randx2,randy2);
        asteroid1 = new Asteroid ((int)(Math.random()*1000),randy);
        asteroid2 = new Asteroid((int)(Math.random()*1000), randy);
        asteroid2.dx  = 3;
        Gavin.dx = 5;
        Gavin.dy = -5;
        Gavin.width = 100;
        Gavin.height = 95;
        gabe.width = 100;
        gabe.height = 100;


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


	public void moveThings()
	{
        //calls the move( ) code in the objects

        if (astro.isAlive == true)
        {astro.move();}
        else {astroPic = Toolkit.getDefaultToolkit().getImage("Grave.png");}
        if (gabe.isAlive == true)
        {gabe.move();}
        else {gabepic = Toolkit.getDefaultToolkit().getImage("Grave.png");}
        if (Gavin.isAlive == true)
        {Gavin.move();}

            if (up == true) {
                astro.ypos -= speed;
            }
            if (down == true) {
                astro.ypos = astro.ypos + speed;
                System.out.println("down");
            }
            if (left == true) {
                astro.xpos = astro.xpos - speed;
                System.out.println("left");
            }
            if (right == true) {
                astro.xpos += speed;
            }

        else {gavinpic = Toolkit.getDefaultToolkit().getImage("Grave.png");}

        asteroid1.move();
        asteroid2.move();
        crashing();

	}
    public void crashing()
    {
        //check to see if any astronauts crash into each other
        if (astro.hitbox.intersects(gabe.hitbox) && astro.isCrashing == false){
            astro.dx = -astro.dx;
            astro.dy = -astro.dy;
            gabe.dx = -gabe.dx;
            gabe.dy = -gabe.dy;
            astro.isCrashing = true;
        }
        if (Gavin.hitbox.intersects(gabe.hitbox) && Gavin.isCrashing == false){
            Gavin.dx = -Gavin.dx;
            Gavin.dy = -Gavin.dy;
            gabe.dx = -gabe.dx;
            gabe.dy = -gabe.dy;
            Gavin.isCrashing = true;
        }
        if (Gavin.hitbox.intersects(astro.hitbox) && Gavin.isCrashing == false){
            Gavin.dx = -Gavin.dx;
            Gavin.dy = -Gavin.dy;
            astro.dx = -astro.dx;
            astro.dy = -astro.dy;
        Gavin.isCrashing = true;
        }

        if (!Gavin.hitbox.intersects(astro.hitbox) && !Gavin.hitbox.intersects(gabe.hitbox)){
        Gavin.isCrashing = false;
        }
        if (!astro.hitbox.intersects(Gavin.hitbox) && !astro.hitbox.intersects(gabe.hitbox)){
            astro.isCrashing = false;
        }
        if (!gabe.hitbox.intersects(astro.hitbox) && !gabe.hitbox.intersects(Gavin.hitbox)){
            gabe.isCrashing = false;
        }




        if (asteroid1.hitbox.intersects(Gavin.hitbox)){
            Gavin.isAlive = false;
    }
        if (asteroid1.hitbox.intersects(astro.hitbox)){
        astro.isAlive = false;
    }
        if (asteroid1.hitbox.intersects(gabe.hitbox)){
        gabe.isAlive = false;

    }
        if (asteroid2.hitbox.intersects(Gavin.hitbox)){
            Gavin.isAlive = false;
        }
        if (asteroid2.hitbox.intersects(astro.hitbox)){
            astro.isAlive = false;
        }
        if (asteroid2.hitbox.intersects(gabe.hitbox)){
            gabe.isAlive = false;
        }
        if (asteroid2.hitbox.intersects(asteroid1.hitbox) && asteroid1.isCrashing ==false){
            asteroid2.height += 15;
            asteroid2.width += 15;
            asteroid1.height += 15;
            asteroid1.width += 15;
            asteroid1.dx = -asteroid1.dx;
            asteroid1.dy = -asteroid1.dy;
            asteroid2.dx = -asteroid2.dx;
            asteroid2.dy = -asteroid2.dy;
            asteroid1.isCrashing = true;
        }
        if (!asteroid2.hitbox.intersects(asteroid1.hitbox)){
            asteroid1.isCrashing = false;
        }
    }
	
   //Pauses or sleeps the computer for the amount specified in milliseconds
   public void pause(int time ){
   		//sleep
			try {
				Thread.sleep(time);
			} catch (InterruptedException e) {

			}
   }

   //Graphics setup method
   private void setUpGraphics() {
       canvas = new Canvas();
       frame = new JFrame("Application Template");   //Create the program window or frame.  Names it.
      canvas.addMouseListener(this);
      panel = (JPanel) frame.getContentPane();  //sets up a JPanel which is what goes in the frame
      panel.setPreferredSize(new Dimension(WIDTH, HEIGHT));  //sizes the JPanel
      panel.setLayout(null);   //set the layout
   
      // creates a canvas which is a blank rectangular area of the screen onto which the application can draw
      // and trap input events (Mouse and Keyboard events)


      //step 2: set canvas as the keylistener
       canvas.addKeyListener(this);

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
		Graphics2D g = (Graphics2D) bufferStrategy.getDrawGraphics();
		g.clearRect(0, 0, WIDTH, HEIGHT);
        //start drawing things here

      //draw the image of the astronaut
        g.drawImage(backgroundpic, 0, 0, 1000, 700, null);
		g.drawImage(astroPic, astro.xpos, astro.ypos, astro.width, astro.height, null);
        g.drawImage(gabepic, gabe.xpos, gabe.ypos, gabe.width, gabe.height, null);
        g.drawImage(gavinpic, Gavin.xpos, Gavin.ypos, Gavin.width, Gavin.height, null);
        g.drawImage(asteroidpic, asteroid1.xpos, asteroid1.ypos, asteroid1.width, asteroid1.height, null);
        g.drawImage(asteroidpic, asteroid2.xpos, asteroid2.ypos, asteroid2.width, asteroid2.height, null);


//stop drawing things here
		g.dispose();

		bufferStrategy.show();
	}


    //step 3: add keylistener methods

    @Override
    public void keyTyped(KeyEvent e) {
    }



    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println(e.getKeyCode());
        if (e.getKeyCode() == 87) {
            up = true;
        }
        if (e.getKeyCode() == 83) {
            down = true;
        }
        if (e.getKeyCode() == 65) {
            left = true;
        }
        if (e.getKeyCode() == 68) {
            right = true;
        }



        if (e.getKeyCode() == 73)
        {
            Gavin.dy = -Math.abs(Gavin.dy);
        }
        if (e.getKeyCode() == 74)
        {
            Gavin.dx = -Math.abs(Gavin.dx);
        }
        if (e.getKeyCode() == 75)
        {
            Gavin.dy = Math.abs(Gavin.dy);
        }
        if (e.getKeyCode() == 76)
        {
            Gavin.dx = Math.abs(Gavin.dx);
        }

    }

    @Override
    public void keyReleased(KeyEvent e)
    {
        if (e.getKeyCode() == 87) {
            up = false;
        }
        if (e.getKeyCode() == 83) {
            down = false;
        }
        if (e.getKeyCode() == 65) {
            left = false;
        }
        if (e.getKeyCode() == 68) {
            right = false;
        }
    }

//step three implement
    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
    e.getPoint();
    Gavin.xpos = e.getX();
    Gavin.ypos = e.getY();
    millitime = System.currentTimeMillis();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    if(System.currentTimeMillis()-millitime > 5000)
    {}
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}