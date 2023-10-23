import java.awt.*;

public class ItemDrops extends Rectangle {
int radius;
    ItemDrops(int x, int y, int radius){
        this.radius = radius;
        this.x = x;
        this.y = y;
        this.width = radius*2;
        this.height = radius*2;
    }

    public void draw(Graphics2D g){
        g.setColor(Color.BLUE);
        g.fillOval(x, y, radius*2, radius*2);
    }

    public void update(){

    }

}
