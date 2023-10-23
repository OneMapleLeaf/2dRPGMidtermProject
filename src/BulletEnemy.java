import java.awt.*;
import java.awt.image.BufferedImage;

public class BulletEnemy {
    Tool tool;
    Player player;
    BufferedImage Shoot, currentImage;
    int currentBulletNum;
    int frameCounter;
    float x, y, radius;
    private final BufferedImage Bullet;

    public BulletEnemy(float x, float y, float radius, BufferedImage Bullet) {
        this.radius = radius;
        this.x = x;
        this.y = y;
        this.Bullet = Bullet;
        setValue();
    }

    public void setValue() {
        currentImage = Bullet.getSubimage(0, 0, (int) radius * 2, (int) radius * 2);
    }

    public void draw(Graphics2D g) {
        g.drawImage(currentImage, (int) x, (int) y, (int) radius * 2, (int) radius * 2, null);
    }

    public void Move(float vel, double angle) {
        this.y += (float) (Math.sin(angle) * vel);
        this.x += (float) (Math.cos(angle) * vel);
        switch (currentBulletNum) {
            case 1 -> currentImage = Bullet.getSubimage(256, 64, 16, 16);
            case 2 -> currentImage = Bullet.getSubimage(256 + 16, 64, 16, 16);
            case 3 -> currentImage = Bullet.getSubimage(256 + 32, 64, 16, 16);
            case 4 -> currentImage = Bullet.getSubimage(256 + 48, 64, 16, 16);
        }
        frameCounter++;
        if (frameCounter > 5) {
            frameCounter = 0;
            currentBulletNum++;
        }
        if (currentBulletNum > 4) {
            currentBulletNum = 0;
        }
    }

    public boolean isAlive() {
        return (!(x > 1000 || x < -50 || y > 700 || y < -50));
    }


    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getRadiusWH(){
        return radius * 2;
    }
}