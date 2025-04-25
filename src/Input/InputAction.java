package Input;

public class InputAction {
    private final Runnable action;

    public InputAction(Runnable action) {
        this.action = action;
    }

    public void execute() {
        if (action != null) {
            action.run();
        }
    }
}
