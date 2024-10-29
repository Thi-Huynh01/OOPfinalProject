import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        String[] Movies = {"Spider-Man 2", "LOTR: The Fellowship of the Ring"};
        String[] Games = {"Sekiro: Shadows Die Twice", "ELDEN RING"};

        GamesCategory game = new GamesCategory("Sekiro: Shadows Die Twice");
        MovieCategory movie = new MovieCategory("Spider-Man 2");

        for (int i = 0; i < Movies.length; i++) {
            movie = new MovieCategory(Movies[i]);
            System.out.println(movie.toString());
        }

        System.out.println();

        for (int i = 0; i < Games.length; i++) {
            game = new GamesCategory(Games[i]);
            System.out.println(game.toString());

        }
    }
}
