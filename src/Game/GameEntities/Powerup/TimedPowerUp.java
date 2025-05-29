package Game.GameEntities.Powerup;

import javax.swing.Timer;

public interface TimedPowerUp {

    int getDurationMs();
    void onStart();
    void onExpire();

    default void applyTimed() {
        onStart();
        System.out.println("apply timed function");
        Timer t = new Timer(getDurationMs(), e -> {
            onExpire();
            ((Timer)e.getSource()).stop();
        });
        t.setRepeats(false);
        t.start();
    }
}
