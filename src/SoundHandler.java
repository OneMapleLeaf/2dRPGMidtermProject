import java.net.URL;

public class SoundHandler {
    Sound sound = new Sound();
    UI ui;
    TitleScreen titleScreen;
    int CurrentSoundNum;
    boolean playSound = true;
    URL url = getClass().getResource("Sound/Guardian of The Former Seas.wav");
    SoundHandler(UI ui, TitleScreen titleScreen){
        this.ui = ui;
        this.titleScreen = titleScreen;
    }
    public void update(){
        switch (titleScreen.GetVolumeSound()){
            case 0 -> CurrentSoundNum = -80;
            case 1 -> CurrentSoundNum = -70;
            case 2 -> CurrentSoundNum = -60;
            case 3 -> CurrentSoundNum = -50;
            case 4 -> CurrentSoundNum = -40;
            case 5 -> CurrentSoundNum = -30;
        }
        if (playSound){
            sound.SetFileUrl(url);
            sound.loop();
            playSound = false;
        }
        sound.VolumeDown(CurrentSoundNum);
    }
}
