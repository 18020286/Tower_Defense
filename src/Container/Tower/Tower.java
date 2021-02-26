package Container.Tower;

import Container.Enemy.Enemy;
import Container.Enemy.PlaneEnemy;
import Container.Field.GameField;
import Container.GameObj;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.SnapshotParameters;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;

public abstract class Tower extends GameObj {
    public static List<Tower> towers = new ArrayList<>();
    protected int damage;
    protected Bullet b = new Bullet();
    protected Image gunImg;
    protected Enemy target;
    protected double shootingRange;
    protected int selling ;
    protected boolean renderFireRange ;
    protected int rateOfFire ;
    public static GraphicsContext gc;
    public int cost;
    protected Tower towerUpgrade;
    private boolean withinFiringRange = false;

    public void render(GraphicsContext gc) {

        gc.drawImage(img, x, y);
        gc.save();
        gc.translate(x+32, y+32);
        gc.rotate(rotation);
        gc.translate(-x-32,-y-32);
        gc.drawImage(gunImg, x, y);
        gc.restore();
        if(isRenderFireRange()) {
            gc.setStroke(Color.RED);
            gc.strokeOval(x - shootingRange + 32, y - shootingRange + 32, shootingRange * 2, shootingRange * 2);
        }
    }
    public int getDamage() {
        return damage;
    }
    public void setDamage(int damage) {
        this.damage = damage;
    }
    public void setAngle() {
        withinFiringRange = false;
        if( target != null)
        {
            double angle = Math.toDegrees(Math.atan((target.y - y) / (target.x - x)));
            if (target.x >= x) {
                rotation = 90 + angle;
            } else {
                rotation = angle + 270;
            }
            withinFiringRange=true;
        }else
            return;
    }
    public void checkTarget() {
        if( target == null) {
            return;
        }

        if( !target.isAlive() ) {
            setTarget( null);
            return;
        }

        if(!isInRange(target.getX(), target.getY())) {
            setTarget(null);
        }
    }
    boolean isInRange(double enemyX, double enemyY){
        if(GameField.distance(x, y, enemyX, enemyY)<=shootingRange)
            return true;
        else
            return false;
    }
    public void findTarget( List<Enemy> targetList) {
        if( getTarget() != null) {
            return;
        }
        for (int k = 0; k < Enemy.enemies.size() ; k++) {
            if(Enemy.enemies.get(k) instanceof PlaneEnemy && (this instanceof NormalTower || this instanceof MachineGunTower)){
                continue;
            }
            if (!Enemy.enemies.get(k).isAlive())
                continue;
            if(isInRange(Enemy.enemies.get(k).x, Enemy.enemies.get(k).y)){
                setTarget(Enemy.enemies.get(k));
                return;
            }
        }
    }

    public Enemy getTarget() {
        return target;
    }

    public void setTarget(Enemy target) {
        this.target = target;
    }

    public boolean hitsTarget(Enemy enemy){
        return target == enemy && withinFiringRange;
    }

    public Bullet creatBullet(double x , double y , double rotation){
        Bullet bullet = new Bullet(x , y , rotation);
        Bullet.bullets.add(bullet);
        return bullet;
    }

    public void update(){
        setAngle();
    }

    public abstract void upgrade();

    public int getSelling() {
        return selling;
    }

    public abstract Image infoImage();

    public Tower getTowerUpgrade() {
        return towerUpgrade;
    }

    public double getShootingRange() {
        return shootingRange;
    }
    void shot(){
        Timeline timeline = new Timeline();
        KeyFrame keyFrame = new KeyFrame(Duration.millis(2000/rateOfFire), event -> {
            if(target!=null){
                Bullet.bullets.add(creatBullet(x, y, rotation));
            }
        });

        timeline.getKeyFrames().add(keyFrame);
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    public boolean isRenderFireRange() {
        return renderFireRange;
    }

    public void setRenderFireRange(boolean renderFireRange) {
        this.renderFireRange = renderFireRange;
    }
}

