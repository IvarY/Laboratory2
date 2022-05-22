import acm.graphics.GCompound;
import acm.graphics.GOval;

public class Ball extends GCompound {
    private double height, width;
    private GOval oval;
    private double dx, dy;
    private double speed;

    public Ball(double height, double width, double dx, double dy, double speed) {
        this.height = height;
        this.width = width;
        this.dx = dx;
        this.dy = dy;
        this.speed = speed;
        oval = new GOval(width, height);
        oval.setFilled(true);
        add(oval, 0, 0);
    }

    public double getDx() {
        return dx;
    }

    public void setDx(double dx) {
        this.dx = dx;
    }

    public double getDy() {
        return dy;
    }

    public void setDy(double dy) {
        this.dy = dy;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }
}
