package Input;

import Utils.GameStateManager;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class InputHandler implements KeyListener {
    private final KeyboardInputHandler keyboardInputHandler;
    private final PlayerInputHandler playerInputHandler;
    private final GameStateManager gsm;

    public InputHandler(GameStateManager gsm,
                        KeyboardInputHandler keyboard,
                        PlayerInputHandler playerHandler) {
        this.gsm = gsm;
        this.keyboardInputHandler = keyboard;
        this.playerInputHandler = playerHandler;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        String state = gsm.getStateName();
        ScreenInputHandler handler = InputHandlerRegistry.get(state);
        if (handler != null) handler.bindKeys();

        keyboardInputHandler.handleKeyPress(e.getKeyCode());
    }

    @Override public void keyReleased(KeyEvent e) {
        playerInputHandler.unbindKeys(e);
    }

    @Override public void keyTyped(KeyEvent e) {}
}