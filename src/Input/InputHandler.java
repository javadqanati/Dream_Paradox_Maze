package Input;

import Game.GameStates.GameStateManager;
import Launcher.GamePanel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Map;

public class InputHandler implements KeyListener {
    private boolean upPressed, downPressed, leftPressed, rightPressed;
    private GamePanel gp;
    private GameStateManager gameStateManager;

    public InputHandler(GamePanel gp, GameStateManager gameStateManager) {
        this.gp=gp;
        this.gameStateManager=gameStateManager;
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
        if(code == KeyEvent.VK_P){
            if (gameStateManager.isPlaying()) {
                gameStateManager.setPause();
            } else {
                gameStateManager.setPlay();
            }

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
