import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.Random;

public class Main {
    public static void main(String[] args) throws SQLException {
        // For the time being, these are the movies in our database.

        String[] Movies = {
                "Goodfellas",
                "Inception",
                "Gladiator",
                "Avatar",
                "Titanic",
                "Superman",
                "Spiderman",
                "Deadpool",
                "Frozen",
                "Barbie",
                "Interstellar",
                "Tenet",
                "Dune",
                "Joker",
                "Scarface",
                "Jaws"
        };

        Random rand = new Random();
        //String[] Games = {"Sekiro Shadows Die Twice", "ELDEN RING", "Halo 3"};

        int randomInt = rand.nextInt(Movies.length);

        MovieCategory movie = new MovieCategory("Deadpool");

        //GameActual gameActual = new GameActual(movie, Movies);
        GameGUI gui = new GameGUI(movie, Movies);

        JFrame frame = new JFrame();
        frame.setTitle("Moviedle");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.add(gui);
        frame.pack();
        frame.setVisible(true);

    }
}
