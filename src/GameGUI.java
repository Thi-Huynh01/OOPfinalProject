import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.*;

public class GameGUI extends JPanel{
    public ArrayList<MovieCategory> movies;
    private final MovieCategory Movie;
    private final String[] titles;
    private final GameActual game;
    private JTextField textField;
    private final JLabel livesLabel;
    private final JTextField guessField;
    private final JButton guessButton;
    private final JLabel codeResponse;
    private final JLabel resultLabel;
    private int lives = 5;
    private MovieCategory guessResult;
    private final JPanel displayPanel;
    public ArrayList<String> attributes;
    public String[] answerAttributes;
    private boolean gameOver = false;
    private boolean gameWin = false;

    public GameGUI(MovieCategory movie, String[] titles) {
        setPreferredSize(new Dimension(800, 600));
        setBackground(Color.PINK);
        this.Movie = movie;
        this.titles = titles;
        this.game = new GameActual(movie, titles);
        this.lives = 7;

        movies = new ArrayList<>();
        guessField = new JTextField(20);
        guessButton = new JButton("Enter Guess");
        codeResponse = new JLabel("Guess the Movie!");
        resultLabel = new JLabel(" ");
        livesLabel = new JLabel("Lives: " + lives);
        displayPanel = new JPanel();
        displayPanel.setPreferredSize(new Dimension(800, 500));
        attributes = new ArrayList<>();

        answerAttributes = new String[]{
                Movie.mname,
                Movie.lead_actor,
                Movie.supp_actor,
                Movie.director,
                Movie.releaseYear,
                Movie.streaming_service,
                Movie.rating,
                Movie.prod_comp
        };

        JPanel userGuess = new JPanel();
        userGuess.add(guessButton);
        userGuess.add(guessField);
        userGuess.add(codeResponse);
        userGuess.add(resultLabel);
        userGuess.add(livesLabel);
        add(userGuess);
        add(displayPanel);

        guessButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleGuess();
            }
        });
    }

    private void handleGuess() {
        String userGuess = guessField.getText();

        if (userGuess.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Guess cannot be empty.");
            return;
        }
        try {
            guessResult = game.queryGuess(userGuess.toLowerCase());
            resultLabel.setText(guessResult.mname);
            addMovie(guessResult);

            // Check if the guess is correct
            if (game.isCorrect(userGuess)) {
                gameWin = true;
                resultLabel.setText("Correct! You guessed it!");
                resultLabel.setForeground(Color.GREEN);
                guessButton.setEnabled(false);
                gameOver = true;

            } else {
                lives--;
                if (lives > 0) {
                    resultLabel.setText("Incorrect! Try again.");
                    resultLabel.setForeground(Color.RED);
                    livesLabel.setText("Lives Left: " + (lives));
                } else {
                    livesLabel.setText("Lives Left: " + 0);
                    resultLabel.setText("Game Over! No lives left." + answerAttributes[0]);
                    resultLabel.setForeground(Color.BLACK);
                    gameOver = true;
                    guessButton.setEnabled(false);
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Movie Does not exit in our data base. Sorry!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Unexpected error: " + e.getMessage());
            System.out.println(e.getMessage());
        }
    }

    public void compareAttributes(MovieCategory userGuess) {

        String[] guessAttributes = {
                userGuess.mname,
                userGuess.lead_actor,
                userGuess.supp_actor,
                userGuess.director,
                userGuess.releaseYear,
                userGuess.streaming_service,
                userGuess.rating,
                userGuess.prod_comp
        };

        for (int i = 0; i < guessAttributes.length; i++) {

            if (guessAttributes[i].equals(answerAttributes[i]))
                guessAttributes[i] += " (C)";
            else
                guessAttributes[i] += " (X)";
            this.attributes.add(guessAttributes[i]);
        }
    }

    public void addMovie(MovieCategory movie) {
        movies.add(movie);
        updateDisplay();
    }

    public void updateDisplay() {

        displayPanel.removeAll();
        compareAttributes(guessResult);
        int i = 0;

        for (MovieCategory movie : movies) {
            ArrayList<String> slicedAttributes = new ArrayList<>(this.attributes.subList(i, i + answerAttributes.length));
            displayPanel.add(new AttributePanel(slicedAttributes));
            i+=answerAttributes.length;
        }

        revalidate();
        repaint();
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (gameOver) {
            g.setFont(new Font("Arial", Font.BOLD, 50));

            if (gameWin) {
                setBackground(Color.GREEN);
                g.drawString("You Win!", 200, 550);
            }
            else {
                setBackground(Color.RED);
                g.drawString("You Lose!", 200, 550);
            }

            remove(displayPanel);
            ImageIcon moviePoster = new ImageIcon("src/images/" + answerAttributes[0] + ".jpeg");
            g.drawImage(moviePoster.getImage(), 300, 100, 200, 300, null);
            g.drawString("The movie is " + answerAttributes[0] + "!", 200, 500);
        }
    }


}
