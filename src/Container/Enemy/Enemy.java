package Container.Enemy;

import Container.Field.GameField;
import Container.Field.Map1;
import Container.Field.Point;
import Container.GameObj;
import Container.Tower.Bullet;
import Container.Tower.Tower;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public abstract class Enemy extends GameObj {
    public static List<Enemy> enemies = new ArrayList<>();
    double distances =0;
    protected int enemyHP;
    protected double speed;
    protected Direction direction;
    protected int health;
    public int reward;
    protected int wayPointIndex = 0;
    public Point getNextWayPoint() {
        if (wayPointIndex < Map1.wayPoints.length - 1)
            return Map1.wayPoints[++wayPointIndex];
        return null;
    }
    public void calculateDirection() {
        if (wayPointIndex >= Map1.wayPoints.length) {
            return;
        }

        Point currentWP = Map1.wayPoints[wayPointIndex];
        if (GameField.distance(x, y, currentWP.x, currentWP.y) <= speed) {
            x = currentWP.x;
            y = currentWP.y;
            Point nextWayPoint = getNextWayPoint();
            if (nextWayPoint == null) return;
            double deltaX = nextWayPoint.x - x;
            double deltaY = nextWayPoint.y - y;
            if (deltaX > speed) direction = Direction.RIGHT;
            else if (deltaX < -speed) direction = Direction.LEFT;
            else if (deltaY > speed) direction = Direction.DOWN;
            else if (deltaY <= -speed) direction = Direction.UP;
        }

    }
    public void render(GraphicsContext gc){
        double scale = (double)health / enemyHP;
        gc.setFill(Color.RED);
        gc.fillRect(x + 12 , y +4, 40 ,6);
        gc.setFill(Color.rgb(27,219,6));
        gc.fillRect(x +12, y +4 , 40  * scale ,6);
    }
    public boolean isAlive() {
        return Double.compare(health, 0) > 0;
    }
    public void getDamagedBy(Bullet bullet) {
        health -= bullet.getDamage();
    }
    public void update(){
        calculateDirection();

        switch (direction) {
            case UP:
                y -= speed;
                break;
            case DOWN:
                y += speed;
                break;
            case LEFT:
                x -= speed;
                break;
            case RIGHT:
                x += speed;
                break;
        }
        distances+=speed;
        enemies.sort(Comparator.comparing(Enemy::getDistances).reversed());
    }

    public double getDistances() {
        return distances;
    }
}
enum Direction {
    LEFT(180), UP(270), RIGHT(0), DOWN(90);

    int degree;

    Direction(int i) {
        degree = i;
    }

    int getDegree() {
        return degree;
    }
}
