package Utils;

import java.text.DecimalFormat;

public class GameTimer {
    private final double startSeconds;
    private double remainingTime;
    private double accumulator;
    private final DecimalFormat df = new DecimalFormat("#0");
    private volatile boolean frozen = false;

    public GameTimer(double startSeconds) {
        this.startSeconds  = startSeconds;
        this.remainingTime = startSeconds;
        this.accumulator   = 0;
    }

    public void update(boolean isPlaying) {
        if (!isPlaying || isFinished() || frozen) return;

        accumulator += 1.0 / 60.0;
        if (accumulator >= 1.0) {
            remainingTime -= 1.0;
            accumulator  -= 1.0;
            if (remainingTime < 0) {
                remainingTime = 0;
            }
        }
    }

    public void freezeTime() {
        frozen = true;
    }

    public void unfreezeTime() {
        frozen = false;
    }

    public boolean isFinished() {
        return remainingTime <= 0;
    }

    public void reset() {
        remainingTime = startSeconds;
        accumulator   = 0;
    }

    public String getFormatted() {
        return df.format(remainingTime) + "s";
    }
}
