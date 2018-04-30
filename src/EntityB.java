package Entity;

import java.awt.*;

public interface EntityB {
    public void tick();
    public void render(Graphics g);
    public Rectangle getBounds();


    public int getX();
    public int getY();
}
