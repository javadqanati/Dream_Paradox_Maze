package Input;

import Launcher.GamePanel;
import Market.PurchaseValidator;
import Market.TransactionHandler;
import UI.Screen;

import java.awt.event.KeyEvent;

public class MarketInputHandler extends ScreenInputHandler {
    private PurchaseValidator validator = new PurchaseValidator();
    private TransactionHandler transactionHandler = new TransactionHandler();

    public MarketInputHandler(KeyboardInputHandler keyboard, Screen screen, GamePanel gp) {
        super(keyboard, screen, gp);
        bindKeys();
    }

    @Override
    public void bindKeys() {
        getKeyboard().bindKey(KeyEvent.VK_UP, () -> getScreen().setCommandNum(getScreen().getCommandNum() - 1));
        getKeyboard().bindKey(KeyEvent.VK_DOWN, () -> getScreen().setCommandNum(getScreen().getCommandNum() + 1));

        getKeyboard().bindKey(KeyEvent.VK_ENTER, this::handleMarketSelection);
    }

    private void handleMarketSelection() {
        switch (getScreen().getCommandNum()) {
            case 0 -> getGp().getGameStateManager().setPause();
            case 1 -> {
                if(validator.canPurchase(getGp().getPlayer(),
                        getGp().getPowerUpShop().getAvailablePowerUps().get("SpeedBoost"))) {
                    transactionHandler.processPurchase(getGp().getPlayer(),
                            getGp().getPowerUpShop().getAvailablePowerUps().get("SpeedBoost"));
                }
            }
            case 2 -> {
                if(validator.canPurchase(getGp().getPlayer(),
                        getGp().getPowerUpShop().getAvailablePowerUps().get("TimeFreeze"))) {
                    transactionHandler.processPurchase(getGp().getPlayer(),
                            getGp().getPowerUpShop().getAvailablePowerUps().get("TimeFreeze"));
                }
            }
            case 3 -> {
                if(validator.canPurchase(getGp().getPlayer(),
                        getGp().getPowerUpShop().getAvailablePowerUps().get("ExtraLife"))) {
                    transactionHandler.processPurchase(getGp().getPlayer(),
                            getGp().getPowerUpShop().getAvailablePowerUps().get("ExtraLife"));
                }
            }
        }
    }
}
