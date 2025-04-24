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
        int charButtonY = character.getWorldY() + character.getSolidArea().y + character.getSolidArea().height;

        int entityLeftCol = charLeftX / gp.getTileSize();
        int entityRightCol = charRightX / gp.getTileSize();
        int entityTopRow = charTopY / gp.getTileSize();
        int entityBottomRow = charButtonY / gp.getTileSize();

        int tileNUm1, tileNum2;

        switch (character.getDirection()) {
            case "up":
                entityTopRow = (charTopY - character.getSpeed()) / gp.getTileSize();
                tileNUm1 = gp.getMaze().getMapTileNum()[entityLeftCol][entityTopRow];
                tileNum2 = gp.getMaze().getMapTileNum()[entityRightCol][entityTopRow];
                if (gp.getMaze().getTile()[tileNUm1].isPassable() || gp.getMaze().getTile()[tileNum2].isPassable()) {
                    character.setCollisionOn(true);
                }
                break;
            case "down":
                entityBottomRow = (charButtonY - character.getSpeed()) / gp.getTileSize();
                tileNUm1 = gp.getMaze().getMapTileNum()[entityLeftCol][entityBottomRow];
                tileNum2 = gp.getMaze().getMapTileNum()[entityRightCol][entityBottomRow];
                if (gp.getMaze().getTile()[tileNUm1].isPassable() || gp.getMaze().getTile()[tileNum2].isPassable()) {
                    character.setCollisionOn(true);
                }
                break;
            case "left":
                entityLeftCol = (charLeftX - character.getSpeed()) / gp.getTileSize();
                tileNUm1 = gp.getMaze().getMapTileNum()[entityLeftCol][entityTopRow];
                tileNum2 = gp.getMaze().getMapTileNum()[entityLeftCol][entityBottomRow];
                if (gp.getMaze().getTile()[tileNUm1].isPassable() || gp.getMaze().getTile()[tileNum2].isPassable()) {
                    character.setCollisionOn(true);
                }
                break;
            case "right":
                entityRightCol = (charRightX - character.getSpeed()) / gp.getTileSize();
                tileNUm1 = gp.getMaze().getMapTileNum()[entityRightCol][entityTopRow];
                tileNum2 = gp.getMaze().getMapTileNum()[entityRightCol][entityBottomRow];
                if (gp.getMaze().getTile()[tileNUm1].isPassable() || gp.getMaze().getTile()[tileNum2].isPassable()) {
                    character.setCollisionOn(true);
                }
                break;
        }

    }

    public int checkObject(Character character, boolean player) {
        int index = 999;

        for (int i = 0; i < gp.getGameEntities().length; i++) {
            if (gp.getGameEntities()[i] != null) {
                character.getSolidArea().x = character.getWorldX() + character.getSolidArea().x;
                character.getSolidArea().y = character.getWorldY() + character.getSolidArea().y;
                gp.getGameEntities()[i].getSolidArea().x = gp.getGameEntities()[i].getWorldX() + gp.getGameEntities()[i].getSolidArea().x;
                gp.getGameEntities()[i].getSolidArea().y = gp.getGameEntities()[i].getWorldY() + gp.getGameEntities()[i].getSolidArea().y;

                directionSwitch(character);
                if (character.getSolidArea().intersects(gp.getGameEntities()[i].getSolidArea())) {
                    if (gp.getGameEntities()[i].isPassable()) {
                        character.setCollisionOn(true);
                    }
                    if (player) {
                        index = i;
                    }
                }
                character.getSolidArea().x = character.getSolidAreaDefaultX();
                character.getSolidArea().y = character.getSolidAreaDefaultY();
                gp.getGameEntities()[i].getSolidArea().x = gp.getGameEntities()[i].getSolidAreaX();
                gp.getGameEntities()[i].getSolidArea().y = gp.getGameEntities()[i].getSolidAreaY();
            }

        }
        return index;
    }

    public int checkEntity(Character character, Character[] target) {
        int index = 999;

        for (int i = 0; i < target.length; i++) {
            if (target[i] != null) {
                character.getSolidArea().x = character.getWorldX() + character.getSolidArea().x;
                character.getSolidArea().y = character.getWorldY() + character.getSolidArea().y;

                target[i].getSolidArea().x = target[i].getWorldX() + target[i].getSolidArea().x;
                target[i].getSolidArea().y = target[i].getWorldY() + target[i].getSolidArea().y;

                directionSwitch(character);
                if (character.getSolidArea().intersects(target[i].getSolidArea())) {
                    character.setCollisionOn(true);
                    index = i;
                }
                character.getSolidArea().x = character.getSolidAreaDefaultX();
                character.getSolidArea().y = character.getSolidAreaDefaultY();
                target[i].getSolidArea().x = target[i].getSolidAreaX();
                target[i].getSolidArea().y = target[i].getSolidAreaY();
            }

        }
        return index;
    }

    public boolean checkPlayer(Character character) {
        boolean contactPlayer = false;

        character.getSolidArea().x = character.getWorldX() + character.getSolidArea().x;
        character.getSolidArea().y = character.getWorldY() + character.getSolidArea().y;

        gp.getPlayer().getSolidArea().x = gp.getPlayer().getWorldX() + gp.getPlayer().getSolidArea().x;
        gp.getPlayer().getSolidArea().y = gp.getPlayer().getWorldY() + gp.getPlayer().getSolidArea().y;

        directionSwitch(character);

        if (character.getSolidArea().intersects(gp.getPlayer().getSolidArea())) {
            character.setCollisionOn(true);
            contactPlayer = true;
        }
        character.getSolidArea().x = character.getSolidAreaDefaultX();
        character.getSolidArea().y = character.getSolidAreaDefaultY();
        gp.getPlayer().getSolidArea().x = gp.getPlayer().getSolidAreaX();
        gp.getPlayer().getSolidArea().y = gp.getPlayer().getSolidAreaY();
        return contactPlayer;
    }

    private void directionSwitch(Character character) {
        switch (character.getDirection()) {
            case "up":
                character.getSolidArea().y -= character.getSpeed();
                break;
            case "down":
                character.getSolidArea().y += character.getSpeed(); break;
            case "left": character.getSolidArea().x -= character.getSpeed(); break;
            case "right": character.getSolidArea().x += character.getSpeed(); break;
        }
    }
}