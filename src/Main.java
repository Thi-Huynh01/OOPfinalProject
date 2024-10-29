import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        // For the time being, these are the movies in our database.
        String[] Movies = {"Spider-Man 2", "LOTR: The Fellowship of the Ring"};

        // Don't worry about the games category for now. We can wait until we fully implemented Movies
        String[] Games = {"Sekiro: Shadows Die Twice", "ELDEN RING"};

        GamesCategory game;
        MovieCategory movie;

        for (int i = 0; i < Movies.length; i++) {
            movie = new MovieCategory(Movies[i]);
            System.out.println(movie);
        }

        System.out.println();

        for (int i = 0; i < Games.length; i++) {
            game = new GamesCategory(Games[i]);
            System.out.println(game);

        }
    }
}
