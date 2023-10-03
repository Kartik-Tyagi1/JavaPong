import java.awt.*;

public class Text {
    public String text;
    public Font font;
    public double x, y;
    public Color textColor;
    public double width, height;

    public Text(double x, double y, Font font, Color color, String text) {
        this.text = text;
        this.font = font;
        this.x = x;
        this.y = y;
        this.textColor = color;
        this.width = font.getSize() * text.length();
        this.height = font.getSize();
    }

    public Text(double x, double y, Font font, Color color, int text) {
        this.text = "" + text;
        this.font = font;
        this.x = x;
        this.y = y;
        this.textColor = color;
    }

    public void draw(Graphics2D g2) {
        g2.setColor(textColor);
        g2.setFont(font);
        g2.drawString(text, (float)x, (float)y);
    }
}
