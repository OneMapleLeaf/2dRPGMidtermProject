import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Objects;

public class TitleScreen {
    ArrayList<TitleScreenOrc> Orc = new ArrayList<>();
    InputHandler inputHandler;
    Button start, exit, option, credits, creditsback, OptionExitButton,VolumeGain,VolumeLoss,SFXLoss,SFXGain;
    private BufferedImage Start, Exit, Option, Credits;

    private BufferedImage WStart, WExit, WOption, WCredits;
    //W = Word
    private BufferedImage DecreaseLeft, IncreaseRight;

    //Option
    private BufferedImage DrawOption, OptionBack, WordBackOpti,OptionMenu,OptionBorder,OptionExit;
    //Credits
    private BufferedImage DrawCredits, CreditsBack, WordBackCred;
    private BufferedImage CurrentVolumeImage, CurrentSFXImage;

    private BufferedImage SBackground, SoulKnight;

    Tool tool;
    BufferedImage   Layout, Background,SoulK, Border,VolumeOptions;
    int VolumeNum = 0;
    int SFXNum = 0;
    boolean CVolume = true;
    boolean InTitleScreen = true;
    boolean BackTrigger;
    boolean IsOption, IsCredits;

    boolean OptionActive = false;
    boolean CreditsActive = false;
    boolean OptionContol;

    int spawnDelay = 100;


    Font font;


    TitleScreen(InputHandler inputHandler, Tool tool) {

        this.inputHandler = inputHandler;
        this.tool = tool;
        SetValues();

    }

    public void draw (Graphics2D g){

        g.setColor(Color.BLUE);
        g.drawImage(SBackground,0,0,960,640,null);

        //Title
        g.setColor(Color.cyan);
        g.drawImage(SoulKnight,230,90, 500, 150,null);

        for (int i = 0; i < Orc.size(); i++){
            Orc.get(i).draw(g);
        }
        //Buttons
        g.drawImage(Start, 370, 250, 250, 70, null);
        g.drawImage(WStart, 425, 260, 130, 50, null);

        g.drawImage(Option, 370, 330, 250, 70, null);
        g.drawImage(WOption, 410, 340, 190, 50, null);

        g.drawImage(Credits, 370, 410, 250, 70, null);
        g.drawImage(WCredits, 420, 420, 180, 50, null);

        g.drawImage(Exit, 370, 490, 250, 70, null);
        g.drawImage(WExit, 430, 500, 130, 45, null);

        start.draw(g);
        exit.draw(g);



        if (OptionActive){
            g.drawImage(OptionBorder, 330-30,150-45,400,400,null);
            g.drawImage(CurrentVolumeImage, 450-30, 310-45, 160, 40, null);
            g.drawImage(CurrentSFXImage, 450-30, 450-45, 160, 40, null);
            OptionExitButton.draw(g);
            VolumeLoss.draw(g);
            VolumeGain.draw(g);
            SFXLoss.draw(g);
            SFXGain.draw(g);
        }
        if (CreditsActive){
            g.drawImage(OptionBorder, 330-30,150-45,400,400,null);
            g.drawImage(CurrentVolumeImage, 450-30, 310-45, 160, 40, null);
            g.drawImage(CurrentSFXImage, 450-30, 450-45, 160, 40, null);
            OptionExitButton.draw(g);
            VolumeLoss.draw(g);
            VolumeGain.draw(g);
            SFXLoss.draw(g);
            SFXGain.draw(g);
        }
        if (CreditsActive){
            g.drawImage(DrawCredits,127, 50, 700,500,null);
            g.drawImage(CreditsBack,690,470,65,35,null);
            creditsback.draw(g);
            g.setFont(font);
            g.setFont(g.getFont().deriveFont(10.0f));
            g. setColor(Color.WHITE);
            g.drawString( "Assets used Authors:", 180,126);
            g.drawString( "Enemy, Player, Environment Sprite Sheets", 180,152);
            g.drawString( "Author : Anokolisa", 180,165);
            g.drawString( "UI", 180,191);
            g.drawString( "Author : Mounir Tohami", 180,204);
            g.drawString( "Shop Icons", 180,230);
            g.drawString( "Author : shikashipx", 180,243);
            g.drawString( "Heart Stat  Icon :", 550,152);
            g.drawString( "Author : Essssam:", 550,165);
            g.drawString( "SirRic Character:", 550,191);
            g.drawString( "Drawn by Yutuc, Eric:", 550,204);
            g.drawString( "UI, Props :", 550,230);
            g.drawString( "Drawn by Batac, Jabez :", 550,243);

            // Separation Line
            g.drawString( "---------------------------------------------------------------------------", 180,269);

            //Roles
            g.drawString( "Master Coder, Enemy, Player, All-around", 180,295);
            g.drawString( "Austria, Clark", 180,308);
            g.drawString( "Stage Creation", 180,334);
            g.drawString( "Batac, Jabez", 180,347);
            g.drawString( "UI", 180,373);
            g.drawString( "Pabustan, Allaine", 180,386);
            g.drawString( "Enemy, Stage Creation", 180,412);
            g.drawString( "Pandio, James Benedict", 180,425);
            g.drawString( "Title screen, UI", 550,295);
            g.drawString( "Reyes, John Lester", 550,308);
            g.drawString( "UI, Stage Creation", 550,334);
            g.drawString( "Trinidar, Rommer", 550,347);
            g.drawString( "Items, UI, Stage Creation", 550,373);
            g.drawString( "Yutuc, Eric", 550,387);

            BackTrigger = true;
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



    }
    public boolean getInTitleScreen(){
        return InTitleScreen;
    }
    public void setInTitleScreen(boolean EXIT){
        InTitleScreen = EXIT;
    }
    public void setVolumeNum(int SetVolume){
        this.VolumeNum = SetVolume;
    }
    public void setSFX(int SetSFX){
        this.SFXNum = SetSFX;
    }
    public int getVolumeNum(){
        return VolumeNum;
    }
    public int getSFXNum(){
        return SFXNum;
    }

    public void update (){
        if (tool.GetMilli() > spawnDelay) {
            Orc.add(new TitleScreenOrc((int) tool.GetRandomNum(1, 2), 500, 96, 96, tool));
            tool.ResetMilliCounter();
            spawnDelay = (int)tool.GetRandomNum(3000, 5000);
        }

        if(VolumeGain.ButtonReady(inputHandler)){
            VolumeNum++;
            CVolume = true;
            if(VolumeNum > 5 ){
                VolumeNum = 5;
            }
        }
        if(VolumeLoss.ButtonReady(inputHandler)){
            VolumeNum--;
            CVolume = true;
            if(VolumeNum < 0 ){
                VolumeNum = 0;
            }
        }

        if(SFXGain.ButtonReady(inputHandler)){
            SFXNum++;
            CVolume = true;
            if(SFXNum > 5 ){
                SFXNum = 5;
            }
        }
        if(SFXLoss.ButtonReady(inputHandler)){
            SFXNum--;
            CVolume = true;
            if(SFXNum < 0 ){
                SFXNum = 0;
            }
        }


        if(start.ButtonReady(inputHandler)){
            InTitleScreen = false;
        }
        if (credits.ButtonReady(inputHandler)){
            CreditsActive = true;
        }
        if (creditsback.ButtonReady(inputHandler)&& CreditsActive){
            CreditsActive = false;
        }
        if(option.ButtonReady(inputHandler)){
            OptionContol = false;
            OptionActive = true;
        }
        if(OptionExitButton.ButtonReady(inputHandler) && OptionActive){
            OptionActive = false;
            OptionContol = true;

        }
        if (exit.ButtonReady(inputHandler) && InTitleScreen){
            System.exit(0);
        }

        for(TitleScreenOrc orcnum : Orc){
            orcnum.update();
        }
        for (int i = 0; i < Orc.size(); i++){
            if (!Orc.get(i).isAlive()){
                System.out.println("qweqweqwe");
                Orc.remove(i);
                i--;
            }
        }
        inputHandler.CLicked();
    }
    public void SetValues(){
        try{
            Layout = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("UI/itemset0/GUI.png")));
            Background = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("UI/itemset0/TitleSreenM.png")));
            SoulK = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("UI/itemset0/SoulKnight (1).png")));
            OptionMenu = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("UI/itemset0/GUI_Option.png")));
            Border = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("UI/itemset0/Final Border.png")));
            VolumeOptions = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("UI/itemset0/Volume and Pause Menu Clicked Buttons.png")));


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



        SBackground = Background.getSubimage(0,0,150,47);
        SoulKnight= SoulK.getSubimage(5,3,510,89);

        Start = Layout.getSubimage(145,81,47,14);
        WStart = Layout.getSubimage(71,148,18,7);
        Exit = Layout.getSubimage(145,81,47,14);
        WExit = Layout.getSubimage(8,164,18,7);
        Option = Layout.getSubimage(145,81,47,14);
        WOption = Layout.getSubimage(99,164,30,7);
        Credits = Layout.getSubimage(145,81,47,14);
        WCredits = Layout.getSubimage(36,148,30,7);
        DrawOption = Border.getSubimage(0,0,151,134);
        DrawCredits = Border.getSubimage(0,0,151,134);
        WordBackOpti = Layout.getSubimage(9,132,17,6);
        WordBackCred = Layout.getSubimage(9,132,17,6);

        //ExitButtons
        CreditsBack = Layout.getSubimage(113,81,30,14);

        OptionBorder = OptionMenu.getSubimage(88,50,67,69);
        OptionExit = OptionMenu.getSubimage(146,54,9,9);

        DecreaseLeft = VolumeOptions.getSubimage(2,1,14,14);
        IncreaseRight = VolumeOptions.getSubimage(66,1,14,14);



        this.start = new Button(425, 260, 130, 50, WStart);
        this.option = new Button(410, 340, 190, 50,  WOption);
        this.credits = new Button (420, 420, 180, 50, WCredits);
        this.exit = new Button (430, 500, 130, 45,  WExit);
        this.creditsback = new Button (696,477,50,17,  WordBackCred);


        this.OptionExitButton = new Button(650,130,50,50, OptionExit);

        //Option Menu Buttons
        this.VolumeLoss = new Button(395-30,310-45,40,40, DecreaseLeft);
        this.VolumeGain = new Button(625-30,310-45,40,40, IncreaseRight);

        //SFX Option Adjuster
        this.SFXLoss = new Button(400-30,450-45,40,40, DecreaseLeft);
        this.SFXGain = new Button(620-30,450-45,40,40, IncreaseRight);

    }

    public int GetVolumeSound(){
        return VolumeNum;
    }
    public void SetCVolume(boolean CV){
        CVolume = CV;
    }
    public boolean GetCVolume(){
        return CVolume;
    }
}