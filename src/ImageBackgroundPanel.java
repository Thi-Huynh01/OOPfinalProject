import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class ImageBackgroundPanel extends JPanel {

    private Image backgroundImage;

    public ImageBackgroundPanel() {
        try {
            backgroundImage = ImageIO.read(new File("src/images/eldenring.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);  // Ensure proper rendering of other components
        // Draw the background image
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }
}