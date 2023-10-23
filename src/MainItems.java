import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class MainItems { //collection of all the inserted items

    ArrayList<ItemDrops> itemDrops = new ArrayList<>();
    ArrayList<CoinDrop> coinDrops = new ArrayList<>();
    ArrayList<Double> coinAngle = new ArrayList<>();
    Tool tool;
    InputHandler inputHandler;
    Player player;
    Sound sound = new Sound();
    BufferedImage Items;
    BufferedImage Coins;
    float CoinY, CoinX;
    int SfxVol;
    UI ui;
    MainItems(Tool tool, InputHandler inputHandler, Player player, UI ui){
        this.ui = ui;
        this.tool = tool;
        this.inputHandler = inputHandler;
        this.player = player;
        SetValue();
    }

    public void draw(Graphics2D g){
        for (int i = 0; i < itemDrops.size(); i++) {
            itemDrops.get(i).draw(g);
        }
        for (int i = 0; i < coinDrops.size(); i++) {
            coinDrops.get(i).draw(g);
        }
    }

    public void update(){

        switch (ui.GetSfxVol()){
            case 0 -> SfxVol = -80;
            case 1 -> SfxVol = -70;
            case 2 -> SfxVol = -60;
            case 3 -> SfxVol = -50;
            case 4 -> SfxVol = -40;
            case 5 -> SfxVol = -30;
        }
        for (int i = 0; i < coinDrops.size(); i++) {
            coinAngle.set(i, Math.atan2(player.getY() + 32 - coinDrops.get(i).getY(), player.getX() + 32 - coinDrops.get(i).getX()));
            coinDrops.get(i).move(5, coinAngle.get(i));
            coinDrops.get(i).update();
        }
        //coin remover
        for (int i = 0; i < coinDrops.size(); i++) {
            if(player.GetHitBox().intersects(new Rectangle((int)coinDrops.get(i).getX(), (int)coinDrops.get(i).getY(), coinDrops.get(i).getRadiusWH(), coinDrops.get(i).getRadiusWH()))){
               sound.SetFile(2);
               sound.VolumeDown(SfxVol);
               sound.play();
                player.GainCoin(1);
                coinDrops.remove(i);
                i--;
            }
        }
        for (int i = 0; i < itemDrops.size(); i++) {
            if(new Rectangle((int) player.getX(), (int) player.getY(), 23 * 3, 23 * 3).intersects(itemDrops.get(i))){
                itemDrops.remove(i);
                i--;
            }
        }
    }

    public void AddCoin(int Amount, int x, int y){
        for (int i = 0; i < Amount; i++) {
            coinDrops.add(new CoinDrop(x, y,10, Items));
            coinAngle.add(Math.atan2(player.getY() - y, player.getX() - x));
        }
    }

    public void SetValue(){
        try {
            Items = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("Items/coins-chests-etc-2-0.png")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Coins = Items.getSubimage(177, 18, 16,16);
    }
}
