import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseInput implements MouseListener {
    private Game game;

    public MouseInput(Game game) {
        this.game = game;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }


    public void mousePressed(MouseEvent e) {
        int mx = e.getX();
        int my = e.getY();

        if (mx >= Game.WIDTH / 2 + 120 && mx <= Game.WIDTH / 2 + 270) {
            if (my >= 150 && my <= 200) {
                //Pressed Play Button
                game.setState(STATE.GAME);
            }
            if (my >= 350 && my <= 400) {
                //Pressed Quit Button
                System.exit(1);
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
