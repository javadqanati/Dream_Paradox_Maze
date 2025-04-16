package graphicals;

import Launcher.GamePanel;
import Game.GameEntities.Character;

public class CollisionChecker {
    private final GamePanel gp;

    public CollisionChecker(GamePanel gp) {
        this.gp = gp;
    }

    public void checkTile(Character character) {
        int charLeftX = character.getWorldX() + character.getSolidArea().x;
        int charRightX = character.getWorldX() + character.getSolidArea().x + character.getSolidArea().width;
        int charTopY = character.getWorldY() + character.getSolidArea().y;
        int charButtomY = character.getWorldY() + character.getSolidArea().y + character.getSolidArea().height;

    }
}
