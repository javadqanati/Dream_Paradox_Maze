package Input;

import Launcher.GamePanel;
import Trade.PowerUpShop;
import Trade.TransactionHandler;
import UI.Screen;
import java.util.List;

public class MarketInputHandler extends ScreenInputHandler {
    private final TransactionHandler transactionHandler = new TransactionHandler();

    public MarketInputHandler(KeyboardInputHandler keyboard, Screen screen, GamePanel gp) {
        super("MARKET", keyboard, screen, gp);

        screen.setOptions(List.of(
                "Back",
                "Speed Boost",
                "Time Freeze",
                "Extra Life"
        ));

        setOptionActions(List.of(
                () -> getGp().getGameStateManager().setPause(),
                () -> attemptPurchase("SpeedBoost"),
                () -> attemptPurchase("TimeFreeze"),
                () -> attemptPurchase("ExtraLife")
        ));

        getScreen().setCommandNum(0);
        bindKeys();
    }

    private void attemptPurchase(String powerUpType) {
        var player = getGp().getPlayer();
        var pu = PowerUpShop
                .getAvailablePowerUps()
                .get(powerUpType);

        if (transactionHandler.canPurchase(player, pu)) {
            transactionHandler.processPurchase(player, pu);
        }
    }
}