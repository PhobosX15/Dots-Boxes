package UI;

import Game.strategy.GameStrategy;
import Game.strategy.Game_Strategy_AI_1_example;
import Game.strategy.Game_Strategy_AI_2_example;
import Game.strategy.Game_Strategy_Human;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class UISelectionMenu {
    public JFrame frame;
    public static ArrayList<JButton> selectStrategyButtons = new ArrayList<>();
    JTextField textField;

    ////////////////////////////////////////////////////////////////////////////////////////
    javax.swing.JComboBox<String> gridSizeComboBox;
    int comboBoxIndex = -1;
    ///////////////////////////////////////////////////////////////////////////////////////


    /**
     * The following variables are the selection variables
     */
    int length = 10;// -1;
    GameStrategy player1 = new Game_Strategy_Human(true, Color.CYAN);
    GameStrategy player2 = new Game_Strategy_Human(false, Color.GREEN);
    JLabel gameLabel = getGameLabel();
    GameBoard gameBoard;
    public boolean isNotGamePlay = true;

    public UISelectionMenu(JFrame frame) {
        this.frame = frame;

    }

    public void initializeSelectionMenu() {

        frame.getContentPane().removeAll();
        frame.revalidate();
        frame.repaint();

        //Add what we need to this menu here
        JPanel backgroundImagePanel = getBackgroundImagePanel();
        JButton startGameButton = getStartGameButton();

        ////////////////////////////////////////////////////////////////////////////////////
        gridSizeComboBox = new javax.swing.JComboBox<>();
        gridSizeComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "3 x 3", "4 x 4", "5 x 5", "7 x 7", "8 x 8" }));
        Font BoxFont = new Font("Arial", Font.PLAIN, 20);
        gridSizeComboBox.setFont(BoxFont);
        gridSizeComboBox.setBackground(Color.white);
        gridSizeComboBox.setForeground(Color.gray.brighter());
        gridSizeComboBox.setBounds(400, 30, 400, 30);
        ///////////////////////////////////////////////////////////////////////////////////

        frame.setContentPane(backgroundImagePanel);
        frame.add(startGameButton);

        ////////////////////////////////////////////////////////////////////////////////////
        frame.add(gridSizeComboBox);
        ////////////////////////////////////////////////////////////////////////////////////

        frame.add(gameLabel);
        JButton[] buttons = getSelectorButtons();
        for (int i = 0; i < buttons.length; i++) {
            frame.add(buttons[i]);
        }
        //Then final step
        frame.setVisible(true);

    }

    private JLabel getGameLabel() {
        JLabel label = new JLabel(player1.title + " Vs " + player2.title);
        label.setFont(new Font("Arial", Font.BOLD, 50));
        label.setOpaque(false);
        label.setBounds(380, 75, 900, 85);
        return label;
    }

    private void updateGameLabel() {
        gameLabel.setText(player1.title + " Vs " + player2.title);
        gameLabel.updateUI();
    }

    public void warningGameLabel() {
        gameLabel.setText("Enter Valid Board Size");
        gameLabel.updateUI();
    }

    private JTextField getTextField() {
        
        //Mettre ComboBox avec des tailles dedans 

        JTextField textField = new JTextField("Board Size: Natural Number");
        Font fieldFont = new Font("Arial", Font.PLAIN, 20);
        textField.setFont(fieldFont);
        textField.setBackground(Color.white);
        textField.setForeground(Color.gray.brighter());
        textField.setColumns(30);
        textField.setBounds(400, 30, 400, 30);
        textField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                String number = textField.getText();
                length = Integer.parseInt(textField.getText());

            }
        });
        textField.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // TODO Auto-generated method stub
                textField.setText("");
                textField.setForeground(Color.black);
            }

            @Override
            public void mousePressed(MouseEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void mouseReleased(MouseEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        return textField;
    }

    private JButton[] getSelectorButtons() {
        JButton[] buttons = new JButton[6];

        ImageIcon p1Human = new ImageIcon(UIMainIntro.class.getResource("../images/Human Player Button.png"));
        JButton bp1Human = new JButton(p1Human);
        bp1Human.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                player1 = new Game_Strategy_Human(true, Color.CYAN);
                updateGameLabel();
            }
        });
        ImageIcon p1Ai1 = new ImageIcon(UIMainIntro.class.getResource("../images/Ai 1 button.png"));
        JButton bp1Ai1 = new JButton(p1Ai1);
        bp1Ai1.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                player1 = new Game_Strategy_AI_1_example(true, Color.CYAN);
                updateGameLabel();
            }
        });
        ImageIcon p1Ai2 = new ImageIcon(UIMainIntro.class.getResource("../images/Ai 2 button.png"));
        JButton bp1Ai2 = new JButton(p1Ai2);
        bp1Ai2.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                player1 = new Game_Strategy_AI_2_example(true, Color.CYAN);
                updateGameLabel();
            }
        });
        ImageIcon p2Human = new ImageIcon(UIMainIntro.class.getResource("../images/Human Player Button.png"));
        JButton bp2Human = new JButton(p1Human);
        bp2Human.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                player2 = new Game_Strategy_Human(false, Color.GREEN);
                updateGameLabel();

            }
        });
        ImageIcon p2Ai1 = new ImageIcon(UIMainIntro.class.getResource("../images/Ai 1 button.png"));
        JButton bp2Ai1 = new JButton(p1Ai1);
        bp2Ai1.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                player2 = new Game_Strategy_AI_1_example(false, Color.GREEN);
                updateGameLabel();
            }
        });
        ImageIcon p2Ai2 = new ImageIcon(UIMainIntro.class.getResource("../images/Ai 2 button.png"));
        JButton bp2Ai2 = new JButton(p1Ai2);
        bp2Ai2.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                player2 = new Game_Strategy_AI_2_example(false, Color.GREEN);
                updateGameLabel();
            }
        });
        int x = 380, y = 170, w = 125, h = 60, x2 = 650;
        bp1Human.setBounds(x, y, w, h);
        bp2Human.setBounds(x2, y, w, h);
        bp1Ai1.setBounds(x, (int) (y + 100), w, h);
        bp2Ai1.setBounds(x2, (int) (y + 100), w, h);
        bp1Ai2.setBounds(x, (int) (y + 200), w, h);
        bp2Ai2.setBounds(x2, (int) (y + 200), w, h);
        buttons[0] = (bp1Human);
        buttons[1] = (bp2Human);
        buttons[2] = (bp1Ai1);
        buttons[3] = (bp2Ai1);
        buttons[4] = (bp1Ai2);
        buttons[5] = (bp2Ai2);

        return buttons;
    }

    private JButton getStartGameButton() {
        ImageIcon startB = new ImageIcon(UIMainIntro.class.getResource("../images/start.png"));
        JButton startGameBoard = new JButton(startB);
        startGameBoard.setBounds(990, 550, 100, 100);
        startGameBoard.setBorderPainted(false);
        startGameBoard.setContentAreaFilled(false);
        startGameBoard.setFocusPainted(false);
        startGameBoard.setVisible(true);

        startGameBoard.addMouseListener(new StartGameMouseAdapter(startGameBoard,this));
        return startGameBoard;
    }

    private JPanel getBackgroundImagePanel() {
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {

                super.paintComponent(g);
                ImageIcon img = new ImageIcon("C:/CODE101/project2.1/DotsAndBoxes/src/images/dotsnboxes.jpg", "blah");
                g.drawImage(img.getImage(), 0, 0, UIMainIntro.SCREEN_WIDTH, UIMainIntro.SCREEN_HEIGHT, this);
            }
        };
        panel.setLayout(null);
        return panel;
    }
}