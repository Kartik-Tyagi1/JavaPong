import javax.swing.JFrame;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.event.KeyEvent;

public class Window extends JFrame implements Runnable{
    Graphics2D g2;
    KL keyListener = new KL();

    public Window() {
        this.setSize(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
        this.setTitle(Constants.title);
        this.setResizable(false);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // closes when we click x on the window

        g2 = (Graphics2D)this.getGraphics();
        this.addKeyListener(keyListener);
    }

    public void update(double deltaTime) {
        g2.setColor(Color.BLACK);
        g2.fillRect(0, 0, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);

        if(keyListener.isKeyPressed(KeyEvent.VK_UP)) {
            System.out.println("User pressed up arrow");
        }
        else if(keyListener.isKeyPressed(KeyEvent.VK_DOWN)) {
            System.out.println("User pressed down arrow");
        }
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

            // Caps FPS to 30
            try {
                Thread.sleep(30);
            }
            catch (Exception e) {

            }
        }
    }
}
