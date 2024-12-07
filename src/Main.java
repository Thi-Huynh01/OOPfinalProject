import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.Random;

public class Main {
    public static void main(String[] args) throws SQLException {
        // For the time being, these are the movies in our database.

        String[] Movies = {"Goodfellas",
                "Inception",
                "Gladiator",
                "Avatar",
                "Titanic",
                "Superman",
                "Spiderman",
                "Deadpool",
                "Frozen",
                "Barbie"
        };

        Random rand = new Random();
        //String[] Games = {"Sekiro Shadows Die Twice", "ELDEN RING", "Halo 3"};

        int randomInt = rand.nextInt(Movies.length);

        //GamesCategory game = new GamesCategory(Games[randomInt]);
        MovieCategory movie = new MovieCategory(Movies[randomInt]);

        //GameActual gameActual = new GameActual(movie, Movies);
        GameGUI gui = new GameGUI(movie, Movies);

        JFrame frame = new JFrame();
        ImageBackgroundPanel background = new ImageBackgroundPanel();
        frame.setTitle("Moviedle");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.add(gui, BorderLayout.CENTER);
        frame.pack();
        frame.setVisible(true);

    }
}
