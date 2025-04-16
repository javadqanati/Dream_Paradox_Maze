package Game.GameEntities;

import Input.InputHandler;
import Launcher.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;

public class Player extends Character{
    private int lives = 1;
    private int collectedFragments;
    private List<PowerUp> powerUps;
    private GamePanel gp;
    private InputHandler input;
    public final int screenX;
    public final int screenY;

    public Player(GamePanel gp, InputHandler input) {

        this.gp = gp;
        this.input = input;
        setDefaultValues();
        getPlayerImage();
        this.setDirection("down");
        screenX= gp.getScreenWidth() /2 - (gp.getTileSize()/2);
        screenY= gp.getScreenHeight() /2- (gp.getTileSize()/2);

        setSolidArea(new Rectangle());
        getSolidArea().x = 8;
        getSolidArea().y = 16;
        getSolidArea().width = 32;
        getSolidArea().height = 32;
    }

    public void setDefaultValues(){
        setWorldX(23 * gp.getTileSize());
        setWorldY(21 * gp.getTileSize());
        setSpeed(4);  // Initialize speed here
        setSpriteNum(1);
        setDirection("down");
    }

    public void getPlayerImage(){
        try{
            setUp1(ImageIO.read(getClass().getResourceAsStream("/Player/boy_up_1.png")));
            setUp2(ImageIO.read(getClass().getResourceAsStream("/Player/boy_up_2.png")));
            setDown1(ImageIO.read(getClass().getResourceAsStream("/Player/boy_down_1.png")));
            setDown2(ImageIO.read(getClass().getResourceAsStream("/Player/boy_down_2.png")));
            setRight1(ImageIO.read(getClass().getResourceAsStream("/Player/boy_right_1.png")));
            setRight2(ImageIO.read(getClass().getResourceAsStream("/Player/boy_right_2.png")));
            setLeft1(ImageIO.read(getClass().getResourceAsStream("/Player/boy_left_1.png")));
            setLeft2(ImageIO.read(getClass().getResourceAsStream("/Player/boy_left_2.png")));
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void collectFragment(){
        collectedFragments += 1;
    }

    public void update(){
        if(input.isUpPressed() || input.isDownPressed() || input.isLeftPressed() || input.isRightPressed()){
            if (input.isUpPressed()){
                setWorldY(getWorldY() - getSpeed());
                setDirection("up");
            } else if (input.isDownPressed()){
                setWorldY(getWorldY() + getSpeed());
                setDirection("down");
            } else if (input.isLeftPressed()){
                setWorldX(getWorldX() - getSpeed());
                setDirection("left");
            } else if (input.isRightPressed()){
                setWorldX(getWorldX() + getSpeed());
                setDirection("right");
            }

            setSpriteCounter(getSpriteCounter() + 1);
            if(getSpriteCounter()>10){
                if(getSpriteNum()==1){
                    setSpriteNum(2);
                }
                else if(getSpriteNum()==2){
                    setSpriteNum(1);
                }
                setSpriteCounter(0);
            }

            setCollisionOn(false);
            gp.getCollisionChecker().checkTile(this);
        }
    }

    public boolean usePowerUp(PowerUp powerUp){
        if (powerUps.contains(powerUp)) {
            powerUp.apply();  // Assuming activate() method exists in PowerUp
            powerUps.remove(powerUp);  // Optional: if one-time use
            return true;
        }
        return false;
    }

    @Override
    public void draw(Graphics2D g2){
        BufferedImage img = null;

        switch(getDirection()) {
            case "up":
                if (getSpriteNum() == 1) {
                    img = getUp1();
                }
                if (getSpriteNum() == 2) {
                    img = getUp2();
                }

                break;
            case "down":
                if (getSpriteNum() == 1) {
                    img = getDown1();
                }
                if (getSpriteNum() == 2) {
                    img = getDown2();
                }
                break;
            case "left":
                if (getSpriteNum() == 1) {
                    img = getLeft1();
                }
                if (getSpriteNum() == 2) {
                    img = getLeft2();
                }
                break;
            case "right":
                if (getSpriteNum() == 1) {
                    img = getRight1();
                }
                if (getSpriteNum() == 2) {
                    img = getRight2();
                }
                break;
        }
        g2.drawImage(img, screenX, screenY, gp.getTileSize(), gp.getTileSize(), null);

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
