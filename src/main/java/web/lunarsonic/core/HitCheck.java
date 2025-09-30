package web.lunarsonic.core;

public class HitCheck {
    private final float x;
    private final int y;
    private final float r;

    public HitCheck(float x, int y, float r) {
        this.x = x;
        this.y = y;
        this.r = r;
    }

    public boolean wasThereHit() {
        return quarterWithTriangle() || quarterWithSector() || quarterWithRectangle();
    }

    private boolean quarterWithTriangle() {
        if (x <= 0 && y >= 0) {
            return y <= x + r;
        }
        return false;
    }

    private boolean quarterWithSector() {
        if (x > 0 && y <= 0) {
            return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2)) <= r;
        }
        return false;
    }

    private boolean quarterWithRectangle() {
        if (x >= 0 && y >= 0) {
            return (x <= r) && (y < r / 2);
        }
        return false;
    }
}
