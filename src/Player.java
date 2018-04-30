import Entity.EntityA;

import java.awt.*;

public class Player extends GameObject implements EntityA {


    private int velX = 0;
    private int velY = 0;

    private Textures tex;

    public Player(int x, int y, Textures tex) {
        super(x,y);
        this.tex = tex;
    }

    public void tick() {
        x += velX;
        y += velY;
        if (x <= 0)
            x = 0;
        if (x >= 610)
            x = 610;
        if (y <= 0)
            y = 0;
        if (y >= 435)
            y = 435;
    }

    public void render(Graphics g) {
        g.drawImage(tex.player, x, y, null);
    }

    public Rectangle getBounds() {
        return new Rectangle(x,y,32,32);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setVelX(int velX) {
        this.velX = velX;
    }

    public void setVelY(int velY) {
        this.velY = velY;
    }
}
