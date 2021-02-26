package Container.Tower;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class MachineGunTower extends Tower{
    public MachineGunTower() {
        this.shootingRange = 2*64;
        this.img = new Image("file:src/AssetsKit_2/PNG/Default size/towerDefense_tile181.png");
        this.gunImg = new Image("file:src/AssetsKit_2/PNG/Default size/towerDefense_tile203.png");
    }
    public MachineGunTower(double x , double y){
        cost=35;
        rateOfFire = 7 ;
        towerUpgrade=new MachineGunTowerLv2();
        this.selling = 20;
        this.shootingRange = 2*64+32;
        this.damage = 13;
        this.x = x;
        this.y = y;
        this.img = new Image("file:src/AssetsKit_2/PNG/Default size/towerDefense_tile181.png");
        this.gunImg = new Image("file:src/AssetsKit_2/PNG/Default size/towerDefense_tile203.png");
        shot();
    }
    public Bullet creatBullet(double x , double y , double rotation){
        Bullet bullet = super.creatBullet(x , y , rotation);
        bullet.damage=damage;
        bullet.img = new Image("file:src/AssetsKit_2/PNG/Default size/towerDefense_tile251.png");
        return bullet;
    }
    public static Tower createNormalTower(double X , double Y) {
        Tower tower = new NormalTower();
        tower.x = X;
        tower.y = Y;
        tower.img = new Image("file:src/AssetsKit_2/PNG/Default size/towerDefense_tile181.png");
        tower.gunImg = new Image("file:src/AssetsKit_2/PNG/Default size/towerDefense_tile203.png");
        return tower;
    }
    public void render(GraphicsContext gc){
        super.render(gc);
    }

    public void update() {
        super.update();

    }
    public void upgrade(){
        Tower.towers.add(new MachineGunTowerLv2(x,y));
        target=null;
        Tower.towers.remove(this);
    }

    @Override
    public Image infoImage() {
        return new Image("file:src/AssetsKit_2/MachineGun2.png");
    }
}
