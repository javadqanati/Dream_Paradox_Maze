package UI;

import Game.GameEntities.GameScreen;
import Launcher.GamePanel;

import javax.swing.*;
import java.awt.*;

public class PauseMenu extends Menu implements GameScreen {

    public PauseMenu(GamePanel gamePanel) {
        super(gamePanel);
    }

    public void quitGame(){}
    public void resumeGame(){}

    @Override
    public void renderMenu(JPanel menuPanel) {

    }

    @Override
    public void update() {

    }

    @Override
    public void render(Graphics g) {
        g.setColor(new Color(0, 0, 0, 150));
        g.fillRect(0, 0, 800, 600);
        g.setColor(Color.WHITE);
        g.drawString("Game Paused", 350, 300);
    }
}
