package Game.GameEntities;

import Launcher.GamePanel;

public final class FireBall extends Projectile {
    public FireBall(GamePanel gp) {
        super(gp, 5);
        setName("FireBall");
        setMaxHealth(1);
        getImages();
    }

    @Override
    public void getImages() {
        loadSprites("/Projectile/fireball_up_1", "/Projectile/fireball_up_2",
                "/Projectile/fireball_left_1", "/Projectile/fireball_left_2",
                "/Projectile/fireball_right_1", "/Projectile/fireball_right_2",
                "/Projectile/fireball_down_1", "/Projectile/fireball_down_2");
    }

    @Override
    public void setAction() {
        attack();
    }

    @Override
    public void attack() {
        getGp().getPlayer().setHealth(getGp().getPlayer().getHealth() - 1);
        Player.setInvincible(true);
    }
}
