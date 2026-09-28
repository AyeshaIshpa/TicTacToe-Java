import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TicTacToe {
    static boolean xturn = true;
    static boolean gameOver = false;

    static void checkwinner(JButton[] buttons) {

        if(gameOver){
            return;
        }

        if (!buttons[0].getText().equals("") &&
                buttons[0].getText().equals(buttons[1].getText()) &&
                buttons[1].getText().equals(buttons[2].getText())) {


            highlightWinner(buttons, 0, 1, 2, buttons[0].getText());
            showwinner(buttons[0].getText());
        }

        if (!buttons[3].getText().equals("") &&
                buttons[3].getText().equals(buttons[4].getText()) &&
                buttons[4].getText().equals(buttons[5].getText())) {


            highlightWinner(buttons, 3, 4, 5, buttons[3].getText());
            showwinner(buttons[3].getText());
        }

        if (!buttons[6].getText().equals("") &&
                buttons[6].getText().equals(buttons[7].getText()) &&
                buttons[7].getText().equals(buttons[8].getText())) {

            highlightWinner(buttons, 6, 7, 8, buttons[6].getText());
            showwinner(buttons[6].getText());
        }

        if (!buttons[0].getText().equals("") &&
                buttons[0].getText().equals(buttons[3].getText()) &&
                buttons[3].getText().equals(buttons[6].getText())) {


            highlightWinner(buttons, 0, 3, 6,buttons[0].getText());
            showwinner(buttons[0].getText());
        }

        if (!buttons[1].getText().equals("") &&
                buttons[1].getText().equals(buttons[4].getText()) &&
                buttons[4].getText().equals(buttons[7].getText())) {

            highlightWinner(buttons, 1, 4, 7, buttons[1].getText());
            showwinner(buttons[1].getText());
        }

        if (!buttons[2].getText().equals("") &&
                buttons[2].getText().equals(buttons[5].getText()) &&
                buttons[5].getText().equals(buttons[8].getText())) {

            highlightWinner(buttons, 2, 5, 8, buttons[2].getText());
            showwinner(buttons[2].getText());
        }

        if (!buttons[0].getText().equals("") &&
                buttons[0].getText().equals(buttons[4].getText()) &&
                buttons[4].getText().equals(buttons[8].getText())) {

            highlightWinner(buttons, 0, 4, 8, buttons[0].getText());
            showwinner(buttons[0].getText());
        }

        if (!buttons[2].getText().equals("") &&
                buttons[2].getText().equals(buttons[4].getText()) &&
                buttons[4].getText().equals(buttons[6].getText())) {

            highlightWinner(buttons, 2, 4, 6, buttons[2].getText());
            showwinner(buttons[2].getText());
        }
    }

    static void checkDraw(JButton[]  buttons){
        for(int i = 0; i<9; i++){
            if(buttons[i].getText().equals("")){
                return;
            }
        }
        gameOver = true;

        JOptionPane.showMessageDialog(null, "It's a Draw");
    }

    static void resetGame(JButton[] buttons, JLabel turnLabel){
        for(int i=0; i<9 ;i++){
            buttons[i].setText("");
            buttons[i].setForeground(new Color(0xF6E0B6));
            buttons[i].setBackground(new Color(0x3D1534));
        }

        xturn=true;
        gameOver=false;
        turnLabel.setText("X's Turn");
    }

    static void showwinner(String winner) {

        gameOver=true;

        Color winnerColour;

        if(winner.equals("X")){
                winnerColour = new Color(0xA6BCC9);
            } else {
                winnerColour = new Color(0xF6E0B6);
        }

        UIManager.put("OptionPane.messageForeground", winnerColour);
        UIManager.put("OptionPane.background", new Color(0x3D1534));
        UIManager.put("Panel.background", new Color(0x3D1534));

        JOptionPane.showMessageDialog(
                null,
                winner + " Wins!",
                "Game Over",
                JOptionPane.INFORMATION_MESSAGE
        );


        UIManager.put("OptionPane.messageForeground", Color.BLACK);
    }

    static void highlightWinner(JButton[] buttons, int a, int b, int c, String winner){

        Color highlightColour;

        if(winner.equals("X")){
            highlightColour = new Color(0xDDFFB2);
        } else {
            highlightColour = new Color(0xA6BCC9);
        }

        buttons[a].setBackground(highlightColour);
        buttons[b].setBackground(highlightColour);
        buttons[c].setBackground(highlightColour);
    }


    public static void main(String[] args){
        JFrame frame = new JFrame("Tic Tac Toe");

        frame.setSize(450, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3,3,8,8));
        panel.setBackground(new Color(0x3D1534));

        JButton[] buttons = new JButton[9];
        JLabel turnLabel = new JLabel("X's Turn", SwingConstants.CENTER);
        turnLabel.setFont(new Font("Playfair Display", Font.BOLD,22));
        turnLabel.setForeground(new Color(0x3D1534));
        turnLabel.setBorder(BorderFactory.createEmptyBorder(15,0,15,0));

        JButton restartButton = new JButton("New Game");
        restartButton.setFont(new Font("Playfair Display",Font.BOLD,18));
        restartButton.setFocusPainted(false);

        restartButton.setBackground(new Color(0x3D1534));
        restartButton.setForeground(new Color(0xFFF4EB));
        restartButton.setBorder(BorderFactory.createEmptyBorder(12, 20, 12, 20));



        restartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetGame(buttons, turnLabel);
            }
        });


        for(int i=0; i< 9; i++){
            buttons[i] = new JButton();

            buttons[i].setFont(new Font("Playfair Display", Font.BOLD,50));
            buttons[i].setFocusPainted(false);

            buttons[i].setBackground(new Color(0x3D1534));

            buttons[i].setBorder(BorderFactory.createLineBorder(new Color(0xFFF4EB), 2));

            buttons[i].setOpaque(true);

            buttons[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JButton clickedButton = (JButton) e.getSource();

                    if(gameOver){
                        return;
                    }

                    if(!clickedButton.getText().equals("")){
                        return;
                    }

                    if(xturn){
                        clickedButton.setText("X");
                        clickedButton.setForeground(new Color(0xA6BCC9));
                        turnLabel.setText("O's Turn");
                    } else {
                        clickedButton.setText("O");
                        clickedButton.setForeground(new Color(0xDDFFB2));
                        turnLabel.setText("X's Turn");
                    }
                    xturn=!xturn;
                    checkwinner(buttons);

                    if(!gameOver){
                        checkDraw(buttons);
                    }
                }
            });

            panel.add(buttons[i]);
        }

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(0xF6E0B6));

        mainPanel.add(turnLabel, BorderLayout.NORTH);
        mainPanel.add(panel, BorderLayout.CENTER);


        frame.add(mainPanel, BorderLayout.CENTER);
        frame.add(restartButton, BorderLayout.SOUTH);

        frame.setVisible(true);
    }
}
