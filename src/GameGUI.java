import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.*;
public class GameGUI extends JPanel{
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


    public GameGUI(MovieCategory movie, String[] titles) {
        setPreferredSize(new Dimension(800, 600));
        this.Movie = movie;
        this.titles = titles;
        this.game = new GameActual(movie, titles);
        this.lives = 5;

        guessField = new JTextField(20);
        guessButton = new JButton("Enter Guess");
        codeResponse = new JLabel("Guess the Movie!");
        resultLabel = new JLabel(" ");
        livesLabel = new JLabel("Lives: " + lives);

        JPanel userGuess = new JPanel();
        userGuess.add(guessButton);
        userGuess.add(guessField);
        userGuess.add(codeResponse);
        userGuess.add(resultLabel);
        userGuess.add(livesLabel);
        add(userGuess);

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
            String guessResult = game.queryGuess(userGuess.toLowerCase());
            resultLabel.setText(guessResult);

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
                    livesLabel.setText("Lives Left: " + lives);
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

}
