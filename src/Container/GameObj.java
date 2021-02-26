package Container;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public abstract class GameObj{
    public int i ;
    public int j;
    public double x ;
    public double y;
    public Image img;
    public double rotation ;
    public boolean removable = false;

    public abstract void render(GraphicsContext gc);
    public abstract void update();
    public double getCenterX() {
        return x + 64 * 0.5;
    }

    public double getCenterY() {
        return y + 64 * 0.5;
    }
    public boolean isRemovable() {
        return removable;
    }

    public void setRemovable(boolean removable) {
        this.removable = removable;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public boolean collidesWith(GameObj other) {

        return ( other.x + 50 >= x && other.y + 50 >= y && other.x <= x + 50 && other.y <= y + 50);

    }
    public boolean outSizePlayZone(){
        return x > 17 * 64 || y > 10 * 64 || (x == 64 + 32 && y < - 64);
    }
}
