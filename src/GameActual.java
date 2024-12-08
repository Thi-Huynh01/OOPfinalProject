import java.awt.*;
import java.util.Arrays;
import java.util.Scanner;
import java.sql.SQLException;

public class GameActual {
    MovieCategory movie; //This is the movie or game that is being guessed
    String[] titles; // This is the list of potential movies that could be in this list

    public GameActual(MovieCategory movie, String[] titles) {
        this.movie = movie;
        this.titles = titles;
    }

    // This is the terminal/console version of the game. This is essentially how the game will be modeled in the GUI
    public void game() throws SQLException {

        Scanner sc = new Scanner(System.in);
        System.out.println("Guess The Movie ");
        String userguess = "";
        int lives = 5;

        while (lives >= 0) {
            userguess = sc.nextLine();

            // input validation
            if (userguess.equals(" ")) {
                System.out.println("Answer can not be NULL");
            }

            else if (isCorrect(userguess)) {
                System.out.println("Correct!");
                break;

            } else {
                System.out.println("Incorrect!");
                lives--;

                System.out.println("Lives left: " + lives);
            }

        }
    }

    // Checks if the guess is equal to the actual movie
    public boolean isCorrect(String guess) throws SQLException {
        return this.movie.getName().toLowerCase().contains(guess);
    }

    // Function to return new MovieCategory query, so that the answer and the guess are on equal grounds
    public MovieCategory queryGuess(String guess) throws SQLException {

        // Iterate through the array of movie titles
        for (String title : this.titles) {

            // If the user-input movie title matches one of these movies, return new MovieCategory object
            if (title.toLowerCase().contains(guess.toLowerCase())) {
                return new MovieCategory(title);
            }
        }

        // If pattern can not be found, return invalid.
        return new MovieCategory("Invalid Movie Title");
    }

}
