package Container ;

import Container.Field.GameField;
import Container.Menu.Audio;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    public static Stage stage;
    public static Scene scene;
    public static GraphicsContext gc;

    public void start(Stage primaryStage) {
        stage = primaryStage;
        GameField gameField = new GameField();
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                gameField.render(gc);
                try {
                    gameField.update();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        stage.setTitle("Tower Defense");
        timer.start();
        stage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }

}










