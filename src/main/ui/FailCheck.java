package ui;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.io.File;

// design for pop-up window for a failed promotion/graduation check
public class FailCheck extends JOptionPane {
    private JDialog dialog;

    // EFFECTS: creates the pop-up window for a failed requirements check
    public FailCheck() {
        super("You don't meet all the requirements yet.");
        playSound("src/main/ui/crowd-groan.wav");
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
