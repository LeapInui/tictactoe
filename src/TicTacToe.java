import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TicTacToe {
    int boardWidth = 500;
    int boardHeight = 550;

    JFrame frame = new JFrame("Tic Tac Toe");
    JLabel textLabel = new JLabel();
    JPanel topPanel = new JPanel();
    JPanel boardPanel = new JPanel();

    JButton[][] board = new JButton[3][3];
    String playerX = "X";
    String playerO = "O";
    String currentPlayer = playerX;

    TicTacToe() {
        frame.setSize(boardWidth, boardHeight);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setVisible(true);

        topPanel.setLayout(new BorderLayout());
        topPanel.add(textLabel);
        frame.add(topPanel, BorderLayout.NORTH);

        textLabel.setText("Tic Tac Toe");
        textLabel.setFont(new Font(null, Font.BOLD, 50));
        textLabel.setBackground(Color.darkGray);
        textLabel.setForeground(Color.white);
        textLabel.setHorizontalAlignment(JLabel.CENTER);
        textLabel.setOpaque(true);
        
        boardPanel.setLayout(new GridLayout(3, 3));
        boardPanel.setBackground(Color.darkGray);
        frame.add(boardPanel);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                JButton tile = new JButton();
                board[i][j] = tile;
                boardPanel.add(tile);

                tile.setFont(new Font(null, Font.BOLD, 100));
                tile.setBackground(Color.darkGray);
                tile.setForeground(Color.white);
                tile.setFocusable(false);

                tile.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        JButton tile = (JButton) e.getSource();
                        
                        if (tile.getText() == "") {
                            tile.setText(currentPlayer);
                        
                            if (currentPlayer == playerX) {
                                currentPlayer = playerO;
                            } else {
                                currentPlayer = playerX;
                            }
                        }
                    }
                });

            }
        }
    }
}
