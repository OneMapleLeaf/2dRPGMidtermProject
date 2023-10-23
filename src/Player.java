import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Objects;

public class Player {
    BufferedImage Idle, Run, Weapon, Bullet, heart;
    BufferedImage Wand, StatBar, GoldCounter, CharFrame, CharacterHead;
    BufferedImage CurrentImage;
    int currentImageNum = 1;
    Stage stage;
    Tool tool;
    InputHandler inputHandler;
    Sound sound = new Sound();
    Font PixelFont;

    private int x = 100;
    private int y = 100;
    private int width = 32 * 3;
    private int xOffset = 0;
    private float AttackSpeed = 500.0f;
    private int health = 5;
    private int MaxHealth = 5;
    private int damage = 10;
    private int ProjectileSpeed = 10;
    private int mana = 5;
    private int coins = 9999999;
    private boolean aggroEnemy = false;
    private int StageDelay = 200;
    private int deathDelay = 200;

    private int SfxVol, SV;

    private boolean moving, idling;
    private boolean nextRound;
    ArrayList<Bullet> fireBullet = new ArrayList<>();
    ArrayList<Double> Angle = new ArrayList<>();

    Circle AggroRadius = new Circle(x, y, 256);
    Circle InteractRadius = new Circle(x, y, 72);
    Circle SirRicCircle = new Circle(350 + 32, 280 + 32, 16);
    Circle ExploreCircle = new Circle(470, 51, 16);
    Rectangle HitBox = new Rectangle(x + 64, y, 48, 96);
    int HitRedOpacity = 0;
    int finishOpacity = 0;
    int deadOpacity = 0;
    Color hitRed = new Color(255, 0, 0, HitRedOpacity);
    Color finishRound = new Color(0, 0, 0, finishOpacity);
    Color deadColor = new Color(255, 0, 0, deadOpacity);

    Player(Tool tool, InputHandler inputHandler, Stage stage){
        this.stage = stage;
        this.inputHandler = inputHandler;
        this.tool = tool;
        SetValues();
    }

    public void draw(Graphics2D g){
        g.drawImage(CurrentImage, x + xOffset, y, width, 32 * 3, null);
        for (int i = 0; i < fireBullet.size(); i++) {
            fireBullet.get(i).draw(g,inputHandler.getX(), inputHandler.getY());
        }
        AffineTransform at = AffineTransform.getTranslateInstance(x + 40, y + 48);
        at.rotate(Math.atan2(inputHandler.getY() - y - 32, inputHandler.getX() - x - 32), 10, 14);
        at.scale(2, 2);
        g.drawImage(Wand, at, null);
        g.setColor(hitRed);
        g.fillRect(0,0, 1000, 1000);
        g.setColor(finishRound);
        g.fillRect(0, 0, 1000, 1000);
        g.setColor(deadColor);
        g.fillRect(0, 0, 1000, 1000);
        StatBar(g);
    }

    public void update(){
        switch (SV){
            case 0 -> SfxVol = -80;
            case 1 -> SfxVol = -70;
            case 2 -> SfxVol = -60;
            case 3 -> SfxVol = -50;
            case 4 -> SfxVol = -40;
            case 5 -> SfxVol = -30;
        }
        if (health >= MaxHealth){
            health = MaxHealth;
        }
        hitRed = new Color(255, 0, 0, HitRedOpacity);
        finishRound = new Color(0, 0, 0, finishOpacity);
        deadColor = new Color(255, 0, 0, deadOpacity);

            if (finishOpacity >= 250){
                StageDelay--;
                if(StageDelay < 0){
                    nextRound = true;
                    StageDelay = 200;
                    finishOpacity = 0;
                }
                finishOpacity-=2;
            }
        HitRedOpacity-=10;
        if (HitRedOpacity <= 0){
            HitRedOpacity = 0;
        }

        if (health <= 0){
            if(deadOpacity >= 255){
                deathDelay--;
                if(deathDelay < 0){
                    health = 5;
                    deathDelay = 200;
                    deadOpacity = 0;
                    finishOpacity = 0;
                }
            } else deadOpacity+=5;
        }
        Animation();
        Movement();
        Weapon();
        SetStage();
        //System.out.printf("%.2f \n", AttackSpeed / 1000);
        HitBox.x = x + 32;
        HitBox.y = y;
        AggroRadius.x = x - 260 + 48;
        AggroRadius.y = y - 240 + 48;
        InteractRadius.x = x - 16;
        InteractRadius.y = y - 16;
    }
    public void RoundFinish(int num){
        this.finishOpacity += num;
    }
    public boolean isNextRound(){
        return nextRound;
    }
    public void setNextRound(boolean b){
        this.nextRound = b;
    }
    public void SetSfxVol(int SfxVol){
        this.SV = SfxVol;
    }
    public int GetStageLvl(){
        return stage.StageLvl;
    }
    public void SetStageLvl(int Lvl){
        stage.setStage(Lvl);
    }
    public void AddStage(){
        stage.addStage();
    }
    public int GetDeathDelay(){
        return deathDelay;
    }
    public boolean GetEnemyAggro(){
        return aggroEnemy;
    }
    public void SetAggroEnemy(boolean Aggro){
        this.aggroEnemy = Aggro;
    }
    public boolean IsInteractSirRic(){
        if (stage.StageLvl != 0) return false;
        return tool.CircleCollision(InteractRadius, SirRicCircle);
    }
    public boolean IsInteractExplore(){
        if (stage.StageLvl != 0) return false;
        return tool.CircleCollision(InteractRadius, ExploreCircle);
    }


    private void SetValues(){
        try {
            heart = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("UI/itemset0/hearts.png")));
            Bullet = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("All_Fire_Bullet_Pixel_16x16_02.png")));
            Weapon = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("Pixel Crawler/Weapons/Bone/Bone.png")));
            Run = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("Pixel Crawler/Heroes/Knight/Run/Run-Sheet.png")));
            Idle = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("Pixel Crawler/Heroes/Knight/Idle/Idle-Sheet.png")));
            StatBar = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("UI/GUI.png")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try{
            InputStream is = getClass().getResourceAsStream("Font/Pixeled.ttf");
            assert is != null;
            PixelFont = Font.createFont(Font.TRUETYPE_FONT, is);
        }   catch (FontFormatException | IOException e){
            throw new RuntimeException(e);
        }
        heart = heart.getSubimage(0, 0, 16 , 16);

        GoldCounter = StatBar.getSubimage(122,49,40,10);
        CharFrame = StatBar.getSubimage(96,36,26,26);
        CharacterHead = Idle.getSubimage(0, 0, 32, 16);

        CurrentImage = Idle.getSubimage(0, 0, 32, 32);
        Wand = Weapon.getSubimage(80, 0, 28, 14);
        idling = true;

    }

    private void Animation(){
        if (tool.GetMilli() >= 100){
            tool.ResetMilliCounter();
            currentImageNum++;
            if (currentImageNum > 4 && idling){
                currentImageNum = 1;
            }
            if (currentImageNum > 6 && moving){
                currentImageNum = 1;
            }
        }
        if (idling){
            switch (currentImageNum) {
                case 1 -> CurrentImage = Idle.getSubimage(0, 0, 32, 32);
                case 2 -> CurrentImage = Idle.getSubimage(32, 0, 32, 32);
                case 3 -> CurrentImage = Idle.getSubimage(64, 0, 32, 32);
                case 4 -> CurrentImage = Idle.getSubimage(96, 0, 32, 32);
            }
        }
        if (moving){
            switch (currentImageNum) {
                case 1 -> CurrentImage = Run.getSubimage(16, 32, 32, 32);
                case 2 -> CurrentImage = Run.getSubimage(80, 32, 32, 32);
                case 3 -> CurrentImage = Run.getSubimage(144, 32, 32, 32);
                case 4 -> CurrentImage = Run.getSubimage(208, 32, 32, 32);
                case 5 -> CurrentImage = Run.getSubimage(272, 32, 32, 32);
                case 6 -> CurrentImage = Run.getSubimage(336, 32, 32, 32);
            }
        }
    }
    private void Weapon(){
        if (inputHandler.Pressed() && tool.GetMilli2() > AttackSpeed){
            sound.SetFile(0);
            sound.VolumeDown(SfxVol);
            sound.play();
                fireBullet.add(new Bullet(x + 36, y + 48, 2, Bullet));
                Angle.add(Math.atan2((inputHandler.getY() - 20) - (y + 48), (inputHandler.getX()) - (x + 48)));
                tool.ResetMilliCounter2();
        }
        for (int i = 0; i < fireBullet.size(); i++) {
           if (fireBullet.get(i).IsAlive()) {
              fireBullet.get(i).Move(ProjectileSpeed, Angle.get(i));
            }
        }
        for (int i = 0; i < fireBullet.size(); i++) {
            if (fireBullet.get(i) != null) {
                if (!fireBullet.get(i).IsAlive()){
                    fireBullet.get(i).DisappearAnimation();
                    if (fireBullet.get(i).DisappearAnimation()){
                        fireBullet.remove(i);
                        Angle.remove(i);
                        i--;
                    }
                }
            }
        }
    }
    private void Movement(){
        int speed = 6;
        if (inputHandler.W() && y > 50){
            y -= speed;
            idling = false;
            moving = true;
        }
        if (inputHandler.S() && y < 475) {
            y += speed;
            idling = false;
            moving = true;
        }
        if (inputHandler.A() && x > 40) {
            x -= speed;
            xOffset = 100;
            width = -32 * 3;
            idling = false;
            moving = true;
        }
        if (inputHandler.D() && x < 825) {
            x += speed;
            xOffset = 0;
            width = 32 * 3;
            idling = false;
            moving = true;
        }
        if (!inputHandler.W() && !inputHandler.A() && !inputHandler.S() && !inputHandler.D()){
            idling = true;
            moving = false;
        }
    }
    public void SetStage(){
        if(inputHandler.One()){
            stage.setStage(1);
        }
        if(inputHandler.Two()){
            stage.setStage(2);
        }
        if(inputHandler.Three()){
            stage.setStage(3);
        }
        if(inputHandler.Four()){
            stage.setStage(4);
        }
        if(inputHandler.Zero()){
            stage.setStage(0);
        }
    }
    private void StatBar(Graphics2D g){
        g.drawImage(CharFrame, 10, 10, 26*3, 26*3, null);
        g.drawImage(CharacterHead, -10, 7, 32*4, 16*4, null);
        g.drawImage(GoldCounter, 88, 13, 40*3, 10*3, null);
        g.setColor(Color.WHITE);
        g.setFont(PixelFont);
        g.setFont(g.getFont().deriveFont(12.0f));
        g.drawString("" + coins, 114, 35);

        for (int i = 0; i < health; i++) {
            g.drawImage(heart, 90 + (i*20), 46, 32, 32, null);
        }
    }
    public void LoseHealth(){
        health--;
    }
    public void GainHealth(){
        health++;
    }
    public void AddMaxHealth(int Amount){
        this.MaxHealth += Amount;
    }
    public void GainCoin(int Amount){
        this.coins += Amount;
    }
    public void LoseCoin(int Amount){
        this.coins -= Amount;
    }
    public int GetCoin(){
        return coins;
    }
    public void HitRed(int num){
        HitRedOpacity = num;
    }
    public float getX(){
        return x;
    }
    public float getY(){
        return y;
    }
    public void SetX(int x){
        this.x = x;
    }
    public void SetY(int y){
        this.y = y;
    }

    public int getDamage() {
        return damage;
    }
    public void AddDamage(int Amount){
        damage += Amount;
    }

    public int getMana() {
        return mana;
    }

    public float getAttackSpeed() {
        return AttackSpeed;
    }
    public void AddAttackSpeed(int Amount){
        AttackSpeed -= Amount;
        if (AttackSpeed < 50){
            AttackSpeed = 50;
        }
    }

    public void AddProjectileSpeed(int Amount){
        ProjectileSpeed += Amount;
    }



    public int getHealth() {
        return health;
    }

    public Circle GetAggroRadius(){
        return AggroRadius;
    }
    public Circle GetInteractRadius(){
        return InteractRadius;
    }
    public Rectangle GetHitBox(){
        return HitBox;
    }
    public ArrayList<Bullet> GetBullet(){
        return fireBullet;
    }
    public ArrayList<Double> GetAngle(){
        return Angle;
    }
}
