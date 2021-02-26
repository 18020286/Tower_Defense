package Container.Field;

import Container.Enemy.Enemy;
import Container.Enemy.SmallerEnemy;
import Container.Enemy.TankerEnemy;
import Container.Main;
import Container.Menu.Audio;
import Container.Menu.HighScore;
import Container.Menu.Scenes;
import Container.Player;
import Container.SettingStartGame.SettingItem;
import Container.Tower.Bullet;
import Container.Tower.Tower;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GameField extends GameStage{
    public static boolean startGame = false;
    public static boolean startLevel = false;
    public static boolean creatEnemy = false;
    public static GraphicsContext gc;
    public static List<Point> unfeasiblePlacement = new ArrayList<>();
    public static Image[][] images = new Image[11][18];
    public static List<Image> decoration = new ArrayList<>();
    public static int level ;

    public GameField(){
        saveImage();
        Main.scene = Scenes.menuGame();
        Main.stage.setScene(Main.scene);
        level = 0 ;
        Player.lives = 5 ;
        Player.cash = 100 ;
//        Enemy.enemies.addAll(SmallerEnemy.listSoldiers());
        //enemies.addAll(TankerEnemy.listTanks());
    }

    public static void unfeasiblePoints() {
        List<String> tao = new ArrayList<>(Arrays.asList("023", "025", "003", "047", "299", "048", "001", "027", "002", "004", "026", "046", "218", "244", "265", "240", "242"));
        for (int i = 0; i < Map1.MAP_SPRITES.length; i++) {
            for (int j = 0; j < Map1.MAP_SPRITES[i].length; j++) {
                if (tao.contains(Map1.MAP_SPRITES[i][j])) {
                    unfeasiblePlacement.add(new Point(64 * j, 64 * i));
                }
            }
        }
        unfeasiblePlacement.add(new Point( 15 * 64,  10 * 64));
        unfeasiblePlacement.add(new Point(16 *64,  10 * 64));
        unfeasiblePlacement.add(new Point( 5 * 64 , 9 * 64));
        unfeasiblePlacement.add(new Point(6 * 64 , 9 * 64));
        unfeasiblePlacement.add(new Point(11 * 64 , 0));
        unfeasiblePlacement.add(new Point(12 * 64 , 0));
        unfeasiblePlacement.add(new Point(12 * 64 , 64));
        unfeasiblePlacement.add(new Point(12 * 64 , 32));
    }

    public void saveImage(){
        for (int i = 0; i < Map1.MAP_SPRITES.length; i++) {
            for (int j = 0; j < Map1.MAP_SPRITES[i].length; j++) {
                images[i][j] = new Image("file:src/AssetsKit_2/PNG/Default size/towerDefense_tile" + Map1.MAP_SPRITES[i][j] + ".png");
            }
        }
        decoration.add(new Image("file:src/AssetsKit_2/PNG/Default size/towerDefense_tile137.png"));
        decoration.add(new Image("file:src/AssetsKit_2/PNG/Default size/towerDefense_tile136.png"));
        decoration.add(new Image("file:src/AssetsKit_2/PNG/Default size/towerDefense_tile134.png"));
        decoration.add(new Image("file:src/AssetsKit_2/PNG/Default size/towerDefense_tile131.png"));
        decoration.add(new Image("file:src/AssetsKit_2/PNG/Default size/towerDefense_tile130.png"));

    }

    public static double distance(double x1, double y1, double x2, double y2) {
        return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
    }

    public static void drawMap(GraphicsContext gc) {
        for (int i = 0; i < images.length; i++) {
            for (int j = 0; j < images[i].length; j++) {
                gc.drawImage(images[i][j], j * 64, i * 64);
            }
        }

        gc.drawImage(decoration.get(0) , 80 + 14 * 64,  10 * 64);
        gc.drawImage(decoration.get(1) , 128 + 14 *64, 30 + 9 * 64 );
        gc.drawImage(decoration.get(2) , 4 * 64  + 25, 8 * 64 + 25);
        gc.drawImage(decoration.get(2) , 6 * 64 , 9 * 64 );
        gc.drawImage(decoration.get(3) , 11 * 64 , 0);
        gc.drawImage(decoration.get(4) , 12 * 64 , 32 );


    }

    public void render(GraphicsContext gc) {
        if(startGame) {
            drawMap(gc);
            updateGF();
            Enemy.enemies.forEach(enemy -> enemy.render(gc));
            Bullet.bullets.forEach(bullet -> bullet.render(gc));
            Tower.towers.forEach(tower -> tower.render(gc));
        }
    }
    public void update() throws IOException {
        if(startLevel) {
            if(Player.lives == 0) {
                reset();
                Main.scene = Scenes.gameOver();
                Main.stage.setScene(Main.scene);
            }
            Tower.towers.forEach(tower -> {
                tower.checkTarget();
                tower.findTarget(Enemy.enemies);
                tower.setAngle();
            });


            checkCollisions();

//            Enemy.enemies.forEach(enemy -> {
//                enemy.update();
//                if(enemy.outSizePlayZone()) {
//                    Enemy.enemies.remove(enemy);
//                    Player.lives -= 1;
//                }
//            });

            //update enemy
            for (int index = 0; index < Enemy.enemies.size(); index++) {
                Enemy.enemies.get(index).update();
                if(Enemy.enemies.get(index).outSizePlayZone()) {
                    Enemy.enemies.remove(index--);
                    Player.lives -= 1;
                }
            }

            Tower.towers.forEach(Tower::update);
//            Bullet.bullets.forEach(bullet -> {
//                bullet.update();
//                if(bullet.outSizePlayZone()) Bullet.bullets.remove(bullet);
//            });
            for (int index = 0; index < Bullet.bullets.size(); index++) {
                Bullet.bullets.get(index).update();
                if(Bullet.bullets.get(index).outSizePlayZone()) Bullet.bullets.remove(index--);
            }
        }
    }
    public void checkCollisions() {
//        for( Tower tower: Tower.towers) {
        for (int j = 0; j < Bullet.bullets.size(); j++) {
            for (int index = 0; index < Enemy.enemies.size(); index++) {
                if(Bullet.bullets.get(j).collidesWith(Enemy.enemies.get(index))) {
                    Enemy.enemies.get(index).getDamagedBy(Bullet.bullets.get(j));
                    Bullet.bullets.remove(j--);
                    if( !Enemy.enemies.get(index).isAlive()) {
                        Player.cash += Enemy.enemies.get(index).reward;
                        Player.score += Enemy.enemies.get(index).reward;
                        Enemy.enemies.remove(index--);
                    }
                    break;
                }
            }
        }
    }

    public void updateGF() {
        Scenes.gameFactors = new SettingItem(GameField.level, Player.lives, Player.cash);
        Scenes.gameFactors.setTranslateX(0);
        Scenes.gameFactors.setTranslateY(11 * 64);
        Scenes.root.getChildren().remove(Scenes.index);
        Scenes.root.getChildren().add(Scenes.gameFactors);
        Scenes.index = Scenes.root.getChildren().size() - 1;
    }
    public static void backToMainMenu(){
        reset();
        Audio.music.stop();
        Main.scene = Scenes.menuGame();
        Main.stage.setScene(Main.scene);
    }
    public static void reset(){
        startGame = false;
        startLevel = false;
        creatEnemy = false;
        level = 0;
        Player.lives = 5;
        Player.cash = 50;
        Tower.towers.forEach(tower -> {
            tower.setTarget(null);
        });
        unfeasiblePlacement.removeAll(unfeasiblePlacement);
        Tower.towers.removeAll(Tower.towers);
        Enemy.enemies.removeAll(Enemy.enemies);
        Bullet.bullets.removeAll(Bullet.bullets);

    }
}
