package Game.GameEntities;

import Launcher.GamePanel;
import graphicals.SpriteMaker;
import java.awt.image.BufferedImage;

public class FireBall extends Projectile {
    public FireBall(GamePanel gp) {
        super(gp, 5);
        setName("FireBall");
        setMaxHealth(1);
        getEnemyImage();
    }

    @Override
    public void getEnemyImage() {
        loadSprites();
    }

    @Override
    public void setAction() {
        attack();
    }

    private void loadSprites() {
        SpriteMaker maker = new SpriteMaker(getGp());
        BufferedImage up1   = maker.characterSkinSetup("/Projectile/fireball_up_1");
        BufferedImage up2   = maker.characterSkinSetup("/Projectile/fireball_up_2");
        BufferedImage down1 = maker.characterSkinSetup("/Projectile/fireball_down_1");
        BufferedImage down2 = maker.characterSkinSetup("/Projectile/fireball_down_2");
        BufferedImage left1 = maker.characterSkinSetup("/Projectile/fireball_left_1");
        BufferedImage left2 = maker.characterSkinSetup("/Projectile/fireball_left_2");
        BufferedImage right1= maker.characterSkinSetup("/Projectile/fireball_right_1");
        BufferedImage right2= maker.characterSkinSetup("/Projectile/fireball_right_2");

        setSpriteFrames(Direction.UP,    up1,   up2);
        setSpriteFrames(Direction.DOWN,  down1, down2);
        setSpriteFrames(Direction.LEFT,  left1, left2);
        setSpriteFrames(Direction.RIGHT, right1,right2);
    }

    @Override
    public void attack() {
        getGp().getPlayer().setHealth(getGp().getPlayer().getHealth() - 1);
        getGp().getPlayer().setInvincible(true);
    }
}
