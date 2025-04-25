package Input;

import Launcher.GamePanel;
import UI.Screen;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class InputHandler implements KeyListener {
    private final KeyboardInputHandler keyboardInputHandler;
    private final PlayerInputHandler playerInputHandler;
    private final GamePanel gp;
    private final Screen screen;

    public InputHandler(GamePanel gp, Screen screen,
                        KeyboardInputHandler keyboardInputHandler, PlayerInputHandler playerInputHandler) {
        this.screen = screen;
        this.gp = gp;
        this.keyboardInputHandler = keyboardInputHandler;
        this.playerInputHandler = playerInputHandler;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (gp.getGameStateManager().isMenu()) {
            new MenuInputHandler(gp, screen, keyboardInputHandler);
        } else if (gp.getGameStateManager().isSettings()) {
            new SettingScreenInputHandler(keyboardInputHandler, screen, gp);
        } else if (gp.getGameStateManager().isPaused()) {
            new PauseInputHandler(keyboardInputHandler, screen, gp);
        } else if (gp.getGameStateManager().isInventory()) {
            new InventoryInputHandler(keyboardInputHandler, screen, gp);
        } else if (gp.getGameStateManager().isMarket()) {
            new MarketInputHandler(keyboardInputHandler, screen, gp);
        } else if (gp.getGameStateManager().isPlaying()){
            new PlayScreenInputHandler(keyboardInputHandler, screen, gp);
        }
        keyboardInputHandler.handleKeyPress(e.getKeyCode());
    }

    @Override
    public void keyReleased(KeyEvent e) {
        playerInputHandler.unbindKeys(e);
    }
}
