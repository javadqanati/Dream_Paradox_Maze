package Input;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * InputAction encapsulates a specific action type and its associated behavior.
 * This immutable version ensures that once an action is created, its properties remain fixed.
 */
public final class InputAction {
    private final String actionType;
    private final ActionListener actionListener;

    /**
     * Constructs an InputAction with the specified action type and listener.
     *
     * @param actionType      A unique identifier for the action.
     * @param actionListener  The ActionListener to invoke when this action is performed.
     */
    public InputAction(String actionType, ActionListener actionListener) {
        this.actionType = actionType;
        this.actionListener = actionListener;
    }

    /**
     * Performs the action by delegating to the associated ActionListener.
     * The ActionEvent parameter may be null if no additional event data is available.
     *
     * @param event the ActionEvent triggering this action, may be null.
     */
    public void performAction(ActionEvent event) {
        if (actionListener != null) {
            actionListener.actionPerformed(event);
        }
    }

    /**
     * Returns the action type identifier.
     *
     * @return a String representing the action type.
     */
    public String getActionType() {
        return actionType;
    }

    /**
     * Returns the associated ActionListener.
     *
     * @return the ActionListener associated with this action.
     */
    public ActionListener getActionListener() {
        return actionListener;
    }
}
