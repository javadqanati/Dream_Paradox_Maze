//package UI;
//
//import Data.SaveFile;
//import Data.SaveManager;
//import Game.GameEntities.PlayingScreen;
//import Game.GameStates.GameState;
//import Launcher.*;
//import org.jetbrains.annotations.NotNull;
//
//import javax.swing.*;
//import java.awt.*;
//import java.time.format.DateTimeFormatter;
//
//public class LoadMenu extends Menu implements Navigable, Loadable {
//    private final GameWindow gameWindow;
//    private final GamePanelConfig config;
//    private final SaveManager saveManager = new SaveManager();
//    private JLabel infoLabel;
//
//    public LoadMenu(GamePanelConfig gamePanelConfig, GamePanel gamePanel, GameWindow gameWindow) {
//        super(gamePanel);
//        this.gameWindow = gameWindow;
//        this.config = gamePanelConfig;
//
//    }
//
//    @Override
//    public void renderMenu(@NotNull JPanel menuPanel) {
//        menuPanel.setLayout(null);
//        int tileSize = config.getOriginalTileSize() * config.getScale();
//        int screenWidth = config.getMaxScreenCol() * tileSize;
//        int screenHeight = config.getMaxScreenRow() * tileSize;
//        int centerX = screenWidth / 2 - 100;
//        int startY = screenHeight / 4;
//
//        JButton newGameButton = new JButton("New Game");
//        newGameButton.setBounds(centerX, startY, 200, 40);
//        newGameButton.addActionListener(e -> newGame());
//        menuPanel.add(newGameButton);
//
//        JButton loadButton = new JButton("Load");
//        loadButton.setBounds(centerX, startY + 70, 200, 40);
//        loadButton.addActionListener(e -> loadGame());
//        menuPanel.add(loadButton);
//
//        JButton deleteButton = new JButton("Delete Save");
//        deleteButton.setBounds(centerX, startY + 140, 200, 40);
//        deleteButton.addActionListener(e -> deleteGame());
//        menuPanel.add(deleteButton);
//
//        JButton backButton = new JButton("Back");
//        backButton.setBounds(centerX, startY + 280, 200, 40);
//        backButton.addActionListener(e -> goBack());
//        menuPanel.add(backButton);
//
//        infoLabel = new JLabel("", SwingConstants.CENTER);
//        infoLabel.setBounds(centerX - 100, startY + 200, 400, 60);
//        infoLabel.setForeground(Color.WHITE);
//        infoLabel.setFont(new Font("Arial", Font.PLAIN, 14));
//        menuPanel.add(infoLabel);
//
//        updateInfoText();
//    }
//
//    private void updateInfoText() {
//        SaveFile lastSave = saveManager.getCurrentSave();
//
//        if (lastSave != null) {
//            String saveName = lastSave.getSaveName();
//            String time = lastSave.getTimeStamp().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
//            infoLabel.setText("<html>Last Save: <b>" + saveName + "</b><br>Time: " + time + "</html>");
//        } else {
//            infoLabel.setText("No saved game found. Start a new game!");
//        }
//    }
//
//    public void newGame() {
//        gameWindow.showScreen("Play");
//    }
//
//    public void loadGame() {
//        System.out.println("Loading the last saved game...");
//    }
//
//    public void deleteGame() {
//        System.out.println("Deleting the saved game...");
//    }
//
//    @Override
//    public void goBack() {
//        gameWindow.showScreen("MainMenu");
//    }
//}
