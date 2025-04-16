//package Game.GameEntities;
//
//import Game.GameStates.GameState;
//import Game.levels.LevelOne;
//import Input.InputAction;
//import Input.InputHandler;
//import Launcher.GamePanel;
//import Launcher.GamePanelConfig;
//import Launcher.GameWindow;
//import UI.Button;
//import UI.PauseMenu;
//import org.jetbrains.annotations.Contract;
//import org.jetbrains.annotations.NotNull;
//
//import javax.swing.*;
//import java.awt.*;
//
//public class PlayingScreen extends JPanel {
//    private final int screenHeight;
//    private final GamePanel gamePanel;
//    private final Button pauseButton;
//    private final Button startButton;
//    private final LevelOne currentLevel;
//    private GamePanelConfig config;
//    private final GameWindow gameWindow;
//    private InputHandler input;
//
//    public PlayingScreen(GameWindow gameWindow, GamePanelConfig config, GamePanel gamePanel,
//                         int screenWidth, int screenHeight, int tileSize, InputHandler inputHandler) {
//        this.screenHeight = screenHeight;
//        this.config=config;
//        this.input=inputHandler;
//        this.gameWindow=gameWindow;
//        this.gamePanel=gamePanel;
//        int uiHeight = screenHeight / 6;
//        int cols = screenWidth / tileSize;
//        int rows = (screenHeight - uiHeight) / tileSize;
//        this.setLayout(null);
//        currentLevel = new LevelOne(cols, rows, tileSize, gamePanel, input);
//        currentLevel.load();
//        MazeEntityManager entityManager = currentLevel.getMazeEntityManager();
//
//        pauseButton = createPauseButton();
//        startButton = createStartButton();
//        this.add(pauseButton.getButton());
//        this.add(startButton.getButton());
//
//        pauseButton.setInputAction(new InputAction("pause", e -> togglePause()));
//        startButton.setInputAction(new InputAction("start", e -> startGame()));
//    }
//
//    @Contract(" -> new")
//    public @NotNull Button createPauseButton() {
//        return new Button("Pause", 100, 40, 10, screenHeight - 50,
//                new InputAction("pause", e -> togglePause()));
//    }
//
//    @Contract(" -> new")
//    public @NotNull Button createStartButton() {
//        return new Button("Start", 100, 40, 120, screenHeight - 50,
//                new InputAction("start", e -> startGame()));
//    }
//
//    private void togglePause() {
//        System.out.println("pause clicked");
//        gamePanel.setGameState(GameState.PAUSED);
//        gameWindow.showScreen("Paused");
//    }
//
//    private void startGame() {
//        // start logic
//    }
//
//    public Button getPauseButton() {
//        return pauseButton;
//    }
//
//    public Button getStartButton() {
//        return startButton;
//    }
//}
