package Container.SettingStartGame;

import Container.Enemy.*;
import Container.Field.GameField;
import Container.Field.Point;
import Container.Main;
import Container.Menu.Audio;
import Container.Menu.Scenes;
import Container.Player;
import Container.Tower.MachineGunTower;
import Container.Tower.MissileLauncherTower;
import Container.Tower.NormalTower;
import Container.Tower.Tower;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;


public class SettingStart extends Parent {
    private double orgSceneX, orgSceneY;
    private double orgTranslateX, orgTranslateY;
    public static int count = 0 ;
    public static int cost ;
    public static int selectTower ;
    public static int index ;
    public static SettingItem settingItem;
    private static Pane pane;
    public SettingStart(){
        GameField.unfeasiblePoints();

        VBox r1 = new VBox(10);
        VBox r2 = new VBox(10);
        r1.setTranslateX(18 * 64 + 30);
        r1.setTranslateY(30);
        r2.setTranslateX(18 * 64 + 30 + 70 + 10);
        r2.setTranslateY(30);
        SettingItem cannon1 = new SettingItem("file:src/AssetsKit_2/PNG/Default size/towerDefense_tile180.png" , "file:src/AssetsKit_2/PNG/Default size/towerDefense_tile249.png");
        SettingItem cannon4 = new SettingItem("file:src/AssetsKit_2/PNG/Default size/towerDefense_tile182.png" , "file:src/AssetsKit_2/PNG/Default size/towerDefense_tile203.png");
        //SettingItem cannon3 = new SettingItem("file:src/AssetsKit_2/PNG/Default size/towerDefense_tile181.png" , "file:src/AssetsKit_2/PNG/Default size/towerDefense_tile205.png");
        SettingItem cannon2 = new SettingItem("file:src/AssetsKit_2/PNG/Default size/towerDefense_tile181.png" , "file:src/AssetsKit_2/PNG/Default size/towerDefense_tile206.png");
        //SettingItem cannon5 = new SettingItem("file:src/AssetsKit_2/PNG/Default size/towerDefense_tile181.png" , "file:src/AssetsKit_2/PNG/Default size/towerDefense_tile206.png");
        //SettingItem cannon6 = new SettingItem("file:src/AssetsKit_2/PNG/Default size/towerDefense_tile182.png" , "file:src/AssetsKit_2/PNG/Default size/towerDefense_tile203.png");
        r1.getChildren().addAll(cannon1 ,cannon2);
        r2.getChildren().addAll(cannon4 );

        //System.out.println(cannon1.localToScene(cannon1.getBoundsInLocal()));
        Line separate= new Line(18* 64 , 0 , 18 * 64 , 12 *64);
        separate.setStrokeWidth(3);
        separate.setStroke(Color.BLACK);
        Rectangle rc = new Rectangle(18 * 64 , 0 , 250 , 12 * 64);
        rc.setFill(Color.GREY);
        rc.setOpacity(0.2);


        SettingItem nextLevel = new SettingItem("Next Level");
        nextLevel.setTranslateX(18 * 64 + 30);
        nextLevel.setTranslateY(270);

        SettingItem mainMenu = new SettingItem("Main Menu");
        mainMenu.setTranslateX(18 * 64 + 30);
        mainMenu.setTranslateY(718 );


        setOnMouseClicked(event -> Main.scene.setOnMouseClicked(mouseEvent ->{
            if(mouseEvent.getButton() == MouseButton.PRIMARY) {
                boolean check = false;
                int x = (int) (mouseEvent.getSceneX() / 64) * 64;
                int y = (int) (mouseEvent.getSceneY() / 64) * 64;

                for (Tower tower : Tower.towers) {
                    if (tower.x == x && tower.y == y){
                        tower.setRenderFireRange(true);
                        getChildren().remove(pane);
                        pane = new Pane();
                        HBox hBox = new HBox(10);
                        hBox.setTranslateY(365);
                        hBox.setTranslateX(15);
                        ImageView test = new ImageView(tower.infoImage());
                        test.setFitHeight(420);
                        test.setFitWidth(245);
                        SettingItem upgrade = new SettingItem("Upgrade", 0);
                        SettingItem sell = new SettingItem("Sell" , 0);

                        hBox.getChildren().addAll(upgrade , sell);
                        pane.getChildren().addAll(test , hBox);
                        pane.setTranslateX(18 * 64 + 3);
                        pane.setTranslateY(344);

                        getChildren().addAll(pane);
                        upgrade.setOnMouseClicked(event1 ->{
                            if(canAfford(tower.getTowerUpgrade())) {
                                tower.upgrade();
                                Player.cash -= tower.getTowerUpgrade().cost;
                            }
                        });
                        sell.setOnMouseClicked(event2 ->{
                            Player.cash += tower.getSelling();
                            tower.setTarget(null);
                            Tower.towers.remove(tower);
                            for (int i = 0; i < GameField.unfeasiblePlacement.size(); i++) {
                                Point a = GameField.unfeasiblePlacement.get(i);
                                if (a.x == x && a.y == y) GameField.unfeasiblePlacement.remove(a);
                            }
                            getChildren().remove(pane);


                        });
                        check = true;
                        break;
                    }else {
                        tower.setRenderFireRange(false);
                    }
                }
                if (!check && mouseEvent.getSceneX() < 18 * 64) {
                    getChildren().remove(pane);

                }
            }
        }));



        getChildren().addAll(rc ,separate , r1 , r2 , nextLevel , mainMenu);

        nextLevel.setOnMouseClicked(e ->{
            Enemy.enemies.removeAll(Enemy.enemies);
            Tower.towers.forEach(tower -> {
                tower.setTarget(null);
            });
            Enemy.enemies.addAll(SmallerEnemy.listSoldiers());
            count = 0;
            GameField.level += 1;
            GameField.startLevel = true;
            Timeline timeline = new Timeline();
            KeyFrame keyFrame = new KeyFrame(Duration.seconds(6), event -> {
                count ++ ;
                if(count == 2){
                    Enemy.enemies.add(BossEnemy.creatSBoss());
                }
                else {
                    Enemy.enemies.addAll(SmallerEnemy.listSoldiers());
                    Enemy.enemies.addAll(TankerEnemy.listTanks());
                    Enemy.enemies.addAll(PlaneEnemy.listPlanes());
                }
            });

            timeline.getKeyFrames().add(keyFrame);
            timeline.setCycleCount(2);
            timeline.play();
        });

        mainMenu.setOnMouseClicked(e ->{
            GameField.backToMainMenu();
        });
        cannon1.setOnMousePressed(e ->{
            selectTower = 1;
        });
        cannon2.setOnMousePressed(e ->{
            selectTower = 2;
        });
//        cannon3.setOnMousePressed(e ->{
//            selectTower = 3;
//            cost = 50;
//        });
        cannon4.setOnMousePressed(e ->{
            selectTower = 4;
        });
//        cannon5.setOnMousePressed(e ->{
//            selectTower = 5;
//            cost = 50;
//        });
//        cannon6.setOnMousePressed(e ->{
//            selectTower = 6;
//            cost = 50;
//        });
        handleSettingItem(cannon1, new NormalTower());
        handleSettingItem(cannon2, new MissileLauncherTower());
//        handleSettingItem(cannon3);
        handleSettingItem(cannon4, new MachineGunTower());
//        handleSettingItem(cannon5);
//        handleSettingItem(cannon6);

        towerFeature(cannon1 , "file:src/AssetsKit_2/cannon.jpg");
        towerFeature(cannon4 , "file:src/AssetsKit_2/MachineGun2.png");
        towerFeature(cannon2 , "file:src/AssetsKit_2/missleLauncher.jpg");

    }

    public void towerFeature(SettingItem cannon , String img){
        cannon.setOnMouseEntered(e ->{
            ImageView imageView = new ImageView(img);
            imageView.setFitHeight(420);
            imageView.setFitWidth(245);
            imageView.setTranslateX(18 * 64 + 3);
            imageView.setTranslateY(344);
            getChildren().add(imageView);
            index = getChildren().size() -1;
        });
        cannon.setOnMouseExited(e ->{
            getChildren().remove(index);
        });
    }

    private void handleSettingItem(SettingItem cannon, Tower tower){
        cannon.setOnMouseClicked(e -> {
            orgSceneX = e.getSceneX();
            orgSceneY = e.getSceneY();
            orgTranslateX = ((SettingItem) (e.getSource())).getTranslateX();
            orgTranslateY = ((SettingItem) (e.getSource())).getTranslateY();

            Circle circle = new Circle(tower.getShootingRange());
            circle.setFill(Color.web("Red", 0.1));
            setCursor(Cursor.HAND);
            getChildren().add(circle);

            SettingItem cnClone = new SettingItem(tower.getShootingRange());
            cnClone.getChildren().addAll(new ImageView(cannon.bImg), new ImageView(cannon.gImg));
            cnClone.getChildren().add(circle);
            cnClone.setLayoutX(cannon.localToScene(cannon.getBoundsInLocal()).getMinX() - tower.getShootingRange()+32);
            cnClone.setLayoutY(cannon.localToScene(cannon.getBoundsInLocal()).getMinY() - tower.getShootingRange()+32);

            Main.scene.setOnMouseMoved(mouseEvent -> {
                cnClone.setOnMouseMoved(mouseEvent1 -> {

                    double offsetX = mouseEvent1.getSceneX() - orgSceneX;
                    double offsetY = mouseEvent1.getSceneY() - orgSceneY;
                    double newTranslateX = orgTranslateX + offsetX;
                    double newTranslateY = orgTranslateY + offsetY;
                    ((SettingItem) (mouseEvent1.getSource())).setTranslateX(newTranslateX);
                    ((SettingItem) (mouseEvent1.getSource())).setTranslateY(newTranslateY);
                    if (!canPlace(new Point((int)(mouseEvent.getSceneX() / 64) * 64, (int)(mouseEvent.getSceneY() / 64) * 64))){
                        circle.setFill(Color.web("Red",0.3));
                    }  else {
                        circle.setFill(Color.web("Blue",0.1));
                    }
                });
                cnClone.setOnMousePressed(e1 -> {
                    if (e1.getButton() == MouseButton.PRIMARY) {
                        int x = (int) (mouseEvent.getSceneX() / 64) * 64;
                        int y = (int) (mouseEvent.getSceneY() / 64) * 64;
                        if (canPlace(new Point(x, y))) {
                            Tower tower1;
                            switch (selectTower) {
                                case 1:
                                    tower1=new NormalTower(x,y);
                                    break;
                                case 2:
                                    tower1=new MissileLauncherTower(x,y);
                                    break;
                                case 4:
                                    tower1=new MachineGunTower(x,y);
                                    break;
                                default:
                                    throw new IllegalStateException("Unexpected value: " + selectTower);
                            }
                            if (canAfford(tower1)) {
                                Scenes.root.getChildren().add(Audio.PlayBuildTowerAudio());
                                Tower.towers.add(tower1);
                                Player.cash -= tower1.cost;
                                GameField.unfeasiblePlacement.add(new Point(x, y));
                            } else {
                                Rectangle rc =new Rectangle(64*4,64);
                                rc.setY(64*11);
                                rc.setX(64*13);
                                rc.setFill(Color.web("red", 0.5));
                                rc.getFill();

                                Timeline timeline = new Timeline();
                                KeyFrame keyFrame = new KeyFrame(Duration.millis(700),new KeyValue(rc.fillProperty(), Color.web("red",0)));
                                timeline.getKeyFrames().add(keyFrame);
                                timeline.setCycleCount(2);
                                timeline.play();

                                getChildren().add(rc);
                            }
                        }
                    }
                    if (e1.getButton() == MouseButton.SECONDARY) {
                        getChildren().remove(cnClone);
                    }
                });

            });
            getChildren().add(cnClone);
        });
    }

    public boolean canPlace(Point point){
        if(GameField.unfeasiblePlacement.contains(point)) return false;
        if(point.x>17*64 || point.y>64*11)return false;
        return true;
    }
    public boolean canAfford(Tower tower){
        return Player.cash >= tower.cost;
    }
}
