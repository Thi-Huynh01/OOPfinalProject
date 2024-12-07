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

    public void game() throws SQLException {

        Scanner sc = new Scanner(System.in);
        System.out.println("Guess The Movie ");
        String userguess = "";
        int lives = 5;

        while (lives >= 0) {
            userguess = sc.nextLine();
            //String guess = queryGuess(userguess.toLowerCase());
            //System.out.println(guess);

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
        //System.out.println(Arrays.toString(compareAttributes()));

    }

    public boolean isCorrect(String guess) throws SQLException {
        return this.movie.getName().toLowerCase().contains(guess);
    }

    public MovieCategory queryGuess(String guess) throws SQLException {

        for (String title : this.titles) {
            if (title.toLowerCase().contains(guess.toLowerCase())) {
                return new MovieCategory(title);
                //return userGuess;
            }
        }

        return new MovieCategory("Invalid Movie Title");
    }

}