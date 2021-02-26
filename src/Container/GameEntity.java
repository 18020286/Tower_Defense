package Container;

import javafx.scene.canvas.GraphicsContext;

public interface GameEntity {

    void render(GraphicsContext gc);
    void update();
}
