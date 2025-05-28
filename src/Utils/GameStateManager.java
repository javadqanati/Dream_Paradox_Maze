package Utils;

public class GameStateManager {
    private enum State {
        PLAY,
        PAUSE,
        MENU,
        SETTINGS,
        LOAD,
        INVENTORY,
        MARKET,
        GAMEOVER,
        STORY
    }

    private State currentState = State.PLAY;
    public boolean isPlaying() {
        return currentState == State.PLAY;
    }
    public boolean isPaused() {
        return currentState == State.PAUSE;
    }
    public boolean isMenu() {
        return currentState == State.MENU;
    }
    public void setMenu() {
        currentState = State.MENU;
    }
    public void setPlay() {
        currentState = State.PLAY;
    }
    public void setPause() {
        currentState = State.PAUSE;
    }
    public String getStateName() {
        return currentState.name();
    }
    public boolean isSettings() {
        return currentState == State.SETTINGS;
    }
    public void setSettings() {
        currentState = State.SETTINGS;
    }
    public void setLoad() {
        currentState = State.LOAD;
    }
    public boolean isInventory() {
        return currentState == State.INVENTORY;
    }
    public void setInventory() {
        currentState = State.INVENTORY;
    }
    public void setMarket() {
        currentState = State.MARKET;
    }
    public void setGameOver() {
        currentState = State.GAMEOVER;
    }
    public boolean isStory() {
        return currentState == State.STORY;
    }
    public void setStory() {
        currentState = State.STORY;
    }
}
