import java.awt.*;
import java.awt.image.BufferedImage;

public class Button extends Rectangle {

    private BufferedImage Image;

    Button(int x, int y, int width, int height, BufferedImage Image) {

        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.Image = Image;
    }


    public boolean ButtonReady(InputHandler inputHandler) {
        Rectangle Mouse = new Rectangle(inputHandler.getX(), inputHandler.getY(), 1, 1);
        if (this.intersects(Mouse)) {
            return inputHandler.CLicked();
        }
        return false;
    }

    public void draw(Graphics2D g) {

        g.drawImage(Image, this.x, this.y, this.width, this.height, null);
    }

}