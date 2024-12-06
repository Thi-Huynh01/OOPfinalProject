import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class AttributePanel extends JPanel {

    ArrayList<String> attributes;

    public AttributePanel(ArrayList<String> attributes) {

        setPreferredSize(new Dimension(670, 50));
        setBackground(Color.GRAY);

        int i = 0;
        for (String c : attributes) {
            JLabel label = new JLabel(attributes.get(i));
            if (attributes.get(i).contains("(X)"))
                label.setForeground(Color.RED);
            else
                label.setForeground(Color.green);

            label.setFont(label.getFont().deriveFont(Font.BOLD));
            add(label);
            i++;
        }

        //add(movieName);
        //add(director);

    }
}
