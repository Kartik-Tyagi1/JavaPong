public class AIController {
    public PlayerController playerController;
    public Rect ball;

    public AIController(PlayerController playerController, Rect ball) {
        this.playerController = playerController;
        this.ball = ball;
    }

    public void update(double deltaTime) {
        playerController.update(deltaTime);
        if(ball.y < playerController.rect.y) {
            playerController.rect.moveRectVertical(-Constants.PADDLE_MOVE_SPEED * deltaTime);
        }
        else if (ball.y + ball.height > playerController.rect.y + playerController.rect.height){
            playerController.rect.moveRectVertical(Constants.PADDLE_MOVE_SPEED * deltaTime);
        }
    }
}
