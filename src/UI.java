import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Objects;

public class UI {

    InputHandler inputHandler;
    Button bag, Pause, OptionB, ResumeW, ExitW, VolumeGain, VolumeLoss, SFXLoss, SFXGain, OptionExitButton;
    Button ExitShopButton;
    Button HPBUTTON;
    Button DMGBUTTON;
    Button ATKSPDBUTTON;
    Button MANABUTTON;
    Button YESBUTTON;
    Button NOBUTTON;
    Button DungeonBYes, DungeonBNo;
    Button OpenUpgradeButton, CloseUpgradeButton, UpgradeS1, UpgradeS2, UpgradeS3, UpgradeS4, UpgradeS5, UpgradeS6, UpgradeS7, UpgradeS8;
    private BufferedImage  USlot1,  USlot2, USlot3, USlot4, USlot5, USlot6, USlot7, USlot8;
    private BufferedImage PauseBorder;
    private BufferedImage BagIcon, BagOpen, BagContent,CharacterFullBody, Option;
    private BufferedImage PButton, RButton, KnightName, ResumeWord, ExitWord;
    private BufferedImage DecreaseLeft, IncreaseRight;
    private BufferedImage OptionBorder, OptionExit;
    private BufferedImage Lock,AnotherButton,YesText,NoText,PurchaseLayout,ShopLay, ManaPotion, HealthPotion, ShopLayout,StatsElements, button, UIStats;
    private BufferedImage StatsButton,ATKSPDPotion, DamagePotion,ATKSPDIcon, DamageIcon,HealthIcon, ManaIcon;
    private BufferedImage OptionLayout, CharImage, SirRicE;
    private BufferedImage UpgradeSource, Upgrade, UpgradeExitButton;
    int currentImageNum = 1;
    Tool tool;
    private BufferedImage Idle,Invent,Layout, Content, KnightTitle, VolumeOptions, CurrentVolumeImage, CurrentSFXImage, OptionMenu;
    int VolumeNum = 0;
    int SFXNum = 0;
    Font font;
    int ManaPotionPrice = 5, HpPotionPrice = 50, DamagePotionPrice = 5, AttackSpeedPotionPrice = 5;
    int DPrice = 10, ASPrice = 10, MHPPrice = 10, ProjSPrice = 10;

    boolean PurchasingHP = false;
    boolean PurchasingDMG = false;
    boolean PurchasingATKSPEED = false;
    boolean PurchasingMANA = false;

    boolean BagActive = false;
    boolean PauseActive = false;
    boolean OptionActive = false;
    boolean SetVolume = true;
    boolean SetSFX = true;
    boolean SirRicActive = false;
    boolean ShopActive = false;
    boolean YesNOActive = false;
    boolean YesNOEnterActive = false;

    int AddInventX = 120;
    int AddInventY = 345;

    int[][] InventoryX = new int[][]{
            {120, 182, 246, 310},
            {120, 182, 246, 310},
            {120, 182, 246, 310}
    };

    int[][] InventoryY = new int[][]{
            {345, 345, 345, 345},
            {407, 407, 407, 407},
            {469, 469, 469, 469}
    };

    int[][] OccuInt = new int[][]{
            {0, 0, 0, 0},
            {0, 0, 0, 0},
            {0, 0, 0, 0}
    };

    ArrayList<MinusCoin> minusCoins = new ArrayList<>();
    ArrayList<Button> Item = new ArrayList<>();

    TitleScreen titleScreen;
    Player player;
    Stage stage;

    private boolean CVolume;


    UI(InputHandler inputHandler, Tool tool, TitleScreen titleScreen, Player player, Stage stage){
         this.stage = stage;
        this.player = player;
        this.titleScreen = titleScreen;
        this.inputHandler = inputHandler;
        this.tool = tool;
        SetValues();
    }

    public void draw (Graphics2D g){
      // g.drawOval(SirRic.GetX(), SirRic.GetY(), SirRic.GetRadiusWH(), SirRic.GetRadiusWH());

        bag.draw(g);
        Pause.draw(g);


        if(BagActive){
            g.drawImage(BagOpen, 800, 30, 55, 55, null);
            g.drawImage(BagContent, 80, 300, 400, 250, null);
            g.drawImage(CharImage, 345, 370, 64 * 2, 64 * 2, null);
            g.drawImage(KnightName, 362, 344, 81, 35, null);
            // stats
            g.drawImage(OptionLayout, 490, 285, 350, 300, null);
            //HP ICON
            g.drawImage(StatsButton, 535, 355, 37, 37, null);
            g.drawImage(HealthIcon, 540, 360, 24, 24, null);
            //DAMAGE ICON
            g.drawImage(StatsButton, 535, 395, 37, 37, null);
            g.drawImage(DamageIcon, 539, 401, 22, 22, null);
            //ATTACK SPEED ICON
            g.drawImage(StatsButton, 535, 440, 37, 37, null);
            g.drawImage(ATKSPDIcon, 539, 446, 22, 22, null);
            //MANA ICON
            g.drawImage(StatsButton, 535, 485, 37, 37, null);
            g.drawImage(ManaIcon, 540, 491, 22, 22, null);

            g.setColor(Color.white);
            g.setFont(font);
            g.setFont(g.getFont().deriveFont(10.0f));
            g.drawString("PLAYER STATS", 595, 340);

            //HP
            g.setColor(Color.green);
            g.setFont(font);
            g.setFont(g.getFont().deriveFont(10.0f));
            g.drawString("HEALTH -  ", 577, 377);
            g.setColor(Color.WHITE);
            g.drawString("   " + player.getHealth(),657, 377);

            //Damage
            g.setColor(Color.red);
            g.setFont(font);
            g.setFont(g.getFont().deriveFont(10.0f));
            g.drawString("DAMAGE -  ", 577, 419);
            g.setColor(Color.WHITE);
            g.drawString("   " + player.getDamage(),657, 419);


            //AttackSpeed
            g.setColor(Color.YELLOW);
            g.setFont(font);
            g.setFont(g.getFont().deriveFont(10.0f));
            g.drawString("ATTACK SPEED " ,577, 452);
            g.drawString("-  ",680, 460);
            g.drawString("COOL DOWN", 577, 472);
            g.setColor(Color.WHITE);
            g.drawString("   " + player.getAttackSpeed(),690, 460);

            //Mana
            g.setColor(Color.CYAN);
            g.setFont(font);
            g.setFont(g.getFont().deriveFont(10.0f));
            g.drawString("MANA -  ", 577, 508);
            g.setColor(Color.WHITE);
            g.drawString("   " + player.getMana(),637, 508);

            for (int i = 0; i < Item.size(); i++) {
                Item.get(i).draw(g);
            }

        }
        if (ShopActive){
            g.drawImage(button, 898, 135, 53, 45, null);
            g.drawImage(ShopLayout, 450, 300, 500, 400, null);
            g.drawImage(Upgrade, 100-80,300,380,300,null);

            g.setFont(font);
            g.setFont(g.getFont().deriveFont(9.0f));
            g.setColor(Color.GREEN);
            g.drawString("+Damage", 143-80,460);
            g.drawString("+AtkSpd", 220-80,460);
            g.drawString("+MaxHP", 302-80,460);
            g.drawString("+Armor", 383-80,460);

            g.drawString("+ARegen", 140-80,570);
            g.drawString("+ProjSPD", 220-80,570);
            g.drawString("+AMOGUS", 302-80,570);
            g.drawString("+BACON", 383-80,570);

            //upper items
            g.drawImage(ManaPotion, 590, 403, 40, 40, null);
            g.drawImage(HealthPotion, 503, 403, 40, 40, null);
            g.drawImage(Lock, 685, 408, 48, 48, null);
            g.drawImage(Lock, 773, 408, 48, 48, null);
            g.drawImage(Lock, 860, 408, 48, 48, null);
            //lower items
            g.drawImage(DamagePotion, 590, 488, 40, 40, null);
            g.drawImage(ATKSPDPotion, 503, 488, 40, 40, null);
            g.drawImage(Lock, 685, 492, 48, 48, null);
            g.drawImage(Lock, 773, 492, 48, 48, null);
            g.drawImage(Lock, 860, 492, 48, 48, null);



            //item names
            g.setColor(Color.WHITE);
            g.setFont(font);
            g.setFont(g.getFont().deriveFont(5.0f));
            g.drawString("RECOVERY ", 493, 383);
            g.drawString("POTION", 498, 393);
            g.drawString("- " + HpPotionPrice + "  GOLD", 535, 388);

            g.setFont(font);
            g.setFont(g.getFont().deriveFont(5.0f));
            g.drawString("MANA ", 588, 383);
            g.drawString("POTION", 585, 393);
            g.drawString("- " + ManaPotionPrice + "  GOLD", 618, 388);

            g.setFont(font);
            g.setFont(g.getFont().deriveFont(5.0f));
            g.drawString("DAMAGE ", 583, 468);
            g.drawString("POTION", 585, 478);
            g.drawString("- " + DamagePotionPrice + "  GOLD", 618, 473);

            g.setFont(font);
            g.setFont(g.getFont().deriveFont(5.0f));
            g.drawString("ATK SPEED ", 493, 468);
            g.drawString("POTION", 498, 478);
            g.drawString("- " + AttackSpeedPotionPrice + "  GOLD", 535, 473);

        }
        if(PauseActive){
            g.drawImage(RButton, 878, 30, 52, 52, null);
            g.drawImage(PauseBorder,330,150,400, 400, null);
            OptionB.draw(g);
            ResumeW.draw(g);
            ExitW.draw(g);
        }

        if(OptionActive){
            g.drawImage(OptionBorder, 330-30,150-45,400,400,null);
            g.drawImage(CurrentVolumeImage, 450-30, 310-45, 160, 40, null);
            g.drawImage(CurrentSFXImage, 450-30, 450-45, 160, 40, null);
            OptionExitButton.draw(g);
            VolumeLoss.draw(g);
            VolumeGain.draw(g);
            SFXLoss.draw(g);
            SFXGain.draw(g);
        }

        if(YesNOActive){
            g.drawImage(PurchaseLayout, 380, 100, 450, 250, null );
            g.drawImage(AnotherButton, 445, 220, 150,100,null);
            g.drawImage(NoText,488, 240, 80,80,null);
            g.drawImage(AnotherButton, 595, 220, 150,100,null);
            g.drawImage(YesText,635, 240, 80,80,null);

            g.setColor(Color.WHITE);
            g.setFont(font);
            g.setFont(g.getFont().deriveFont(10.0f));
            g.drawString("ARE YOU SURE YOU WANT", 485, 155);
            g.drawString("TO BUY THIS ITEM?", 500, 175);
        }
        if(YesNOEnterActive){
            g.drawImage(PurchaseLayout, 380, 100, 450, 250, null );
            g.drawImage(AnotherButton, 445, 220, 150,100,null);
            g.drawImage(NoText,488, 240, 80,80,null);
            g.drawImage(AnotherButton, 595, 220, 150,100,null);
            g.drawImage(YesText,635, 240, 80,80,null);

            g.setColor(Color.WHITE);
            g.setFont(font);
            g.setFont(g.getFont().deriveFont(10.0f));
            g.drawString("ARE YOU SURE YOU WANT", 485, 155);
            g.drawString("ENTER THE DUNGEON?", 500, 175);
        }

        if (SirRicActive && !ShopActive){
            g.drawImage(SirRicE, 350 + 64, 280 , 64,64, null);
        }

        switch (SFXNum) {
            case 0 -> CurrentSFXImage =  VolumeOptions.getSubimage(19,77,44,12);
            case 1 -> CurrentSFXImage =  VolumeOptions.getSubimage(19,62,44,12);
            case 2 -> CurrentSFXImage =  VolumeOptions.getSubimage(19,47,44,12);
            case 3 -> CurrentSFXImage =  VolumeOptions.getSubimage(19,32,44,12);
            case 4 -> CurrentSFXImage =  VolumeOptions.getSubimage(19,17,44,12);
            case 5 -> CurrentSFXImage =  VolumeOptions.getSubimage(19,2, 44,12);

        }
        switch (VolumeNum) {
            case 0 -> CurrentVolumeImage =  VolumeOptions.getSubimage(19,77,44,12);
            case 1 -> CurrentVolumeImage =  VolumeOptions.getSubimage(19,62,44,12);
            case 2 -> CurrentVolumeImage =  VolumeOptions.getSubimage(19,47,44,12);
            case 3 -> CurrentVolumeImage =  VolumeOptions.getSubimage(19,32,44,12);
            case 4 -> CurrentVolumeImage =  VolumeOptions.getSubimage(19,17,44,12);
            case 5 -> CurrentVolumeImage =  VolumeOptions.getSubimage(19,2, 44,12);
        }
        for (int i = 0; i < minusCoins.size(); i++) {
            minusCoins.get(i).draw(g);
        }
    }


    public void update(){
        for (int i = 0; i < minusCoins.size(); i++) {
            minusCoins.get(i).move();
        }
        for (int i = 0; i < minusCoins.size(); i++) {
            if(!minusCoins.get(i).IsAlive()){
                minusCoins.remove(i);
                i--;
            }
        }

        for (int i = 0; i < Item.size(); i++) {
            if(Item.get(i).ButtonReady(inputHandler) && BagActive){
                for (int j = 0; j < 3; j++) {
                    player.GainHealth();
                }
                for (int j = 0; j < 3; j++) {
                    for (int k = 0; k < 4; k++) {
                        if (InventoryX[j][k] == Item.get(i).x && InventoryY[j][k] == Item.get(i).y) {
                            OccuInt[j][k] = 0;
                            break;
                        }
                    }
                }
                Item.remove(i);
                i--;
            }
        }

        if (player.IsInteractExplore()){
            stage.setStage(1);
            YesNOEnterActive = true;
        }
        if (YesNOEnterActive){
            if(DungeonBYes.ButtonReady(inputHandler)){
                player.SetAggroEnemy(true);
                YesNOEnterActive = false;
            }
            if(DungeonBNo.ButtonReady(inputHandler)){
                YesNOEnterActive = false;
                player.SetX(100);
                player.SetY(100);
                stage.setStage(0);
            }
        }

        if (UpgradeS1.ButtonReady(inputHandler) && ShopActive){
            if (player.GetCoin() > DPrice){
                minusCoins.add(new MinusCoin(inputHandler.getX(), inputHandler.getY(), DPrice));
                player.LoseCoin(DPrice);
                DPrice += 5;
                player.AddDamage(3);
            }
        }
        if (UpgradeS2.ButtonReady(inputHandler) && ShopActive){
            if (player.GetCoin() > ASPrice){
                minusCoins.add(new MinusCoin(inputHandler.getX(), inputHandler.getY(), ASPrice));
                player.LoseCoin(ASPrice);
                ASPrice += 10;
                player.AddAttackSpeed(20);
            }
        }
        if (UpgradeS3.ButtonReady(inputHandler) && ShopActive){
            if (player.GetCoin() > MHPPrice){
                minusCoins.add(new MinusCoin(inputHandler.getX(), inputHandler.getY(), MHPPrice));
                player.LoseCoin(MHPPrice);
                MHPPrice += 20;
                player.AddMaxHealth(1);
                player.GainHealth();
            }
        }
        if (UpgradeS6.ButtonReady(inputHandler) && ShopActive){
            if(player.GetCoin() > ProjSPrice){
                minusCoins.add(new MinusCoin(inputHandler.getX(), inputHandler.getY(), ProjSPrice));
                player.LoseCoin(ProjSPrice);
                ProjSPrice += 20;
                player.AddProjectileSpeed(1);
            }
        }


        if (HPBUTTON.ButtonReady(inputHandler) && ShopActive){
            YesNOActive = true;
            PurchasingHP = true;
        }
        if (PurchasingHP){
            if (YESBUTTON.ButtonReady(inputHandler) && YesNOActive) {
                if (player.GetCoin() > HpPotionPrice){
                    player.LoseCoin(HpPotionPrice);
                }
                if (Item.size() < 12){
                    for (int i = 0; i < 3; i++) {
                        for (int j = 0; j < 4; j++) {
                            if(OccuInt[i][j] == 0){
                                Item.add(new Button(InventoryX[i][j], InventoryY[i][j], 40, 40, HealthPotion));
                                OccuInt[i][j] = 1;
                                i = 3;
                                j = 4;
                            }
                        }
                    }

                }

                YesNOActive = false;
                PurchasingHP = false;
            }
            if (NOBUTTON.ButtonReady(inputHandler) && YesNOActive) {
                YesNOActive = false;
                PurchasingHP = false;
            }
        }


        if(SetVolume){
            SetVolume = false;
            VolumeNum = titleScreen.getVolumeNum();
        }
        if(SetSFX){
            SetSFX = false;
            SFXNum = titleScreen.getSFXNum();
        }
        if(VolumeGain.ButtonReady(inputHandler)){
            VolumeNum++;
            CVolume = true;
            if(VolumeNum > 5 ){
                VolumeNum = 5;
            }
            titleScreen.setVolumeNum(VolumeNum);
        }
        if(VolumeLoss.ButtonReady(inputHandler)){
            VolumeNum--;
            CVolume = true;
            if(VolumeNum < 0 ){
                VolumeNum = 0;
            }
            titleScreen.setVolumeNum(VolumeNum);
        }

        if(SFXGain.ButtonReady(inputHandler)){
            SFXNum++;
            CVolume = true;
            if(SFXNum > 5 ){
                SFXNum = 5;
            }
            titleScreen.setSFX(SFXNum);
        }
        if(SFXLoss.ButtonReady(inputHandler)){
            SFXNum--;
            CVolume = true;
            if(SFXNum < 0 ){
                SFXNum = 0;
            }
            titleScreen.setSFX(SFXNum);
        }

        if(bag.ButtonReady(inputHandler) && !PauseActive){
            BagActive = !BagActive;
        }
        if(Pause.ButtonReady(inputHandler)){
            PauseActive = true;
            BagActive = false;
        }
        if(ResumeW.ButtonReady(inputHandler) && PauseActive){
            PauseActive = false;

        }

        if(ResumeW.ButtonReady(inputHandler) && PauseActive){
            PauseActive = false;
        }

        if(OptionB.ButtonReady(inputHandler) && PauseActive){
            OptionActive = true;
            PauseActive = false;
        }

        if(OptionExitButton.ButtonReady(inputHandler) && OptionActive){
            OptionActive = false;
            PauseActive = true;
        }
        if (ExitW.ButtonReady(inputHandler) && PauseActive){
            titleScreen.setInTitleScreen(true);
            PauseActive = false;
            SetVolume = true;
            SetSFX = true;
        }
        if (player.IsInteractSirRic()){
            if(inputHandler.E()){
                ShopActive = true;
            }
            SirRicActive = true;
        } else SirRicActive = false;

        if (ExitShopButton.ButtonReady(inputHandler) || CloseUpgradeButton.ButtonReady(inputHandler) && ShopActive){
            ShopActive = false;
        }
        inputHandler.CLicked();
        player.SetSfxVol(SFXNum);
        if (CVolume) {
            CVolume = false;
        }
        Animation();

    }

    private void Animation() {
        if (tool.GetMilli() >= 300) {
            tool.ResetMilliCounter();
            currentImageNum++;
            if (currentImageNum > 4) {
                currentImageNum = 0;
            }
        }
        switch (currentImageNum) {
            case 1 -> CharacterFullBody = Idle.getSubimage(0, 0, 32, 32);
            case 2 -> CharacterFullBody = Idle.getSubimage(32, 0, 32, 32);
            case 3 -> CharacterFullBody = Idle.getSubimage(64, 0, 32, 32);
            case 4 -> CharacterFullBody = Idle.getSubimage(96, 0, 32, 32);
        }
    }

    public void SetValues(){

        try{
            ShopLay = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("UI/itemset0/GUI_Option.png")));
            StatsElements = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("UI/itemset0/#2 - Transparent & Drop Shadow.png")));
            UIStats = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("UI/itemset0/hearts.png")));
            Invent = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("UI/itemset0/GUI Final.png")));
            Content = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("UI/itemset0/UI the best.png")));
            Layout = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("UI/itemset0/GUI.png")));
            Idle = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("Pixel Crawler/Heroes/Knight/Idle/Idle-Sheet.png")));
            KnightTitle = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("UI/itemset0/Knight Title.png")));
            VolumeOptions = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("UI/itemset0/Volume and Pause Menu Clicked Buttons.png")));
            OptionMenu = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("UI/itemset0/GUI_Option.png")));
            UpgradeSource = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("UI/itemset0/Upgrades.png")));
        } catch (IOException e){
            throw new RuntimeException(e);
        }

        try{
            InputStream is = getClass().getResourceAsStream("Font/Pixeled.ttf");
            assert is != null;
            font = Font.createFont(Font.TRUETYPE_FONT, is);
        }   catch (FontFormatException | IOException e){
            throw new RuntimeException(e);
        }

        Lock = Layout.getSubimage(100, 67, 16, 16);
        ATKSPDIcon = StatsElements.getSubimage(67, 99, 24,24);
        DamageIcon = StatsElements.getSubimage(290, 165, 24,24);
        ManaIcon = StatsElements.getSubimage(37, 291, 24,24);
        DamagePotion = StatsElements.getSubimage(229, 291, 24,26);
        ATKSPDPotion = StatsElements.getSubimage(197, 291, 24,26);
        ManaPotion = StatsElements.getSubimage(165, 291, 24,26);
        HealthPotion = StatsElements.getSubimage(133, 291, 24,26);
        ShopLayout = ShopLay.getSubimage(4, 110, 80, 80);
        OptionLayout = Layout.getSubimage(2, 33, 48, 48);
        HealthIcon = UIStats.getSubimage(0, 0, 16,16);
        StatsButton = Layout.getSubimage(33, 80, 16,16);
        button = ShopLay.getSubimage(75, 117, 16,9);
        BagIcon = Invent.getSubimage(88,95,23,23);
        BagOpen = Invent.getSubimage(64,95,23,23);
        CharImage = Idle.getSubimage(0, 0, 32, 32);
        BagContent = Content.getSubimage(113,255,78,48);
        KnightName = KnightTitle.getSubimage(145,229,45,18);
        PurchaseLayout = Invent.getSubimage(2, 34, 48,46);
        NoText = Invent.getSubimage(139, 147, 16,16);
        YesText = Invent.getSubimage(170, 147, 16,16);
        AnotherButton = Invent.getSubimage(113, 113, 32,16);
        SirRicE = ShopLay.getSubimage(137, 38, 16, 16);

        //Upgrade
        Upgrade = UpgradeSource.getSubimage(3,110,88,65);
        UpgradeExitButton = UpgradeSource.getSubimage(82,117,9,9);

        //Bag Button Transitions
        BagIcon = Invent.getSubimage(88,95,23,23);
        BagOpen = Invent.getSubimage(64,95,23,23);

        // Character im the inventory
        CharacterFullBody = Idle.getSubimage(0, 0, 32, 32);

        // Pause & Resume Icon Transitions
        PButton = Layout.getSubimage(167,39,18,18);
        RButton = Invent.getSubimage(167,46,19,19);

        // Bag Button Contents
        BagContent = Content.getSubimage(113,255,78,48);
        KnightName = OptionMenu.getSubimage(136,2,45,21);

        //Pause Menu Choices
        PauseBorder = Layout.getSubimage(11,192,50,65);
        ResumeWord = KnightTitle.getSubimage(17,201,30,14);
        Option = Content.getSubimage(17,217,30,14);
        ExitWord = KnightTitle.getSubimage(17,233,30,14);

        //Volume & SFX Increase Button
        DecreaseLeft = VolumeOptions.getSubimage(2,1,14,14);
        IncreaseRight = VolumeOptions.getSubimage(66,1,14,14);

        //Option Buttons Images
        OptionBorder = OptionMenu.getSubimage(88,50,67,69);
        OptionExit = OptionMenu.getSubimage(146,54,9,9);

        //buttons in the screen
        this.bag = new Button(800, 30, 55, 55, BagIcon);
        this.Pause = new Button(880, 30, 50, 50, PButton);

        //pause tab choices
        this.ResumeW = new Button (380,193,240,100, ResumeWord);
        this.OptionB = new Button (380,297,240,100, Option);
        this.ExitW = new Button (380,401,240,100, ExitWord);

        //Volume Option Adjuster
        this.VolumeLoss = new Button(395-30,310-45,40,40, DecreaseLeft);
        this.VolumeGain = new Button(625-30,310-45,40,40, IncreaseRight);

        //SFX Option Adjuster
        this.SFXLoss = new Button(400-30,450-45,40,40, DecreaseLeft);
        this.SFXGain = new Button(620-30,450-45,40,40, IncreaseRight);

        //Option Menu Buttons
        this.OptionExitButton = new Button(650,130,50,50, OptionExit);

        //Upgrade Slots
        this.UpgradeS1 = new Button(135-80,370,75,81, USlot1);
        this.UpgradeS2 = new Button(216-80,370,75,81, USlot2);
        this.UpgradeS3 = new Button(297-80,370,75,81, USlot3);
        this.UpgradeS4 = new Button(375-80,370,75,81, USlot4);

        this.UpgradeS5 = new Button(130-80,477,77,81, USlot5);
        this.UpgradeS6 = new Button(215-80,477,77,81, USlot6);
        this.UpgradeS7 = new Button(300-80,477,77,81, USlot7);
        this.UpgradeS8 = new Button(375-80,477,77,81, USlot8);

        this.CloseUpgradeButton = new Button(440-80,332,40,40,UpgradeExitButton);


        this.ExitShopButton = new Button(898, 335, 53, 45, button);
        this.bag = new Button(800, 30, 55, 55, BagIcon);
        this.HPBUTTON = new Button(491, 393, 65, 56, StatsButton);
        this.DMGBUTTON = new Button(578, 478, 66, 56, StatsButton);
        this.ATKSPDBUTTON = new Button(491, 478, 66, 56, StatsButton);
        this.MANABUTTON = new Button(578, 393, 66, 56, StatsButton);
        this.YESBUTTON = new Button(595, 220, 150,100, AnotherButton);
        this.NOBUTTON = new Button(445, 220, 150,100, AnotherButton);
        this.DungeonBYes = new Button(595, 220, 150,100, AnotherButton);
        this.DungeonBNo = new Button(445, 220, 150,100, AnotherButton);
    }

   public int GetSfxVol(){
        return SFXNum;
   }
}
