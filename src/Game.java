import Entity.EntityA;
import Entity.EntityB;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.List;


public class Game extends Canvas implements Runnable {

    public static final int WIDTH = 320;
    public static final int HEIGHT = WIDTH / 12 * 9;
    public static final int SCALE = 2;
    private final String TITLE = "2D Space Game";

    private boolean running = false;
    private Thread thread;

    private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
    private BufferedImage spriteSheet = null;
    private BufferedImage background = null;

    private boolean isShooting = false;

    private int enemyCount = 3;
    private int enemyKilled = 0;

    private Player p;
    private Controller c;
    private Textures tex;
    private Menu menu;

    public List<EntityA> ea;
    public List<EntityB> eb;

    private STATE state = STATE.MENU;


    private void init() {
        requestFocus();
        BufferedImageLoader loader = new BufferedImageLoader();

        spriteSheet = loader.loadImage("sprite_sheet.png");
        background = loader.loadImage("background.jpg");

        tex = new Textures(this);
        p = new Player(200, 200, tex);
        c = new Controller(tex, this);
        menu = new Menu();

        ea = c.getEa();
        eb = c.getEb();

        this.addKeyListener(new KeyInput(this));
        this.addMouseListener(new MouseInput(this));

        c.createEnemy(enemyCount);
    }


    private synchronized void start() {
        if (running)
            return;

        running = true;
        thread = new Thread(this);
        thread.start();
    }

    private synchronized void stop() {
        if (!running)
            return;

        running = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.exit(1);

    }

    public void run() {
        init();
        long lastTime = System.nanoTime();
        final double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        int updates = 0;
        int frames = 0;
        long timer = System.currentTimeMillis();
        while (running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            if (delta >= 1) {
                tick();
                updates++;
                delta--;
            }
            render();
            frames++;

            if (System.currentTimeMillis() - timer > 100) {
                timer += 100;
                System.out.println(updates + "Ticks, Fps " + frames);
                updates = 0;
                frames = 0;
            }

        }
        stop();
    }

    private void tick() {
        if (state == STATE.GAME) {
            p.tick();
            c.tick();
        }
        if (enemyKilled >= enemyCount) {
            enemyCount += 2;
            enemyKilled = 0;
            c.createEnemy(enemyCount);
        }

    }

    private void render() {
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) {
            createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();

        g.drawImage(background, 0, 0, null);

        if (state == STATE.GAME) {
            p.render(g);
            c.render(g);
        } else if (state == STATE.MENU) {
            menu.render(g);
        }
        g.dispose();
        bs.show();

    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (state == STATE.GAME) {
            if (key == KeyEvent.VK_RIGHT) {
                p.setVelX(5);
            } else if (key == KeyEvent.VK_LEFT) {
                p.setVelX(-5);
            } else if (key == KeyEvent.VK_DOWN) {
                p.setVelY(5);
            } else if (key == KeyEvent.VK_UP) {
                p.setVelY(-5);
            } else if (key == KeyEvent.VK_SPACE && !isShooting) {
                c.addEntity(new Bullet(p.getX(), p.getY(), tex, this));
                isShooting = true;
            }
        }


    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_RIGHT) {
            p.setVelX(0);
        } else if (key == KeyEvent.VK_LEFT) {
            p.setVelX(0);
        } else if (key == KeyEvent.VK_DOWN) {
            p.setVelY(0);
        } else if (key == KeyEvent.VK_UP) {
            p.setVelY(0);
        } else if (key == KeyEvent.VK_SPACE) {
            isShooting = false;
        }
    }

    public static void main(String args[]) {
        Game game = new Game();

        game.setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
        game.setMaximumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
        game.setMinimumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));

        JFrame frame = new JFrame((game.TITLE));
        frame.add(game);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        game.start();
    }

    public BufferedImage getSpriteSheet() {
        return spriteSheet;
    }

    public int getEnemyCount() {
        return enemyCount;
    }

    public void setEnemyCount(int enemyCount) {
        this.enemyCount = enemyCount;
    }

    public int getEnemyKilled() {
        return enemyKilled;
    }

    public void setEnemyKilled(int enemyKilled) {
        this.enemyKilled = enemyKilled;
    }

    public STATE getState() {
        return state;
    }

    public void setState(STATE state) {
        this.state = state;
    }

}



