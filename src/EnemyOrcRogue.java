import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Objects;

public class EnemyOrcRogue {
    Tool tool;
    BufferedImage currentImage;
    int CurrentImageNum = 1;
    boolean IsMovingLeft;
    boolean IsMovingRight;
    boolean IsMovingUp;
    boolean isMovingDown;
    boolean isIdling;
    boolean AniMove, AniIdle;
    boolean inAction = false;
    boolean isAggro = false;
    boolean isDead;
    BufferedImage Idle;
    BufferedImage Run;
    BufferedImage Death;
    int velX;
    int velY;
    int frameNumAni = 99;
    int frameNumMov = 99;
    int offset = 0;
    int width, height;
    int  DeathImageNum = 1;

    float x, y;
    float playerX;
    int health = 100;
    private boolean Damaged;

    public EnemyOrcRogue(Tool tool, float x, float y, int width, int height) {
        this.tool = tool;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        SetValue();
    }


    public void SetValue() {
        try {
            Run = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("Pixel Crawler/Enemy/Orc Crew/Orc - Rogue/Run/Run-Sheet.png")));
            Idle = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("Pixel Crawler/Enemy/Orc Crew/Orc - Rogue/Idle/Idle-Sheet.png")));
            Death = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("Pixel Crawler/Enemy/Orc Crew/Orc - Rogue/Death/Death-Sheet.png")));
        }catch (Exception e){
            e.printStackTrace();
        }
        currentImage = Run.getSubimage(0, 0, 32, 32);
    }

    public void draw(Graphics g) {
        g.drawImage(currentImage, (int) x + offset, (int) y, width, height, null);
    }

    public void update() {
        if (health <= 0) {
            deathAnimation();
            return;
        }
        Animation();
        AniMove = true;
        if (isAggro) {
            aggroLogic();
            AniIdle = false;
        } else {
            logic();
        }
    }

    public void Animation() {
        frameNumAni++;
        if (frameNumAni >= 10) {
            frameNumAni = 0;
            CurrentImageNum++;
            if (CurrentImageNum > 4 && AniIdle) {
                CurrentImageNum = 1;
            }
            if (CurrentImageNum > 6 && AniMove) {
                CurrentImageNum = 1;
            }
        }
        if (AniMove) {
            switch (CurrentImageNum) {
                case 1 -> currentImage = Run.getSubimage(16, 32, 32, 32);
                case 2 -> currentImage = Run.getSubimage(80, 32, 32, 32);
                case 3 -> currentImage = Run.getSubimage(144, 32, 32, 32);
                case 4 -> currentImage = Run.getSubimage(208, 32, 32, 32);
                case 5 -> currentImage = Run.getSubimage(272, 32, 32, 32);
                case 6 -> currentImage = Run.getSubimage(336, 32, 32, 32);
            }
        }
        if (AniIdle) {
            switch (CurrentImageNum) {
                case 1 -> currentImage = Idle.getSubimage(0, 0, 32, 32);
                case 2 -> currentImage = Idle.getSubimage(32, 0, 32, 32);
                case 3 -> currentImage = Idle.getSubimage(64, 0, 32, 32);
                case 4 -> currentImage = Idle.getSubimage(96, 0, 32, 32);
            }
        }
    }
    public void deathAnimation() {
        frameNumAni++;
        if (frameNumAni >= 10) {
            frameNumAni = 0;
            DeathImageNum++;
            if (DeathImageNum > 6) {
                isDead = true;
            }
        }
        switch (DeathImageNum) {
            case 1 -> currentImage = Death.getSubimage(23, 32, 35, 32);
            case 2 -> currentImage = Death.getSubimage(87, 32, 35, 32);
            case 3 -> currentImage = Death.getSubimage(151, 32, 35, 32);
            case 4 -> currentImage = Death.getSubimage(215, 32, 35, 32);
            case 5 -> currentImage = Death.getSubimage(279, 32, 35, 32);
            case 6 -> currentImage = Death.getSubimage(343, 32, 35, 32);
        }
    }

    public void logic() {
        if (!inAction) {
            inAction = true;
            SetMovement();
        }
        frameNumMov++;
        if (inAction) {
            if (IsMovingLeft && frameNumMov > 100) {
                frameNumMov = 0;
                inAction = false;
                AniMove = true;
                AniIdle = false;
                velY = 0;
                velX = -1;
                width = -96;
                offset = 96;
            }
            if (IsMovingRight && frameNumMov > 100) {
                frameNumMov = 0;
                inAction = false;
                AniMove = true;
                AniIdle = false;
                velY = 0;
                velX = 1;
                width = 96;
                offset = 0;
            }
            if (IsMovingUp && frameNumMov > 100) {
                frameNumMov = 0;
                inAction = false;
                AniMove = true;
                AniIdle = false;
                velX = 0;
                velY = -1;
            }
            if (isMovingDown && frameNumMov > 100) {
                frameNumMov = 0;
                inAction = false;
                AniMove = true;
                AniIdle = false;
                velX = 0;
                velY = 1;
            }
            if (isIdling && frameNumMov > 100) {
                frameNumMov = 0;
                inAction = false;
                AniMove = false;
                AniIdle = true;
                velX = 0;
                velY = 0;
            }
            x += velX;
            y += velY;
            if (x < 64) {
                x = 64;
                IsMovingRight = true;
                IsMovingLeft = false;
                velX = 1;
            }
            if (x > 960 - 128) {
                x = 960 - 128;
                IsMovingLeft = true;
                IsMovingRight = false;
                velX = -1;
            }
            if (y < 64) {
                y = 64;
                isMovingDown = true;
                IsMovingUp = false;
                velY = 1;
            }
            if (y > 640 - 160) {
                y = 640 - 160;
                IsMovingUp = true;
                isMovingDown = false;
                velY = -1;
            }
        }
    }

    private void SetMovement() {
        switch ((int) tool.GetRandomNum(1, 5)) {
            case 1 -> {
                isIdling = true;
                IsMovingRight = false;
                IsMovingLeft = false;
                isMovingDown = false;
                IsMovingUp = false;
            }
            case 2 -> {
                IsMovingRight = true;
                isIdling = false;
                IsMovingLeft = false;
                isMovingDown = false;
                IsMovingUp = false;
            }
            case 3 -> {
                IsMovingLeft = true;
                isIdling = false;
                IsMovingRight = false;
                isMovingDown = false;
                IsMovingUp = false;
            }
            case 4 -> {
                IsMovingUp = true;
                isMovingDown = false;
                IsMovingLeft = false;
                IsMovingRight = false;
                isIdling = false;
            }
            case 5 -> {
                isMovingDown = true;
                IsMovingUp = false;
                IsMovingLeft = false;
                IsMovingRight = false;
                isIdling = false;
            }
        }

    }

    public void aggroLogic() {
        isIdling = false;
        if (playerX < x) {
            IsMovingLeft = true;
            IsMovingRight = false;
            width = -96;
            offset = 96;
        }
        if (playerX > x) {
            IsMovingRight = true;
            IsMovingLeft = false;
            width = 96;
            offset = 0;
        }
        if (x < 64) {
            x += 10;
        }
        if (x > 960 - 128) {
            x -= 10;
        }
        if (y < 64) {
            y += 10;
        }
        if (y > 640 - 160) {
            y -= 10;
        }
    }

    public void move(float vel, double angle, float playerX) {
        if(!isAggro || health <= 0) {return;}
        this.y += (float) (Math.sin(angle) * vel);
        this.x += (float) (Math.cos(angle) * vel);
        this.playerX = playerX + 48;
    }

    public void setLogic(boolean isAggro){
        this.isAggro = isAggro;
        AniIdle = false;
    }

    public void moveBack (float moveX, float moveY){
        this.x -= moveX * 2;
        this.y -= moveY * 2;
    }
    public void MoveNX(float move) {
        this.x -= move;
    }

    public void MoveNY(float move) {
        this.y -= move;
    }
    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }
    public boolean IsDead() {
        return isDead;
    }
    public Boolean isHealthZero(){
        return this.health <= 0;
    }
    public void IsDamaged(boolean d){
        this.Damaged = d;
    }
    public boolean GetIsDamaged(){
        return this.Damaged;
    }

    public void damageTaken(int damage) {
        health -= damage;
        if (health <= 0) {
            health = 0;
        }
    }

}


