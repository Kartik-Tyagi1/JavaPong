import javax.crypto.Cipher;
import javax.swing.JFrame;
import java.awt.*;


public class MainMenu extends JFrame implements Runnable{
    public Graphics2D g2;
    public KL keyListener = new KL();
    public ML mouseListener = new ML();
    public Text mainMenuText, startGameText, exitGameText;
    Font tnrFont = new Font("Times New Roman", Font.BOLD, Constants.TEXT_SIZE);
    Font tnrFontMainMenu = new Font("Times New Roman", Font.BOLD, 60);
    public boolean isRunning = true;


    public MainMenu() {
        // Window properties
        this.setSize(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
        this.setTitle(Constants.title);
        this.setResizable(false);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // closes when we click x on the window
        this.addMouseListener(mouseListener);
        this.addMouseMotionListener(mouseListener);

        // Graphics and Key Listener
        g2 = (Graphics2D)this.getGraphics();
        this.addKeyListener(keyListener);

        mainMenuText = new Text(Constants.SCREEN_WIDTH / 2.0 - 160, Constants.SCREEN_HEIGHT / 2.0 - 100.0, tnrFontMainMenu, Color.WHITE, "Main Menu");
        startGameText = new Text(Constants.SCREEN_WIDTH / 2.0 - 120, Constants.SCREEN_HEIGHT / 2.0, tnrFont, Color.WHITE, "Start Game");
        exitGameText = new Text(Constants.SCREEN_WIDTH / 2.0 - 110, Constants.SCREEN_HEIGHT / 2.0 + 60.0, tnrFont, Color.WHITE, "Exit Game");
    }

    public void update(double deltaTime) {
        // Create double buffer image
        Image dbImage = createImage(getWidth(), getHeight());

        // Create double buffer graphics object which get the graphics from the image object
        Graphics dbg = dbImage.getGraphics();

        // Add player and ball drawing to graphics object on the image
        this.draw(dbg);

        // Draw the image to the window with the graphics we added
        g2.drawImage(dbImage, 0, 0, this);

        if(mouseListener.getMouseX() > startGameText.x && mouseListener.getMouseX() < startGameText.x + startGameText.width - 200 &&
            mouseListener.getMouseY() > startGameText.y - startGameText.height / 2 && mouseListener.getMouseY() < startGameText.y + startGameText.height / 2)
        {
            startGameText.textColor = new Color(69, 69, 69);

            // Mouse is over start game and clicked on it -> Start game
            if(mouseListener.isMousePressed()) {
                Main.changeState(1);
            }
        }
        else
        {
            startGameText.textColor = Color.WHITE;
        }

        if(mouseListener.getMouseX() > exitGameText.x && mouseListener.getMouseX() < exitGameText.x + exitGameText.width - 165 &&
                mouseListener.getMouseY() > exitGameText.y - exitGameText.height / 2 && mouseListener.getMouseY() < exitGameText.y + exitGameText.height / 2)
        {
            exitGameText.textColor = new Color(69, 69, 69);
            // Mouse is over start game and clicked on it -> Start game
            if(mouseListener.isMousePressed()) {
                Main.changeState(2);
            }
        }
        else
        {
            exitGameText.textColor = Color.WHITE;
        }
    }

    public void draw(Graphics g) {
        Graphics2D g2 = (Graphics2D)g;

        // Background color
        g2.setColor(Color.BLACK);
        g2.fillRect(0, 0, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);

        mainMenuText.draw(g2);
        startGameText.draw(g2);
        exitGameText.draw(g2);
    }

    public void stop() {
        isRunning = false;
    }

    @Override
    public void run() {
        double lastFrameTime = 0.0;
        while (isRunning) {
            // Get time the frame has ended
            double time = Time.getTime();

            // Calculate the time between the last frame and the current frame
            double deltaTime = time - lastFrameTime;

            // Reset the last frame for the next frame calculation
            lastFrameTime = time;

            update(deltaTime);
        }
        this.dispose();
        return;
    }
}

