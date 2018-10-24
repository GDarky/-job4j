package dstelmachenko.multithreading;

import javafx.scene.shape.Rectangle;

public class RectangleMove implements Runnable {
    private final Rectangle rect;
    private int delta = 1;

    public RectangleMove(Rectangle rect) {
        this.rect = rect;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            delta = checkCollision();
            this.rect.setX(this.rect.getX() + delta);
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
            }
        }
    }

    private int checkCollision() {
        if ((this.rect.getX() >= 300) || (this.rect.getX() <= 0)) {
            delta = -delta;
        }
        return delta;
    }
}
