import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Objects;

public class Stage {
    int StageLvl = 0;
    private BufferedImage BlackTiles, BottomWall, Floor, Wall, Pillar, Drainage, TopCornerLWall, SideLeftWall, TopCornerRWall, SideRightWall, OakDoor, BottomCornerLWall, BottomCornerRWall, SideFloorLCorner, SideFloorRCorner, SideFloorLeft, SideFloorRight, FloorBottom, TopFloor, SideFloorLCornerBottom, SideFloorRCornerBottom;
    private BufferedImage DoorPortal, LBotWall, LSideWall, LTopWall, LTIle, SwordinStone, DeadSkeleton, RedFlag, WallChain, KeyHole, HangingShelf, FullBox, WreckBox, BloodyFloor, RockWindow, ReverseLGrass, ReverseTopLGrass, CenterRedFloor, Chest, BloodyFloorCSide, FlowerBedRed;
    private int[][] TileMap;
    private BufferedImage WreckWall, ChainSkel, Fence, SmallLimeTree, SmallGreenTree, Mred, Mblue, WoodenBoxes, SmallRocks, Grass, FloorWood, WoodWallBottom, WoodWallTop, WoodWallLSide, Plant, WoodWallCornerBottom, WoodWallCornerTop, Hole, BigTree, BigShade, Mud, SideMud, SideTopMud, SideBotMud, TopBotMud, ConeStone, GreenDarkGreen, Bush, FlowerBedWhite;
    private BufferedImage LastPortal, TechShelf, DataGraph, TechTable, TechPole, GlassBeaker, GlassCup, GlassPotion, SmallGlassBottle, DataGraph2, DoorPortalDown, BlueHole, CreatureChamber, VerticalTable, SecurityPad;
    private BufferedImage SirRic, Bed, Bed2, Bones, PrisonTable, TrapDoor, TreeStump;
    BufferedImage SirRicLayout, Explore;
    private BufferedImage Chicken,Soil;
    private int TileScale = 64;
    int FrameNum = 0;
    Font PixelFont;
    int CurrentImage;
    ArrayList<Rectangle> ItemHitBox = new ArrayList<>();

    InputHandler inputHandler;

    Stage(InputHandler inputHandler) {
        this.inputHandler = inputHandler;
        SetValues();
    }

    public void draw(Graphics2D g) {
        UpdateTiles();
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 15; j++) {
                switch (TileMap[i][j]) {
                    //STAGE1
                    case 1 -> g.drawImage(Floor, j * TileScale, i * TileScale, 64, 64, null);
                    case 2 -> g.drawImage(Wall, j * TileScale, i * TileScale, 64 * 2, 64 * 2, null);
                    case 3 -> g.drawImage(BlackTiles, j * TileScale, i * TileScale, 64, 64, null);
                    case 4 -> g.drawImage(Pillar, j * TileScale, i * TileScale, 64, 128 + 42, null);
                    case 5 -> g.drawImage(TopFloor, j * TileScale, i * TileScale, 64, 64, null);
                    case 6 -> g.drawImage(Drainage, j * TileScale, i * TileScale, 64, 64, null);
                    case 7 -> g.drawImage(TopCornerLWall, j * TileScale - 1, i * TileScale, 76, 128, null);
                    case 8 -> g.drawImage(SideLeftWall, j * TileScale, i * TileScale, 64, 68, null);
                    case 9 -> g.drawImage(TopCornerRWall, j * TileScale - 15, i * TileScale, 190, 128, null);
                    case 10 -> g.drawImage(SideRightWall, j * TileScale, i * TileScale, 64, 64, null);
                    case 11 -> g.drawImage(OakDoor, j * TileScale, i * TileScale, 64, 128, null);
                    case 12 -> g.drawImage(BottomCornerLWall, j * TileScale, i * TileScale - 8, 71, 72, null);
                    case 13 -> g.drawImage(BottomWall, j * TileScale, i * TileScale, 64, 64, null);
                    case 14 -> g.drawImage(BottomCornerRWall, j * TileScale - 7, i * TileScale - 8, 71, 72, null);
                    case 15 -> g.drawImage(SideFloorLCorner, j * TileScale, i * TileScale, 64, 64, null);
                    case 16 -> g.drawImage(SideFloorRCorner, j * TileScale, i * TileScale, 64, 64, null);
                    case 17 -> g.drawImage(SideFloorLeft, j * TileScale, i * TileScale, 64, 64, null);
                    case 18 -> g.drawImage(SideFloorRight, j * TileScale, i * TileScale, 64, 64, null);
                    case 19 -> g.drawImage(FloorBottom, j * TileScale, i * TileScale, 64, 64, null);
                    case 20 -> g.drawImage(SideFloorLCornerBottom, j * TileScale, i * TileScale, 64, 64, null);
                    case 21 -> g.drawImage(SideFloorRCornerBottom, j * TileScale, i * TileScale, 64, 64, null);
                    case 46 -> g.drawImage(TrapDoor, j * TileScale, i * TileScale, 64, 64, null);

                    //STAGE2

                    case 22 -> g.drawImage(WoodWallTop, j * TileScale, i * TileScale, 64 * 2, 140, null);
                    case 23 -> g.drawImage(FloorWood, j * TileScale, i * TileScale + 10, 64, 74, null);
                    case 24 -> g.drawImage(WoodWallLSide, j * TileScale, i * TileScale, 64, 64, null);
                    case 25 -> g.drawImage(WoodWallBottom, j * TileScale, i * TileScale - 6, 64, 70, null);
                    case 26 -> g.drawImage(WoodWallLSide, j * TileScale + 64, i * TileScale - 6, -64, 74, null);
                    case 27 -> g.drawImage(WoodWallCornerBottom, j * TileScale + 64, i * TileScale - 2, -64, 68, null);
                    case 28 -> g.drawImage(WoodWallCornerBottom, j * TileScale, i * TileScale - 8, 64, 74, null);
                    case 29 -> g.drawImage(WoodWallCornerTop, j * TileScale, i * TileScale - 2, 64, 74, null);
                    case 30 -> g.drawImage(WoodWallCornerTop, j * TileScale + 64, i * TileScale - 2, -64, 74, null);
                    case 31 -> g.drawImage(Mud, j * TileScale, i * TileScale + 10, 64, 74, null);
                    case 32 -> g.drawImage(TopBotMud, j * TileScale, i * TileScale, 64, 74, null);
                    case 33 -> g.drawImage(TopBotMud, j * TileScale, i * TileScale + 74, 64, -74, null);
                    case 34 -> g.drawImage(SideTopMud, j * TileScale, i * TileScale + 10, 64, 74, null);
                    case 35 -> g.drawImage(SideTopMud, j * TileScale + 64, i * TileScale + 10, -64, 74, null);
                    case 36 -> g.drawImage(SideBotMud, j * TileScale, i * TileScale + 10, 64, 74, null);
                    case 37 -> g.drawImage(SideBotMud, j * TileScale + 64, i * TileScale + 10, -64, 74, null);
                    case 38 -> g.drawImage(SideMud, j * TileScale, i * TileScale + 10, 64, 74, null);
                    case 39 -> g.drawImage(SideMud, j * TileScale + 64, i * TileScale + 10, -64, 74, null);
                    case 40 -> g.drawImage(GreenDarkGreen, j * TileScale, i * TileScale, 64, 74, null);
                    //LOBBY
                    case 41 -> g.drawImage(LTIle, j * TileScale + 3, i * TileScale, 64, 64, null);
                    case 42 -> g.drawImage(LTopWall, j * TileScale, i * TileScale, 129, 64 * 2, null);
                    case 43 -> g.drawImage(LSideWall, j * TileScale, i * TileScale, 68, 64 * 2, null);
                    case 44 -> g.drawImage(LSideWall, j * TileScale + 80, i * TileScale, -84, 64 * 2, null);
                    case 45 -> g.drawImage(LBotWall, j * TileScale, i * TileScale, 64 * 2, 64, null);
                }
            }
        }
        if (StageLvl == 0) {
            g.drawImage(DoorPortal, 395, 28, 180, 100, null);
            g.drawImage(DoorPortalDown, 395, 560, 180, 100, null);
            g.drawImage(TechShelf, 790, 40, 100, 150, null);
            g.drawImage(DataGraph, 635, 30, 150, 150, null);
            g.drawImage(DataGraph2, 205, 30, 150, 150, null);
            g.drawImage(TechTable, 430, 290, 100, 100, null);
            g.drawImage(TechPole, 70, 370, 50, 200, null);
            g.drawImage(TechPole, 840, 370, 50, 200, null);
            g.drawImage(GlassBeaker, 450, 300, 30, 30, null);
            g.drawImage(GlassCup, 490, 300, 30, 30, null);
            g.drawImage(BlueHole, 77, 40, 40, 40, null);
            g.drawImage(BlueHole, 140, 40, 40, 40, null);
            g.drawImage(CreatureChamber, 67, 50, 60, 130, null);
            g.drawImage(CreatureChamber, 130, 50, 60, 130, null);
            g.drawImage(VerticalTable, 65, 250, 60, 120, null);
            g.drawImage(VerticalTable, 835, 250, 60, 120, null);
            g.drawImage(SecurityPad, 590, 73, 20, 30, null);
            g.drawImage(Explore, 420, 1, 44 * 3, 13 * 3, null);

            g.setFont(PixelFont);
            g.setFont(g.getFont().deriveFont(15.0f));
            g.setColor(Color.RED);
            g.drawString("Sir Ric", 355,280);

            g.drawImage(SirRic, 350, 280, 96, 96, null);


        } else if (StageLvl == 1) {
            g.drawImage(WreckWall,670,0,64,128, null);
            g.drawImage(Bed, 70, 93, 84, 128, null);
            g.drawImage(Bed, 805, 93, 84, 128, null);
            g.drawImage(Bed2, 70, 448, 84, 128, null);
            g.drawImage(Bed2, 805, 448, 84, 128, null);
            g.drawImage(ChainSkel, 225, 43, 64, 78, null);
            g.drawImage(Bones, 815, 465, 64, 64, null);
            g.drawImage(PrisonTable, 550, 79, 84, 74, null);


        } else if (StageLvl == 2) {
            g.drawImage(BloodyFloor, 200, 193, 56, 123, null);
            g.drawImage(Pillar, 323, 0, 64, 170, null);
            g.drawImage(Pillar, 583, 0, 64, 170, null);
            g.drawImage(RedFlag, 331, 20, 60, 150, null);
            g.drawImage(RedFlag, 591, 20, 60, 150, null);
            g.drawImage(WallChain, 410, 50, 18, 60, null);
            g.drawImage(WallChain, 545, 50, 18, 60, null);
            g.drawImage(KeyHole, 520, 75, 10, 20, null);
            g.drawImage(HangingShelf, 218, 60, 76, 40, null);
            g.drawImage(RockWindow, 120, 42, 38, 60, null);
            g.drawImage(RockWindow, 795, 42, 38, 60, null);
            g.drawImage(FullBox, 74, 90, 50, 70, null);
            g.drawImage(WreckBox, 74, 160, 50, 70, null);
            g.drawImage(ReverseLGrass, 715, 451, 180, 125, null);
            g.drawImage(ReverseTopLGrass, 795, 351, 100, 100, null);
            g.drawImage(CenterRedFloor, 380, 250, 216, 202, null);
            g.drawImage(Chest, 665, 98, 90, 60, null);
            g.drawImage(BloodyFloorCSide, 772, 129, 123, 128, null);
            g.drawImage(DeadSkeleton, 772, 129, 84, 74, null);

        } else if (StageLvl == 3) {
            g.drawImage(Hole, 435, 10, 84, 150, null);
            g.drawImage(SmallLimeTree, -30, 1, 100, 64 * 3, null);
            g.drawImage(SmallGreenTree, -50, 80, 100, 64 * 3, null);
            g.drawImage(SmallLimeTree, -30, 160, 100, 64 * 3, null);
            g.drawImage(SmallGreenTree, -50, 240, 100, 64 * 3, null);
            g.drawImage(SmallLimeTree, -30, 320, 100, 64 * 3, null);
            g.drawImage(SmallGreenTree, -50, 400, 100, 64 * 3, null);
            g.drawImage(SmallLimeTree, 890, 1, 100, 64 * 3, null);
            g.drawImage(SmallGreenTree, 910, 80, 100, 64 * 3, null);
            g.drawImage(SmallLimeTree, 890, 160, 100, 64 * 3, null);
            g.drawImage(SmallGreenTree, 910, 240, 100, 64 * 3, null);
            g.drawImage(SmallLimeTree, 890, 320, 100, 64 * 3, null);
            g.drawImage(SmallGreenTree, 910, 400, 100, 64 * 3, null);
            g.drawImage(SmallLimeTree, 890, 480, 100, 64 * 3, null);
            g.drawImage(SmallLimeTree, -30, 480, 100, 64 * 3, null);
            g.drawImage(Fence, 65, 590, 128, 64, null);
            g.drawImage(Fence, 185 + 125, 590, -128, 64, null);
            g.drawImage(Fence, 295, 590, 128, 64, null);
            g.drawImage(Fence, 543, 590, 128, 64, null);
            g.drawImage(Fence, 665 + 120, 590, -128, 64, null);
            g.drawImage(Fence, 770, 590, 128, 64, null);
            g.drawImage(BigShade, 62, 130, 150, 30, null);
            g.drawImage(BigTree, 62, 0, 150, 160, null);
            g.drawImage(BigShade, 748, 130, 150, 30, null);
            g.drawImage(BigTree, 748, 0, 150, 160, null);
            g.drawImage(BigShade, 262, 130, 150, 30, null);
            g.drawImage(BigTree, 262, 0, 150, 160, null);
            g.drawImage(BigShade, 548, 130, 150, 30, null);
            g.drawImage(BigTree, 548, 0, 150, 160, null);
          //  g.drawImage(TreeStump, 570, 200, 88, 44, null);
            g.drawImage(FlowerBedWhite, 550, 380, 60, 50, null);
            g.drawImage(FlowerBedWhite, 338, 480, 60, 50, null);
            g.drawImage(FlowerBedWhite, 785, 510, 60, 50, null);
            g.drawImage(FlowerBedRed, 340, 290, -60, 50, null);
            g.drawImage(FlowerBedRed, 580, 260, 60, 50, null);
            g.drawImage(ConeStone, 785,505 , 30, 20, null);
            g.drawImage(ConeStone, 800,505, 30, 20, null);
            g.drawImage(ConeStone, 800,480 , 20, 40, null);
            g.drawImage(SmallRocks, 850, 200, 30, 25, null);
            g.drawImage(Chicken,300,555,-19*2,21*2,null);
            g.drawImage(Chicken,200,580,19,21,null);
            g.drawImage(Chicken,325,580,-19,21,null);
            g.drawImage(WreckBox, 780, 160, 45, 45, null);
            g.drawImage(Plant, 120, 510, 40, 35, null);
            g.drawImage(Plant, 838, 480, 40, 35, null);
            g.drawImage(Plant, 800, 150, -40, 35, null);
            g.drawImage(Grass, 400, 280, 200, 150, null);
            g.drawImage(FullBox, 480, 280, 35, 45, null);
            g.drawImage(FullBox, 480, 300, 35, 45, null);
            g.drawImage(FullBox, 480, 320, 35, 45, null);
            g.drawImage(FullBox, 480, 340, 35, 45, null);
            g.drawImage(FullBox, 445, 280, 35, 45, null);
            g.drawImage(FullBox, 445, 300, 35, 45, null);
            g.drawImage(FullBox, 445, 320, 35, 45, null);
            g.drawImage(FullBox, 445, 340, 35, 45, null);
            g.drawImage(FullBox, 451, 290, 60, 45, null);
            g.drawImage(FullBox, 451, 310, 60, 45, null);
            g.drawImage(Chicken,450,265,19*3,21*3,null);
            g.drawImage(Soil,180,210,28*3,27*2,null);
            g.drawImage(Soil,210,200,28*3,27*2,null);
            g.drawImage(Soil,190,180,28*3,27*2,null);

        } else if (StageLvl == 4) {
            g.drawImage(BigShade, 62, 130, 150, 30, null);
            g.drawImage(BigShade, 748, 130, 150, 30, null);
            g.drawImage(BigTree, 62, 0, 150, 160, null);
            g.drawImage(ConeStone, 708, 78, 60, 80, null);
            g.drawImage(BigTree, 748, 0, 150, 160, null);
            g.drawImage(Bush, 308, 100, 60, 60, null);
            g.drawImage(Bush, 588, 100, 60, 60, null);
            g.drawImage(Plant, 838, 480, 60, 50, null);
            g.drawImage(Plant, 208, 280, 60, 50, null);
            g.drawImage(FlowerBedWhite, 338, 480, 60, 50, null);
            g.drawImage(FlowerBedWhite, 138, 180, 60, 50, null);
            g.drawImage(FlowerBedWhite, 808, 280, 60, 50, null);
            g.drawImage(Grass, 400, 280, 200, 150, null);
            g.drawImage(FlowerBedRed, 538, 290, -120, 100, null);
            g.drawImage(SmallRocks, 468, 320, 60, 50, null);
            g.drawImage(SmallRocks, 168, 470, -100, 100, null);
            g.drawImage(SwordinStone, 460, 270, 60, 100, null);
            g.drawImage(WoodenBoxes, 200, 98, 80, 64, null);
            g.drawImage(Mred, 530, 348, 20, 20, null);
            g.drawImage(Mblue, 800, 498, 20, 20, null);
            g.drawImage(Mblue, 420, 298, 20, 20, null);
            g.drawImage(Mred, 100, 438, 20, 20, null);
            g.drawImage(SmallLimeTree, -30, 1, 100, 64 * 3, null);
            g.drawImage(SmallGreenTree, -50, 80, 100, 64 * 3, null);
            g.drawImage(SmallLimeTree, -30, 160, 100, 64 * 3, null);
            g.drawImage(SmallGreenTree, -50, 240, 100, 64 * 3, null);
            g.drawImage(SmallLimeTree, -30, 320, 100, 64 * 3, null);
            g.drawImage(SmallGreenTree, -50, 400, 100, 64 * 3, null);
            g.drawImage(SmallLimeTree, 890, 1, 100, 64 * 3, null);
            g.drawImage(SmallGreenTree, 910, 80, 100, 64 * 3, null);
            g.drawImage(SmallLimeTree, 890, 160, 100, 64 * 3, null);
            g.drawImage(SmallGreenTree, 910, 240, 100, 64 * 3, null);
            g.drawImage(SmallLimeTree, 890, 320, 100, 64 * 3, null);
            g.drawImage(SmallGreenTree, 910, 400, 100, 64 * 3, null);
            g.drawImage(SmallLimeTree, 890, 480, 100, 64 * 3, null);
            g.drawImage(SmallLimeTree, -30, 480, 100, 64 * 3, null);
            g.drawImage(SmallGreenTree, 30, 580, 100, 64 * 3, null);
            g.drawImage(SmallLimeTree, 160, 580, 100, 64 * 3, null);
            g.drawImage(SmallGreenTree, 280, 580, 100, 64 * 3, null);
            g.drawImage(SmallLimeTree, 430, 580, 100, 64 * 3, null);
            g.drawImage(SmallGreenTree, 580, 580, 100, 64 * 3, null);
            g.drawImage(SmallLimeTree, 710, 580, 100, 64 * 3, null);
            g.drawImage(SmallGreenTree, 830, 580, 100, 64 * 3, null);
            g.drawImage(LastPortal, 415, 22, 160, 148, null);

        }
    }

    public void HitBoxInit() {
        for (int i = 0; i < 1; i++) {
            ItemHitBox.add(new Rectangle(0, 0, 0, 0));
        }
    }


    public void Animation() {
        FrameNum++;
        if (FrameNum >= 10) {
            FrameNum = 0;
            CurrentImage++;
            if (CurrentImage > 4) {
                CurrentImage = 1;
            }
        }
        switch (CurrentImage) {
            case 1 -> SirRic = SirRicLayout.getSubimage(0, 0, 32, 32);
            case 2 -> SirRic = SirRicLayout.getSubimage(32, 0, 32, 32);
            case 3 -> SirRic = SirRicLayout.getSubimage(64, 0, 32, 32);
            case 4 -> SirRic = SirRicLayout.getSubimage(96, 0, 32, 32);
        }
    }

    public void update() {
        Animation();
        UpdateTiles();
    }

    public void SetHitBox(){
            // Stage 0
            ItemHitBox.add(new Rectangle(350, 280, 96, 96)); //Sir Ric Hitbox
            ItemHitBox.add(new Rectangle(790, 40, 100, 150));
            ItemHitBox.add(new Rectangle(70, 370, 50, 200));
            ItemHitBox.add(new Rectangle(840, 370, 50, 200));
            ItemHitBox.add(new Rectangle(430, 290, 100, 100));
            ItemHitBox.add(new Rectangle(67, 50, 60, 130));
            ItemHitBox.add(new Rectangle(130, 50, 60, 130));
            ItemHitBox.add(new Rectangle(65, 250, 60, 120));
            ItemHitBox.add(new Rectangle(835, 250, 60, 120));
            ItemHitBox.add(new Rectangle(635, 30, 150, 150));
            ItemHitBox.add(new Rectangle(205, 30, 150, 150));
            // stage 1
            ItemHitBox.add(new Rectangle(70, 93, 84, 128));
            ItemHitBox.add(new Rectangle(805, 93, 84, 128));
            ItemHitBox.add(new Rectangle(70, 448, 84, 128));
            ItemHitBox.add(new Rectangle(805, 448, 84, 128));
            ItemHitBox.add(new Rectangle(550, 79, 86, 78));
            // stage 3
            ItemHitBox.add(new Rectangle(480, 280, 200, 200));
    }

    public void SetValues() {
        HitBoxInit();
        BufferedImage S1environment, Lenvironment, LenvironmentWall, environment, environment2, Portal;
        BufferedImage furnitures, furnituresWoodTrees, furnituresWoodProps, furnituresLobby, chicken, soil;
        try {
            S1environment = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("Pixel Crawler/Environment/Dungeon Prison/Assets/dungeonex.png")));
            Lenvironment = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("Pixel Crawler/Environment/Lobby/tilesFloor32.png")));
            LenvironmentWall = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("Pixel Crawler/Environment/Lobby/tilesWalls.png")));
            environment = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("Pixel Crawler/Environment/Dungeon Prison/Assets/Tiles.png")));
            environment2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("Pixel Crawler/Environment/Green Woods/Assets/Tiles.png")));
            furnitures = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("Pixel Crawler/Environment/Dungeon Prison/Assets/Props.png")));
            furnituresWoodTrees = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("Pixel Crawler/Environment/Green Woods/Assets/Trees.png")));
            furnituresWoodProps = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("Pixel Crawler/Environment/Green Woods/Assets/Props.png")));
            furnituresLobby = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("Pixel Crawler/Environment/Lobby/tilesStuff.png")));
            SirRicLayout = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("Pixel Crawler/Heroes/SirRic/SirRic.png")));
            Portal = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("Pixel Crawler/Environment/Green Woods/Assets/Ancient_Portal.png")));
            chicken = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("Pixel Crawler/Environment/Green Woods/Assets/ChickenIdle-Sheet.png")));
            soil = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("Pixel Crawler/Environment/Green Woods/Assets/Tilled Dirt.png")));
            Explore = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("UI/itemset0/GUI_Option.png")));

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

        SirRic = SirRicLayout.getSubimage(0, 0, 32, 32);

        //LOBBY
        LTIle = Lenvironment.getSubimage(256, 508, 96, 66);
        LTopWall = LenvironmentWall.getSubimage(38, 0, 84, 96);
        LSideWall = LenvironmentWall.getSubimage(0, 87, 32, 73);
        LBotWall = LenvironmentWall.getSubimage(38, 0, 84, 30);
        DoorPortal = furnituresLobby.getSubimage(313, 323, 172, 88);
        DoorPortalDown = furnituresLobby.getSubimage(25, 323, 172, 88);
        TechShelf = furnituresLobby.getSubimage(1057, 287, 61, 98);
        DataGraph = furnituresLobby.getSubimage(1057, 385, 93, 63);
        DataGraph2 = furnituresLobby.getSubimage(833, 353, 93, 63);
        TechTable = furnituresLobby.getSubimage(863, 293, 97, 59);
        VerticalTable = furnituresLobby.getSubimage(85, 31, 43, 97);
        TechPole = furnituresLobby.getSubimage(837, 261, 21, 91);
        GlassBeaker = furnituresLobby.getSubimage(973, 71, 17, 17);
        GlassCup = furnituresLobby.getSubimage(995, 71, 11, 17);
        BlueHole = furnituresLobby.getSubimage(1003, 195, 19, 25);
        CreatureChamber = furnituresLobby.getSubimage(985, 223, 45, 113);
        SecurityPad = furnituresLobby.getSubimage(1033, 195, 19, 25);
        Explore = Explore.getSubimage(137, 23, 44, 13);

        //STAGE
        BlackTiles = environment.getSubimage(13, 55, 5, 5);
        Floor = environment.getSubimage(79, 16, 17, 17);
        CenterRedFloor = environment.getSubimage(225, 214, 52, 52);
        Drainage = environment.getSubimage(112, 162, 32, 31);
        TopFloor = environment.getSubimage(71, 0, 17, 17);
        Wall = environment.getSubimage(0, 0, 49, 48);
        OakDoor = environment.getSubimage(32, 112, 32, 48);
        TopCornerLWall = environment.getSubimage(198, 112, 20, 48);
        TopCornerRWall = environment.getSubimage(198, 164, 50, 48);
        SideLeftWall = environment.getSubimage(241, 114, 17, 17);
        SideRightWall = environment.getSubimage(300, 113, 17, 17);
        BottomCornerLWall = environment.getSubimage(241, 131, 19, 19);
        BottomCornerRWall = environment.getSubimage(298, 131, 19, 19);
        BottomWall = environment.getSubimage(260, 133, 17, 17);
        SideFloorLCorner = environment.getSubimage(64, 0, 16, 17);
        SideFloorRCorner = environment.getSubimage(95, 0, 18, 17);
        SideFloorLeft = environment.getSubimage(63, 16, 17, 17);
        SideFloorRight = environment.getSubimage(95, 24, 18, 17);
        SideFloorLCornerBottom = environment.getSubimage(63, 32, 17, 17);
        SideFloorRCornerBottom = environment.getSubimage(95, 32, 18, 17);
        FloorBottom = environment.getSubimage(79, 32, 17, 17);
        Pillar = environment.getSubimage(48, 0, 16, 64);
        BloodyFloorCSide = environment.getSubimage(115, 0, 33, 33);
        Bed = S1environment.getSubimage(6, 168, 51, 87);
        Bed2 = S1environment.getSubimage(70, 179, 52, 76);
        TrapDoor = environment.getSubimage(112, 127, 32, 33);

        //PROPSPRISON
        RedFlag = furnitures.getSubimage(2, 187, 15, 40);
        WallChain = furnitures.getSubimage(101, 37, 8, 40);
        KeyHole = furnitures.getSubimage(85, 186, 6, 10);
        HangingShelf = furnitures.getSubimage(114, 34, 43, 20);
        FullBox = furnitures.getSubimage(0, 9, 15, 22);
        WreckBox = furnitures.getSubimage(0, 66, 15, 22);
        BloodyFloor = environment.getSubimage(128, 192, 15, 31);
        RockWindow = furnitures.getSubimage(145, 1, 13, 19);
        ReverseLGrass = environment.getSubimage(176, 84, 47, 27);
        ReverseTopLGrass = environment.getSubimage(162, 1, 47, 27);
        Chest = furnitures.getSubimage(97, 204, 29, 19);
        DeadSkeleton = environment.getSubimage(223, 156, 40, 23);
        ChainSkel = S1environment.getSubimage(0, 260, 32, 57);
        WreckWall = environment.getSubimage(160,112,32,48);
        Bones = S1environment.getSubimage(64, 288, 32, 32);
        PrisonTable = furnitures.getSubimage(128, 161, 48, 29);

        //STAGE2
        WoodWallTop = environment2.getSubimage(73, 281, 48, 48);
        WoodWallBottom = environment2.getSubimage(81, 202, 37, 37);
        FloorWood = environment2.getSubimage(25, 25, 47, 47);
        WoodWallLSide = environment2.getSubimage(114, 236, 36, 35);
        WoodWallCornerBottom = environment2.getSubimage(143, 170, 45, 42);
        WoodWallCornerTop = environment2.getSubimage(195, 170, 45, 42);
        Hole = environment2.getSubimage(0, 128, 31, 57);
        BigTree = furnituresWoodTrees.getSubimage(128, 128, 105, 126);
        BigShade = furnituresWoodTrees.getSubimage(48, 257, 80, 31);
        Mud = environment2.getSubimage(255, 45, 5, 5);
        SideMud = environment2.getSubimage(217, 41, 33, 30);
        TopBotMud = environment2.getSubimage(254, 2, 31, 55);
        SideTopMud = environment2.getSubimage(217, 12, 35, 65);
        SideBotMud = environment2.getSubimage(217, 69, 39, 35);
        GreenDarkGreen = environment2.getSubimage(160, 0, 48, 48);
        Grass = environment2.getSubimage(98, 2, 60, 61);

        //PROPSForestWOOD
        ConeStone = furnituresWoodProps.getSubimage(82, 34, 28, 44);
        Bush = furnituresWoodProps.getSubimage(2, 100, 43, 44);
        Plant = furnituresWoodProps.getSubimage(0, 67, 31, 23);
        FlowerBedWhite = furnituresWoodProps.getSubimage(148, 2, 41, 41);
        FlowerBedRed = furnituresWoodProps.getSubimage(148, 51, 41, 41);
        SmallRocks = furnituresWoodProps.getSubimage(49, 17, 31, 30);
        SwordinStone = furnituresWoodProps.getSubimage(55, 84, 53, 100);
        WoodenBoxes = furnituresWoodProps.getSubimage(112, 9, 32, 23);
        Mred = furnituresWoodProps.getSubimage(20, 17, 10, 14);
        Mblue = furnituresWoodProps.getSubimage(35, 17, 10, 14);
        SmallLimeTree = furnituresWoodTrees.getSubimage(0, 1, 48, 96);
        SmallGreenTree = furnituresWoodTrees.getSubimage(0, 129, 48, 96);
        LastPortal = Portal.getSubimage(0, 0, 378, 384);
        Fence = furnituresWoodProps.getSubimage(0, 197, 240, 64);
        TreeStump = furnituresWoodProps.getSubimage(204, 0, 182, 83);
        Chicken = chicken.getSubimage(0,0,19 ,21);
        Soil = soil.getSubimage(26, 58,  28, 27);


        //DoorLock
        KeyHole = furnitures.getSubimage(85, 186, 6, 10);

    }

    public void UpdateTiles(){
        if (StageLvl == 0) {
            TileMap = new int[][]{
                    {43, 42, 0, 42, 0, 42, 0, 43, 42, 0, 42, 0, 42, 0, 44},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {43, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 44},
                    {43, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 44},
                    {43, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 44},
                    {43, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 44},
                    {43, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 44},
                    {43, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 44},
                    {43, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 44},
                    {0, 45, 0, 45, 0, 45, 0, 43, 45, 0, 45, 0, 45, 0, 0}
            };
        } else if (StageLvl == 1) {
            TileMap = new int[][]{
                    {7, 2, 0, 2, 0, 2, 0, 11, 2, 0, 2, 0, 2, 0, 9},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {8, 15, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 16, 10},
                    {8, 17, 1, 6, 6, 1, 1, 1, 1, 1, 6, 6, 1, 18, 10},
                    {8, 17, 1, 6, 6, 1, 6, 6, 6, 1, 6, 6, 1, 18, 10},
                    {8, 17, 1, 1, 1, 1, 6, 46, 6, 1, 1, 1, 1, 18, 10},
                    {8, 17, 1, 6, 6, 1, 6, 6, 6, 1, 6, 6, 1, 18, 10},
                    {8, 17, 1, 6, 6, 1, 1, 1, 1, 1, 6, 6, 1, 18, 10},
                    {8, 20, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 21, 10},
                    {12, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 14}
            };
        } else if (StageLvl == 2) {
            TileMap = new int[][]{
                    {7, 2, 0, 2, 0, 2, 0, 11, 2, 0, 2, 0, 2, 0, 9},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {8, 15, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 16, 10},
                    {8, 17, 1, 1, 6, 6, 1, 1, 1, 6, 6, 1, 1, 18, 10},
                    {8, 17, 1, 1, 6, 1, 3, 0, 0, 1, 6, 1, 1, 18, 10},
                    {8, 17, 1, 1, 6, 1, 0, 0, 0, 1, 6, 1, 1, 18, 10},
                    {8, 17, 1, 1, 6, 1, 0, 0, 0, 1, 6, 1, 1, 18, 10},
                    {8, 17, 1, 1, 6, 6, 1, 1, 1, 6, 6, 1, 1, 18, 10},
                    {8, 20, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 21, 10},
                    {12, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 14}
            };
        } else if (StageLvl == 3) {
            TileMap = new int[][]{
                    {22, 0, 22, 0, 22, 0, 22, 0, 22, 0, 22, 0, 22, 0, 22},
                    {0, 0, 0, 0, 0, 0, 0, 32, 0, 0, 0, 0, 0, 0, 0},
                    {23, 23, 23, 23, 23, 23, 38, 31, 39, 23, 23, 23, 23, 23, 23},
                    {23, 23, 23, 23, 23, 23, 38, 31, 39, 23, 23, 23, 23, 23, 23},
                    {23, 23, 23, 23, 23, 23, 38, 31, 39, 23, 23, 23, 23, 23, 23},
                    {23, 23, 23, 23, 23, 23, 38, 31, 39, 23, 23, 23, 23, 23, 23},
                    {23, 23, 23, 23, 23, 23, 38, 31, 39, 23, 23, 23, 23, 23, 23},
                    {23, 23, 23, 23, 23, 23, 38, 31, 39, 23, 23, 23, 23, 23, 23},
                    {23, 23, 23, 23, 23, 23, 38, 31, 39, 23, 23, 23, 23, 23, 23},
                    {23, 23, 23, 23, 23, 23, 38, 31, 39, 23, 23, 23, 23, 23, 23},
            };
        } else if (StageLvl == 4) {
            TileMap = new int[][]{
                    {29, 22, 0, 22, 0, 22, 0, 22, 0, 22, 0, 22, 0, 22, 30},
                    {24, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 26},
                    {24, 23, 23, 23, 23, 40, 23, 23, 23, 40, 23, 23, 23, 23, 26},
                    {24, 23, 34, 32, 32, 32, 32, 32, 32, 32, 32, 32, 35, 23, 26},
                    {24, 23, 38, 31, 31, 31, 31, 31, 31, 31, 31, 31, 39, 23, 26},
                    {24, 23, 38, 31, 31, 31, 31, 31, 31, 31, 31, 31, 39, 23, 26},
                    {24, 23, 38, 31, 31, 31, 31, 31, 31, 31, 31, 31, 39, 23, 26},
                    {24, 23, 36, 33, 33, 33, 33, 33, 33, 33, 33, 33, 37, 23, 26},
                    {24, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 26},
                    {27, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 28},
            };

        }
    }

    public void setStage(int stage){
        this.StageLvl = stage;
    }
    public void addStage(){
        this.StageLvl++;
        if(StageLvl > 4){
            StageLvl = 4;
        }
    }
}
