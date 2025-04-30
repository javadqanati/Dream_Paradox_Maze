package UI;

import java.awt.*;
import java.io.InputStream;

public interface CustomFontProvider {
    default Font getCustomFont() {
        Font font = null;
        try {
            InputStream is = getClass().getResourceAsStream("/font/x12y16pxMaruMonica.ttf");
            assert is != null;
            font = Font.createFont(Font.TRUETYPE_FONT, is);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return font;
    }
}
