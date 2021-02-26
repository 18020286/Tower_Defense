package Container.Tower;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class MissileLauncherTowerLv2 extends Tower {
    public MissileLauncherTowerLv2() {
        this.shootingRange = 5*64;
        this.img = new Image("file:src/AssetsKit_2/PNG/Default size/towerDefense_tile182.png");
        this.gunImg = new Image("file:src/AssetsKit_2/PNG/Default size/towerDefense_tile206.png");
    }
    public MissileLauncherTowerLv2(double x , double y){
        cost=40;
        rateOfFire = 5 ;
        this.selling = 50 ;
        this.shootingRange = 5*64;
        this.damage = 35;
        this.x = x;
        this.y = y;
        this.img = new Image("file:src/AssetsKit_2/PNG/Default size/towerDefense_tile182.png");
        this.gunImg = new Image("file:src/AssetsKit_2/PNG/Default size/towerDefense_tile206.png");
        shot();
    }
    public Bullet creatBullet(double x , double y , double rotation){
        Bullet bullet = super.creatBullet(x , y , rotation);
        bullet.damage=damage;
        bullet.img = new Image("file:src/AssetsKit_2/PNG/Default size/towerDefense_tile252.png");
        return bullet;
    }

    public Image infoImage(){
        return new Image("file:src/AssetsKit_2/MissleLaucher2.png");
    }

    public void render(GraphicsContext gc){
        super.render(gc);
    }

    public void update() {
        super.update();

    }
    public void upgrade(){

    }
}
