package Entity;

import java.awt.*;

public interface EntityA {
    public void tick();
    public void render(Graphics g);
    public Rectangle getBounds();

    public int getX();
    public int getY();
}
