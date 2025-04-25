package Input;

import java.util.HashMap;
import java.util.Map;

public class KeyboardInputHandler {
    private final Map<Integer, Runnable> keyActions = new HashMap<>();

    public void bindKey(int keyCode, Runnable action) {
        keyActions.put(keyCode, action);
    }

    public void handleKeyPress(int keyCode) {
        Runnable action = keyActions.get(keyCode);
        if (action != null) {
            action.run();
        }
    }
}
