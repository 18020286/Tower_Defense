package Container.Menu ;

import Container.Field.GameField;
import Container.Field.Parameter;
import Container.Main;
import javafx.animation.TranslateTransition;
import javafx.scene.Parent;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.util.Duration;

import static Container.Menu.HighScore.ReadHighScore;


public class GameMenu extends Parent implements Parameter {
    public GameMenu() {

        VBox menu0 = new VBox(10);
        VBox menu1 = new VBox(10);

        menu0.setTranslateX(550);
        menu0.setTranslateY(300);

        menu1.setTranslateX(550);
        menu1.setTranslateY(300);

        final int offset = screenWidth+1;

        menu1.setTranslateX(offset);

        MenuButton btnStart = new MenuButton("START GAME");
        btnStart.setOnMouseClicked(event -> {
            Audio.music.stop();
            Main.scene = Scenes.gameTowerDF();
            Main.stage.setScene(Main.scene);
            GameField.startGame = true;
        });


        MenuButton btnOptions = new MenuButton("OPTIONS");
        btnOptions.setOnMouseClicked(event -> {
            getChildren().add(menu1);

            TranslateTransition tt = new TranslateTransition(Duration.seconds(0.25), menu0);
            tt.setToX(menu0.getTranslateX() - offset);

            TranslateTransition tt1 = new TranslateTransition(Duration.seconds(0.5), menu1);
            tt1.setToX(menu0.getTranslateX());

            tt.play();
            tt1.play();

            tt.setOnFinished(e -> {
                getChildren().remove(menu0);
            });
        });



        MenuButton btnExit = new MenuButton("QUIT");
        btnExit.setOnMouseClicked(e -> {
            System.exit(0);
        });

        MenuButton btnBack = new MenuButton("BACK");
        btnBack.setOnMouseClicked(event -> {
            getChildren().add(menu0);

            TranslateTransition tt = new TranslateTransition(Duration.seconds(0.25), menu1);
            tt.setToX(menu1.getTranslateX() + offset);

            TranslateTransition tt1 = new TranslateTransition(Duration.seconds(0.5), menu0);
            tt1.setToX(menu1.getTranslateX());

            tt.play();
            tt1.play();

            tt.setOnFinished(e -> {
                getChildren().remove(menu1);
            });
        });
        String a;
        if(Audio.volume!=0)
            a="SOUND: ON";
        else
            a="SOUND: OFF";
        MenuButton btnSound = new MenuButton(a);

        btnSound.setOnMouseClicked(mouseEvent -> {
            if(btnSound.getText().getText().equals("SOUND: OFF")){
                btnSound.setText("SOUND: ON");
                Audio.setVolume(0.2);
            } else {
                btnSound.setText("SOUND: OFF");
                Audio.setVolume(0);
            }
            System.out.println(btnSound.getText().getText());

        });

        menu1.getChildren().addAll(btnBack, btnSound);

        MenuButton btnHighScore = new MenuButton("High Score");
        btnHighScore.setOnMouseClicked(mouseEvent -> {
            Image imageHighScore = new Image("file:resource/image/highScoreImage.png",screenWidth, screenHeight,false,false);
            ImageView ivHighScore = new ImageView(imageHighScore);

            HighScore.setHighScores();
            StringBuilder name = new StringBuilder();
            StringBuilder score = new StringBuilder();
            for (int i = 0; i < HighScore.getHighScores().size(); i++) {
                if(HighScore.getHighScores().get(i).getScore()!=0){
                    name.append(HighScore.getHighScores().get(i).getName()+"\n");
                    score.append(HighScore.getHighScores().get(i).getScore()+"\n");
                }
            }
            System.out.println(name);
            System.out.println(score);
            Text text = new Text(screenWidth/2-100, screenHeight/3, name.toString());
            text.setLineSpacing(20);
            text.setStyle("-fx-font: 20 bell ; -fx-fill: #0026e7; -fx-text-alignment:  Left");

            Text text1 = new Text(screenWidth/2+50, screenHeight/3, score.toString());
            text1.setLineSpacing(20);
            text1.setStyle("-fx-font: 20 bell ; -fx-fill: #0026e7; -fx-text-alignment:  Right");

            btnBack.setLayoutX(screenWidth/2-125);
            btnBack.setLayoutY(500);
            btnBack.setOnMouseClicked(mouseEvent1 -> {
                getChildren().removeAll(ivHighScore,text, text1, btnBack);
            });
            getChildren().addAll(ivHighScore,text, text1, btnBack);
        });



        menu0.getChildren().addAll(btnStart, btnOptions, btnHighScore, btnExit);


        getChildren().add(menu0);
    }
}