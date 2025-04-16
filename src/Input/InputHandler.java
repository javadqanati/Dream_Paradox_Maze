package Input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Map;

public class InputHandler implements KeyListener {
    private final Map<Integer, Boolean> keyboardState;
    private final Map<Integer, Boolean> mouseState;
    private boolean upPressed, downPressed, leftPressed, rightPressed;
    public InputHandler() {
        keyboardState = new HashMap<>();
        mouseState = new HashMap<>();
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


    public void processInput() {
        // Example: Reset one-time events or process particular input logic if needed.
    }

    public void setKeyState(int keyCode, boolean isPressed) {
        keyboardState.put(keyCode, isPressed);
    }

    public void setMouseState(int button, boolean isPressed) {
        mouseState.put(button, isPressed);
    }

    public boolean isKeyPressed(int keyCode) {
        return keyboardState.getOrDefault(keyCode, false);
    }

    public boolean isMousePressed(int button) {
        return mouseState.getOrDefault(button, false);
    }

    public boolean getInputState(int code, boolean isKeyboard) {
        return isKeyboard ? isKeyPressed(code) : isMousePressed(code);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code=e.getKeyCode();
        if(code == KeyEvent.VK_W){
            upPressed=true;
        }
        if(code == KeyEvent.VK_S){
            downPressed=true;
        }
        if(code == KeyEvent.VK_A){
            leftPressed=true;
        }
        if(code == KeyEvent.VK_D){
            rightPressed=true;
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code=e.getKeyCode();
        if(code == KeyEvent.VK_W){
            upPressed=false;
        }
        if(code == KeyEvent.VK_S){
            downPressed=false;
        }
        if(code == KeyEvent.VK_A){
            leftPressed=false;
        }
        if(code == KeyEvent.VK_D){
            rightPressed=false;
        }
    }
}
