import java.awt.*;
import java.sql.SQLException;
import java.util.Random;

public class Main {
    public static void main(String[] args) throws SQLException {
        // For the time being, these are the movies in our database.

        String[] Movies = {"Spider man 2", "LOTR: The Fellowship of the Ring"};
        Random rand = new Random();
        String[] Games = {"Sekiro Shadows Die Twice", "ELDEN RING", "Halo 3"};

        int randomInt = rand.nextInt(Movies.length);

        // Don't worry about the games category for now. We can wait until we fully implemented Movies

        GamesCategory game = new GamesCategory(Games[randomInt]);
        MovieCategory movie = new MovieCategory(Movies[randomInt]);

        GameActual gameActual = new GameActual(movie, Movies);

        gameActual.game();

    }

    public Color attributeCorrect() {

        return Color.GREEN;
    }
}
