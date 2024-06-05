import java.awt.*;
import javax.swing.*;

public class TicTacToe {
    int boardWidth = 500;
    int boardHeight = 500;
    
    JFrame frame = new JFrame("Tic Tac Toe");
    JLabel textLabel = new JLabel();
    JPanel topPanel = new JPanel();
    JPanel boardPanel = new JPanel();    

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
        textLabel.setBackground(Color.DARK_GRAY);
        textLabel.setForeground(Color.WHITE);
        textLabel.setHorizontalAlignment(JLabel.CENTER);
        textLabel.setOpaque(true);
        
        boardPanel.setLayout(new GridLayout());
        boardPanel.setBackground(Color.DARK_GRAY);
        frame.add(boardPanel);
        
    }

}
