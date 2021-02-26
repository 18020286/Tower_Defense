package Container.SettingStartGame;

import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.image.ImageView;


public class SettingItem extends StackPane {
    private Text text;
    Image bImg;
    Image gImg;

    SettingItem(double r) {
        Circle circle = new Circle(r);
        circle.setFill(Color.TRANSPARENT);
        setCursor(Cursor.HAND);
        getChildren().add(circle);
    }

    SettingItem(String base, String gun) {
        setCursor(Cursor.HAND);
        Rectangle rc = new Rectangle(64, 64);
        rc.setOpacity(0.6);
        rc.setStroke(Color.DARKSEAGREEN);
        rc.setFill(Color.WHITESMOKE);
        bImg = new Image(base);
        gImg = new Image(gun);
        ImageView baseImg = new ImageView(bImg);
        ImageView gunImg = new ImageView(gImg);
        getChildren().addAll(rc, baseImg, gunImg);
    }
    SettingItem(String item){
        setCursor(Cursor.HAND);
        text = new Text(item);
        text.setFont(Font.font (Font.getFontNames().get(20), FontWeight.SEMI_BOLD,  20));
        text.setFill(Color.BLACK);

        Rectangle rc = new Rectangle(144 , 40);
        rc.setOpacity(0.5);
        rc.setStroke(Color.BLACK);
        rc.setFill(Color.GRAY);

        setAlignment(Pos.CENTER);
        getChildren().addAll(rc , text);

    }

    SettingItem(String item, int number){
        setCursor(Cursor.HAND);
        text = new Text(item);
        text.setFont(Font.font (Font.getFontNames().get(20), FontWeight.SEMI_BOLD,  20));
        text.setFill(Color.BLACK);

        Rectangle rc = new Rectangle(95 , 35);
        rc.setOpacity(0.5);
        rc.setStroke(Color.BLACK);
        rc.setFill(Color.GRAY);

        setAlignment(Pos.CENTER);
        getChildren().addAll(rc , text);
    }

    public SettingItem(int level , int lives , int cash){
        Rectangle rc = new Rectangle(64 * 18 , 64);
        rc.setFill(Color.GREY);
        rc.setOpacity(0.2);
        String str = "Level:\t" + level + "/50\t\t\t\tLives:\t" + lives + "\t\t\tCash$:\t" + cash ;
        text = new Text(str);
        text.setFont(Font.font (Font.getFontNames().get(20), FontWeight.SEMI_BOLD, FontPosture.ITALIC,  30));
        text.setFill(Color.BLACK);
        setAlignment(Pos.CENTER);
        getChildren().addAll(rc , text);
    }
}
