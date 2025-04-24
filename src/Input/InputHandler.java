package Input;

import Game.GameEntities.Player;
import Game.GameEntities.PowerUp;
import Game.GameStates.GameStateManager;
import Launcher.GamePanel;
import Market.PowerUpShop;
import Market.PurchaseValidator;
import Market.TransactionHandler;
import UI.Screen;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Map;

public class InputHandler implements KeyListener {
    private boolean upPressed, downPressed, leftPressed, rightPressed;
    private final GamePanel gp;
    private final GameStateManager gameStateManager;
    private final Screen screen;

    public InputHandler(GamePanel gp, GameStateManager gameStateManager) {
        this.gp = gp;
        this.gameStateManager = gameStateManager;
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
        int code = e.getKeyCode();

        if (gp.getGameStateManager().isMenu()) {
            handleMenuNavigation(code);
        } else if (gp.getGameStateManager().isSettings()) {
            handleSettingsNavigation(code);
        } else if (gp.getGameStateManager().isPaused()) {
            handlePauseNavigation(code);
        } else if (gp.getGameStateManager().isInventory()) {
            handleInventoryNavigation(code);
        } else if (gp.getGameStateManager().isMarket()) {
            handleMarketNavigation(code);
        }

        if (code == KeyEvent.VK_W) {
            upPressed = true;
        }
        if (code == KeyEvent.VK_S) {
            downPressed = true;
        }
        if (code == KeyEvent.VK_A) {
            leftPressed = true;
        }
        if (code == KeyEvent.VK_D) {
            rightPressed = true;
        }
        if (code == KeyEvent.VK_P) {
            if (gameStateManager.isPlaying()) {
                gameStateManager.setPause();
            } else {
                gameStateManager.setPlay();
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_W) {
            upPressed = false;
        }
        if (code == KeyEvent.VK_S) {
            downPressed = false;
        }
        if (code == KeyEvent.VK_A) {
            leftPressed = false;
        }
        if (code == KeyEvent.VK_D) {
            rightPressed = false;
        }
    }

    private void handleMenuNavigation(int code) {
        navigate(code);
        if (code == KeyEvent.VK_ENTER) {
            if (screen.getCommandNum() == 0) {
                gp.getGameStateManager().setPlay();
            }
            if (screen.getCommandNum() == 1) {
                gp.getGameStateManager().setLoad();
            }
            if (screen.getCommandNum() == 2) {
                gp.getGameStateManager().setSettings();
            }
            if (screen.getCommandNum() == 3) {
                System.exit(0);
            }
        }
    }

    private void navigate(int code) {
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
    }

    private void handleSettingsNavigation(int code) {
        if (code == KeyEvent.VK_UP) {
            screen.setCommandNum((screen.getCommandNum() - 1 + 3) % 3);
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

    private void handlePauseNavigation(int code) {
        navigate(code);
        if (code == KeyEvent.VK_ENTER) {
            switch (screen.getCommandNum()) {
                case 0 -> gp.getGameStateManager().setInventory();
                case 1 -> gp.getGameStateManager().setMarket();
                case 2 -> gp.getGameStateManager().setPlay();
                case 3 -> gp.getGameStateManager().setMenu();
            }
        }
    }

    private void handleInventoryNavigation(int code) {
        navigate(code);
        if (code == KeyEvent.VK_ENTER) {
            switch (screen.getCommandNum()) {
                case 0 -> gp.getGameStateManager().setPause();
                case 1 -> usePowerUp("SpeedBoost");
                case 2 -> usePowerUp("TimeFreeze");
                case 3 -> usePowerUp("ExtraLife");
            }
        }
    }

    private void handleMarketNavigation(int code) {
        Player player = gp.getPlayer();

        if (code == KeyEvent.VK_UP) {
            screen.setCommandNum(screen.getCommandNum() - 1);
            if (screen.getCommandNum() < 0) {
                screen.setCommandNum(3);
            }
        }
        if (code == KeyEvent.VK_DOWN) {
            screen.setCommandNum(screen.getCommandNum() + 1);
            if (screen.getCommandNum() >= 3) {
                screen.setCommandNum(0);
            }
        }

        if (code == KeyEvent.VK_ENTER) {
            PurchaseValidator purchaseValidator = new PurchaseValidator();
            TransactionHandler transactionHandler = new TransactionHandler();
            Map<String, PowerUp> availablePowerUps = PowerUpShop.getAvailablePowerUps();

            if (screen.getCommandNum() == 0) {
                gp.getGameStateManager().setPause();
                screen.setSelectionMessage("");
            }
            if (screen.getCommandNum() == 1) {
                if(purchaseValidator.canPurchase(player, availablePowerUps.get("SpeedBoost"))) {
                    transactionHandler.processPurchase(player, availablePowerUps.get("SpeedBoost"));
                }
            }
            if (screen.getCommandNum() == 2) {
                if(purchaseValidator.canPurchase(player, availablePowerUps.get("SpeedBoost"))) {
                    transactionHandler.processPurchase(player, availablePowerUps.get("SpeedBoost"));
                }
            }
            if (screen.getCommandNum() == 3) {
                if(purchaseValidator.canPurchase(player, availablePowerUps.get("SpeedBoost"))) {
                    transactionHandler.processPurchase(player, availablePowerUps.get("SpeedBoost"));
                }
            }
        }
    }

    private void usePowerUp(String powerUpName) {
        boolean used = gp.getPlayer().usePowerUp(powerUpName);
        screen.setSelectionMessage(used ? powerUpName + " activated!" : "You don't have a " + powerUpName + "!");
    }
}
