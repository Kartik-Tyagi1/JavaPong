public class Ball {
    public Rect ballRect, leftPaddle, rightPaddle;
    private double vy = 200.0; // y velocity
    private double vx = -10.0; // x velocity

    public Ball(Rect ballRect, Rect leftPaddle, Rect rightPaddle) {
        this.ballRect = ballRect;
        this.leftPaddle = leftPaddle;
        this.rightPaddle = rightPaddle;
    }

    public void update(double deltaTime) {
        checkHorizontalCollision();
        checkVerticalCollision();

        // Cannot use moveRect code here since that has extra conditions that do not apply to the ball
        this.ballRect.x += vx * deltaTime;
        this.ballRect.y += vy * deltaTime;
    }

    private void flipXVelocity() {
        this.vx *= -1;
    }
    private void flipYVelocity() {
        this.vy *= -1;
    }

    private void checkHorizontalCollision() {
        // ball moving right
        if(vx > 0 )
        {
            // Check if ball has collided with the left edge of the right paddle
            // Check if ball has not passed the right paddle
            // Check if the top of the ball is within the height range of the right paddle
            if(this.ballRect.x >= this.rightPaddle.x + this.rightPaddle.width && this.ballRect.x + this.ballRect.width <= this.rightPaddle.x &&
                    this.ballRect.y >= this.rightPaddle.y && this.ballRect.y <= this.rightPaddle.y + this.rightPaddle.height)
            {
                flipXVelocity();
            }
            // Check if ball has passed the right paddle
            else if(this.ballRect.x + this.ballRect.width > this.rightPaddle.x + this.rightPaddle.width) {
                System.out.println("Player gained a point");
            }
        }
        // ball moving left
        else if(vx < 0)
        {
            // Check if ball has collided with the right edge of the left paddle
            // Check if ball has not passed the left paddle
            // Check if the top of the ball is within the height range of the left paddle
            if(this.ballRect.x <= this.leftPaddle.x + this.leftPaddle.width && this.ballRect.x + this.ballRect.width >= this.leftPaddle.x &&
                    this.ballRect.y >= this.leftPaddle.y && this.ballRect.y <= this.leftPaddle.y + this.leftPaddle.height)
            {
                flipXVelocity();
            }
            // Check if ball has passed the left paddle
            else if(this.ballRect.x + this.ballRect.width < this.leftPaddle.x ) {
                System.out.println("AI gained a point");
            }
        }
    }

    private void checkVerticalCollision() {
        // Ball moving down
        if(vy > 0)
        {
            if(this.ballRect.y + this.ballRect.height > Constants.SCREEN_HEIGHT) {
                flipYVelocity();
            }
        }
        // Ball moving up
        else if(vy < 0)
        {
            if(this.ballRect.y < Constants.TOOLBAR_HEIGHT) {
                flipYVelocity();
            }
        }
    }

}
