import Entity.EntityB;

import java.awt.*;
import java.util.Random;

public class Enemy extends GameObject implements EntityB {

    Random r = new Random();
    private Game game;
    private Controller c;
    private int speed = r.nextInt(3) + 1;

    private Textures tex;

    public Enemy(int x, int y, Textures tex, Controller c, Game game) {
        super(x, y);
        this.tex = tex;
        this.c = c;
        this.game = game;
    }

    public void tick() {
        y += speed;

        if (y > (Game.HEIGHT * Game.SCALE)) {
            speed = r.nextInt(3) + 1;
            x = r.nextInt(640);
            y = -10;
            x = r.nextInt(Game.WIDTH * Game.SCALE);
        }
        if (Phisics.Collision(this, game.ea)) {
            c.removeEntity(this);
            game.setEnemyKilled(game.getEnemyKilled()+1);
        }
    }

    public void render(Graphics g) {
        g.drawImage(tex.enemy, x, y, null);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, 32, 32);
    }
}
