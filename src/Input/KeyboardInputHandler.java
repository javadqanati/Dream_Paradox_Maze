package Input;

import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

/**
 * KeyboardInputHandler handles keyboard-related input, including key presses and releases.
 * It allows keys to be bound to specific actions and processes key input events accordingly.
 */
public class KeyboardInputHandler extends InputHandler {
    private final Map<Integer, InputAction> keyMappings;

    /**
     * Constructs a new KeyboardInputHandler with an empty key mapping.
     */
    public KeyboardInputHandler() {
        keyMappings = new HashMap<>();
    }

    /**
     * Binds a key to a specific InputAction.
     *
     * @param keyCode the key code (e.g., KeyEvent.VK_W for the 'W' key).
     * @param action  the action to perform when the key is pressed.
     */
    public void bindKey(int keyCode, InputAction action) {
        keyMappings.put(keyCode, action);
    }

    /**
     * Processes a key input event, updating the key state and invoking the associated action
     * if the key is pressed.
     *
     * @param keyCode the key code (e.g., KeyEvent.VK_W for the 'W' key).
     * @param isPressed true if the key is pressed, false otherwise.
     */
    public void processKeyInput(int keyCode, boolean isPressed) {
        // Update key state
        setKeyState(keyCode, isPressed);

        // If the key is pressed and there is a mapped action, invoke the action
        if (isPressed && keyMappings.containsKey(keyCode)) {
            keyMappings.get(keyCode).performAction(null);
        }
    }

    /**
     * Returns the InputAction associated with a specific key.
     *
     * @param keyCode the key code (e.g., KeyEvent.VK_W for the 'W' key).
     * @return the associated InputAction, or null if no action is mapped to the key.
     */
    public InputAction getKeyMapping(int keyCode) {
        return keyMappings.get(keyCode);
    }
}
