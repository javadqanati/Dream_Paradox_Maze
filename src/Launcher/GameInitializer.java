//package Launcher;
//
//import Game.GameEntities.PlayingScreen;
//import Input.InputHandler;
//import UI.LoadMenu;
//import UI.MainMenu;
//import UI.PauseMenu;
//import UI.SettingMenu;
//import java.util.ArrayList;
//import java.util.List;
//
//public class GameInitializer {
//    private final GameWindow gameWindow;
//    private final GamePanel gamePanel;
//    private final List<Loadable> resources = new ArrayList<>();
//    private InputHandler inputHandler;
//
//    public GameInitializer(GameWindow gameWindow, GamePanel gamePanel) {
//        this.gameWindow = gameWindow;
//        this.gamePanel = gamePanel;
//        this.inputHandler = gamePanel.getInputHandler();
//    }
//
//    public void loadResources() {
//        initializeMainMenu();
//        initializeSettingMenu();
//        initializeSaveLoadMenu();
//        initializePlayScreen();
//        initializePauseScreen();
//        loadSettings();
//    }
//
//    private void initializeMainMenu() {
//        MainMenu mainMenu = new MainMenu(gamePanel, gameWindow);
//        mainMenu.load();
//        gameWindow.registerScreen("MainMenu", mainMenu.getMenuPanel());
//        resources.add(mainMenu);
//    }
//
//    private void initializeSettingMenu() {
//        SettingMenu settingMenu = new SettingMenu(gamePanel, gameWindow);
//        settingMenu.load();
//        gameWindow.registerScreen("SettingMenu", settingMenu.getMenuPanel());
//        resources.add(settingMenu);
//    }
//
//    private void initializeSaveLoadMenu() {
//        LoadMenu loadMenu = new LoadMenu(gamePanel, gameWindow);
//        loadMenu.load();
//        gameWindow.registerScreen("SaveLoadMenu", loadMenu.getMenuPanel());
//        resources.add(loadMenu);
//    }
//
//    public void initializePlayScreen() {
//        PlayingScreen playingScreen = new PlayingScreen(gameWindow, gamePanel,
//                gamePanel.getScreenWidth(),
//                gamePanel.getScreenHeight(),
//                config.getOriginalTileSize(),
//                gamePanel.getInputHandler()
//        );
//        gameWindow.getCardPanel().add(playingScreen, "Play");
//        gameWindow.registerScreen("Play", playingScreen);
//    }
//
//    public void initializePauseScreen(){
//        PauseMenu pauseMenu = new PauseMenu(gamePanel, config);
//        pauseMenu.renderMenu(pauseMenu.getMenuPanel());
//        pauseMenu.load();
//        gameWindow.getCardPanel().add(pauseMenu.getMenuPanel(), "Pause");
//        gameWindow.registerScreen("Pause", pauseMenu.getMenuPanel());
//    }
//
//    private void initializeEnemies() {
//    }
//
//    private void loadSettings() {
//        // Implement settings-related resource loading as needed.
//    }
//}
