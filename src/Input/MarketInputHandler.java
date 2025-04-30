package Input;

import Launcher.GamePanel;
import Trade.PurchaseValidator;
import Trade.TransactionHandler;
import UI.Screen;
import java.util.List;

public class MarketInputHandler extends ScreenInputHandler {
    private final PurchaseValidator validator     = new PurchaseValidator();
    private final TransactionHandler transactionHandler = new TransactionHandler();

    public MarketInputHandler(KeyboardInputHandler keyboard, Screen screen, GamePanel gp) {
        super(keyboard, screen, gp);

        screen.setOptions(List.of(
                "Back",
                "Speed Boost",
                "Time Freeze",
                "Extra Life"
        ));

        setOptionActions(List.of(
                () -> getGp().getGameStateManager().setPause(),

                () -> {
                    var player = getGp().getPlayer();
                    var pu = getGp().getPowerUpShop()
                            .getAvailablePowerUps()
                            .get("SpeedBoost");
                    if (validator.canPurchase(player, pu)) {
                        transactionHandler.processPurchase(player, pu);
                    }
                },

                () -> {
                    var player = getGp().getPlayer();
                    var pu = getGp().getPowerUpShop()
                            .getAvailablePowerUps()
                            .get("TimeFreeze");
                    if (validator.canPurchase(player, pu)) {
                        transactionHandler.processPurchase(player, pu);
                    }
                },

                () -> {
                    var player = getGp().getPlayer();
                    var pu = getGp().getPowerUpShop()
                            .getAvailablePowerUps()
                            .get("ExtraLife");
                    if (validator.canPurchase(player, pu)) {
                        transactionHandler.processPurchase(player, pu);
                    }
                }
        ));
        getScreen().setCommandNum(0);

        bindKeys();
    }
}
