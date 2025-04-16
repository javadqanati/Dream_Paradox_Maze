//package UI;
//
//import Game.GameEntities.GameScreen;
//import Input.InputAction;
//import Launcher.GamePanel;
//
//import javax.swing.*;
//import java.awt.*;
//
//public class PauseMenu extends Menu implements GameScreen {
//    private GamePanelConfig config;
//    private Button resume;
//    private Button inventory;
//    private Button quit;
//
//    public PauseMenu(GamePanel gamePanel, GamePanelConfig config) {
//        super(gamePanel);
//        this.config=config;
//
//    }
//
//
//
//    @Override
//    public void renderMenu(JPanel menuPanel) {
//        menuPanel.setLayout(null);
//        int tileSize = config.getOriginalTileSize() * config.getScale();
//        int screenWidth = config.getMaxScreenCol() * tileSize;
//        int screenHeight = config.getMaxScreenRow() * tileSize;
//        int centerX = screenWidth / 2 - 100;
//        int startY = screenHeight / 4;
//
//
//        resume = new Button("Resume", 100, 40, centerX, startY,
//                new InputAction("resume", e -> resumeGame()));
//
//        inventory = new Button("Inventory", 100, 40, centerX, startY + 70,
//                new InputAction("inventory", e -> resumeGame()));
//
//        quit = new Button("Quit", 100, 40, centerX, startY + 100,
//                new InputAction("quit", e -> resumeGame()));
//
//        menuPanel.add(resume.getButton());
//        menuPanel.add(inventory.getButton());
//        menuPanel.add(quit.getButton());
//
//        addButton(resume);
//        addButton(inventory);
//        addButton(quit);
//    }
//
//    @Override
//    public void update() {
//
//    }
//
//    @Override
//    public void render(Graphics g) {
//        g.setColor(new Color(0, 0, 0, 150));
//        g.fillRect(0, 0, 800, 600);
//        g.setColor(Color.WHITE);
//        g.drawString("Game Paused", 350, 300);
//    }
//
//    @Override
//    public void load(){
//        renderMenu(getMenuPanel());
//    }
//
//    public void quitGame(){}
//    public void resumeGame(){}
//}
