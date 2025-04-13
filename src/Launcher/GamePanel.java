package Launcher;

import GameStates.*;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class GamePanel extends JPanel {
    private final Map<GameState, GameStateHandler> stateHandlers = new HashMap<>();
    private GameState currentState = GameState.PLAYING;
    private static int screenWidth;
    private static int screenHeight;
    private final GameWindow gameWindow;

    public GamePanel(@NotNull GamePanelConfig config, @NotNull GameWindow gameWindow) {
        this.gameWindow = gameWindow;

        int originalTileSize = config.getOriginalTileSize();
        int scale = config.getScale();
        int maxScreenCol = config.getMaxScreenCol();
        int maxScreenRow = config.getMaxScreenRow();

        int tileSize = originalTileSize * scale;
        screenWidth = tileSize * maxScreenCol;
        screenHeight = tileSize * maxScreenRow;

        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);

        // Register only in-game states (menus are in GameWindow now)
        stateHandlers.put(GameState.PLAYING, new PlayingState());
        stateHandlers.put(GameState.PAUSED, new PausedState());
    }

    /**
     * Called by GameLoop
     */
    public void updateGame() {
        GameStateHandler handler = stateHandlers.get(currentState);
        if (handler != null) handler.update();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        GameStateHandler handler = stateHandlers.get(currentState);
        if (handler != null) handler.render(g);
    }

    public void setGameState(GameState newState) {
        this.currentState = newState;
    }

    public GameState getGameState() {
        return currentState;
    }

    public int getScreenWidth() {
        return screenWidth;
    }

    public int getScreenHeight() {
        return screenHeight;
    }

    public GameWindow getGameWindow() {
        return gameWindow;
    }
}
