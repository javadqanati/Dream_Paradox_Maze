package Input;

import Game.GameStates.GameStateManager;
import Launcher.GamePanel;
import UI.*;

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
                        GameStateManager gsm,
                        KeyboardInputHandler keyboardInputHandler,
                        PlayerInputHandler playerInputHandler,
                        Map<String, Screen> screens) {
        this.gsm = gsm;
        this.keyboardInputHandler = keyboardInputHandler;
        this.playerInputHandler = playerInputHandler;

        handlerMap = new HashMap<>();
        handlerMap.put("PLAY",      new PlayScreenInputHandler(keyboardInputHandler, screens.get("PLAY"), gp));
        handlerMap.put("PAUSE",     new PauseInputHandler(keyboardInputHandler, screens.get("PAUSE"), gp));
        handlerMap.put("INVENTORY", new InventoryInputHandler(keyboardInputHandler, screens.get("INVENTORY"), gp));
        handlerMap.put("MARKET",    new MarketInputHandler(keyboardInputHandler, screens.get("MARKET"), gp));
        handlerMap.put("SETTINGS",   new SettingScreenInputHandler(keyboardInputHandler, screens.get("SETTINGS"), gp));
        handlerMap.put("MENU",      new MenuInputHandler(keyboardInputHandler, screens.get("MENU"), gp));
        handlerMap.put("GAMEOVER", new GameOverInputHandler(keyboardInputHandler, screens.get("GAMEOVER"), gp));
        handlerMap.put("STORY", handlerMap.get("PLAY"));

        String initial = gsm.getStateName();
        ScreenInputHandler first = handlerMap.get(initial);
        if (first != null) first.bindKeys();
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

        // 2) Then dispatch the keystroke
        keyboardInputHandler.handleKeyPress(e.getKeyCode());
    }

    @Override
    public void keyReleased(KeyEvent e) {
        playerInputHandler.unbindKeys(e);
    }
}
