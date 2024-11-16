import java.awt.*;
import java.util.Arrays;
import java.util.Scanner;
import java.sql.SQLException;

public class GameActual {
    MovieCategory movie; //This is the movie or game that is being guessed
    MovieCategory userGuess;
    String[] titles; // This is the list of potential movies that could be in this list
    String[] answerAttributes;

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
            String guess = queryGuess(userguess.toLowerCase());
            System.out.println(guess);

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
            System.out.println(Arrays.toString(compareAttributes()));

        }

    }

    public Color[] compareAttributes() {

        String[] guessAttributes = {
                userGuess.lead_actor,
                userGuess.releaseYear,
                userGuess.director,
                userGuess.streaming_service,
                userGuess.rating,
                userGuess.prod_comp,
                userGuess.supp_actor
        };

        String[] answerAttributes = {
                movie.lead_actor,
                movie.releaseYear,
                movie.director,
                movie.streaming_service,
                movie.rating,
                movie.prod_comp,
                movie.supp_actor
        };
        Color[] attributes = new Color[guessAttributes.length];

        for (int i = 0; i < guessAttributes.length; i++) {
                if (guessAttributes[i].equals(answerAttributes[i])) {
                    attributes[i] = Color.green;
                }
                else{
                    attributes[i] = Color.red;
                }
        }
        System.out.println(Arrays.toString(attributes));
        return attributes;
    }

    public boolean isCorrect(String guess) throws SQLException {
        return this.movie.getName().toLowerCase().contains(guess);
    }

    public String queryGuess(String guess) throws SQLException {

        for (String title : this.titles) {
            if (title.toLowerCase().contains(guess.toLowerCase())) {
                System.out.println(title);
                userGuess = new MovieCategory(title);
                return userGuess.toString();
            }
        }

        return guess;
    }

}
