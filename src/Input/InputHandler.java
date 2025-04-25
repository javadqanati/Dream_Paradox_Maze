package Input;

import Game.GameStates.GameStateManager;
import Launcher.GamePanel;
import UI.Screen;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Map;

public class InputHandler implements KeyListener {
    private final KeyboardInputHandler keyboardInputHandler;
    private final PlayerInputHandler playerInputHandler;
    private final Map<String, ScreenInputHandler> handlerMap;
    private final GameStateManager gsm;

    public InputHandler(GamePanel gp,
                        Screen screen,
                        GameStateManager gsm,
                        KeyboardInputHandler keyboardInputHandler,
                        PlayerInputHandler playerInputHandler) {
        this.gsm = gsm;
        this.keyboardInputHandler = keyboardInputHandler;
        this.playerInputHandler = playerInputHandler;

        handlerMap = new HashMap<>();
        handlerMap.put("PLAY",      new PlayScreenInputHandler(keyboardInputHandler, screen, gp));
        handlerMap.put("PAUSE",     new PauseInputHandler(keyboardInputHandler, screen, gp));
        handlerMap.put("INVENTORY", new InventoryInputHandler(keyboardInputHandler, screen, gp));
        handlerMap.put("MARKET",    new MarketInputHandler(keyboardInputHandler, screen, gp));
        handlerMap.put("SETTINGS",   new SettingScreenInputHandler(keyboardInputHandler, screen, gp));
        handlerMap.put("MENU",      new MenuInputHandler(keyboardInputHandler, screen, gp));
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // not used
    }

    @Override
    public void keyPressed(KeyEvent e) {
        String stateName = gsm.getStateName();
        ScreenInputHandler handler = handlerMap.get(stateName);

        if (handler != null) {
            handler.bindKeys();
        }

        keyboardInputHandler.handleKeyPress(e.getKeyCode());
    }

    @Override
    public void keyReleased(KeyEvent e) {
        playerInputHandler.unbindKeys(e);
    }
}
