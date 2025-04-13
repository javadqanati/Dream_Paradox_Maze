package Launcher;

import UI.*;

import java.util.ArrayList;
import java.util.List;

public class GameInitializer {
    private final GameWindow gameWindow;
    private final GamePanelConfig config;
    private final GamePanel gamePanel;
    private final List<Loadable> resources = new ArrayList<>();

    public GameInitializer(GameWindow gameWindow, GamePanelConfig config, GamePanel gamePanel) {
        this.gameWindow = gameWindow;
        this.config = config;
        this.gamePanel = gamePanel;
    }

    public void loadResources() {
        initializeMainMenu();
        initializeSettingMenu();
        initializeSaveLoadMenu();
        initializePlayer();
        initializeEnemies();
        loadSettings();
    }

    private void initializeMainMenu() {
        MainMenu mainMenu = new MainMenu(config, gamePanel, gameWindow);
        mainMenu.load();
        gameWindow.getCardPanel().add(mainMenu.getMenuPanel(), "MainMenu");
        gameWindow.showScreen("MainMenu");
        resources.add(mainMenu);
    }

    private void initializeSettingMenu(){
         SettingMenu settingMenu = new SettingMenu(config, gamePanel, gameWindow);
         settingMenu.load();
         gameWindow.getCardPanel().add(settingMenu.getMenuPanel(), "SettingMenu");
         resources.add(settingMenu);
    }

    private void initializeSaveLoadMenu(){
        LoadMenu loadMenu =new LoadMenu(config, gamePanel, gameWindow);
        loadMenu.load();
        gameWindow.getCardPanel().add(loadMenu.getMenuPanel(), "SaveLoadMenu");
        resources.add(loadMenu);
    }

    private void initializePlayer() {
        // Add player-related loadables if needed
    }

    private void initializeEnemies() {
        // Add enemy-related loadables if needed
    }

    private void loadSettings() {
        // Load settings-related loadables if needed
    }
}
