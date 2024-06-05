import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TicTacToe {
    int boardWidth = 500;
    int boardHeight = 600;

    JFrame frame = new JFrame("Tic Tac Toe");
    JLabel textLabel = new JLabel();
    JPanel topPanel = new JPanel();
    JPanel bottomPanel = new JPanel();
    JPanel boardPanel = new JPanel();

    JButton[][] board = new JButton[3][3];
    JButton restartButton = new JButton();

    String playerX = "X";
    String playerO = "O";
    String currentPlayer = playerX;
    Boolean playerWin = false;
    int turns = 0;

    // Construct main frame
    TicTacToe() {
        frame.setSize(boardWidth, boardHeight);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setVisible(true);

        // Add window listener to print termination message when window closes
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.out.println("Tic Tac Toe is closing!");
            }
        });

        topPanel.setLayout(new BorderLayout());
        topPanel.add(textLabel);
        frame.add(topPanel, BorderLayout.NORTH);

        bottomPanel.setLayout(new BorderLayout());
        bottomPanel.add(restartButton);
        frame.add(bottomPanel, BorderLayout.SOUTH);

        textLabel.setText("Tic Tac Toe");
        textLabel.setFont(new Font(null, Font.BOLD, 50));
        textLabel.setBackground(new Color(20, 0, 84));
        textLabel.setForeground(new Color(178, 112, 250));
        textLabel.setHorizontalAlignment(JLabel.CENTER);
        textLabel.setOpaque(true);

        restartButton.setText("Restart");
        restartButton.setFont(new Font(null, Font.BOLD, 30));
        restartButton.setBackground(new Color(20, 0, 84));
        restartButton.setFocusable(false);
        
        // Add listener to reset game when restart button is pressed
        restartButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                playerWin = false;
                turns = 0;
                
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        restart(board[i][j]);
                    }
                }
            }
        });

        boardPanel.setLayout(new GridLayout(3, 3));
        boardPanel.setBackground(Color.white);
        frame.add(boardPanel);

        // Add buttons for 3x3 grid
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                JButton tile = new JButton();
                board[i][j] = tile;
                boardPanel.add(tile);

                tile.setFont(new Font(null, Font.BOLD, 100));
                tile.setBackground(new Color(20, 0, 84));
                tile.setFocusable(false);

                // Add listener to update tile text
                tile.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        if (playerWin) return; // Exit method early if player wins - no tiles are updated
                        JButton tile = (JButton) e.getSource();

                        // Ensures that tiles are empty before being updated
                        if (tile.getText() == "") {
                            tile.setForeground(new Color(112, 213, 250));
                            tile.setText(currentPlayer);
                            turns++;
                            winCheck();
                            
                            // Alternates between player X and O
                            if (!playerWin) {
                                if (currentPlayer == playerX) {
                                    tile.setForeground(new Color(223, 112, 250));
                                    currentPlayer = playerO;
                                } else {
                                    currentPlayer = playerX;
                                }
                            }
                        }
                    }
                });
            }
        }
    }

    // Check win condition
    private void winCheck() {
        // Horizontal matches
        for (int i = 0; i < 3; i++) {
            if (board[i][0].getText() != "") { 
                if (board[i][0].getText() == board[i][1].getText() &&
                    board[i][1].getText() == board[i][2].getText()) {
                        for (int j = 0; j < 3; j++) {
                            setWinner(board[i][j]);
                        }
                        playerWin = true;
                        return;
                }
            }
        }

        // Vertical matches
        for (int j = 0; j < 3; j++) {
            if (board[0][j].getText() != "") {
                if (board[0][j].getText() == board[1][j].getText() &&
                    board[1][j].getText() == board[2][j].getText()) {
                        for (int i = 0; i < 3; i++) {
                            setWinner(board[i][j]);
                        }
                        playerWin = true;
                        return;
                    }
                    

            }
        }

        // Diagonal matches
        if (board[0][0].getText() != "" &&
            board[0][0].getText() == board[1][1].getText() &&
            board[1][1].getText() == board[2][2].getText()) {
                for (int i = 0; i < 3; i++) {
                    setWinner(board[i][i]);
                }
                playerWin = true;
                return;
            }
        if (board[0][2].getText() != "" &&
            board[0][2].getText() == board[1][1].getText() &&
            board[1][1].getText() == board[2][0].getText()) {
                setWinner(board[0][2]);
                setWinner(board[1][1]);
                setWinner(board[2][0]);
                playerWin = true;
                return;
            }

        // If the game is a tie
        if (turns == 9) {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    gameTie(board[i][j]);
                }
            }
            playerWin = true;
        }

    }

    // Updates text and colour when a player wins
    private void setWinner(JButton tile) {
        tile.setBackground(new Color(72, 55, 168));
        tile.setForeground(Color.yellow);
        textLabel.setText(currentPlayer + " wins!");
    }

    // Updates text and colour if the game is a tie
    private void gameTie(JButton tile) {
        tile.setBackground(new Color(72, 55, 168));
        tile.setForeground(Color.yellow);
        textLabel.setText("Draw!");
    }

    // Resets the text and colour when restarting
    private void restart(JButton tile) {
        tile.setBackground(new Color(20, 0, 84));
        tile.setText("");
        textLabel.setText("Tic Tac Toe");
    }

    public static void main(String[] args) {
        TicTacToe game = new TicTacToe();
        System.out.println("Tic Tac Toe is starting!");
    }
}
