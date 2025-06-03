package Input;

import Game.GameEntities.Player;
import Game.GameEntities.Powerup.PowerUp;
import Game.GameEntities.Powerup.PowerUpFactory;
import Launcher.GamePanel;
import Utils.TransactionHandler;
import UI.Screen;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class MarketInputHandler extends ScreenInputHandler {
    private final TransactionHandler transactionHandler = new TransactionHandler();

    public MarketInputHandler(KeyboardInputHandler keyboard, Screen screen, GamePanel gp) {
        super("MARKET", keyboard, screen, gp);

        Map<String, Function<GamePanel, ? extends PowerUp>> powerUps = PowerUpFactory.getAvailablePowerUpNames();

        List<String> optionNames = new ArrayList<>();
        optionNames.add("Back");
        optionNames.addAll(powerUps.keySet());

        screen.setOptions(optionNames);

        List<Runnable> optionActions = new ArrayList<>();
        optionActions.add(() -> getGp().getGameStateManager().setPause());

        powerUps.keySet().forEach(powerUpType ->
                optionActions.add(() -> attemptPurchase(powerUpType))
        );

        setOptionActions(optionActions);
        getScreen().setCommandNum(0);
        bindKeys();
    }

    private void attemptPurchase(String powerUpType) {
        Player player = getGp().getPlayer();

        Function<GamePanel, ? extends PowerUp> constructor =
                PowerUpFactory.getAvailablePowerUpNames().get(powerUpType);

        if (constructor == null) {
            System.err.println("PowerUp type not found: " + powerUpType);
            return;
        }

        PowerUp pu = PowerUpFactory.create(powerUpType, getGp());

        if (transactionHandler.canPurchase(player, pu)) {
            transactionHandler.processPurchase(player, pu);
            getScreen().showNotification(pu.getType() + " purchased and added to the inventory");
        } else {
            getScreen().showNotification("transaction failed");
        }
    }
}