import java.awt.*;
import java.awt.geom.Rectangle2D;

public class Rect {
    public double x, y;
    public double width, height;
    private Color color;

    public Rect(double x, double y, double width, double height, Color color) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.color = color;
    }

    public void drawRect(Graphics2D g2) {
        g2.setColor(color);
        g2.fill(new Rectangle2D.Double(x, y, width, height));
    }

    // Used for paddle movement
    public void moveRectVertical(double dy) {
        if(this.y + dy + Constants.PADDLE_HEIGHT < Constants.SCREEN_HEIGHT - Constants.INSETS_BOTTOM && this.y + dy > Constants.TOOLBAR_HEIGHT) {
            this.y += dy;
        }
    }
}
