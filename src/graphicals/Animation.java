package graphicals;

import java.util.List;

class Animation {
    private List<Sprite> frames;
    private int currentFrame;
    private int frameDuration;
    private int timer;

    public Animation(List<Sprite> frames, int frameDuration) {
        this.frames = frames;
        this.frameDuration = frameDuration;
        this.currentFrame = 0;
        this.timer = 0;
    }

    public void update() {
        timer++;
        if (timer >= frameDuration) {
            timer = 0;
            currentFrame = (currentFrame + 1) % frames.size();
        }
    }

    public Sprite getCurrentFrame() {
        return frames.get(currentFrame);
    }

    public void reset() {
        currentFrame = 0;
        timer = 0;
    }

    public void setFrames(List<Sprite> newFrames) {
        this.frames = newFrames;
        reset();
    }

    public void setFrameDuration(int frameDuration) {
        this.frameDuration = frameDuration;
    }

    public int getCurrentFrameIndex() {
        return currentFrame;
    }
}

