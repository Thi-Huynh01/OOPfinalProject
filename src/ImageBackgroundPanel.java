import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class ImageBackgroundPanel extends JPanel {

    private Image backgroundImage;

    public ImageBackgroundPanel() {
        try {
            //backgroundImage = ImageIO.read(new File("Users/Ghost/IdeaProjects/oopFinalProject/src/images/superman.jpeg"));
            backgroundImage = ImageIO.read(ImageBackgroundPanel.class.getResource("/images/Transformers.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);  // Ensure proper rendering of other components
        // Draw the background image
        g.drawImage(backgroundImage, 0, 0, 180, 240, this);
    }
}