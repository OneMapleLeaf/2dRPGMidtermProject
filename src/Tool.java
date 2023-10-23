public class Tool {
    int MilliSec1 = 0;
    int MilliSec2 = 0;
    public void setMilliSecCounter(int Milli){
        MilliSec1 += Milli;
        MilliSec2 += Milli;
    }
    public void ResetMilliCounter(){
        MilliSec1 = 0;
    }
    public void ResetMilliCounter2(){
        MilliSec2 = 0;
    }

    public int GetMilli(){
        return MilliSec1;
    }
    public int GetMilli2(){return MilliSec2;}
    public int GetRandomNum(int min, int max){
        return (int) (Math.random() * (max - min + 1) + min);
    }
    public boolean CircleCollision(Circle c1, Circle c2) {
        double radii_sum = c1.GetRadius() + c2.GetRadius();
        int radii_difference = (c1.GetRadius() - c2.GetRadius());
        double length = Math.sqrt(Math.pow(c2.GetX() - c1.GetX() - radii_difference, 2) + Math.pow(c2.GetY() - c1.GetY() - radii_difference, 2) * 1.0f);
        return length <= radii_sum;
    }
    public double GetCircleOverlap(Circle c1, Circle c2){
        int radii_difference = (c1.GetRadius() - c2.GetRadius());
        double length = Math.sqrt(Math.pow(c2.GetX() - c1.GetX() - radii_difference, 2) + Math.pow(c2.GetY() - c1.GetY() - radii_difference, 2) * 1.0f);
        return c1.GetRadius() + c2.GetRadius() - length;
    }
    public double GetCircleDist(Circle c1, Circle c2){
        int radii_difference = (c1.GetRadius() - c2.GetRadius());
        return Math.sqrt(Math.pow(c2.GetX() - c1.GetX() - radii_difference, 2) + Math.pow(c2.GetY() - c1.GetY() - radii_difference, 2) * 1.0f);
    }
}