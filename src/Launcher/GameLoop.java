package Launcher;

public class GameLoop implements Runnable {
    private static GamePanel gamePanel; // Static reference or use a setter
    private final Thread gameThread;
    private boolean running;

    public GameLoop() {
        this.gameThread = new Thread(this);
        this.running = false;
    }

    public static void setGamePanel(GamePanel panel) {
        gamePanel = panel;
    }

    @Override
    public void run() {
        long lastTime = System.nanoTime();
        final double amountOfTicks = 60.0;
        double ns = 1000000000.0 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;

        while (running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;

            while (delta >= 1) {
                if (gamePanel != null) {
                    gamePanel.updateGame();
                    gamePanel.repaint();
                }
                delta--;
            }

            frames++;
            if (System.currentTimeMillis() - timer >= 1000) {
                // Debug: Show FPS
                System.out.println("FPS: " + frames);
                frames = 0;
                timer += 1000;
            }

            try {
                Thread.sleep(2); // Light sleep
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void start() {
        if (!running) {
            running = true;
            gameThread.start();
        }
    }

    public void stop() {
        running = false;
        try {
            gameThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public Thread getGameThread() {
        return gameThread;
    }
}
