package btd.utils;
import javax.sound.sampled.*;
import java.io.File;

public class Sound {
    private Clip music;

    public void playBackgroundSound(String filePath) {
        try {
            File musicPath = new File(filePath);
            if (musicPath.exists()) {
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
                music = AudioSystem.getClip();
                music.open(audioInput);
                
                music.loop(Clip.LOOP_CONTINUOUSLY);
                music.start();
            }
        } catch (Exception e) {
            System.out.println("Erreur de lecture audio : " + e.getMessage());
        }
    }

    public void stopSound() {
        if (music != null && music.isRunning()) {
            music.stop();
        }
}
}
