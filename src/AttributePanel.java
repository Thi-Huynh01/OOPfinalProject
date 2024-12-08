import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

// Panel to display the attributes of the user-input guess
public class AttributePanel extends JPanel {

    public AttributePanel(ArrayList<String> attributes) {

        // Set size and color of the panel
        setPreferredSize(new Dimension(750, 50));
        setBackground(Color.BLACK);

        // Display the attributes of the movie in the panel
        int i = 0;
        for (String c : attributes) {

            // ALL user-input movie attributes is put into one ArrayList
            JLabel label = new JLabel(attributes.get(i));
            // If the guess attribute is not equal to the attribute of the answer...

            if (attributes.get(i).contains("(X)"))
                label.setForeground(Color.RED);  // color red to indicate that it is incorrect
            else
                label.setForeground(Color.GREEN); // color green to indicate that it is correct

            // Set font and add to panel
            label.setFont(label.getFont().deriveFont(Font.BOLD));
            add(label);
            i++;
        }

    }
}
