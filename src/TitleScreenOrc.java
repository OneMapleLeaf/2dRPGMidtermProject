import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Objects;

public class TitleScreenOrc {
    BufferedImage CurrentImage, OrcLayout;

    Boolean MovingLeft = false, MovingRight = false;
    Tool tool;

    int ran, y, velx, vely, offset = 0, width, height, framenumani = 0, Currentimagenum = 1, x;

    TitleScreenOrc(int ran, int y, int width, int height, Tool tool) {
        this.ran = ran;
        this.y = y;
        this.width = width;
        this.height = height;
        this.tool = tool;
        setmovement();
        setvalue();
        if (ran == 1) {
            x = -100;
        }
        if (ran == 2) {
            x = 1000;
        }
    }

    public void setvalue() {
        try {
            OrcLayout = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("Pixel Crawler/Enemy/Orc Crew/Orc/Run/Run-Sheet.png")));
        } catch (Exception e) {
            e.printStackTrace();
        }
        CurrentImage = OrcLayout.getSubimage(0, 0, 32, 32);
    }

    public void draw(Graphics g) {
        g.drawImage(CurrentImage, x + offset, y, width, height, null);
    }

    public void update() {
        animation();
        logic();
    }

    public void animation() {
        framenumani++;
        if (framenumani >= 10) {
            framenumani = 0;
            Currentimagenum++;
            if (Currentimagenum > 6) {
                Currentimagenum = 1;
            }
        }
        switch (Currentimagenum) {
            case 1 -> CurrentImage = OrcLayout.getSubimage(16, 32, 32, 32);
            case 2 -> CurrentImage = OrcLayout.getSubimage(80, 32, 32, 32);
            case 3 -> CurrentImage = OrcLayout.getSubimage(144, 32, 32, 32);
            case 4 -> CurrentImage = OrcLayout.getSubimage(208, 32, 32, 32);
            case 5 -> CurrentImage = OrcLayout.getSubimage(272, 32, 32, 32);
            case 6 -> CurrentImage = OrcLayout.getSubimage(336, 32, 32, 32);

        }
    }

    public void setmovement() {
        switch (ran) {
            case 1 -> {
                MovingLeft = false;
                MovingRight = true;
            }
            case 2 -> {
                MovingLeft = true;
                MovingRight = false;
            }
        }
    }

    public void logic() {
        if (MovingLeft) {
            velx = -1;
            width = -96;
            offset = 96;
        }
        if (MovingRight) {
            velx = 1;
            width = 96;
            offset = 0;
        }
        x += velx;
        y += vely;

    }

    public boolean isAlive() {
        if (ran == 1) {
            return x < 1000;
        }
        if (ran == 2) {
            return x > -100;
        }
        return false;
    }
}

