//package Input;
//
//import java.awt.Point;
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * MouseInputHandler handles mouse-related input, including the state of mouse buttons
// * and the position of the mouse. It allows mouse buttons to be bound to specific actions
// * and processes mouse input events accordingly.
// */
//public class MouseInputHandler extends InputHandler {
//    private final Map<Integer, InputAction> mouseButtonMappings;
//    private final Map<Integer, Boolean> mouseButtonsState;
//    private Point mousePosition;
//
//    /**
//     * Constructs a new MouseInputHandler with empty state maps and a default mouse position.
//     */
//    public MouseInputHandler() {
//        mouseButtonMappings = new HashMap<>();
//        mouseButtonsState = new HashMap<>();
//        mousePosition = new Point(0, 0);
//    }
//
//    /**
//     * Binds a mouse button to a specific InputAction.
//     *
//     * @param buttonCode the mouse button code (e.g., MouseEvent.BUTTON1 for left-click).
//     * @param action     the action to perform when the button is pressed.
//     */
//    public void bindMouseButton(int buttonCode, InputAction action) {
//        mouseButtonMappings.put(buttonCode, action);
//    }
//
//    /**
//     * Processes a mouse input event, updating the state of the specified button
//     * and invoking the corresponding action if the button is pressed.
//     *
//     * @param buttonCode the code of the mouse button (e.g., MouseEvent.BUTTON1).
//     * @param isPressed  true if the button is pressed, false otherwise.
//     * @param position   the current mouse position (x, y).
//     */
//    public void processMouseInput(int buttonCode, boolean isPressed, Point position) {
//        // Update mouse button state and position
//        mouseButtonsState.put(buttonCode, isPressed);
//        this.mousePosition = position;
//
//        // If the button is pressed, invoke the associated action
//        if (isPressed && mouseButtonMappings.containsKey(buttonCode)) {
//            mouseButtonMappings.get(buttonCode).performAction(null);
//        }
//    }
//
//    /**
//     * Returns the current state of the mouse buttons (pressed or not).
//     *
//     * @return a map of mouse button codes and their respective press states.
//     */
//    public Map<Integer, Boolean> getMouseState() {
//        return new HashMap<>(mouseButtonsState);
//    }
//
//    /**
//     * Returns the current mouse position.
//     *
//     * @return the mouse position as a Point (x, y).
//     */
//    public Point getMousePosition() {
//        return mousePosition;
//    }
//}
