package UI;

import Game.GameEntities.PowerUp;
import Launcher.GamePanel;

import javax.swing.*;
import java.util.List;

public class MarketMenu extends Menu implements Navigable{
    private List<PowerUp> availablePowerUps;
    private PowerUp selectedPowerUp;

    public MarketMenu(GamePanel gamePanel) {
        super(gamePanel);
    }

    public void displayAvailablePowerUps(){}
    public void purchase(){}
    @Override
    public void goBack(){

    }

    @Override
    public void renderMenu(JPanel menuPanel) {

    }
}
