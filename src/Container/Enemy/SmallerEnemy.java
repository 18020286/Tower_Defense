package Container.Enemy;

import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class SmallerEnemy extends Enemy{
    public SmallerEnemy() {
        this.reward = 5;
        this.enemyHP = 100;
        this.health = 100;
        this.speed = 2 ;
        this.direction = Direction.DOWN;
    }

    public void render(GraphicsContext gc) {
        SnapshotParameters params = new SnapshotParameters();
        params.setFill(Color.TRANSPARENT);

        ImageView iv = new ImageView(img);
        iv.setRotate(this.direction.getDegree());
        Image base = iv.snapshot(params, null);

        gc.drawImage(base, x, y);

        super.render(gc);
    }
    public static SmallerEnemy creatSoldier(int x , int type){
        SmallerEnemy smallerEnemy = new SmallerEnemy();
        smallerEnemy.i = 14;
        smallerEnemy.j = 0 ;
        smallerEnemy.x = smallerEnemy.i * 64 + 32;
        smallerEnemy.y = (smallerEnemy.j - x )* 64 ;
        switch(type){
            case 0 :
                smallerEnemy.img = new Image("file:src/AssetsKit_2/PNG/Default size/towerDefense_tile245.png");
                break;
            case 1:
                smallerEnemy.img = new Image("file:src/AssetsKit_2/PNG/Default size/towerDefense_tile246.png");
                break;
            case 2 :
                smallerEnemy.img = new Image("file:src/AssetsKit_2/PNG/Default size/towerDefense_tile247.png");
                break;
            case 3:
                smallerEnemy.img = new Image("file:src/AssetsKit_2/PNG/Default size/towerDefense_tile248.png");
                break;
        }

        return smallerEnemy;
    }
    public static ArrayList<SmallerEnemy> listSoldiers(){
        ArrayList<SmallerEnemy> soldiers = new ArrayList<>();
        for(int i = 0 ; i < 4 ; i++){
            soldiers.add(creatSoldier(i + 1 , i % 4));
        }
        return soldiers;
    }
}
