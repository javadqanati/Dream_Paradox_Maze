package UI;

import Launcher.GamePanel;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class StoryWindow extends Screen {
    private final List<String> lines = new ArrayList<>();
    private final float fontSize = 26f;
    Logger LOGGER = Logger.getLogger(StoryWindow.class.getName());

    public StoryWindow(GamePanel gp, String resourcePath) {
        super(gp, "STORY");
        loadResource(resourcePath);
    }

    protected void loadResource(String resourcePath) {
        int boxWidthPx = getGp().getScreenWidth() - getGp().getTileSize() * 6;
        try (InputStream is = getClass().getResourceAsStream(resourcePath)) {
            assert is != null;
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
                String raw;
                while ((raw = reader.readLine()) != null) {
                    wrapAndAddLine(raw, boxWidthPx);
                }
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Failed loading the story ", e);
            lines.add("[ERROR loading story]");
        }
    }

    private void wrapAndAddLine(String text, int maxWidth) {
        BufferedImage img = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = img.createGraphics();
        g2.setFont(getCustomFont().deriveFont(Font.PLAIN, fontSize));
        FontMetrics fm = g2.getFontMetrics();

        String[] words = text.split(" ");
        StringBuilder line = new StringBuilder();

        for (String word : words) {
            String testLine = line.isEmpty() ? word : line + " " + word;
            int testWidth = fm.stringWidth(testLine);

            if (testWidth > maxWidth && !line.isEmpty()) {
                lines.add(line.toString());
                line = new StringBuilder(word);
            } else {
                line = new StringBuilder(testLine);
            }
        }

        if (!line.isEmpty()) {
            lines.add(line.toString());
        }
        g2.dispose();
    }

    @Override
    public void draw(Graphics2D g2) {
        int padding = getGp().getTileSize();
        int x = padding * 2;
        int y = padding / 2;
        int width = getGp().getScreenWidth() - padding * 4;

        g2.setFont(getCustomFont().deriveFont(Font.PLAIN, fontSize));
        FontMetrics fm = g2.getFontMetrics();
        int lineHeight = fm.getHeight();
        int totalTextHeight = lines.size() * lineHeight;
        int height = totalTextHeight + padding * 2;

        drawSubWindow(g2, x, y, width, height);
        drawText(g2, x + padding, y + padding, lineHeight);
    }

    private void drawSubWindow(Graphics2D g2, int x, int y, int w, int h) {
        Color bg = new Color(15, 28, 67, 200);
        g2.setColor(bg);
        g2.fillRoundRect(x, y, w, h, 35, 35);

        g2.setStroke(new BasicStroke(5));
        g2.setColor(Color.WHITE);
        g2.drawRoundRect(x + 5, y + 5, w - 10, h - 10, 35, 35);
    }

    private void drawText(Graphics2D g2, int startX, int startY, int lineHeight) {
        g2.setFont(getCustomFont().deriveFont(Font.PLAIN, fontSize));
        g2.setColor(Color.WHITE);
        int y = startY;
        for (String line : lines) {
            g2.drawString(line, startX, y);
            y += lineHeight;
        }
    }
}
