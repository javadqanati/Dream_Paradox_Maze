package Launcher;

public class GameStarter {
    public static void startGame() {
        GamePanel gamePanel = new GamePanel();
        WindowManager windowManager = new WindowManager(gamePanel);
        gamePanel.setWindowManager(windowManager);
        gamePanel.setUpGame();
        gamePanel.getLoop().start();
    }
}
