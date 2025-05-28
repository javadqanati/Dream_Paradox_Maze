package UI;

import Game.GameEntities.Entity;
import Game.GameEntities.MemoryFragment;
import Game.GameEntities.PlayerLife;
import Launcher.GamePanel;
import Utils.GameTimer;
import static Game.GameEntities.Entity.DOWN;
import java.awt.*;
import java.awt.image.BufferedImage;

public class PlayScreen extends Screen {
    private final BufferedImage memoryFragmentImg;
    private final BufferedImage full_heart, half_heart, heart_blank;
    private static final GameTimer timer  = new GameTimer(120);
    private static boolean gameFinished   = false;
    private static boolean optionsOn      = false;
    private static String  message        = "";
    private static int messageCounter;
    private StoryWindow storyWindow;

    public PlayScreen(GamePanel gp) {
        super(gp, "PLAY");

        Entity memoryFragment = new MemoryFragment(gp);
        Entity playerLife     = new PlayerLife(gp);

        memoryFragmentImg = memoryFragment.getCurrentSprite(DOWN(), 0);

        BufferedImage[] heartFrames = playerLife.getDownSprites();
        full_heart  = heartFrames[0];
        half_heart  = heartFrames[1];
        heart_blank = heartFrames[2];
    }

    public void loadStory(String resourcePath) {
        this.storyWindow = new StoryWindow(getGp(), resourcePath);
    }

    public void setGameFinished(boolean finished) {
        gameFinished = finished;
        if (finished) {
            getGp().getAudioManager().playSE("Next Level");
            optionsOn = true;
        }
    }

    public void resetTimer() {
        timer.reset();
        gameFinished   = false;
        optionsOn      = false;
        message        = "";
        messageCounter = 0;
    }

    private void drawPlayerLife(Graphics2D g2) {
        int maxLife     = getGp().getPlayer().getMaxHealth();
        int currentLife = getGp().getPlayer().getHealth();
        int tileSize    = getGp().getTileSize();
        int totalHearts = maxLife / 2;
        int startX      = (getGp().getScreenWidth() - totalHearts * tileSize) / 2;
        int y           = tileSize / 2;

        for (int i = 0; i < totalHearts; i++) {
            BufferedImage heart;
            if (currentLife >= 2) {
                heart = full_heart; currentLife -= 2;
            } else if (currentLife == 1) {
                heart = half_heart; currentLife -= 1;
            } else {
                heart = heart_blank;
            }
            g2.drawImage(heart, startX + i * tileSize, y, tileSize, tileSize, null);
        }
    }

    @Override
    public void draw(Graphics2D g2) {
        if (getGp().getGameStateManager().isStory()) {
            storyWindow.draw(g2);
            return;
        }
        if (optionsOn) {
            g2.setFont(getScreenfont().deriveFont(Font.BOLD, 50f));
            g2.setColor(Color.BLUE);
            String title = "Level Complete!";
            int x = getXforCenteredText(title, g2);
            int y = getGp().getTileSize() * 3;
            g2.drawString(title, x, y);

            g2.setFont(getScreenfont().deriveFont(Font.BOLD, 36f));
            g2.setColor(Color.white);
            String text = "Next Level";
            x = getXforCenteredText(text, g2);
            y += (int) (getGp().getTileSize() * 3.5);
            g2.drawString(text, x, y);
            if(getCommandNum() == 0){
                g2.drawString(">", x - getGp().getTileSize(), y);
            }

            text = "Quit";
            x = getXforCenteredText(text, g2);
            y += getGp().getTileSize();
            g2.drawString(text, x, y);
            if(getCommandNum() == 1){
                g2.drawString(">", x - getGp().getTileSize(), y);
            }
            return;
        }

        g2.setFont(getScreenfont().deriveFont(Font.BOLD, 30f));
        g2.setColor(Color.WHITE);
        g2.drawImage(memoryFragmentImg,
                getGp().getTileSize()/2, getGp().getTileSize()/2,
                getGp().getTileSize(), getGp().getTileSize(), null);
        g2.drawString("x " + getGp().getPlayer().getCollectedFragments(), 74, 65);

        boolean isPlaying = getGp().getGameStateManager().isPlaying();
        timer.update(isPlaying);
        if (timer.isFinished()) {
            getGp().getGameStateManager().setGameOver();
            return;
        }
        g2.drawString("Time: " + timer.getFormatted(), getGp().getTileSize()*15, 65);

        drawPlayerLife(g2);

        if (!message.isEmpty()) {
            g2.setFont(getScreenfont().deriveFont(30f));
            g2.drawString(message, getGp().getTileSize()/2, getGp().getTileSize()*5);
            if (++messageCounter > 120) message = "";
        }
    }

    public boolean isGameFinished() {
        return gameFinished;
    }

    public GameTimer getTimer() {
        return timer;
    }
}
