package UI;

import Launcher.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class StoryWindow {
    private final GamePanel gp;
    private final List<String> lines = new ArrayList<>();
    private Font purisaB;

    public StoryWindow(GamePanel gp, String resourcePath) {
        this.gp = gp;
        loadStory(resourcePath);
    }

    private void loadStory(String resourcePath) {
        // First: load + size the font
        try ( InputStream fis = getClass().getResourceAsStream("/font/Purisa Bold.ttf") ) {
            purisaB = Font.createFont(Font.TRUETYPE_FONT, fis)
                    .deriveFont(Font.PLAIN, 18f);
        } catch (Exception e) {
            e.printStackTrace();
            // maybe fall back to a default system font:
            purisaB = new Font("Serif", Font.PLAIN, gp.getTileSize());
        }

        // Now you know purisaB has a real size, so you can wrap lines against it:
        int boxWidthPx = gp.getScreenWidth() - gp.getTileSize() * 6;
        try ( InputStream is = getClass().getResourceAsStream(resourcePath);
              BufferedReader reader = new BufferedReader(new InputStreamReader(is)) ) {
            String raw;
            while ((raw = reader.readLine()) != null) {
                wrapAndAddLine(raw, purisaB, boxWidthPx);
            }
        } catch (Exception e) {
            e.printStackTrace();
            lines.add("[ERROR loading story]");
        }
    }


    private void wrapAndAddLine(String text, Font font, int maxWidth) {
        BufferedImage img = new BufferedImage(1,1,BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = img.createGraphics();
        g2.setFont(purisaB);
        FontMetrics fm = g2.getFontMetrics();

        String[] words = text.split(" ");
        StringBuilder line = new StringBuilder();
        for (String word : words) {
            String test = line.length() == 0 ? word : line + " " + word;
            if (fm.stringWidth(test) > maxWidth) {
                lines.add(line.toString());
                line = new StringBuilder(word);
            } else {
                line = new StringBuilder(test);
            }
        }
        if (line.length() > 0) {
            lines.add(line.toString());
        }
        g2.dispose();
    }

    public void drawScreen(Graphics2D g2) {
        int padding = gp.getTileSize();
        int x = padding * 2;
        int y = padding / 2;
        int width = gp.getScreenWidth() - padding * 4;

        g2.setFont(purisaB);
        FontMetrics fm = g2.getFontMetrics();
        int lineHeight = fm.getHeight();
        int totalTextHeight = lines.size() * lineHeight;
        int height = totalTextHeight + padding * 2;

        drawSubWindow(g2, x, y, width, height);
        drawText(g2, x + padding, y + padding, lineHeight);
    }

    private void drawSubWindow(Graphics2D g2, int x, int y, int w, int h) {
        Color bg = new Color(0, 0, 0, 200);
        g2.setColor(bg);
        g2.fillRoundRect(x, y, w, h, 35, 35);

        g2.setStroke(new BasicStroke(5));
        g2.setColor(Color.WHITE);
        g2.drawRoundRect(x + 5, y + 5, w - 10, h - 10, 35, 35);
    }

    private void drawText(Graphics2D g2, int startX, int startY, int lineHeight) {
        g2.setFont(purisaB);
        g2.setColor(Color.WHITE);
        int y = startY;
        for (String line : lines) {
            g2.drawString(line, startX, y);
            y += lineHeight;
        }
    }
}
