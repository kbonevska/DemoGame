import Entity.EntityA;

import java.awt.*;

public class Bullet extends GameObject implements EntityA {


    private Textures tex;
    private Game game;

    public Bullet(int x, int y, Textures tex, Game game) {
        super(x, y);
        this.tex = tex;
        this.game = game;
    }

    public void tick() {
        y -= 10;


    }


    public void render(Graphics g) {
        g.drawImage(tex.missle, x, y, null);
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, 32, 32);
    }

    public int getX() {
        return x;
    }


    public int getY() {
        return y;
    }
}
