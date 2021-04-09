package ui;

import javax.swing.*;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.Clip;
import javax.sound.sampled.AudioSystem;
import java.io.File;

// design for pop-up window for a passed promotion/graduation check
public class PassCheck extends JOptionPane {
    private JDialog dialog;

    // EFFECTS: creates the pop-up window for a passing requirements check
    public PassCheck() {
        super("Congratulations, you meet the requirements!");
        playSound("src/main/ui/applause4.wav");
        dialog = this.createDialog("Checker");
        dialog.setVisible(true);
    }

    // EFFECTS: plays the specified sound file, throws Exception if the file can't be found.
    public void playSound(String soundName) {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(soundName).getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (Exception ex) {
            System.out.println("Error with playing sound.");
            ex.printStackTrace();
        }
    }
}
