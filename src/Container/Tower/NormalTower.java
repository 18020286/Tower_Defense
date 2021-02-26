package Container.Tower;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.util.Duration;

public class NormalTower extends Tower {

    public NormalTower() {
        this.shootingRange = 3*64;
        this.img = new Image("file:src/AssetsKit_2/PNG/Default size/towerDefense_tile181.png");
        this.gunImg = new Image("file:src/AssetsKit_2/PNG/Default size/towerDefense_tile249.png");

    }
    public NormalTower(double x , double y){
        towerUpgrade = new NormalTowerLV2();
        rateOfFire = 7;
        cost=20;
        this.selling = 15;
        this.shootingRange = 2*64+32;
        this.damage = 10;
        this.x = x;
        this.y = y;
        this.img = new Image("file:src/AssetsKit_2/PNG/Default size/towerDefense_tile181.png");
        this.gunImg = new Image("file:src/AssetsKit_2/PNG/Default size/towerDefense_tile249.png");
        shot();
    }

    public Bullet creatBullet(double x , double y , double rotation){
        Bullet bullet = super.creatBullet(x , y , rotation+5);
        bullet.damage=damage;
        bullet.img = new Image("file:src/AssetsKit_2/PNG/Default size/towerDefense_tile272.png");
        return bullet;
    }

    public void render(GraphicsContext gc){
        super.render(gc);
    }

    public void update() {
        super.update();

    }
    public void upgrade(){
        Tower.towers.add(new NormalTowerLV2(x,y));
        target = null;
        Tower.towers.remove(this);
    }

    @Override
    public Image infoImage() {
        return new Image("file:src/AssetsKit_2/cannon.jpg");
    }
}