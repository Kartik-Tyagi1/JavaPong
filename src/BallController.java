public class BallController {
    public Rect ballRect, leftPaddle, rightPaddle;
    private double vy = 50.0; // y velocity
    private double vx = -100.0; // x velocity

    public BallController(Rect ballRect, Rect leftPaddle, Rect rightPaddle) {
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

    private void flipYVelocity() {
        this.vy *= -1;
    }

    private void checkHorizontalCollision() {
        // ball moving right
        if(vx >= 0.0 )
        {
            // Check if ball has collided with the left edge of the right paddle
            // Check if ball has not passed the right paddle
            // Check if the top of the ball is higher than bottom of paddle, and bottom of ball is lower than top of paddle
            if(this.ballRect.x + this.ballRect.width >= this.rightPaddle.x && this.ballRect.x <= this.rightPaddle.x + this.rightPaddle.width &&
                    this.ballRect.y >= this.rightPaddle.y && this.ballRect.y <= this.rightPaddle.y + this.rightPaddle.height)
            {
                // Use the calculated angle to get the new velocities
                double theta = calculateNewVeloctiyAngle(this.rightPaddle);
                double newVx = Math.abs((Math.cos(theta)) * Constants.BALL_SPEED);
                double newVy = (-Math.sin(theta)) * Constants.BALL_SPEED;

                // Set the velocities and flip the sign for the x
                double oldSign = Math.signum(vx);
                this.vx = newVx * (-1.0 * oldSign);
                this.vy = newVy;
            }
            // Check if ball has passed the right paddle
            else if(this.ballRect.x + this.ballRect.width > this.rightPaddle.x + this.rightPaddle.width) {
                System.out.println("Player gained a point");
            }
        }
        // ball moving left
        else if(vx < 0.0)
        {
            // Check if ball has collided with the right edge of the left paddle
            // Check if ball has not passed the left paddle
            // Check if the top of the ball is higher than bottom of paddle, and bottom of ball is lower than top of paddle
            if(this.ballRect.x <= this.leftPaddle.x + this.leftPaddle.width && this.ballRect.x + this.ballRect.width >= this.leftPaddle.x &&
                    this.ballRect.y >= this.leftPaddle.y && this.ballRect.y  <= this.leftPaddle.y + this.leftPaddle.height)
            {
                double theta = calculateNewVeloctiyAngle(this.leftPaddle);

                // Use the calculated angle to get the new velocities
                double newVx = Math.abs((Math.cos(theta)) * Constants.BALL_SPEED);
                double newVy = (-Math.sin(theta)) * Constants.BALL_SPEED;

                // Set the velocities and flip the sign for the x
                double oldSign = Math.signum(vx);
                this.vx = newVx * (-1.0 * oldSign);
                this.vy = newVy;

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

    public double calculateNewVeloctiyAngle(Rect paddle) {
        // Calculate where in which half of the paddle the ball hit
        // A positive value means it hit the top half, negative means it hit the bottom half
        double relativeIntersectY = (paddle.y + (paddle.height / 2.0)) - (ballRect.y + (ballRect.height / 2.0));

        // Normalize the intersection
        double normalizedIntersectY = relativeIntersectY / (paddle.height / 2.0);

        // Calculate a return angle
        double theta = normalizedIntersectY * Constants.MAX_ANGLE;

        return Math.toRadians(theta);
    }

}
