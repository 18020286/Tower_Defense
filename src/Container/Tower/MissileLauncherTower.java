package Container.Tower;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class MissileLauncherTower extends Tower {
    public MissileLauncherTower() {
        this.shootingRange = 4*64;
        this.img = new Image("file:src/AssetsKit_2/PNG/Default size/towerDefense_tile182.png");
        this.gunImg = new Image("file:src/AssetsKit_2/PNG/Default size/towerDefense_tile206.png");
    }
    public MissileLauncherTower(double x , double y){
        cost=50;
        rateOfFire = 4;
        towerUpgrade= new MissileLauncherTowerLv2();
        this.selling = 30 ;
        this.shootingRange = 4*64;
        this.damage = 25;
        this.x = x;
        this.y = y;
        this.img = new Image("file:src/AssetsKit_2/PNG/Default size/towerDefense_tile182.png");
        this.gunImg = new Image("file:src/AssetsKit_2/PNG/Default size/towerDefense_tile205.png");
        shot();
    }
    public Bullet creatBullet(double x , double y , double rotation){
        Bullet bullet = super.creatBullet(x , y , rotation+5);
        bullet.damage=damage;
        bullet.img = new Image("file:src/AssetsKit_2/PNG/Default size/towerDefense_tile251.png");
        return bullet;
    }
    public static Tower createNormalTower(double X , double Y) {
        Tower tower = new NormalTower();
        tower.x = X;
        tower.y = Y;
        tower.img = new Image("file:src/AssetsKit_2/PNG/Default size/towerDefense_tile182.png");
        tower.gunImg = new Image("file:src/AssetsKit_2/PNG/Default size/towerDefense_tile250.png");
        return tower;
    }
    public void render(GraphicsContext gc){
        super.render(gc);
    }

    public void update() {
        super.update();

    }
    public void upgrade(){
        Tower.towers.add(new MissileLauncherTowerLv2(x,y));
        target=null;
        Tower.towers.remove(this);
    }

    @Override
    public Image infoImage() {
        return new Image("file:src/AssetsKit_2/MissleLaucher.png");
    }
}
