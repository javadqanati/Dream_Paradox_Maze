package Game.GameEntities;

import Launcher.GamePanel;

public final class Chiz extends Projectile {
    public Chiz(GamePanel gp) {
        super(gp, 4);
        setName("FireBall");
        setMaxHealth(1);
        getImages();
    }

    @Override
    public void getImages() {
        loadSprites("/Projectile/chiz_up1", "/Projectile/chiz_up2",
                "/Projectile/chiz_left1", "/Projectile/chiz_left2",
                "/Projectile/chiz_right1", "/Projectile/chiz_right2",
                "/Projectile/chiz_down1", "/Projectile/chiz_down2");
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
