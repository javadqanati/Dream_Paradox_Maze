package UI;

import GameEntities.PowerUp;
import Launcher.GamePanel;

import javax.swing.*;
import java.util.List;

public class InventoryMenu extends Menu implements Navigable{
    private List<PowerUp> playerPowerUps;

    public InventoryMenu(GamePanel gamePanel) {
        super(gamePanel);
    }

    public void displayItems(){}
    public void useItems(){}

    @Override
    public void goBack(){

    }

    @Override
    public void renderMenu(JPanel menuPanel) {

    }
}
