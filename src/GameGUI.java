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


    public GameGUI(MovieCategory movie, String[] titles) {
        setPreferredSize(new Dimension(800, 600));
        this.Movie = movie;
        this.titles = titles;
        this.game = new GameActual(movie, titles);

        guessField = new JTextField(20);
        guessButton = new JButton("Enter Guess");
        codeResponse = new JLabel("Guess the Movie!");

        JPanel userGuess = new JPanel();
        userGuess.add(guessButton);
        userGuess.add(guessField);
        userGuess.add(codeResponse);

        add(userGuess);

        guessButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String userGuessText = guessField.getText();
                String movieQuery;
                if (userGuessText.trim().isEmpty()) {
                    codeResponse.setText("Answer cannot be empty!");
                } else {
                    try {
                        movieQuery = game.queryGuess(guessField.getText());

                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        });


    }


}
