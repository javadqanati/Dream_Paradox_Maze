
package graphicals;

import Game.GameEntities.*;
import Game.GameEntities.Character;
import Launcher.GamePanel;
import java.awt.Rectangle;
import java.util.List;

import static Game.GameEntities.Entity.*;

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
        DirectionType dir = character.getDirection();
        if (dir == UP()) {
            future.y -= speed;
        } else if (dir == DOWN()) {
            future.y += speed;
        } else if (dir == LEFT()) {
            future.x -= speed;
        } else if (dir == RIGHT()) {
            future.x += speed;
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
        DirectionType dir = character.getDirection();

        if (dir == UP()) {
            collision = tiles[map[leftCol][topRow]].isPassable() ||
                    tiles[map[rightCol][topRow]].isPassable();
        } else if (dir == DOWN()) {
            collision = tiles[map[leftCol][bottomRow]].isPassable() ||
                    tiles[map[rightCol][bottomRow]].isPassable();
        } else if (dir == LEFT()) {
            collision = tiles[map[leftCol][topRow]].isPassable() ||
                    tiles[map[leftCol][bottomRow]].isPassable();
        } else if (dir == RIGHT()) {
            collision = tiles[map[rightCol][topRow]].isPassable() ||
                    tiles[map[rightCol][bottomRow]].isPassable();
        }
        if (collision) {
            character.setCollisionOn(true);
        }
    }

    public void checkObject(Character character, boolean player) {
        Rectangle area = projectedArea(character);
        var entities = gp.getEntityManager().getEntities();
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
                if (player && e instanceof Interactable interactable && character instanceof Player p) {
                    interactable.onPlayerInteract(p);
                }
                if (!e.isPassable()) {
                    character.setCollisionOn(true);
                }
            }
        }
    }

    public void checkEntity(Character character, List<Enemy> targets) {
        Rectangle area = projectedArea(character);

        for (Enemy t : targets) {
            if (t == null) continue;

            Rectangle other = new Rectangle(
                    t.getWorldX() + t.getSolidArea().x,
                    t.getWorldY() + t.getSolidArea().y,
                    t.getSolidArea().width,
                    t.getSolidArea().height
            );

            if (area.intersects(other)) {
                character.setCollisionOn(true);
            }
        }
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
