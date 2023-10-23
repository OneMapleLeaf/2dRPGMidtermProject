import javax.swing.*;
import java.awt.*;

public class Panel extends JPanel implements Runnable{
    final double fps = 60;
    Panel(){
        // 30 by 20 Map (32 by 32 pixel tile)
        this.setPreferredSize(new Dimension(960, 640));
        this.setFocusable(true);
        this.setDoubleBuffered(true);
        this.addMouseMotionListener(inputHandler);
        this.addMouseListener(inputHandler);
        this.addKeyListener(inputHandler);
    }
    Thread gameThread;
    int CurrentFps = 0;
    Tool Playertool = new Tool();
    Tool EnemyTool = new Tool();
    Tool ItemTool = new Tool();
    Tool UiTool = new Tool();
    Tool TitleTool = new Tool();
    InputHandler inputHandler = new InputHandler();
    Stage stage = new Stage(inputHandler);
    TitleScreen titleScreen = new TitleScreen(inputHandler, TitleTool);
    Player player = new Player(Playertool, inputHandler, stage);
    UI Ui = new UI(inputHandler, UiTool, titleScreen, player, stage);
    MainItems mainItems = new MainItems(ItemTool, inputHandler, player, Ui);
    MainEnemy mainEnemy = new MainEnemy(EnemyTool, inputHandler, player, mainItems);
    SoundHandler soundHandler = new SoundHandler(Ui, titleScreen);

    public void update(){
        soundHandler.update();
        if(titleScreen.getInTitleScreen()){
            titleScreen.update();
            return;
        }
        stage.update();
        mainEnemy.update();
        player.update();
        mainItems.update();
        Ui.update();
        CurrentFps++;
    }

    public void paint(Graphics gg) {
        super.paint(gg);
        Graphics2D g = (Graphics2D) gg;
        if(titleScreen.getInTitleScreen()) {
            titleScreen.draw(g);
            return;
        }
        stage.draw(g);
        player.draw(g);
        mainEnemy.draw(g);
        mainItems.draw(g);
        Ui.draw(g);
        g.dispose();
    }

    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        double drawInterval = 1000000000 /fps;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long MilliTimer = 0;

        while (gameThread != null){
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            MilliTimer += (currentTime - lastTime);
            lastTime = currentTime;

            if (delta >= 1){
                update();
                delta--;
            }
            repaint();
            if (MilliTimer >= 1000000){
                Playertool.setMilliSecCounter(1);
                UiTool.setMilliSecCounter(1);
                TitleTool.setMilliSecCounter(1);
                MilliTimer = 0;
            }

        }
    }
}
