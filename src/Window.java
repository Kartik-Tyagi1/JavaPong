import javax.crypto.Cipher;
import javax.swing.JFrame;
import java.awt.*;


public class Window extends JFrame implements Runnable{
    Graphics2D g2;
    KL keyListener = new KL();
    Rect player1, ai, ball;
    PlayerController playerController;

    public Window() {
        // Window properties
        this.setSize(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
        this.setTitle(Constants.title);
        this.setResizable(false);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // closes when we click x on the window

        // JFRAME has a bit of extra space on the top an bottom of the window which need to be accounted for
        Constants.TOOLBAR_HEIGHT = this.getInsets().top;
        Constants.INSETS_BOTTOM = this.getInsets().bottom;

        // Graphics and Key Listener
        g2 = (Graphics2D)this.getGraphics();
        this.addKeyListener(keyListener);

        // Players and Ball
        player1 = new Rect(Constants.HZ_PADDING, 40, Constants.PADDLE_WIDTH, Constants.PADDLE_HEIGHT, Constants.PADDLE_COLOR);
        playerController = new PlayerController(player1, keyListener);

        ai = new Rect(Constants.SCREEN_WIDTH - Constants.PADDLE_WIDTH - Constants.HZ_PADDING, 40, Constants.PADDLE_WIDTH, Constants.PADDLE_HEIGHT, Constants.PADDLE_COLOR);
        ball = new Rect(Constants.SCREEN_WIDTH / 2, Constants.SCREEN_HEIGHT / 2, Constants.BALL_WIDTH, Constants.BALL_HEIGHT, Constants.BALL_COLOR);

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

        playerController.update(deltaTime);
    }

    public void draw(Graphics g) {
        Graphics2D g2 = (Graphics2D)g;

        // Background color
        g2.setColor(Color.BLACK);
        g2.fillRect(0, 0, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);

        player1.drawRect(g2);
        ai.drawRect(g2);
        ball.drawRect(g2);
    }

    @Override
    public void run() {
        double lastFrameTime = 0.0;
        while (true) {
            // Get time the frame has ended
            double time = Time.getTime();

            // Calculate the time between the last frame and the current frame
            double deltaTime = time - lastFrameTime;

            // Reset the last frame for the next frame calculation
            lastFrameTime = time;

            update(deltaTime);
        }
    }
}
