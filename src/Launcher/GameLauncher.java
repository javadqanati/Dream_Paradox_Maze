package Launcher;

import javax.swing.*;

public class GameLauncher {
    public static void main(String[] args) {
        startGame();
    }

    public static void startGame() {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Maze Paradox Game");
        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);
        window.pack();
        window.setVisible(true);
        window.setLocationRelativeTo(null);
        gamePanel.setUpGame();
        gamePanel.getLoop().start();
    }
}
