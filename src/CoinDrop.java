import java.awt.*;
import java.awt.image.BufferedImage;

public class CoinDrop{
    int radius;
    BufferedImage Coin;
    BufferedImage CurrentImage;
    int FrameNum = 0;
    int CurrentImageNum = 1;
    float x, y;
    CoinDrop(float x, float y, int radius, BufferedImage Coin){     //coin properties
        this.radius = radius;
        this.x = x;
        this.y = y;
        this.Coin = Coin;

    }

    public void draw(Graphics2D g){
        //placeholder of sprite
      g.drawImage(CurrentImage, (int)x, (int)y, radius*2, radius*2,null);
    }

    public void update(){
    Animation();
    }
    private void Animation(){
        FrameNum++;
        if (FrameNum > 4){
            FrameNum = 0;
            CurrentImageNum++;
        }
        if(CurrentImageNum > 7){
            CurrentImageNum = 1;
        }
        switch (CurrentImageNum){
            case 1 -> CurrentImage = Coin.getSubimage(177, 18, 16,16);
            case 2 -> CurrentImage = Coin.getSubimage(193, 18, 16,16);
            case 3 -> CurrentImage = Coin.getSubimage(209, 18, 16,16);
            case 4 -> CurrentImage = Coin.getSubimage(225, 18, 16,16);
            case 5 -> CurrentImage = Coin.getSubimage(241, 18, 16,16);
            case 6 -> CurrentImage = Coin.getSubimage(257, 18, 16,16);
            case 7 -> CurrentImage = Coin.getSubimage(273, 18, 16,16);
        }
    }
    public void move(int vel, double angle){
        this.x += (float) (Math.cos(angle) * vel);
        this.y += (float) (Math.sin(angle) * vel);
    }

    public float getY() {
        return y;
    }

    public float getX() {
        return x;
    }

    public int getRadiusWH() {
        return radius * 2;
    }
}
