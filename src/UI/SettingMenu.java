//package UI;
//
//import Data.Setting;
//import Launcher.GamePanel;
//import Launcher.GameWindow;
//import Launcher.Loadable;
//import org.jetbrains.annotations.NotNull;
//
//import javax.swing.*;
//
//public class SettingMenu extends Menu implements Navigable, Loadable {
//    private final GamePanelConfig config;
//    private final Setting setting;
//    private JSlider musicSlider;
//    private JSlider soundSlider;
//    private JCheckBox fullScreenToggle;
//    private final GameWindow gameWindow;
//
//    public SettingMenu(GamePanelConfig config, GamePanel gamePanel, GameWindow gameWindow) {
//        super(gamePanel);
//        this.config = config;
//        this.gameWindow = gameWindow;
//        this.setting = new Setting(50, false, 50); // Default settings
//    }
//
//    @Override
//    public void renderMenu(@NotNull JPanel menuPanel) {
//        menuPanel.setLayout(null);
//        int tileSize = config.getOriginalTileSize() * config.getScale();
//        int screenWidth = config.getMaxScreenCol() * tileSize;
//        int screenHeight = config.getMaxScreenRow() * tileSize;
//
//        int centerX = screenWidth / 2 - 100;
//        int startY = screenHeight / 4;
//
//        musicSlider = createSlider("Music Volume", centerX, startY);
//        menuPanel.add(musicSlider);
//
//        soundSlider = createSlider("Sound Volume", centerX, startY + 80);
//        menuPanel.add(soundSlider);
//
//        fullScreenToggle = new JCheckBox("Fullscreen");
//        fullScreenToggle.setBounds(centerX, startY + 160, 200, 40);
//        fullScreenToggle.setSelected(setting.isSetFullScreen());
//        menuPanel.add(fullScreenToggle);
//
//        JButton applyButton = new JButton("Apply");
//        applyButton.setBounds(centerX, startY + 220, 200, 40);
//        applyButton.addActionListener(e -> applySettings());
//        menuPanel.add(applyButton);
//
//        JButton backButton = new JButton("Back");
//        backButton.setBounds(centerX, startY + 280, 200, 40);
//        backButton.addActionListener(e -> goBack());
//        menuPanel.add(backButton);
//    }
//
//    private @NotNull JSlider createSlider(String name, int x, int y) {
//        JLabel label = new JLabel(name);
//        label.setBounds(x, y, 200, 20);
//        getMenuPanel().add(label);
//
//        JSlider slider = new JSlider(0, 100);
//        slider.setBounds(x, y + 20, 200, 40);
//        slider.setMajorTickSpacing(25);
//        slider.setPaintTicks(true);
//        slider.setPaintLabels(true);
//
//        if (name.equals("Music Volume")) {
//            slider.setValue(setting.getMusicVolume());
//        } else {
//            slider.setValue(setting.getSoundVolume());
//        }
//
//        return slider;
//    }
//
//    private void applySettings() {
//        setting.setMusicVolume(musicSlider.getValue());
//        setting.setSoundVolume(soundSlider.getValue());
//        setting.setSetFullScreen(fullScreenToggle.isSelected());
//
//        // Add persistence logic if needed (save to file, etc.)
//        System.out.println("Settings applied: Music=" + setting.getMusicVolume() +
//                ", Sound=" + setting.getSoundVolume() +
//                ", Fullscreen=" + setting.isSetFullScreen());
//    }
//
//    public void displayComponents() {
//        getMenuPanel().setVisible(true);
//    }
//
//    public void update() {
//        // If settings are changed elsewhere and need to reflect here
//        musicSlider.setValue(setting.getMusicVolume());
//        soundSlider.setValue(setting.getSoundVolume());
//        fullScreenToggle.setSelected(setting.isSetFullScreen());
//    }
//
//    @Override
//    public void goBack() {
//        gameWindow.showScreen("MainMenu");
//    }
//
//    @Override
//    public void load() {
//        renderMenu(getMenuPanel());
//    }
//}
