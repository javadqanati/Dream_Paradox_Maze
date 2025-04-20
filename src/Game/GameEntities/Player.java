package Game.GameEntities;

import Audio.AudioManager;
import Input.InputHandler;
import Launcher.GamePanel;
import graphicals.SpriteMaker;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;

public class Player extends Character{
    private int lives = 1;
    private int collectedFragments = 0;
    private List<PowerUp> powerUps;
    private InputHandler input;
    private final int screenX;
    private final int screenY;
    private final AudioManager audio = new AudioManager();

    public int getScreenY() {
        return screenY;
    }

    public int getScreenX() {
        return screenX;
    }

    public Player(GamePanel gp, InputHandler input) {
        super(gp);
        this.input = input;
        setDefaultValues();
        getPlayerImage();
        this.setDirection("down");
        screenX= gp.getScreenWidth() /2 - (gp.getTileSize()/2);
        screenY= gp.getScreenHeight() /2- (gp.getTileSize()/2);

        setSolidArea(new Rectangle());
        getSolidArea().x = 2;
        getSolidArea().y = 3;
        getSolidArea().width = 30;
        getSolidArea().height = 30;
        setSolidAreaDefaultX(getSolidArea().x);
        setSolidAreaDefaultY(getSolidArea().y);
    }

    public void setDefaultValues(){
        setWorldX(7 * getGp().getTileSize());
        setWorldY(4 * getGp().getTileSize());
        setSpeed(4);  // Initialize speed here
        setSpriteNum(1);
        setDirection("down");
    }

    public void getPlayerImage(){
        SpriteMaker spriteMaker = new SpriteMaker(getGp());

        setUp1(spriteMaker.characterSkinSetup("/Player/boy_up_1"));
        setUp2(spriteMaker.characterSkinSetup("/Player/boy_up_2"));
        setDown1(spriteMaker.characterSkinSetup("/Player/boy_down_1"));
        setDown2(spriteMaker.characterSkinSetup("/Player/boy_down_2"));
        setRight1(spriteMaker.characterSkinSetup("/Player/boy_right_1"));
        setRight2(spriteMaker.characterSkinSetup("/Player/boy_right_2"));
        setLeft1(spriteMaker.characterSkinSetup("/Player/boy_left_1"));
        setLeft2(spriteMaker.characterSkinSetup("/Player/boy_left_2"));
    }

    public void update(){
        if(input.isUpPressed() || input.isDownPressed() || input.isLeftPressed() || input.isRightPressed()){
            if (input.isUpPressed()){
                setDirection("up");
            } else if (input.isDownPressed()){
                setDirection("down");
            } else if (input.isLeftPressed()){
                setDirection("left");
            } else if (input.isRightPressed()){
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
            getGp().getCollisionChecker().checkTile(this);
            int entityIndex = getGp().getCollisionChecker().checkEntity(this, true);
            collectFragments(entityIndex);

            if(!isCollisionOn()){
                switch (getDirection()){
                    case "up":
                        setWorldY(getWorldY() - getSpeed());
                        break;
                    case "down":
                        setWorldY(getWorldY() + getSpeed());
                        break;
                    case "left":
                        setWorldX(getWorldX() - getSpeed());
                        break;
                    case "right":
                        setWorldX(getWorldX() + getSpeed());
                        break;
                }
            }
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
        g2.drawImage(img, screenX, screenY, null);

    }

    public void collectFragments(int i){
        if(i != 999){
            String name = getGp().getGameEntities()[i].getName();
            switch (name){
                case "Memory Fragment":
                    collectedFragments++;
                    getGp().getGameEntities()[i] = null;
                    System.out.println(collectedFragments);
                    audio.playSE(1);
                    break;
                case "Entrance":
                    audio.playSE(3);
                    break;
                case "Exit":
                    getGp().getHud().setGameFinished(true);
                    getGp().getAudioManager().playSE(4);
                    getGp().getAudioManager().stopMusic();
                    break;
                case "Speed Boost":
                    setSpeed(getSpeed() + 2);
                    getGp().getGameEntities()[i] = null;
                    audio.playSE(2);
                    getGp().getHud().showMessage("Speed Up!");
                    break;
            }
        }
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
