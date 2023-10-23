import java.awt.*;

public class MinusCoin {
    int x, y, value;
    int frameTimer = 100;
    MinusCoin(int x, int y, int value){
        this.x = x;
        this.y = y;
        this.value = value;
    }
    public void draw(Graphics2D g){
        g.setColor(Color.YELLOW);
        g.setFont(g.getFont().deriveFont(8.0f));
        g.drawString("-"+value, x, y);
    }
    public void move(){
        frameTimer--;
        y --;
    }
    public boolean IsAlive(){
        return frameTimer > 0;
    }
}
