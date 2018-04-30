import Entity.EntityA;
import Entity.EntityB;

import java.awt.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Controller {

    private List<EntityA> ea = new LinkedList<>();
    private List<EntityB> eb = new LinkedList<>();

    EntityA entA;
    EntityB entB;
    private Textures tex;
    Random r = new Random();
    private Game game;

    public Controller(Textures tex, Game game) {
        this.tex = tex;
        this.game = game;
    }

    public void createEnemy(int enemyCount) {
        for (int i = 0; i < enemyCount; i++) {
            addEntity(new Enemy(r.nextInt(640), -10, tex,this,game));
        }
    }

    public void tick() {
        //A Class
        for (int i = 0; i < ea.size(); i++) {
            entA = ea.get(i);

            entA.tick();
        }
        //B Class
        for (int i = 0; i < eb.size(); i++) {
            entB = eb.get(i);

            entB.tick();
        }
    }


    public void render(Graphics g) {
        for (int i = 0; i < ea.size(); i++) {
            entA = ea.get(i);

            entA.render(g);
        }
        for (int i = 0; i < eb.size(); i++) {
            entB = eb.get(i);

            entB.render(g);
        }
    }

    public void addEntity(EntityA block) {
        ea.add(block);
    }

    public void removeEntity(EntityA block) {
        ea.remove(block);
    }

    public void addEntity(EntityB block) {
        eb.add(block);
    }

    public void removeEntity(EntityB block) {
        eb.remove(block);
    }

    public List<EntityA> getEa() {
        return ea;
    }

    public List<EntityB> getEb() {
        return eb;
    }
}
