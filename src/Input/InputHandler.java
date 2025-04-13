package Input;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;

class InputHandler {
    private Map<Integer, Boolean> keyboardState;
    private Map<Integer, Boolean> mouseState;

    public InputHandler() {
        keyboardState = new HashMap<>();
        mouseState = new HashMap<>();
    }

    // Called each frame to process input state
    public void processingInput() {
        // Example: You might want to reset one-time events or process specific logic
        // This would normally include checking key states and acting accordingly
    }

    // Store key press/release
    public void setKeyState(int keyCode, boolean isPressed) {
        keyboardState.put(keyCode, isPressed);
    }

    // Store mouse press/release
    public void setMouseState(int button, boolean isPressed) {
        mouseState.put(button, isPressed);
    }

    // Query key state
    public boolean isKeyPressed(int keyCode) {
        return keyboardState.getOrDefault(keyCode, false);
    }

    // Query mouse button state
    public boolean isMousePressed(int button) {
        return mouseState.getOrDefault(button, false);
    }

    // Optional: Generic input querying
    public boolean getInputState(int code, boolean isKeyboard) {
        return isKeyboard ? isKeyPressed(code) : isMousePressed(code);
    }
}
