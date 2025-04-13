package UI;

import Launcher.GamePanel;
import Launcher.GamePanelConfig;
import Launcher.GameWindow;
import Launcher.Loadable;

import javax.swing.*;

public class MainMenu extends Menu implements Loadable {
    private final GamePanelConfig config;
    private final GameWindow gameWindow;

    public MainMenu(GamePanelConfig config, GamePanel gamePanel, GameWindow gameWindow) {
        super(gamePanel);
        this.config = config;
        this.gameWindow = gameWindow;
    }

    @Override
    protected void renderMenu(JPanel panel) {
        int tileSize = config.getOriginalTileSize() * config.getScale();
        int screenWidth = config.getMaxScreenCol() * tileSize;
        int screenHeight = config.getMaxScreenRow() * tileSize;

        int buttonWidth = 200;
        int buttonHeight = 50;
        int centerX = (screenWidth - buttonWidth) / 2;
        int startY = screenHeight / 3;

        Button startButton = new Button("Start Game", buttonWidth, buttonHeight,
                centerX, startY, e -> startGame());
        Button settingsButton = new Button("Settings", buttonWidth, buttonHeight,
                centerX, startY + 70, e -> openSettings());
        Button exitButton = new Button("Exit", buttonWidth, buttonHeight,
                centerX, startY + 140, e -> exitGame());

        addButton(startButton);
        addButton(settingsButton);
        addButton(exitButton);
    }

    private void startGame() {
        gameWindow.showScreen("SaveLoadMenu");
    }

    private void openSettings() {
        gameWindow.showScreen("SettingMenu");
    }

    private void exitGame() {
        System.exit(0);
    }
}
