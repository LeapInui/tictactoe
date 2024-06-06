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
    JButton themeButton = new JButton();

    Color backgroundColour = new Color(255, 249, 196);
    Color textColour = new Color(207, 185, 151);
    Color oColour = new Color(255, 105, 97);
    Color xColour = new Color(167, 199, 231);
    Color winbgColour = new Color(255, 236, 179);
    Color winColour = new Color(177, 156, 217);

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
        bottomPanel.setBackground(backgroundColour);
        bottomPanel.add(restartButton, BorderLayout.EAST);
        bottomPanel.add(themeButton, BorderLayout.WEST);
        frame.add(bottomPanel, BorderLayout.SOUTH);

        textLabel.setText("Tic Tac Toe");
        textLabel.setFont(new Font(null, Font.BOLD, 50));
        textLabel.setBackground(backgroundColour);
        textLabel.setForeground(textColour);
        textLabel.setHorizontalAlignment(JLabel.CENTER);
        textLabel.setOpaque(true);

        themeButton.setText(" Themes ");
        themeButton.setFont(new Font(null, Font.BOLD, 30));
        themeButton.setBackground(backgroundColour);
        themeButton.setForeground(textColour);
        themeButton.setFocusable(false);

        // Add listener for themes
        themeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                themes();
            }
        });

        restartButton.setText(" Restart  ");
        restartButton.setFont(new Font(null, Font.BOLD, 30));
        restartButton.setBackground(backgroundColour);
        restartButton.setForeground(textColour);
        restartButton.setFocusable(false);
        
        // Add listener to reset game when restart button is pressed
        restartButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        restart(board[i][j]);
                    }
                }
            }
        });

        boardPanel.setLayout(new GridLayout(3, 3));
        boardPanel.setBackground(backgroundColour);
        frame.add(boardPanel);

        // Add buttons for 3x3 grid
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                JButton tile = new JButton();
                board[i][j] = tile;
                boardPanel.add(tile);

                tile.setFont(new Font(null, Font.BOLD, 100));
                tile.setBackground(boardPanel.getBackground());
                tile.setFocusable(false);

                // Add listener to update tile text
                tile.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        if (playerWin) return; // Exit method early if player wins - no tiles are updated
                        JButton tile = (JButton) e.getSource();

                        // Ensures that tiles are empty before being updated
                        if (tile.getText() == "") {
                            tile.setForeground(oColour);
                            tile.setText(currentPlayer);
                            turns++;
                            winCheck();
                            
                            // Alternates between player X and O
                            if (!playerWin) {
                                if (currentPlayer == playerX) {
                                    tile.setForeground(xColour);
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
        tile.setBackground(winbgColour);
        tile.setForeground(winColour);
        textLabel.setText(currentPlayer + " wins!");
    }

    // Updates text and colour if the game is a tie
    private void gameTie(JButton tile) {
        tile.setBackground(winbgColour);
        tile.setForeground(winColour);
        textLabel.setText("Draw!");
    }

    // Resets the text and colour when restarting
    private void restart(JButton tile) {
        playerWin = false;
        turns = 0;
        tile.setBackground(backgroundColour);
        tile.setText("");
        textLabel.setText("Tic Tac Toe");
    }

    // Popup for themes
    private void themes() {
        JDialog themeDialog = new JDialog(frame, "Themes", true);
        JPanel themePanel = new JPanel();
        JButton theme1Button = new JButton();
        JButton theme2Button = new JButton();
        JButton theme3Button = new JButton();


        themeDialog.setSize(400, 600);
        themeDialog.setResizable(false);
        themeDialog.setLocationRelativeTo(frame);
        themeDialog.setLayout(new BorderLayout());
        
        themePanel.setLayout(new GridLayout(3, 1));
        themePanel.setBackground(new Color(20, 0, 84));
        themeDialog.add(themePanel);

        theme1Button.setText("Theme 1");
        theme1Button.setFont(new Font(null, Font.BOLD, 40));
        theme1Button.setBackground(backgroundColour);
        theme1Button.setForeground(textColour);
        theme1Button.setFocusable(false);
        themePanel.add(theme1Button);

        theme1Button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                backgroundColour = new Color(255, 249, 196);
                textColour = new Color(207, 185, 151);
                oColour = new Color(255, 105, 97);
                xColour = new Color(167, 199, 231);
                winbgColour = new Color(255, 236, 179);
                winColour = new Color(177, 156, 217);
                themeDialog.dispose();

                bottomPanel.setBackground(backgroundColour);
                textLabel.setBackground(backgroundColour);
                themeButton.setBackground(backgroundColour);
                restartButton.setBackground(backgroundColour);
                boardPanel.setBackground(backgroundColour);

                textLabel.setForeground(textColour);
                themeButton.setForeground(textColour);
                restartButton.setForeground(textColour);

                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        restart(board[i][j]);
                        board[i][j].setBackground(backgroundColour);
                        board[i][j].setForeground(textColour);
                    }
                }
            }
        });

        theme2Button.setText("Theme 2");
        theme2Button.setFont(new Font(null, Font.BOLD, 40));
        theme2Button.setBackground(backgroundColour);
        theme2Button.setForeground(textColour);
        theme2Button.setFocusable(false);
        themePanel.add(theme2Button);

        theme2Button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                backgroundColour = new Color(66, 66, 66);
                textColour = new Color(255, 255, 255);
                oColour = new Color(255, 205, 210);
                xColour = new Color(186, 104, 200);
                winbgColour = new Color(158, 158, 158);
                winColour = new Color(13, 71, 161);
                themeDialog.dispose();

                bottomPanel.setBackground(backgroundColour);
                textLabel.setBackground(backgroundColour);
                themeButton.setBackground(backgroundColour);
                restartButton.setBackground(backgroundColour);
                boardPanel.setBackground(backgroundColour);

                textLabel.setForeground(textColour);
                themeButton.setForeground(textColour);
                restartButton.setForeground(textColour);

                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        restart(board[i][j]);
                        board[i][j].setBackground(backgroundColour);
                        board[i][j].setForeground(textColour);
                    }
                }
            }
        });

        theme3Button.setText("Theme 3");
        theme3Button.setFont(new Font(null, Font.BOLD, 40));
        theme3Button.setBackground(backgroundColour);
        theme3Button.setForeground(textColour);
        theme3Button.setFocusable(false);
        themePanel.add(theme3Button);
        
        theme3Button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                backgroundColour = new Color(77, 182, 172);
                textColour = new Color(0, 137, 123);
                oColour = new Color(255, 205, 210);
                xColour = new Color(179, 229, 252);
                winbgColour = new Color(128, 203, 196);
                winColour = new Color(255, 224, 130);
                themeDialog.dispose();

                bottomPanel.setBackground(backgroundColour);
                textLabel.setBackground(backgroundColour);
                themeButton.setBackground(backgroundColour);
                restartButton.setBackground(backgroundColour);
                boardPanel.setBackground(backgroundColour);

                textLabel.setForeground(textColour);
                themeButton.setForeground(textColour);
                restartButton.setForeground(textColour);

                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        restart(board[i][j]);
                        board[i][j].setBackground(backgroundColour);
                        board[i][j].setForeground(textColour);
                    }
                }
            }
        });

        themeDialog.setVisible(true);
    }

    public static void main(String[] args) {
        TicTacToe game = new TicTacToe();
        System.out.println("Tic Tac Toe is starting!");
    }
}
