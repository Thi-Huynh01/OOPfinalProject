// Thi Huynh
// Ethan Pack
// Brock Morgan

import javax.swing.*;
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
                "Jaws",
                "Titanic",
                "Transformers"
        };

        // Generate random int to pass in random movie title
        Random rand = new Random();
        int randomInt = rand.nextInt(Movies.length);
        MovieCategory movie = new MovieCategory(Movies[randomInt]);

        // This is where the whole game takes place
        GameGUI gui = new GameGUI(movie, Movies);

        // Create JFrame
        JFrame frame = new JFrame();
        frame.setTitle("Moviedle");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(gui);
        frame.pack();
        frame.setVisible(true);

    }
}
