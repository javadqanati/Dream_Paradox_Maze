package UI;

import Launcher.GamePanel;
import Launcher.GamePanelConfig;
import Launcher.GameWindow;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public abstract class Menu {
    private final List<Button> buttons;
    protected final JPanel menuPanel;
    protected final GamePanel gamePanel;

    public Menu(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        this.buttons = new ArrayList<>();
        this.menuPanel = new JPanel(null);
        menuPanel.setPreferredSize(new Dimension(
                gamePanel.getScreenWidth(), gamePanel.getScreenHeight()
        ));
    }

    public void addButton(Button button) {
        buttons.add(button);
    }

    public void removeButton(Button button) {
        buttons.remove(button);
    }

    public void load() {
        renderMenu(menuPanel);
        for (Button button : buttons) {
            button.draw(); // Apply temporary style
            menuPanel.add(button.getButton());
        }
    }

    public JPanel getMenuPanel() {
        return menuPanel;
    }

    public void updateMenu() {
        // Optional override
    }

    protected abstract void renderMenu(JPanel panel);
}
