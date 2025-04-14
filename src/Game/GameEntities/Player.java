package Game.GameEntities;

import java.util.List;

public class Player extends Character{
    private int lives = 1;
    private int collectedFragments;
    private List<PowerUp> powerUps;

    public Player(int collectedFragments, int lives, List<PowerUp> powerUps) {
        this.collectedFragments = collectedFragments;
        this.lives = lives;
        this.powerUps = (List<PowerUp>) powerUps;
    }

    public void collectFragment(){
        collectedFragments += 1;
    }

    public boolean usePowerUp(PowerUp powerUp){
        if (powerUps.contains(powerUp)) {
            powerUp.apply();  // Assuming activate() method exists in PowerUp
            powerUps.remove(powerUp);  // Optional: if one-time use
            return true;
        }
        return false;
    }

    public int getCollectedFragments() {
        return collectedFragments;
    }

    public int getLives() {
        return lives;
    }

    public List<PowerUp> getPowerUps() {
        return powerUps;
    }
}
