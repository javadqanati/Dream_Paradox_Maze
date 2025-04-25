package Input;

import java.util.HashMap;
import java.util.Map;

public class KeyboardInputHandler {
    private final Map<Integer, Runnable> keyActions = new HashMap<>();

    // Binds a key to an action
    public void bindKey(int keyCode, Runnable action) {
        keyActions.put(keyCode, action);
    }

    // Handles key press events and runs the associated action
    public void handleKeyPress(int keyCode) {
        Runnable action = keyActions.get(keyCode);
        if (action != null) {
            action.run();
        }
    }

    // Unbinds a key from its action
    public void unbindKey(int keyCode) {
        keyActions.remove(keyCode);
    }
}
