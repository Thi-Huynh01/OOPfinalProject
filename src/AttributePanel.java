import javax.swing.*;
import java.awt.*;

public class AttributePanel extends JPanel {

    public AttributePanel(MovieCategory userGuess, Color[] attributeColors) {

        setPreferredSize(new Dimension(670, 50));
        setBackground(Color.PINK);

        JLabel movieName = new JLabel(userGuess.mname);
        movieName.setFont(movieName.getFont().deriveFont(Font.BOLD));
        add(movieName);

    }
}
