package Input;

import Game.GameStates.GameStateManager;
import Launcher.GamePanel;
import UI.MainScreen;
import UI.Screen;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Map;

public class InputHandler implements KeyListener {
    private boolean upPressed, downPressed, leftPressed, rightPressed;
    private GamePanel gp;
    private GameStateManager gameStateManager;
    private Screen screen;

    public InputHandler(GamePanel gp, GameStateManager gameStateManager) {
        this.gp=gp;
        this.gameStateManager=gameStateManager;
        screen = new Screen(gp);
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

        if(gp.getGameStateManager().isMenu()) {
            if (code == KeyEvent.VK_UP) {
                screen.setCommandNum(screen.getCommandNum() - 1);
                if (screen.getCommandNum() < 0) {
                    screen.setCommandNum(3);
                }
            }
            if (code == KeyEvent.VK_DOWN) {
                screen.setCommandNum(screen.getCommandNum() + 1);
                if (screen.getCommandNum() > 3) {
                    screen.setCommandNum(0);
                }
            }
            if (code == KeyEvent.VK_ENTER) {
                if (screen.getCommandNum() == 0) {
                    gp.getGameStateManager().setPlay();
                }
                if (screen.getCommandNum() == 1) {

                }
                if (screen.getCommandNum() == 2) {
                    gp.getGameStateManager().setSettings();
                }
                if (screen.getCommandNum() == 3) {
                    System.exit(0);
                }
            }
        } else if (gp.getGameStateManager().isSettings()) {
            if (code == KeyEvent.VK_UP) {
                screen.setCommandNum((screen.getCommandNum() - 1 + 3) % 3); // wrap around 0-2
            }
            if (code == KeyEvent.VK_DOWN) {
                screen.setCommandNum((screen.getCommandNum() + 1) % 3);
            }
            if (code == KeyEvent.VK_ENTER) {
                switch (screen.getCommandNum()) {
                    case 0 -> gp.getAudioManager().toggleMusicMute();
                    case 1 -> gp.getAudioManager().toggleSfxMute();
                    case 2 -> gp.getGameStateManager().setMenu();
                }
            }
        }


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
