package Container.Tower;

import Container.GameObj;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.transform.Rotate;

import java.util.ArrayList;
import java.util.List;

public class Bullet extends GameObj {
    public static List<Bullet> bullets = new ArrayList<>();
    private int rateOfFire;
    int damage;
    public Bullet(){}
    public Bullet(double x , double y , double rotation) {
        this.rateOfFire = 5;
        this.img = new Image("file:src/AssetsKit_2/PNG/Default size/towerDefense_tile272.png");
        this.x = x;
        this.y = y;
        this.rotation = rotation;
    }

    public int getDamage() {
        return damage;
    }

    public void render(GraphicsContext gc){
        //gc.drawImage(this.img , this.x , this.y);
        gc.save();
        Rotate r = new Rotate(this.rotation, this.x + 32, this.y + 32);
        gc.setTransform(r.getMxx(), r.getMyx(), r.getMxy(), r.getMyy(), r.getTx(), r.getTy());
        //iv.setRotate(this.rotation);
        gc.drawImage(this.img, x, y);
        gc.restore();
    }
    public void update(){
        this.x += rateOfFire * Math.cos(Math.toRadians(rotation - 90));
        this.y += rateOfFire * Math.sin(Math.toRadians(rotation - 90));
    }

}
