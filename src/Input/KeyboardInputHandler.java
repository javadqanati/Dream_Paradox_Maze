package Input;

import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

public class KeyboardInputHandler extends InputHandler {
    private final Map<Integer, InputAction> keyMappings;

    public KeyboardInputHandler() {
        keyMappings = new HashMap<>();
    }

    // Assign a key to a specific action
    public void bindKey(int keyCode, InputAction action) {
        keyMappings.put(keyCode, action);
    }

    // Called to handle key events (e.g., from KeyListener)
    public void processKeyInput(int keyCode, boolean isPressed) {
        setKeyState(keyCode, isPressed);

        if (isPressed && keyMappings.containsKey(keyCode)) {
            keyMappings.get(keyCode).performAction();
        }
    }

    // Get the InputAction for a given key
    public InputAction getKeyMapping(int keyCode) {
        return keyMappings.get(keyCode);
    }
}
