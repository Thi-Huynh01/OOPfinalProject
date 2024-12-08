import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;

public class GameGUI extends JPanel {

    // ArrayLists to hold the movies and its attributes
    public ArrayList<MovieCategory> movies;
    public ArrayList<String> attributes;

    // Create the three objects that are essential to running this game
    private MovieCategory guessResult;
    private final MovieCategory Movie;
    private final GameActual game;

    // GUI items
    private final JTextField guessField;
    private final JPanel displayPanel;
    private final JLabel livesLabel;
    private final JLabel codeResponse;
    private final JLabel resultLabel;
    private final JLabel hintLabel;
    private final JButton guessButton;
    private final JButton hintButton;

    // String arrays to hold answer attributes and movie titles
    private final String[] answerAttributes;
    private final String[] titles;

    // Variables that depends on the user's actions
    private boolean gameOver = false;
    private boolean gameWin = false;
    private int lives;

    // Constructor
    public GameGUI(MovieCategory movie, String[] titles) {

        // Set size and background of JPanel
        setPreferredSize(new Dimension(800, 600));
        setBackground(Color.BLACK);

        // Set variables
        this.Movie = movie;
        this.titles = titles;
        this.game = new GameActual(movie, titles);
        this.lives = 7;

        // String array to hold the attributes of the Movie
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

        // Create and add all GUI items
        movies = new ArrayList<>();
        guessField = new JTextField(20);
        guessButton = new JButton("Enter Guess");
        hintButton = new JButton("Hint");
        hintLabel = new JLabel();
        codeResponse = new JLabel("Guess the Movie!");
        resultLabel = new JLabel(" ");
        livesLabel = new JLabel("Lives: " + lives);
        attributes = new ArrayList<>();
        displayPanel = new JPanel();
        displayPanel.setPreferredSize(new Dimension(800, 500));

        // This is where the answer field and stats for the user will be
        JPanel userGuess = new JPanel();
        userGuess.add(guessButton);
        userGuess.add(guessField);
        userGuess.add(codeResponse);
        userGuess.add(resultLabel);
        userGuess.add(livesLabel);
        userGuess.add(hintButton);
        userGuess.add(hintLabel);

        // Add both to the JPanel
        add(userGuess);
        add(displayPanel);

        // This is where all the user guess things are handled
        guessButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleGuess();
            }
        });

        // This is where the hint button functionality is
        hintButton.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {

               // Hint will be ONE of the attributes (except the movie title)
               Random rand = new Random();
               int min = 1;
               int max = answerAttributes.length - 1;
               int random = rand.nextInt(max - min + 1) + min;

               // Disable the hint button after used
               hintButton.setEnabled(false);
               hintLabel.setText(answerAttributes[random]);

               // Using a hint will take one life
               lives --;
               livesLabel.setText("Lives Left: " + (lives));
           }
        });
    }

    // This function is essentially where the whole game is
    private void handleGuess() {

        // This is where the user guess is stored
        String userGuess = guessField.getText();

        // If player put in empty string, display error message
        if (userGuess.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Guess cannot be empty.");
            return;
        }

        try {
            // Query the user result and return a MovieCategory object to compare to the answer's MovieCategory
            guessResult = game.queryGuess(userGuess.toLowerCase());
            addMovie(guessResult);

            // Check if the guess is correct
            if (game.isCorrect(userGuess)) {
                gameWin = true; // Player won the game
                gameOver = true; // Therefore, game is over
                resultLabel.setForeground(Color.GREEN);
            // If guess is incorrect...
            } else {
                // Player loses one life
                lives--;

                if (lives > 0) {
                    // Display how many lives left
                    resultLabel.setForeground(Color.RED);
                    livesLabel.setText("Lives Left: " + (lives));
                } else {
                    // No more lives left, end the game
                    lives = 0;
                    gameOver = true;
                    livesLabel.setText("Lives Left: " + lives);
                    resultLabel.setForeground(Color.BLACK);
                }
            }

        } catch (SQLException e) { // If the movie entered is not in our database, let the player know.
            JOptionPane.showMessageDialog(this, "Movie Does not exit in our database. Sorry!");
            System.out.println(e);
        } catch (Exception e) { // Catch all errors
            JOptionPane.showMessageDialog(this, "Unexpected error: " + e.getMessage());
            System.out.println(e.getMessage());
        }
    }

    // Compare the player guess attributes to the actual movie's attributes
    public void compareAttributes(MovieCategory userGuess) {

        // All the guess attributes will be stored in this String array.
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

            // Concatenate a (C) for correct
            if (guessAttributes[i].equals(answerAttributes[i]))
                guessAttributes[i] += " (C)";
            // Concatenate a (X) for incorrect
            else
                guessAttributes[i] += " (X)";
            this.attributes.add(guessAttributes[i]);
        }
    }

    // Add movie to ArrayList
    public void addMovie(MovieCategory movie) {
        movies.add(movie);
        updateDisplay();
    }

    // Update the display
    public void updateDisplay() {

        // Remove all the user's guesses
        displayPanel.removeAll();

        // Compare the two attributes
        compareAttributes(guessResult);
        int i = 0;

        // Make an AttributePanel for all user guesses
        for (MovieCategory movie : movies) {
            ArrayList<String> slicedAttributes = new ArrayList<>(this.attributes.subList(i, i + answerAttributes.length));
            displayPanel.add(new AttributePanel(slicedAttributes));
            i+=answerAttributes.length;
        }

        revalidate();
        repaint();
    }

    // Call when the game is over
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // If the game is over...
        if (gameOver) {
            guessButton.setEnabled(false);
            hintButton.setEnabled(false);
            remove(displayPanel);
            g.setFont(new Font("Arial", Font.BOLD, 50));

            // If the player won the game...
            if (gameWin) {
                setBackground(Color.GREEN);
                g.drawString("You Win!", 200, 550);
            }
            // If the player lost the game...
            else {
                setBackground(Color.RED);
                g.drawString("You Lose!", 200, 550);
            }
            String fileExtension = ".jpeg";
            if (Movie.mname.equals("Transformers") || (Movie.mname.equals("Titanic"))) {
                fileExtension = ".jpg";
            }
            // Display the movie poster of the answer
            ImageIcon moviePoster = new ImageIcon("src/images/" + answerAttributes[0] + fileExtension);
            g.drawImage(moviePoster.getImage(), 300, 100, 200, 300, null);

            g.drawString("The movie is " + answerAttributes[0], 100, 500);
        }
    }
}
