import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Bullet {
    private float x;
    private float y;
    private final float scale;
    private final BufferedImage Bullet;
    private BufferedImage CurrentFrame;
    private int CurrentImageNum = 1, CurrentImageDisappearNum = 1, frameCounterD = 0;
    private int frameCounter = 0;
    private boolean rotating = true;
    private int Mx, My;
    private float Rx, Ry;
    private int yImage = 16 * 4;
    private  int xImage = 256;
    private  int ShowNum = 0;
    private boolean movement = true;
    private boolean Collided = false;

    Bullet(float x, float y, float scale, BufferedImage Bullet){
        this.scale = scale;
        this.x = x;
        this.y = y;
        this.Bullet = Bullet;
        SetValue();
    }
    public void draw(Graphics2D g, int mouseX, int mouseY){
        ShowNum++;
        if (!(ShowNum > 3)) return;
        if (rotating){
            Mx = mouseX;
            My = mouseY;
            Rx = x;
            Ry = y;
            rotating = false;
        }
        AffineTransform at = AffineTransform.getTranslateInstance(x, y);
            at.rotate(Math.atan2(My - Ry, Mx - Rx), 8 * scale, 8 * scale);
        at.scale(scale, scale);
        g.drawImage(CurrentFrame, at, null);
    }

    private void SetValue(){
        CurrentFrame = Bullet.getSubimage(0, 17, 16, 16);
    }

    public void Move(float vel, double angle){
        if(!movement) return;
        this.y += (float) (Math.sin(angle) * vel);
        this.x += (float) (Math.cos(angle) * vel);

        switch (CurrentImageNum){
            case 1 -> CurrentFrame = Bullet.getSubimage(xImage, yImage, 16, 16);
            case 2 -> CurrentFrame = Bullet.getSubimage(xImage + (16), yImage, 16, 16);
            case 3 -> CurrentFrame = Bullet.getSubimage(xImage + (16 * 2), yImage, 16, 16);
            case 4 -> CurrentFrame = Bullet.getSubimage(xImage + (16 * 3), yImage, 16, 16);
        }
        frameCounter++;
        if (frameCounter > 5){
            frameCounter = 0;
            CurrentImageNum++;
        }
        if (CurrentImageNum > 4) CurrentImageNum = 1;
    }
    public boolean DisappearAnimation(){
        switch (CurrentImageDisappearNum){
            case 1 -> CurrentFrame = Bullet.getSubimage(16 * 15, 16 * 18, 16, 16);
            case 2 -> CurrentFrame = Bullet.getSubimage(16 * 16, 16 * 18, 16, 16);
        }
        frameCounterD++;
        if (frameCounterD > 5){
            frameCounterD = 0;
            CurrentImageDisappearNum++;
        }
     return CurrentImageDisappearNum > 2;


    }
    public void SetIsCollided(boolean Collided){
        this.Collided = Collided;
    }
    public boolean IsCollided(){
        return this.Collided;
    }
    public boolean IsAlive(){
        return  (!(x > 960-64 ||x < 64 || y > 640-64 || y < 64));
    }
   public void Movement(boolean movement){
        this.movement = movement;
   }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }
}
