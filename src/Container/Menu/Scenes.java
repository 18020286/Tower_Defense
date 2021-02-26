package Container.Menu ;

import Container.Field.GameField;
import Container.Field.Parameter;
import Container.Main;
import Container.Player;
import Container.SettingStartGame.SettingItem;
import Container.SettingStartGame.SettingStart;
import javafx.event.Event;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.media.MediaView;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicBoolean;

public class Scenes implements Parameter {
    public static Group root ;
    public static SettingItem gameFactors ;
    public static int index ;
    public static Scene menuGame(){
        Pane root = new Pane();
        root.setPrefSize(screenWidth, screenHeight);

        Image img = new Image("file:src/AssetsKit_2/gameStart.jpg");

        ImageView imgView = new ImageView(img);
        imgView.setFitWidth(screenWidth);
        imgView.setFitHeight(screenHeight);

        GameMenu gameMenu = new GameMenu();
        MediaView audio = Audio.PlayBackgroundMusic();
        root.getChildren().addAll(imgView,audio, gameMenu);
        Scene scene = new Scene(root);
        return scene;

    }

    public static Scene gameTowerDF(){
        Canvas canvas = new Canvas(64 * 18 + 250, 64 * 12);
        Main.gc = canvas.getGraphicsContext2D();
        root = new Group();
        MediaView music = Audio.PlayBackgroundMusic();

        gameFactors = new SettingItem(GameField.level , Player.lives , Player.cash);
        gameFactors.setTranslateX(0);
        gameFactors.setTranslateY(11 * 64);
        SettingStart settingStart = new SettingStart();
        root.getChildren().addAll(canvas , settingStart ,music, gameFactors );
        index = root.getChildren().size() - 1;
        Scene scene = new Scene(root);
        return scene ;
    }
    public static Scene gameOver() throws IOException {
        Pane root = new Pane();
        root.setPrefSize(1366 , 768);
        Image image = new Image("file:resource/image/highScoreImage.png",screenWidth, screenHeight,false,false);
        ImageView imageView = new ImageView(image);

        VBox vBox = new VBox(10);
        vBox.setPrefWidth(600);
        vBox.setTranslateX(screenWidth/2-300);
        vBox.setTranslateY(screenHeight/3);
        vBox.setAlignment(Pos.CENTER);

        Text text = new Text( "Game Over\nYour Score: " + Player.score);
        text.setStyle("-fx-font: 30 bell ; -fx-fill: #0026e7; -fx-text-alignment:  center");
        vBox.getChildren().add(text);

        HighScore.setHighScores();
        if(Player.score>HighScore.getHighScores().get(4).getScore()){
            TextField textField = new TextField("Enter your name");
            textField.setAlignment(Pos.CENTER);
            textField.setMinWidth(180);

            Button button = new Button("Enter");
            button.setCursor(Cursor.HAND);
            button.setOnMouseClicked(mouseEvent -> {
                try {
                    HighScore.add(textField.getText(), Player.score);
                    vBox.getChildren().remove(button);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            vBox.getChildren().addAll(textField,button);
        }

        mButton quit = new mButton("QUIT");
        quit.setOnMouseClicked(mouseEvent -> {
            System.exit(0);
        });

        mButton playAgain = new mButton("PLAY AGAIN");
        playAgain.setOnMouseClicked(mouseEvent -> {
            Audio.music.stop();
            Main.scene = Scenes.gameTowerDF();
            Main.stage.setScene(Main.scene);
            GameField.startGame = true;
        });

        mButton mainMenu= new mButton("MAIN MENU");
        mainMenu.setOnMouseClicked(mouseEvent -> {
            GameField.backToMainMenu();
        });

        HBox hBox =new HBox(20);
        hBox.setAlignment(Pos.CENTER);
        hBox.getChildren().addAll(mainMenu, playAgain, quit);
        vBox.getChildren().add(hBox);
        root.getChildren().addAll(imageView, vBox);
        Scene scene = new Scene(root);

        return scene;
    }

}
