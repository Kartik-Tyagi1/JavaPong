import javax.swing.JFrame;
import java.awt.Graphics2D;
import java.awt.Color;

public class Window extends JFrame implements Runnable{
    public int x = 20;
    public int y = 20;
    Graphics2D g2;
    public Window() {
        this.setSize(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
        this.setTitle(Constants.title);
        this.setResizable(false);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // closes when we click x on the window
        g2 = (Graphics2D)this.getGraphics();
    }

    public void update(double deltaTime) {
        g2.setColor(Color.BLACK);
        g2.fillRect(x, y, 50, 50);
        x += 2;
        y += 2;
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
