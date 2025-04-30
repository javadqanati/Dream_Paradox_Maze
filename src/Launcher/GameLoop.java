package Launcher;

public class GameLoop implements Runnable {
    public interface LoopListener {
        void update();
        void render();
    }

    private final LoopListener listener;
    private final int fps;
    private Thread thread;

    public GameLoop(LoopListener listener, int fps) {
        this.listener = listener;
        this.fps = fps;
    }

    public void start() {
        thread = new Thread(this, "GameLoop");
        thread.start();
    }

    public void stop() {
        thread = null;
    }

    @Override
    public void run() {
        double drawInterval = 1_000_000_000.0 / fps;
        double delta = 0;
        long lastTime = System.nanoTime();
        long timer = 0, drawCount = 0;

        while (Thread.currentThread() == thread) {
            long now = System.nanoTime();
            delta += (now - lastTime) / drawInterval;
            timer += (now - lastTime);
            lastTime = now;

            if (delta >= 1) {
                listener.update();
                listener.render();
                delta--;
                drawCount++;
            }

            if (timer >= 1_000_000_000) {
                System.out.println("FPS: " + drawCount);
                drawCount = 0;
                timer = 0;
            }
        }
    }
}
