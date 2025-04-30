
package graphicals;

import Game.GameEntities.Enemy;
import Launcher.GamePanel;
import Game.GameEntities.Character;
import java.awt.Rectangle;
import java.util.List;

public class CollisionChecker {
    private final GamePanel gp;

    public CollisionChecker(GamePanel gp) {
        this.gp = gp;
    }

    private Rectangle projectedArea(Character character) {
        Rectangle sa = character.getSolidArea();
        int x = character.getWorldX() + sa.x;
        int y = character.getWorldY() + sa.y;
        Rectangle future = new Rectangle(x, y, sa.width, sa.height);
        int speed = character.getSpeed();
        switch (character.getDirection()) {
            case UP    -> future.y -= speed;
            case DOWN  -> future.y += speed;
            case LEFT  -> future.x -= speed;
            case RIGHT -> future.x += speed;
        }
        return future;
    }

    public void checkTile(Character character) {
        Rectangle area = projectedArea(character);
        int tileSize = gp.getTileSize();
        int leftCol   = area.x / tileSize;
        int rightCol  = (area.x + area.width  - 1) / tileSize;
        int topRow    = area.y / tileSize;
        int bottomRow = (area.y + area.height - 1) / tileSize;

        var map   = gp.getMaze().getMapTileNum();
        var tiles = gp.getMaze().getTile();

        boolean collision = false;
        switch (character.getDirection()) {
            case UP -> collision =
                    tiles[map[leftCol][topRow]].isPassable() ||
                            tiles[map[rightCol][topRow]].isPassable();
            case DOWN -> collision =
                    tiles[map[leftCol][bottomRow]].isPassable() ||
                            tiles[map[rightCol][bottomRow]].isPassable();
            case LEFT -> collision =
                    tiles[map[leftCol][topRow]].isPassable() ||
                            tiles[map[leftCol][bottomRow]].isPassable();
            case RIGHT -> collision =
                    tiles[map[rightCol][topRow]].isPassable() ||
                            tiles[map[rightCol][bottomRow]].isPassable();
        }
        if (collision) {
            character.setCollisionOn(true);
        }
    }

    public int checkObject(Character character, boolean player) {
        Rectangle area = projectedArea(character);
        int index = 999;
        var entities = gp.getEntitySetter().getEntities();

        for (int i = 0; i < entities.size(); i++) {
            var e = entities.get(i);
            if (e == null) continue;

            Rectangle other = new Rectangle(
                    e.getWorldX() + e.getSolidArea().x,
                    e.getWorldY() + e.getSolidArea().y,
                    e.getSolidArea().width,
                    e.getSolidArea().height
            );

            if (area.intersects(other)) {
                if (!e.isPassable()) {
                    character.setCollisionOn(true);
                }
                if (player) {
                    index = i;
                }
            }
        }
        return index;
    }

    public int checkEntity(Character character, List<Enemy> targets) {
        Rectangle area = projectedArea(character);
        int index = 999;

        for (int i = 0; i < targets.size(); i++) {
            var t = targets.get(i);
            if (t == null) continue;

            Rectangle other = new Rectangle(
                    t.getWorldX() + t.getSolidArea().x,
                    t.getWorldY() + t.getSolidArea().y,
                    t.getSolidArea().width,
                    t.getSolidArea().height
            );

            if (area.intersects(other)) {
                character.setCollisionOn(true);
                index = i;
            }
        }
        return index;
    }

    public boolean checkPlayer(Character character) {
        Rectangle area = projectedArea(character);
        var player = gp.getPlayer();
        Rectangle other = new Rectangle(
                player.getWorldX() + player.getSolidArea().x,
                player.getWorldY() + player.getSolidArea().y,
                player.getSolidArea().width,
                player.getSolidArea().height
        );

        if (area.intersects(other)) {
            character.setCollisionOn(true);
            return true;
        }
        return false;
    }
}
