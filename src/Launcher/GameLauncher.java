package Launcher;


public class GameLauncher {
    public static void main(String[] args) {
        startGame();
    }

    public static void startGame() {
        GamePanel gamePanel = new GamePanel();
        WindowManager windowManager = new WindowManager(gamePanel);
        gamePanel.setWindowManager(windowManager);
        gamePanel.setUpGame();
        gamePanel.getLoop().start();
    }
}