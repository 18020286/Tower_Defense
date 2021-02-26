package Container.Enemy;

import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

public class BossEnemy extends Enemy{

    public BossEnemy() {
        this.reward = 50;
        this.enemyHP = 6000;
        this.health = 6000;
        this.speed = 0.7 ;
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
    public static BossEnemy creatSBoss(){
        BossEnemy bossEnemy = new BossEnemy();
        bossEnemy.i = 14;
        bossEnemy.j = - 3 ;
        bossEnemy.x = bossEnemy.i * 64 + 32;
        bossEnemy.y = (bossEnemy.j) * 64  ;
        bossEnemy.img = new Image("file:src/AssetsKit_2/boss1.png");
        return bossEnemy;
    }

}
