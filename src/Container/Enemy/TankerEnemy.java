package Container.Enemy;

import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class TankerEnemy extends Enemy{
    protected Image gunImg;

    public TankerEnemy() {
        this.reward = 10;
        this.enemyHP = 500;
        this.health = 500;
        this.speed = 2;
        this.direction = Direction.DOWN;
    }

    public void render(GraphicsContext gc) {
        SnapshotParameters params = new SnapshotParameters();
        params.setFill(Color.TRANSPARENT);

        ImageView iv = new ImageView(img);
        iv.setRotate(this.direction.getDegree());
        Image base = iv.snapshot(params, null);

        ImageView iv2 = new ImageView(gunImg);
        iv2.setRotate(this.direction.getDegree());
        Image gun = iv2.snapshot(params, null);

        gc.drawImage(base, x, y);
        gc.drawImage(gun, x, y);
        super.render(gc);

    }
    public static TankerEnemy createTank(int x , int type) {
        TankerEnemy tanker = new TankerEnemy();
        tanker.i = 14;
        tanker.j = - 6 - x;
        tanker.x = tanker.i * 64 + 32;
        tanker.y = tanker.j * 64;

        switch (type){
            case 0 :
                tanker.img = new Image("file:src/AssetsKit_2/PNG/Default size/towerDefense_tile268.png");
                tanker.gunImg = new Image("file:src/AssetsKit_2/PNG/Default size/towerDefense_tile291.png");
                break;
            case 1:
                tanker.img = new Image("file:src/AssetsKit_2/PNG/Default size/towerDefense_tile269.png");
                tanker.gunImg = new Image("file:src/AssetsKit_2/PNG/Default size/towerDefense_tile292.png");
        }

        return tanker;
    }
    public static ArrayList<TankerEnemy> listTanks(){
        ArrayList<TankerEnemy> tanks = new ArrayList<>();
        for(int i = 0 ; i < 2 ; i++){
            tanks.add(createTank(i, i % 2));
        }
        return tanks;
    }


}
