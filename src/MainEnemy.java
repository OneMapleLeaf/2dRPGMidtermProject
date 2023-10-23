import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Objects;

public class MainEnemy {
    Tool tool;
    InputHandler inputHandler;
    MainItems items;
    //Orc
    ArrayList<EnemyOrc> orc = new ArrayList<>();
    ArrayList<Double> OrcAngle = new ArrayList<>();
    ArrayList<Circle> OrcCircleCollision = new ArrayList<>();
    ArrayList<Rectangle> OrcHitBox = new ArrayList<>();
    ArrayList<Boolean> orcAttacked = new ArrayList<>();
    ArrayList<Integer> OrcMeleeDelay = new ArrayList<>();

    //Rogue
    ArrayList<EnemyOrcRogue> orcRogue = new ArrayList<>();
    ArrayList<Double> OrcRogueAngle = new ArrayList<>();
    ArrayList<Circle> OrcRogueCircleCollision = new ArrayList<>();
    ArrayList<Double> OrcRogueAngleBullet = new ArrayList<>();
    ArrayList<Rectangle> OrcRogueHitBox = new ArrayList<>();
    ArrayList<BulletEnemy> OrcBullet = new ArrayList<>();
    ArrayList<Integer> OrcRogueAttackSpeed = new ArrayList<>();
    ArrayList<Integer> OrcRogueAttackDelay = new ArrayList<>();

    //Skeleton
    ArrayList<EnemySkeleton> Skeleton = new ArrayList<>();
    ArrayList<Double> SkeletonAngle = new ArrayList<>();
    ArrayList<Circle> SkeletonCircleCollision = new ArrayList<>();
    ArrayList<Rectangle> SkeletonHitBox = new ArrayList<>();
    ArrayList<Boolean> SkeletonAttacked = new ArrayList<>();
    ArrayList<Integer> SkeletonMeleeDelay = new ArrayList<>();

    //Skeleton Rogue
    ArrayList<EnemySkeletonRogue> SkeletonRogue = new ArrayList<>();
    ArrayList<Double> SkeletonRogueAngle = new ArrayList<>();
    ArrayList<Circle> SkeletonRogueCircleCollision = new ArrayList<>();
    ArrayList<Double> SkeletonRogueAngleBullet = new ArrayList<>();
    ArrayList<Rectangle> SkeletonRogueHitBox = new ArrayList<>();
    ArrayList<BulletEnemy> SkeletonBullet = new ArrayList<>();
    ArrayList<Integer> SkeletonAttackSpeed = new ArrayList<>();
    ArrayList<Integer> SkeletonRogueAttackDelay = new ArrayList<>();


    Player player;
    Sound sound = new Sound();
    int temp = 0;
    int stageLevel = 1;

    int stage1Round = 3;
    int stage2Round = 4;
    int stage3Round = 4;
    int stage4Round = 4;

    BufferedImage bulletImage;
    BufferedImage rangeRun, rangeIdle, meleeRun, meleeIdle;
    ArrayList<Integer> vel = new ArrayList<>();

    int skeletonSize = 0;
    int skeletonRogueSize = 0;
    int orcSize = 10;
    int orcRogueSize = 0;

    boolean SpawnEnemy = true;

    MainEnemy(Tool tool, InputHandler inputHandler, Player player, MainItems items) {
        this.items = items;
        this.tool = tool;
        this.inputHandler = inputHandler;
        this.player = player;
    }

    public void EnemyInit() {
        setEnemyImage();
        initOrc();
        initSkeleton();
    }

    public void initOrc() {
        for (int i = 0; i < orcSize; i++) {
            int randomX = tool.GetRandomNum(64, 832);
            int randomY = tool.GetRandomNum(64, 480);

            orc.add(new EnemyOrc(tool, randomX, randomY, 96, 96));
            OrcHitBox.add(new Rectangle(randomX, randomY, 64, 96));
            OrcCircleCollision.add(new Circle(randomX, randomY, 24));
            OrcAngle.add(Math.atan2(randomY - player.getY(), randomX - player.getX()));
            vel.add(1);

            orcAttacked.add(false);
            OrcMeleeDelay.add(100);
        }
        for (int i = 0; i < orcRogueSize; i++) {
            int randomX = tool.GetRandomNum(64, 832);
            int randomY = tool.GetRandomNum(64, 480);

            orcRogue.add(new EnemyOrcRogue(tool, randomX, randomY, 96, 96));
            OrcRogueHitBox.add(new Rectangle(randomX, randomY, 96, 32));
            OrcRogueAngle.add(Math.atan2(randomY - player.getY(), randomX - player.getX()));
            OrcRogueCircleCollision.add(new Circle(randomX, randomY, 24));

            OrcRogueAttackSpeed.add(2);
            OrcRogueAttackDelay.add(tool.GetRandomNum(1, 100));
            vel.add(1);
        }
    }

    public void initSkeleton() {

        for (int i = 0; i < skeletonSize; i++) {
            int randomX = tool.GetRandomNum(64, 832);
            int randomY = tool.GetRandomNum(64, 480);

            Skeleton.add(new EnemySkeleton(tool, randomX, randomY, 96, 96));
            SkeletonHitBox.add(new Rectangle(randomX, randomY, 64, 96));
            SkeletonCircleCollision.add(new Circle(randomX, randomY, 24));
            SkeletonAngle.add(Math.atan2(randomY - player.getY(), randomX - player.getX()));
            vel.add(1);

            SkeletonAttacked.add(false);
            SkeletonMeleeDelay.add(100);
        }
        for (int i = 0; i < skeletonRogueSize; i++) {
            int randomX = tool.GetRandomNum(64, 832);
            int randomY = tool.GetRandomNum(64, 480);

            SkeletonRogue.add(new EnemySkeletonRogue(tool, randomX, randomY, 96, 96));
            SkeletonRogueHitBox.add(new Rectangle(randomX, randomY, 96, 32));
            SkeletonRogueAngle.add(Math.atan2(randomY - player.getY(), randomX - player.getX()));
            SkeletonRogueCircleCollision.add(new Circle(randomX, randomY, 24));

            SkeletonAttackSpeed.add(2);
            SkeletonRogueAttackDelay.add( tool.GetRandomNum(1, 100));
            vel.add(1);
        }
    }


    public void setEnemyImage() {
        try {
            if (stageLevel == 1) {
                rangeRun = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("Pixel Crawler/Enemy/Orc Crew/Orc - Rogue/Run/Run-Sheet.png")));
                rangeIdle = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("Pixel Crawler/Enemy/Orc Crew/Orc - Rogue/Idle/Idle-Sheet.png")));

                meleeRun = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("Pixel Crawler/Enemy/Orc Crew/Orc/Run/Run-Sheet.png")));
                meleeIdle = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("Pixel Crawler/Enemy/Orc Crew/Orc/Idle/Idle-Sheet.png")));
            }
            if (stageLevel == 2) {
                rangeRun = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("Pixel Crawler/Enemy/Skeleton Crew/Skeleton - Rogue/Run/Run-Sheet.png")));
                rangeIdle = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("Pixel Crawler/Enemy/Skeleton Crew/Skeleton - Rogue/Idle/Idle-Sheet.png")));

                meleeRun = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("Pixel Crawler/Enemy/Skeleton Crew/Skeleton - Warrior/Run/Run-Sheet.png")));
                meleeIdle = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("Pixel Crawler/Enemy/Skeleton Crew/Skeleton - Warrior/Idle/Idle-Sheet.png")));
            }
            bulletImage = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("All_Fire_Bullet_Pixel_16x16_05.png")));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g) {
//Orc
        for (int i = 0; i < orc.size(); i++) {
            orc.get(i).draw(g);
            g.setColor(Color.RED);
  //          g.drawRect(OrcHitBox.get(i).x, OrcHitBox.get(i).y, OrcHitBox.get(i).width, OrcHitBox.get(i).height);
 //           g.drawOval(OrcCircleCollision.get(i).x, OrcCircleCollision.get(i).y, OrcCircleCollision.get(i).GetRadiusWH(), OrcCircleCollision.get(i).GetRadiusWH());
        }
        for (int i = 0; i < orcRogue.size(); i++) {
            orcRogue.get(i).draw(g);
            g.setColor(Color.RED);
 //           g.drawRect(OrcRogueHitBox.get(i).x, OrcRogueHitBox.get(i).y, OrcRogueHitBox.get(i).width, OrcRogueHitBox.get(i).height);
//            g.drawOval(OrcRogueCircleCollision.get(i).x, OrcRogueCircleCollision.get(i).y, OrcRogueCircleCollision.get(i).GetRadiusWH(), OrcRogueCircleCollision.get(i).GetRadiusWH());
        }
        //Skeleton
        for (int i = 0; i < Skeleton.size(); i++) {
            Skeleton.get(i).draw(g);
            g.setColor(Color.RED);
 //           g.drawRect(SkeletonHitBox.get(i).x, SkeletonHitBox.get(i).y, SkeletonHitBox.get(i).width, SkeletonHitBox.get(i).height);
//            g.drawOval(SkeletonCircleCollision.get(i).x, SkeletonCircleCollision.get(i).y, SkeletonCircleCollision.get(i).GetRadiusWH(), SkeletonCircleCollision.get(i).GetRadiusWH());
        }
        for (int i = 0; i < SkeletonRogue.size(); i++) {
            SkeletonRogue.get(i).draw(g);
            g.setColor(Color.RED);
 //           g.drawRect(SkeletonRogueHitBox.get(i).x, SkeletonRogueHitBox.get(i).y, SkeletonRogueHitBox.get(i).width, SkeletonRogueHitBox.get(i).height);
//            g.drawOval(SkeletonRogueCollision.get(i).x, SkeletonRogueCollision.get(i).y, SkeletonRogueCollision.get(i).GetRadiusWH(), SkeletonRogueCollision.get(i).GetRadiusWH());
        }
        //Bullets
        for (int i = 0; i < OrcBullet.size(); i++) {
            OrcBullet.get(i).draw(g);
        }
        for (int i = 0; i < SkeletonBullet.size(); i++) {
            SkeletonBullet.get(i).draw(g);
        }
    }

    public void update() {
        if (player.GetEnemyAggro()){
            AggroEnemy();
        }
        if(player.GetStageLvl() == 1  && SpawnEnemy){
            setEnemyImage();
           AddSkeleton(1);
           AddSkeletonRogue(1);
           SpawnEnemy = false;
        }
        if (player.GetStageLvl() == 1 && Skeleton.isEmpty() && SkeletonRogue.isEmpty()){
            if (stage1Round > 0){
                stage1Round--;
                SpawnEnemy = true;
            }
        }
        if(player.GetStageLvl() == 2  && SpawnEnemy){
            setEnemyImage();
            AddSkeleton(2);
            AddSkeletonRogue(2);
            SpawnEnemy = false;
        }
        if (player.GetStageLvl() == 2 && Skeleton.isEmpty() && SkeletonRogue.isEmpty()){
            if (stage2Round > 0){
                stage2Round--;
                SpawnEnemy = true;
            }
        }
        if(player.GetStageLvl() == 3  && SpawnEnemy){
            setEnemyImage();
            AddOrc(2);
            AddOrcRogue(2);
            SpawnEnemy = false;
        }
        if (player.GetStageLvl() == 3 && orc.isEmpty() && orcRogue.isEmpty()){
            if (stage3Round > 0){
                stage3Round--;
                SpawnEnemy = true;
            }
        }
        if(player.GetStageLvl() == 4  && SpawnEnemy){
            setEnemyImage();
            AddOrc(3);
            AddOrcRogue(3);
            SpawnEnemy = false;
        }
        if (player.GetStageLvl() == 4 && orc.isEmpty() && orcRogue.isEmpty()){
            if (stage4Round > 0){
                stage4Round--;
                SpawnEnemy = true;
            }
        }
        if (stage1Round == 0){
            ResetEnemy();
            player.RoundFinish(2);
            if(player.isNextRound()) {
                player.AddStage();
                stage1Round = 3;
                player.setNextRound(false);
            }
        }
        if (stage2Round == 0){
            ResetEnemy();
            player.RoundFinish(2);
            if(player.isNextRound()) {
                player.AddStage();
                stage2Round = 4;
                player.setNextRound(false);
            }
        }
        if (stage3Round == 0){
            ResetEnemy();
            player.RoundFinish(2);
            if(player.isNextRound()) {
                player.AddStage();
                stage3Round = 4;
                player.setNextRound(false);
            }
        }
        if (stage4Round == 0){
            ResetEnemy();
            player.RoundFinish(2);
            if(player.isNextRound()) {
                player.SetStageLvl(0);
                stage4Round = 4;
                player.setNextRound(false);
            }
        }

        if(player.GetStageLvl() == 0){
            SpawnEnemy = true;
            stage1Round = 3;
            stage2Round = 4;
            stage3Round = 4;
            stage4Round = 4;
            ResetEnemy();
        }

        if (player.getHealth() <= 0){
            ResetEnemy();
            if (player.GetDeathDelay() < 10) {
                player.SetStageLvl(0);
            }
        }

        deadEnemy();
        setEnemies();
        enemyHitBoxCollision();
        setAttackDelay();
        setBulletCount();
        attackPlayer();
        /* Testing Area */
        if (inputHandler.SPACE()) {
            for (int i = 0; i < orc.size(); i++) {
                orc.get(i).isAggro = true;
            }
            for (int i = 0; i < orcRogue.size(); i++) {
                orcRogue.get(i).isAggro = true;
            }
            for (int i = 0; i < Skeleton.size(); i++) {
                Skeleton.get(i).isAggro = true;
            }
            for (int i = 0; i < SkeletonRogue.size(); i++) {
                SkeletonRogue.get(i).isAggro = true;
            }
        }
    }

    public void AggroEnemy(){
        for (int i = 0; i < orc.size(); i++) {
            orc.get(i).isAggro = true;
        }
        for (int i = 0; i < orcRogue.size(); i++) {
            orcRogue.get(i).isAggro = true;
        }
        for (int i = 0; i < Skeleton.size(); i++) {
            Skeleton.get(i).isAggro = true;
        }
        for (int i = 0; i < SkeletonRogue.size(); i++) {
            SkeletonRogue.get(i).isAggro = true;
        }
    }

    public void RemoveAggro(){
        for (int i = 0; i < orc.size(); i++) {
            orc.get(i).isAggro = false;
        }
        for (int i = 0; i < orcRogue.size(); i++) {
            orcRogue.get(i).isAggro = false;
        }
        for (int i = 0; i < Skeleton.size(); i++) {
            Skeleton.get(i).isAggro = false;
        }
        for (int i = 0; i < SkeletonRogue.size(); i++) {
            SkeletonRogue.get(i).isAggro = false;
        }
    }

    public void enemyHitBoxCollision() {

        //Orc to Orc Collision
        for (int i = 0; i < orc.size(); i++) {
            for (int j = i + 1; j < orc.size(); j++) {
                if (tool.CircleCollision(OrcCircleCollision.get(i), OrcCircleCollision.get(j)) && !orc.get(j).isHealthZero()) {
                    int moveX = (int) tool.GetCircleOverlap(OrcCircleCollision.get(i), OrcCircleCollision.get(j)) * (OrcCircleCollision.get(i).GetX() - OrcCircleCollision.get(j).GetX()) / (int) tool.GetCircleDist(OrcCircleCollision.get(i), OrcCircleCollision.get(j));
                    int moveY = (int) tool.GetCircleOverlap(OrcCircleCollision.get(i), OrcCircleCollision.get(j)) * (OrcCircleCollision.get(i).GetY() - OrcCircleCollision.get(j).GetY()) / (int) tool.GetCircleDist(OrcCircleCollision.get(i), OrcCircleCollision.get(j));
                    orc.get(j).MoveNX(moveX);
                    orc.get(j).MoveNY(moveY);
                }
            }
        }


        //Orc to Player Collision
        for (int i = 0; i < orc.size(); i++) {
            if (OrcHitBox.get(i).intersects(player.HitBox) && !orc.get(i).isHealthZero()) {
                orc.get(i).moveBack((float) Math.cos(OrcAngle.get(i)), (float) Math.sin(OrcAngle.get(i)));
            }
        }

        //OrcRouge to OrcRouge Collision
        for (int i = 0; i < orcRogue.size(); i++) {
            for (int j = i + 1; j < orcRogue.size(); j++) {
                if (tool.CircleCollision(OrcRogueCircleCollision.get(i), OrcRogueCircleCollision.get(j)) && !orcRogue.get(j).isHealthZero()) {
                    int moveX = (int) tool.GetCircleOverlap(OrcRogueCircleCollision.get(i), OrcRogueCircleCollision.get(j)) * (OrcRogueCircleCollision.get(i).GetX() - OrcRogueCircleCollision.get(j).GetX()) / (int) tool.GetCircleDist(OrcRogueCircleCollision.get(i), OrcRogueCircleCollision.get(j));
                    int moveY = (int) tool.GetCircleOverlap(OrcRogueCircleCollision.get(i), OrcRogueCircleCollision.get(j)) * (OrcRogueCircleCollision.get(i).GetY() - OrcRogueCircleCollision.get(j).GetY()) / (int) tool.GetCircleDist(OrcRogueCircleCollision.get(i), OrcRogueCircleCollision.get(j));
                    orcRogue.get(j).MoveNX(moveX);
                    orcRogue.get(j).MoveNY(moveY);
                }
            }
        }


        //OrcRouge to Player Collision
        for (int i = 0; i < orcRogue.size(); i++) {
            if (tool.CircleCollision(player.GetAggroRadius(), OrcRogueCircleCollision.get(i)) && !orcRogue.get(i).isHealthZero()) {
                orcRogue.get(i).moveBack((float) Math.cos(OrcRogueAngle.get(i)), (float) Math.sin(OrcRogueAngle.get(i)));
            }
        }


        //OrcBullets to Player Collision
        for (int i = 0; i < OrcBullet.size(); i++) {
            if (player.HitBox.intersects(new Rectangle((int) OrcBullet.get(i).getX(), (int) OrcBullet.get(i).getY(), (int) OrcBullet.get(i).getRadiusWH(), (int) OrcBullet.get(i).getRadiusWH()))) {
                OrcBullet.remove(i);
                player.LoseHealth();
                player.HitRed(255);
                sound.SetFile(7);
                sound.VolumeDown(-20);
                sound.play();
                OrcRogueAngleBullet.remove(i);
                i--;
            }
        }


        //PlayerBullets to Orc Collision
        for (int i = 0; i < orc.size(); i++) {
            for (int j = 0; j < player.GetBullet().size(); j++) {
                if (OrcHitBox.get(i).intersects(new Rectangle((int) player.GetBullet().get(j).getX(),(int) player.GetBullet().get(j).getY(), 32, 32)) && !orc.get(i).isHealthZero() && !player.GetBullet().get(j).IsCollided()){
                    player.GetBullet().get(j).SetIsCollided(true);
                    player.GetBullet().get(j).Movement(false);
                    orc.get(i).IsDamaged(true);
                }
                if(orc.get(i).GetIsDamaged()){
                    orc.get(i).damageTaken(player.getDamage());
                    orc.get(i).IsDamaged(false);
                }
                if(player.GetBullet().get(j).IsCollided()){
                    if (player.GetBullet().get(j).DisappearAnimation()){
                        player.GetBullet().get(j).SetIsCollided(false);
                        player.GetBullet().remove(j);
                        player.GetAngle().remove(j);
                        orc.get(i).moveBack((float) Math.cos(OrcAngle.get(i)), (float) Math.sin(OrcAngle.get(i)));
                        j--;
                    }
                }
            }
        }


        //PlayerBullets to OrcRouge Collision
        for (int i = 0; i < orcRogue.size(); i++) {
            for (int j = 0; j < player.GetBullet().size(); j++) {
                if (OrcRogueHitBox.get(i).intersects(new Rectangle((int) player.GetBullet().get(j).getX(),(int) player.GetBullet().get(j).getY(), 32, 32)) && !orcRogue.get(i).isHealthZero() && !player.GetBullet().get(j).IsCollided()){
                    player.GetBullet().get(j).SetIsCollided(true);
                    player.GetBullet().get(j).Movement(false);
                    orcRogue.get(i).IsDamaged(true);
                }
                if(orcRogue.get(i).GetIsDamaged()){
                    orcRogue.get(i).damageTaken(10);
                    orcRogue.get(i).IsDamaged(false);
                }
                if(player.GetBullet().get(j).IsCollided()){
                    if (player.GetBullet().get(j).DisappearAnimation()){
                        player.GetBullet().get(j).SetIsCollided(false);
                        player.GetBullet().remove(j);
                        player.GetAngle().remove(j);
                        orcRogue.get(i).moveBack((float) Math.cos(OrcRogueAngle.get(i)), (float) Math.sin(OrcRogueAngle.get(i)));
                        j--;
                    }
                }
            }
        }


        //Skeleton to Skeleton Collision
        for (int i = 0; i < Skeleton.size(); i++) {
            for (int j = i + 1; j < Skeleton.size(); j++) {
                if (tool.CircleCollision(SkeletonCircleCollision.get(i), SkeletonCircleCollision.get(j)) && !Skeleton.get(i).isHealthZero()) {
                    int moveX = (int) tool.GetCircleOverlap(SkeletonCircleCollision.get(i), SkeletonCircleCollision.get(j)) * (SkeletonCircleCollision.get(i).GetX() - SkeletonCircleCollision.get(j).GetX()) / (int) tool.GetCircleDist(SkeletonCircleCollision.get(i), SkeletonCircleCollision.get(j));
                    int moveY = (int) tool.GetCircleOverlap(SkeletonCircleCollision.get(i), SkeletonCircleCollision.get(j)) * (SkeletonCircleCollision.get(i).GetY() - SkeletonCircleCollision.get(j).GetY()) / (int) tool.GetCircleDist(SkeletonCircleCollision.get(i), SkeletonCircleCollision.get(j));
                    Skeleton.get(j).MoveNX(moveX);
                    Skeleton.get(j).MoveNY(moveY);
                }
            }
        }

        //Skeleton to Player Collision
        for (int i = 0; i < Skeleton.size(); i++) {
            if (SkeletonHitBox.get(i).intersects(player.HitBox) && !Skeleton.get(i).isHealthZero()) {
                Skeleton.get(i).moveBack((float) Math.cos(SkeletonAngle.get(i)), (float) Math.sin(SkeletonAngle.get(i)));
            }
        }

        //PlayerBullet to Skeleton Collision
        for (int i = 0; i < Skeleton.size(); i++) {
            for (int j = 0; j < player.GetBullet().size(); j++) {
                if (SkeletonHitBox.get(i).intersects(new Rectangle((int) player.GetBullet().get(j).getX(),(int) player.GetBullet().get(j).getY(), 32, 32)) && !Skeleton.get(i).isHealthZero() && !player.GetBullet().get(j).IsCollided()){
                    player.GetBullet().get(j).SetIsCollided(true);
                    player.GetBullet().get(j).Movement(false);
                    Skeleton.get(i).IsDamaged(true);
                }
                if(Skeleton.get(i).GetIsDamaged()){
                    Skeleton.get(i).damageTaken(player.getDamage());
                    Skeleton.get(i).IsDamaged(false);
                }
                if(player.GetBullet().get(j).IsCollided()){
                    if (player.GetBullet().get(j).DisappearAnimation()){
                        player.GetBullet().get(j).SetIsCollided(false);
                        player.GetBullet().remove(j);
                        player.GetAngle().remove(j);
                        Skeleton.get(i).moveBack((float) Math.cos(SkeletonAngle.get(i)), (float) Math.sin(SkeletonAngle.get(i)));
                        j--;
                    }
                }
            }
        }


        //SkeletonRogue to SkeletonRogue Collision
        for (int i = 0; i < SkeletonRogue.size(); i++) {
            for (int j = i + 1; j < SkeletonRogue.size(); j++) {
                if (tool.CircleCollision(SkeletonRogueCircleCollision.get(i), SkeletonRogueCircleCollision.get(j)) && !SkeletonRogue.get(i).isHealthZero()) {
                    int moveX = (int) tool.GetCircleOverlap(SkeletonRogueCircleCollision.get(i), SkeletonRogueCircleCollision.get(j)) * (SkeletonRogueCircleCollision.get(i).GetX() - SkeletonRogueCircleCollision.get(j).GetX()) / (int) tool.GetCircleDist(SkeletonRogueCircleCollision.get(i), SkeletonRogueCircleCollision.get(j));
                    int moveY = (int) tool.GetCircleOverlap(SkeletonRogueCircleCollision.get(i), SkeletonRogueCircleCollision.get(j)) * (SkeletonRogueCircleCollision.get(i).GetY() - SkeletonRogueCircleCollision.get(j).GetY()) / (int) tool.GetCircleDist(SkeletonRogueCircleCollision.get(i), SkeletonRogueCircleCollision.get(j));
                    SkeletonRogue.get(j).MoveNX(moveX);
                    SkeletonRogue.get(j).MoveNY(moveY);
                }
            }
        }


        //SkeletonRouge to Player Collision
        for (int i = 0; i < SkeletonRogue.size(); i++) {
            if (tool.CircleCollision(player.GetAggroRadius(), SkeletonRogueCircleCollision.get(i)) && !SkeletonRogue.get(i).isHealthZero()) {
                SkeletonRogue.get(i).moveBack((float) Math.cos(SkeletonRogueAngle.get(i)), (float) Math.sin(SkeletonRogueAngle.get(i)));
            }
        }

        //PlayerBullet to SkeletonRouge Collision
        for (int i = 0; i < SkeletonRogue.size(); i++) {
            for (int j = 0; j < player.GetBullet().size(); j++) {
                if (SkeletonRogueHitBox.get(i).intersects(new Rectangle((int) player.GetBullet().get(j).getX(),(int) player.GetBullet().get(j).getY(), 32, 32)) && !SkeletonRogue.get(i).isHealthZero() && !player.GetBullet().get(j).IsCollided()){
                    player.GetBullet().get(j).SetIsCollided(true);
                    player.GetBullet().get(j).Movement(false);
                    SkeletonRogue.get(i).IsDamaged(true);
                }
                if(SkeletonRogue.get(i).GetIsDamaged()){
                    SkeletonRogue.get(i).damageTaken(player.getDamage());
                    SkeletonRogue.get(i).IsDamaged(false);
                }
                if(player.GetBullet().get(j).IsCollided()){
                    if (player.GetBullet().get(j).DisappearAnimation()){
                        player.GetBullet().get(j).SetIsCollided(false);
                        player.GetBullet().remove(j);
                        player.GetAngle().remove(j);
                        SkeletonRogue.get(i).moveBack((float) Math.cos(SkeletonRogueAngle.get(i)), (float) Math.sin(SkeletonRogueAngle.get(i)));
                        j--;
                    }
                }
            }
        }


        //SkeletonBullet to Player Collision
        for (int i = 0; i < SkeletonBullet.size(); i++) {
            if (player.HitBox.intersects(new Rectangle((int) SkeletonBullet.get(i).getX(), (int) SkeletonBullet.get(i).getY(), (int) SkeletonBullet.get(i).getRadiusWH(), (int) SkeletonBullet.get(i).getRadiusWH()))) {
                SkeletonBullet.remove(i);
                SkeletonRogueAngleBullet.remove(i);
                player.LoseHealth();
                player.HitRed(255);
                sound.SetFile(7);
                sound.play();
                i--;
            }
        }
    }

    public void setAttackDelay() {
//Orc
        for (int i = 0; i < orcRogue.size(); i++) {
            if (orcRogue.get(i).isAggro){
                temp = OrcRogueAttackDelay.get(i) - 1;
                OrcRogueAttackDelay.set(i, temp);
            }
            if (OrcRogueAttackDelay.get(i) < 0) {

                if (OrcRogueAttackSpeed.get(i) < 0 && !orcRogue.get(i).isHealthZero()) {
                    OrcRogueAttackSpeed.set(i, 30);
                    OrcBullet.add(new BulletEnemy(orcRogue.get(i).getX() + 32, orcRogue.get(i).getY() + 32, 16, bulletImage));
                    OrcRogueAngleBullet.add(Math.atan2(player.getY() - orcRogue.get(i).getY(), player.getX() - orcRogue.get(i).getX()));
                }else {
                    temp = OrcRogueAttackSpeed.get(i);
                    temp--;
                    OrcRogueAttackSpeed.set(i, temp);
                }
            }
            temp = 0;
        }


        //Skeleton
        for (int i = 0; i < SkeletonRogueAttackDelay.size(); i++) {
            if (SkeletonRogue.get(i).isAggro){
                temp = SkeletonRogueAttackDelay.get(i) - 1;
                SkeletonRogueAttackDelay.set(i, temp);
            }
            if (SkeletonRogueAttackDelay.get(i) < 0) {
                if (SkeletonAttackSpeed.get(i) < 0) {
                    SkeletonAttackSpeed.set(i, 300);
                    SkeletonBullet.add(new BulletEnemy(SkeletonRogue.get(i).getX(), SkeletonRogue.get(i).getY(), 16, bulletImage));
                    SkeletonRogueAngleBullet.add(Math.atan2(player.getY() - SkeletonRogue.get(i).getY(), player.getX() - SkeletonRogue.get(i).getX()));
                } else {
                    temp = SkeletonAttackSpeed.get(i) - 1;
                    SkeletonAttackSpeed.set(i, temp);
                }
            }
            temp = 0;
        }
    }

    public void attackPlayer() {
        //Orc
        if (!player.GetEnemyAggro()) return;
        for (int i = 0; i < orc.size(); i++) {
            if (OrcHitBox.get(i).intersects(player.HitBox) && !orcAttacked.get(i)) {
                orcAttacked.set(i, true);
            }

            if (orcAttacked.get(i)) {
                temp = OrcMeleeDelay.get(i);
                temp--;
                OrcMeleeDelay.set(i, temp);
                if (OrcMeleeDelay.get(i) < 0) {
                    player.HitRed(255);
                    player.LoseHealth();
                    OrcMeleeDelay.set(i, 100);
                    orcAttacked.set(i, false);
                }
            }

            temp = 0;
        }

        //Skeleton
        for (int i = 0; i < Skeleton.size(); i++) {
            if (SkeletonHitBox.get(i).intersects(player.HitBox) && !SkeletonAttacked.get(i)) {
                SkeletonAttacked.set(i, true);
            }
            if (SkeletonAttacked.get(i)) {
                temp = SkeletonMeleeDelay.get(i);
                temp--;
                SkeletonMeleeDelay.set(i, temp);
                if (SkeletonMeleeDelay.get(i) < 0) {
                    player.HitRed(255);
                    player.LoseHealth();
                    SkeletonMeleeDelay.set(i, 100);
                    SkeletonAttacked.set(i, false);
                }
            }
            temp = 0;
        }
    }

    public void setBulletCount() {
        //Orc
        for (int i = OrcBullet.size() - 1; i >= 0; i--) {
            OrcBullet.get(i).Move(3, OrcRogueAngleBullet.get(i));
            //Bullet Deletion to avoid memory leak
            if (!OrcBullet.get(i).isAlive()) {
                OrcBullet.remove(i);
                OrcRogueAngleBullet.remove(i);
                i--;
            }
        }
        //Bullet
        for (int i = SkeletonBullet.size() - 1; i >= 0; i--) {
            SkeletonBullet.get(i).Move(3, SkeletonRogueAngleBullet.get(i));
            //Bullet Deletion to avoid memory leak
            if (!SkeletonBullet.get(i).isAlive()) {
                SkeletonBullet.remove(i);
                SkeletonRogueAngleBullet.remove(i);
                i--;
            }
        }
    }

    public void setEnemies() {
        for (int i = 0; i < orc.size(); i++) {
            //Melee Orc
            orc.get(i).update();
            orc.get(i).move(vel.get(i), OrcAngle.get(i), player.getX());
            //HitBox
            OrcHitBox.set(i, new Rectangle((int) orc.get(i).getX() + 32, (int) orc.get(i).getY() + 16, 32, 64));
            OrcCircleCollision.set(i, new Circle((int) orc.get(i).getX() + 24, (int) orc.get(i).getY() + 32, 24));
            //Angle
            OrcAngle.set(i, Math.atan2(player.getY() - orc.get(i).getY(), player.getX() - orc.get(i).getX()));
        }
        for (int i = 0; i < orcRogue.size(); i++) {
            //Range Orc
            orcRogue.get(i).update();
            orcRogue.get(i).move(vel.get(i), OrcRogueAngle.get(i), player.getX());
            //HitBox
            OrcRogueCircleCollision.set(i, new Circle((int) orcRogue.get(i).getX() + 24, (int) orcRogue.get(i).getY() + 32, 24));
            OrcRogueHitBox.set(i, new Rectangle((int) orcRogue.get(i).getX() + 32, (int) orcRogue.get(i).getY() + 16, 32, 64));
            //Angle
            OrcRogueAngle.set(i, Math.atan2(player.getY() - orcRogue.get(i).getY(), player.getX() - orcRogue.get(i).getX()));
        }

        for (int i = 0; i < Skeleton.size(); i++) {
            //Melee Orc
            Skeleton.get(i).update();
            Skeleton.get(i).move(vel.get(i), SkeletonAngle.get(i), player.getX());
            //HitBox
            SkeletonHitBox.set(i, new Rectangle((int) Skeleton.get(i).getX() + 32, (int) Skeleton.get(i).getY() + 16, 32, 64));
            SkeletonCircleCollision.set(i, new Circle((int) Skeleton.get(i).getX() + 24, (int) Skeleton.get(i).getY() + 32, 24));
            //Angle
            SkeletonAngle.set(i, Math.atan2(player.getY() - Skeleton.get(i).getY(), player.getX() - Skeleton.get(i).getX()));
        }
        for (int i = 0; i < SkeletonRogue.size(); i++) {
            //Range Skeleton
            SkeletonRogue.get(i).update();
            SkeletonRogue.get(i).move(vel.get(i), SkeletonRogueAngle.get(i), player.getX());
//            orcRogue.get(i).move(vel.get(i), OrcRogueAngle.get(i), player.getX());
            //HitBox
            SkeletonRogueCircleCollision.set(i, new Circle((int) SkeletonRogue.get(i).getX() + 24, (int) SkeletonRogue.get(i).getY() + 32, 24));
            SkeletonRogueHitBox.set(i, new Rectangle((int) SkeletonRogue.get(i).getX() + 32, (int) SkeletonRogue.get(i).getY() + 16, 32, 64));
            //Angle
            SkeletonRogueAngle.set(i, Math.atan2(player.getY() - SkeletonRogue.get(i).getY(), player.getX() - SkeletonRogue.get(i).getX()));
        }
    }

    public void deadEnemy() {
        //Orc
        for (int i = 0; i < orc.size(); i++) {
            if (orc.get(i).IsDead()) {
                for (int j = 0; j < 5; j++) {
                    items.AddCoin(1, (int) orc.get(i).getX() + tool.GetRandomNum(-30, 30), (int) orc.get(i).getY() + tool.GetRandomNum(-30, 30));
                }
                orc.remove(i);
                OrcAngle.remove(i);
                OrcCircleCollision.remove(i);
                OrcHitBox.remove(i);
                orcAttacked.remove(i);
                OrcMeleeDelay.remove(i);
                i--;
            }
        }
        for (int i = 0; i < orcRogue.size(); i++) {
            if (orcRogue.get(i).IsDead()) {
                for (int j = 0; j < 5; j++) {
                    items.AddCoin(1, (int) orcRogue.get(i).getX() + tool.GetRandomNum(-30, 30), (int) orcRogue.get(i).getY() + tool.GetRandomNum(-30, 30));
                }
                orcRogue.remove(i);
                OrcRogueHitBox.remove(i);
                OrcRogueAngle.remove(i);
                OrcRogueCircleCollision.remove(i);
                OrcRogueAttackDelay.remove(i);
                i--;
            }
        }
        //Skeleton
        for (int i = 0; i < Skeleton.size(); i++) {
            if (Skeleton.get(i).IsDead()) {
                for (int j = 0; j < 5; j++) {
                    items.AddCoin(1, (int) Skeleton.get(i).getX() + tool.GetRandomNum(-30, 30), (int) Skeleton.get(i).getY() + tool.GetRandomNum(-30, 30));
                }
                Skeleton.remove(i);
                SkeletonAngle.remove(i);
                SkeletonCircleCollision.remove(i);
                SkeletonHitBox.remove(i);
                SkeletonAttacked.remove(i);
                SkeletonMeleeDelay.remove(i);
                i--;
            }
        }
        for (int i = 0; i < SkeletonRogue.size(); i++) {
            if (SkeletonRogue.get(i).IsDead()) {
                for (int j = 0; j < 5; j++) {
                    items.AddCoin(1, (int) SkeletonRogue.get(i).getX() + tool.GetRandomNum(-30, 30), (int) SkeletonRogue.get(i).getY() + tool.GetRandomNum(-30, 30));
                }
                SkeletonRogue.remove(i);
                SkeletonRogueHitBox.remove(i);
                SkeletonRogueAngle.remove(i);
                SkeletonRogueCircleCollision.remove(i);
                SkeletonRogueAttackDelay.remove(i);
                i--;
            }
        }

    }
    public void ResetEnemy(){
        RemoveAggro();
        for (int i = 0; i < orc.size(); i++) {
                orc.remove(i);
                OrcAngle.remove(i);
                OrcCircleCollision.remove(i);
                OrcHitBox.remove(i);
                orcAttacked.remove(i);
                OrcMeleeDelay.remove(i);
                i--;
            }

        for (int i = 0; i < orcRogue.size(); i++) {
                orcRogue.remove(i);
                OrcRogueHitBox.remove(i);
                OrcRogueAngle.remove(i);
                OrcRogueCircleCollision.remove(i);
                OrcRogueAttackDelay.remove(i);
                i--;
        }
        //Skeleton
        for (int i = 0; i < Skeleton.size(); i++) {
                Skeleton.remove(i);
                SkeletonAngle.remove(i);
                SkeletonCircleCollision.remove(i);
                SkeletonHitBox.remove(i);
                SkeletonAttacked.remove(i);
                SkeletonMeleeDelay.remove(i);
                i--;
        }
        for (int i = 0; i < SkeletonRogue.size(); i++) {
                SkeletonRogue.remove(i);
                SkeletonRogueHitBox.remove(i);
                SkeletonRogueAngle.remove(i);
                SkeletonRogueCircleCollision.remove(i);
                SkeletonRogueAttackDelay.remove(i);
                i--;
        }
    }
    public void AddOrc(int Amount){
        for (int i = 0; i < Amount; i++) {
            int randomX = tool.GetRandomNum(64, 832);
            int randomY = tool.GetRandomNum(64, 480);

            orc.add(new EnemyOrc(tool, randomX, randomY, 96, 96));
            OrcHitBox.add(new Rectangle(randomX, randomY, 64, 96));
            OrcCircleCollision.add(new Circle(randomX, randomY, 24));
            OrcAngle.add(Math.atan2(randomY - player.getY(), randomX - player.getX()));
            vel.add(1);

            orcAttacked.add(false);
            OrcMeleeDelay.add(100);
        }
    }

    public void AddOrcRogue(int Amount){
        for (int i = 0; i < Amount; i++) {
            int randomX = tool.GetRandomNum(64, 832);
            int randomY = tool.GetRandomNum(64, 480);

            orcRogue.add(new EnemyOrcRogue(tool, randomX, randomY, 96, 96));
            OrcRogueHitBox.add(new Rectangle(randomX, randomY, 96, 32));
            OrcRogueAngle.add(Math.atan2(randomY - player.getY(), randomX - player.getX()));
            OrcRogueCircleCollision.add(new Circle(randomX, randomY, 24));

            OrcRogueAttackSpeed.add(2);
            OrcRogueAttackDelay.add(tool.GetRandomNum(1, 100));
            vel.add(1);
        }
    }

    public void AddSkeleton(int Amount){
        for (int i = 0; i < Amount; i++) {
            int randomX =  tool.GetRandomNum(64, 832);
            int randomY =  tool.GetRandomNum(64, 480);

            Skeleton.add(new EnemySkeleton(tool, randomX, randomY, 96, 96));
            SkeletonHitBox.add(new Rectangle(randomX, randomY, 64, 96));
            SkeletonCircleCollision.add(new Circle(randomX, randomY, 24));
            SkeletonAngle.add(Math.atan2(randomY - player.getY(), randomX - player.getX()));
            vel.add(1);

            SkeletonAttacked.add(false);
            SkeletonMeleeDelay.add(100);
        }
    }

    public void AddSkeletonRogue(int Amount){
        for (int i = 0; i < Amount; i++) {
            int randomX = tool.GetRandomNum(64, 832);
            int randomY = tool.GetRandomNum(64, 480);

            SkeletonRogue.add(new EnemySkeletonRogue(tool, randomX, randomY, 96, 96));
            SkeletonRogueHitBox.add(new Rectangle(randomX, randomY, 96, 32));
            SkeletonRogueAngle.add(Math.atan2(randomY - player.getY(), randomX - player.getX()));
            SkeletonRogueCircleCollision.add(new Circle(randomX, randomY, 24));

            SkeletonAttackSpeed.add(2);
            SkeletonRogueAttackDelay.add( tool.GetRandomNum(1, 100));
            vel.add(1);
        }
    }
}