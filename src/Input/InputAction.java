package Input;

import java.awt.event.KeyEvent;

public class InputAction {
    private String actionType;
    private Runnable action;

    public InputAction(String actionType, Runnable action) {
        this.actionType = actionType;
        this.action = action;
    }

    public void performAction() {
        if (action != null) {
            action.run();
        }
    }

    public String getActionType() {
        return actionType;
    }

    public void setActionType(String actionType) {
        this.actionType = actionType;
    }

    public void setAction(Runnable action) {
        this.action = action;
    }

    public Runnable getAction() {
        return action;
    }
}
