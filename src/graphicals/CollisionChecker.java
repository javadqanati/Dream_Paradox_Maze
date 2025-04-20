package graphicals;

import Game.GameEntities.GameEntities;
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
                if(gp.getMaze().getTile()[tileNUm1].isPassable() || gp.getMaze().getTile()[tileNum2].isPassable()) {
                    character.setCollisionOn(true);
                }
                break;
            case "down":
                entityBottomRow = (charButtonY - character.getSpeed()) / gp.getTileSize();
                tileNUm1 = gp.getMaze().getMapTileNum()[entityLeftCol][entityBottomRow];
                tileNum2 = gp.getMaze().getMapTileNum()[entityRightCol][entityBottomRow];
                if(gp.getMaze().getTile()[tileNUm1].isPassable() || gp.getMaze().getTile()[tileNum2].isPassable()) {
                    character.setCollisionOn(true);
                }
                break;
            case "left":
                entityLeftCol = (charLeftX - character.getSpeed()) / gp.getTileSize();
                tileNUm1 = gp.getMaze().getMapTileNum()[entityLeftCol][entityTopRow];
                tileNum2 = gp.getMaze().getMapTileNum()[entityLeftCol][entityBottomRow];
                if(gp.getMaze().getTile()[tileNUm1].isPassable() || gp.getMaze().getTile()[tileNum2].isPassable()) {
                    character.setCollisionOn(true);
                }
                break;
            case "right":
                entityRightCol = (charRightX - character.getSpeed()) / gp.getTileSize();
                tileNUm1 = gp.getMaze().getMapTileNum()[entityRightCol][entityTopRow];
                tileNum2 = gp.getMaze().getMapTileNum()[entityRightCol][entityBottomRow];
                if(gp.getMaze().getTile()[tileNUm1].isPassable() || gp.getMaze().getTile()[tileNum2].isPassable()) {
                    character.setCollisionOn(true);
                }
                break;
        }

    }

    public int checkEntity(Character character, boolean player) {
        int index = 999;

        for(int i = 0; i < gp.getGameEntities().length; i++) {
            if(gp.getGameEntities()[i] != null) {
                character.getSolidArea().x = character.getWorldX() + character.getSolidArea().x;
                character.getSolidArea().y = character.getWorldY() + character.getSolidArea().y;
                gp.getGameEntities()[i].getSolidArea().x = gp.getGameEntities()[i].getWorldX() + gp.getGameEntities()[i].getSolidArea().x;
                gp.getGameEntities()[i].getSolidArea().y = gp.getGameEntities()[i].getWorldY() + gp.getGameEntities()[i].getSolidArea().y;

                switch (character.getDirection()){
                    case "up":
                        character.getSolidArea().y -= character.getSpeed();
                        if(character.getSolidArea().intersects(gp.getGameEntities()[i].getSolidArea())) {
                            if(gp.getGameEntities()[i].isPassable()){
                                character.setCollisionOn(true);
                            }
                            if(player){
                                index = i;
                            }
                        }
                        break;
                    case "down":
                        character.getSolidArea().y += character.getSpeed();
                        if(character.getSolidArea().intersects(gp.getGameEntities()[i].getSolidArea())) {
                            if(gp.getGameEntities()[i].isPassable()){
                                character.setCollisionOn(true);
                            }
                            if(player){
                                index = i;
                            }
                        }
                        break;
                    case "left":
                        character.getSolidArea().x -= character.getSpeed();
                        if(character.getSolidArea().intersects(gp.getGameEntities()[i].getSolidArea())) {
                            if(gp.getGameEntities()[i].isPassable()){
                                character.setCollisionOn(true);
                            }
                            if(player){
                                index = i;
                            }
                        }
                        break;
                    case "right":
                        character.getSolidArea().x += character.getSpeed();
                        if(character.getSolidArea().intersects(gp.getGameEntities()[i].getSolidArea())) {
                            if(gp.getGameEntities()[i].isPassable()){
                                character.setCollisionOn(true);
                            }
                            if(player){
                                index = i;
                            }
                        }
                        break;
                }
                character.getSolidArea().x = character.getSolidAreaDefaultX();
                character.getSolidArea().y = character.getSolidAreaDefaultY();
                gp.getGameEntities()[i].getSolidArea().x = gp.getGameEntities()[i].getSolidAreaX();
                gp.getGameEntities()[i].getSolidArea().y = gp.getGameEntities()[i].getSolidAreaY();
            }

        }
        return index;

    }
}
