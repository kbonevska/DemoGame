import java.awt.*;

public class Menu {

    public Rectangle playButton = new Rectangle(Game.WIDTH / 2 + 100, 150, 150, 50);
    public Rectangle helpButton = new Rectangle(Game.WIDTH / 2 + 100, 250, 150, 50);
    public Rectangle quitButton = new Rectangle(Game.WIDTH / 2 + 100, 350, 150, 50);


    public void render(Graphics g) {
        Graphics2D g2d =(Graphics2D) g;

        Font fnt0 = new Font("arial", Font.BOLD, 50);
        g.setFont(fnt0);
        g.setColor(Color.white);
        g.drawString("STAR WARS", Game.WIDTH / 2, 100);

        Font fnt1 = new Font("arial",Font.BOLD,30);
        g.drawString("PLAY",playButton.x+8,playButton.y+42);
        g.drawString("HELP",helpButton.x+8,helpButton.y+42);
        g.drawString("QUIT",quitButton.x+8,quitButton.y+42);
        g2d.draw(playButton);
        g2d.draw(helpButton);
        g2d.draw(quitButton);

    }

}
