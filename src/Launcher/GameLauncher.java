package Launcher;

import UI.LoadMenu;
import UI.MainMenu;
import UI.PauseMenu;

public class GameLauncher {
    public static void main(String[] args) {
        StartGame();
    }

    public static void StartGame(){
        initializeGame();
        GameLoop gameLoop=new GameLoop();
        gameLoop.getGameThread().start();
    }

    public static void initializeGame(){
        GamePanelConfig gamePanelConfig=new GamePanelConfig();
        GameWindow gameWindow=new GameWindow();
        GamePanel gamePanel=new GamePanel(gamePanelConfig, gameWindow);
        gameWindow.createWindow(gamePanel);
        gameWindow.registerScreen("Game", gamePanel);
        gameWindow.registerScreen("MainMenu", new MainMenu(gamePanelConfig, gamePanel,
                gameWindow).getMenuPanel());
        gameWindow.registerScreen("PauseMenu", new PauseMenu(gamePanel).getMenuPanel());
        gameWindow.registerScreen("LoadMenu", new LoadMenu(gamePanelConfig, gamePanel,
                gameWindow).getMenuPanel());
        gameWindow.getWindow().pack();
        GameInitializer gameInitializer=new GameInitializer(gameWindow, gamePanelConfig, gamePanel);
        gameInitializer.loadResources();
    }
}
