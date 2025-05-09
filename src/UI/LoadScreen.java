package UI;

import Launcher.GamePanel;
import java.awt.*;

public class LoadScreen extends Screen {
    private final GamePanel gp;
    private long startTime = -1;

    public LoadScreen(GamePanel gp) {
        super(gp, "LOAD");
        this.gp = gp;
    }

    @Override
    public void draw(Graphics2D g2) {

        if (startTime == -1) {
            startTime = System.currentTimeMillis();
        }

        super.draw(g2);

        long elapsed = System.currentTimeMillis() - startTime;

        int dots = (int)((elapsed / 500) % 4);
        String text = "Loading" + ".".repeat(Math.max(0, dots));

        g2.setFont(getScreenfont().deriveFont(Font.BOLD, 36f));
        g2.setColor(Color.WHITE);
        int x = getXforCenteredText(text, g2);
        int y = gp.getScreenHeight() / 2;
        g2.drawString(text, x, y);

        if (elapsed >= 4000) {
            System.out.println("elapsed time finished");
            gp.loadGame();
            startTime = -1;
            getGp().getGameStateManager().setStory();
        }

    }
}
