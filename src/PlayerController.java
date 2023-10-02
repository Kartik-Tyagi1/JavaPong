import java.awt.event.KeyEvent;

public class PlayerController {
    public Rect rect;
    public KL keyListener;

    public PlayerController(Rect rect, KL keyListener) {
        this.rect = rect;
        this.keyListener = keyListener;
    }

    // AI Will not have a key listener
    public PlayerController(Rect rect) {
        this.rect = rect;
        this.keyListener = null;
    }

    public void update(double deltaTime) {
        if(keyListener != null) {
            if(keyListener.isKeyPressed(KeyEvent.VK_UP)) {
                rect.moveRectVertical(-Constants.PADDLE_MOVE_SPEED * deltaTime);
            }
            else if(keyListener.isKeyPressed(KeyEvent.VK_DOWN)) {
                rect.moveRectVertical(Constants.PADDLE_MOVE_SPEED * deltaTime);
            }
        }
        else {

        }

    }


}
