import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import java.net.URL;

public class Sound {
    Clip clip;
    URL[] soundURL = new URL[10];
    FloatControl fc;
    float CurrentVolume = 0;
    Sound(){
        soundURL[0] = getClass().getResource("Sound/shoot.wav");
        soundURL[1] = getClass().getResource("Sound/Guardian of The Former Seas.wav");
        soundURL[2] = getClass().getResource("Sound/collectcoin-6075.wav");
        soundURL[3] = getClass().getResource("Sound/fire-magic-6947.wav");
        soundURL[4] = getClass().getResource("Sound/fire-spell-100276.wav");
        soundURL[5] = getClass().getResource("Sound/kb_brownnoise_hit-1-38104.wav");
        soundURL[6] = getClass().getResource("Sound/punch-6-166699.wav");
        soundURL[7] = getClass().getResource("Sound/trailer-hit-145003.wav");
        soundURL[8] = getClass().getResource("Sound/WTF_MAN.wav");
        soundURL[9] = getClass().getResource("Sound/Aray.wav");
    }

    public void SetFile(int i){
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);
            fc = (FloatControl)clip.getControl(FloatControl.Type.MASTER_GAIN);
        } catch (Exception ignored){
        }
    }
    public void SetFileUrl(URL url){
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(url);
            clip = AudioSystem.getClip();
            clip.open(ais);
            fc = (FloatControl)clip.getControl(FloatControl.Type.MASTER_GAIN);
        } catch (Exception ignored){
        }
    }

    public void play(){
        clip.start();
    }
    public void loop(){
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
    public void stop(){
        clip.stop();
    }
    public void VolumeUp(float gain){
        CurrentVolume = gain;
        if (CurrentVolume > 6.0f){
            CurrentVolume = 6.0f;
        }
        fc.setValue(CurrentVolume);
    }
    public void VolumeDown(float lose){

        CurrentVolume = lose;
        if (CurrentVolume < -80.0f){
            CurrentVolume = -80.0f;
        }
        fc.setValue(CurrentVolume);
    }
}
