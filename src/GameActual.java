import java.util.Scanner;
import java.sql.SQLException;

public class GameActual {
    Category category;
    String [] titles;

    public GameActual(Category category, String[] titles) {
        this.category = category;
        this.titles = titles;
    }
    public String attributes() {
        return this.category.toString();
    }

    public String title () throws SQLException {
        return this.category.getName();
    }

    public void game() throws SQLException {

        Scanner sc = new Scanner(System.in);
        System.out.println("Guess The Title ");
        String title = "";
        int lives = 5;

        while (lives >= 0) {
            title = sc.nextLine();
            String guess = queryGuess(title);
            System.out.println(guess);

            if (isCorrect(title)) {
                System.out.println("Correct!");
                break;

            } else {
                System.out.println("Incorrect!");
                lives--;
                System.out.println("Lives left: " + lives);
            }
        }

    }

    public boolean isCorrect(String guess) throws SQLException {
        return this.category.getName().toLowerCase().contains(guess);
    }

    public String queryGuess(String guess) throws SQLException {
        MovieCategory userGuess;

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
