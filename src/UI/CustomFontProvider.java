package UI;

import java.awt.*;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

public interface CustomFontProvider {
    Logger LOGGER = Logger.getLogger(CustomFontProvider.class.getName());

    default Font getCustomFont() {
        Font font = null;
        try {
            InputStream is = getClass().getResourceAsStream("/font/x12y16pxMaruMonica.ttf");
            assert is != null;
            font = Font.createFont(Font.TRUETYPE_FONT, is);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Failed getting the costume font ", e);
        }
        return font;
    }
}
