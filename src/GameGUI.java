import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.*;

public class GameGUI extends JPanel{
    public ArrayList<MovieCategory> movies;
    private MovieCategory Movie;
    private String[] titles;
    private GameActual game;
    private JTextField textField;
    private JLabel livesLabel;
    private JTextField guessField;
    private JButton guessButton;
    private JLabel codeResponse;
    private JLabel resultLabel;
    private int lives = 5;
    public AttributePanel attributePanel;
    private String userGuess;
    private MovieCategory guessResult;
    private JPanel displayPanel;
    public Color[] test;


    public GameGUI(MovieCategory movie, String[] titles) {
        setPreferredSize(new Dimension(800, 600));
        this.Movie = movie;
        this.titles = titles;
        this.game = new GameActual(movie, titles);
        this.lives = 5;

        movies = new ArrayList<>();
        guessField = new JTextField(20);
        guessButton = new JButton("Enter Guess");
        codeResponse = new JLabel("Guess the Movie!");
        resultLabel = new JLabel(" ");
        livesLabel = new JLabel("Lives: " + lives);
        displayPanel = new JPanel();
        displayPanel.setPreferredSize(new Dimension(500, 300));

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
            Color[] attributes = compareAttributes(guessResult);
            addMovie(guessResult);

            // Check if the guess is correct
            if (game.isCorrect(userGuess)) {
                resultLabel.setText("Correct! You guessed it!");
                resultLabel.setForeground(Color.GREEN);
                guessButton.setEnabled(false);  // Disable further input after correct guess
            } else {
                lives--;
                if (lives > 0) {
                    resultLabel.setText("Incorrect! Try again.");
                    resultLabel.setForeground(Color.RED);
                    livesLabel.setText("Lives Left: " + (lives - 1));
                } else {
                    resultLabel.setText("Game Over! No lives left.");
                    resultLabel.setForeground(Color.BLACK);
                    guessButton.setEnabled(false);  // Disable further input when game over
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Database error occurred: " + e.getMessage());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Unexpected error: " + e.getMessage());
        }
    }

    public Color[] compareAttributes(MovieCategory userGuess) {

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
                Movie.lead_actor,
                Movie.releaseYear,
                Movie.director,
                Movie.streaming_service,
                Movie.rating,
                Movie.prod_comp,
                Movie.supp_actor
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

    public void addMovie(MovieCategory movie) {
        movies.add(movie);
        updateDisplay();
    }

    public void updateDisplay() {

        displayPanel.removeAll();

        for (MovieCategory movie : movies) {
            displayPanel.add(new AttributePanel(movie, test));
        }

        revalidate();
        repaint();
    }

}
