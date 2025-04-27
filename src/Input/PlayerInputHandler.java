package Input;

import java.awt.event.KeyEvent;

public class PlayerInputHandler {
    private final KeyboardInputHandler keyboard;
    private boolean upPressed, downPressed, leftPressed, rightPressed;

    public PlayerInputHandler(KeyboardInputHandler keyboard) {
        this.keyboard = keyboard;
        bindKeys();
    }

    public void bindKeys() {
        keyboard.bindKey(KeyEvent.VK_W, () -> upPressed = true);
        keyboard.bindKey(KeyEvent.VK_S, () -> downPressed = true);
        keyboard.bindKey(KeyEvent.VK_A, () -> leftPressed = true);
        keyboard.bindKey(KeyEvent.VK_D, () -> rightPressed = true);
    }

    public void unbindKeys(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_W) upPressed = false;
        if (code == KeyEvent.VK_S) downPressed = false;
        if (code == KeyEvent.VK_A) leftPressed = false;
        if (code == KeyEvent.VK_D) rightPressed = false;
    }

    public boolean isUpPressed() {
        return upPressed;
    }

    public boolean isDownPressed() {
        return downPressed;
    }

    public boolean isLeftPressed() {
        return leftPressed;
    }

    public boolean isRightPressed() {
        return rightPressed;
    }

    public boolean isMovementKeyPressed() {
        return upPressed || downPressed || leftPressed || rightPressed;
    }
}
