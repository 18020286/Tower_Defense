package Container.Menu;

import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;

public class mButton extends Button {
    public mButton(String s) {
        super(s);
        setPrefSize(150, 30);
        setCursor(Cursor.HAND);

        setStyle("-fx-font: 15 bell ; -fx-text-fill: #ff0011; -fx-text-alignment:  center; -fx-background-color: #b2fcff");
        DropShadow drop = new DropShadow();

        setOnMouseEntered(event -> setEffect(drop));
        setOnMouseExited(event -> setEffect(null));
    }
}
