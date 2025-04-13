package Input;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;

public class MouseInputHandler extends InputHandler {
    private final Map<Integer, InputAction> mouseButtonMappings;
    private final Map<Integer, Boolean> mouseButtonsState;
    private Point mousePosition;

    public MouseInputHandler() {
        mouseButtonMappings = new HashMap<>();
        mouseButtonsState = new HashMap<>();
        mousePosition = new Point(0, 0);
    }

    // Bind a mouse button to an InputAction
    public void bindMouseButton(int buttonCode, InputAction action) {
        mouseButtonMappings.put(buttonCode, action);
    }

    // Process a mouse input event
    public void processMouseInput(int buttonCode, boolean isPressed, Point position) {
        mouseButtonsState.put(buttonCode, isPressed);
        this.mousePosition = position;

        if (isPressed && mouseButtonMappings.containsKey(buttonCode)) {
            mouseButtonMappings.get(buttonCode).performAction();
        }
    }

    // Get mouse state
    public Map<Integer, Boolean> getMouseState() {
        return new HashMap<>(mouseButtonsState);
    }

    public Point getMousePosition() {
        return mousePosition;
    }
}
