package Game.GameStates;

public class GameStateManager {
    private enum State {
        PLAY,
        PAUSE,
        MENU,
        SETTINGS
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

    public State getCurrentState() {
        return currentState;
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
}
