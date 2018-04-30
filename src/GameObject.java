import java.awt.*;

public abstract class  GameObject {
    public int x, y;

    public GameObject(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Rectangle getBounds(int width, int height) {
        return new Rectangle(x,y,width,height);
    }
}
