import java.awt.*;

public class Circle extends Rectangle {
    int radius;
    Circle(int x, int y, int radius){
        this.x = x;
        this.y = y;
        this.radius = radius;
    }
    public int GetX(){
        return this.x;
    }
    public int GetY(){
        return this.y;
    }
    public int GetRadius(){
        return this.radius;
    }
    public int GetRadiusWH(){
        return this.radius * 2;
    }
}
