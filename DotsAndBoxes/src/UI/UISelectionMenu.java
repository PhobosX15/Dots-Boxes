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
    private JFrame frame;
    public  static ArrayList<JButton> selectStrategyButtons = new ArrayList<>();
    JTextField textField;



    /**
     * The following variables are the selection variables
     */
    int length =10;// -1;
    GameStrategy player1 = new Game_Strategy_Human(true);
    GameStrategy player2 = new Game_Strategy_Human(false);
    JLabel gameLabel = getGameLabel();

    public UISelectionMenu(JFrame frame) {
        this.frame =frame;

    }

    public void initializeSelectionMenu(){

        frame.getContentPane().removeAll();
        frame.revalidate();
        frame.repaint();

        //Add what we need to this menu here
        JPanel backgroundImagePanel = getBackgroundImagePanel();
        JButton startGameButton = getStartGameButton();
        textField = getTextField();
        frame.setContentPane(backgroundImagePanel);
        frame.add(startGameButton);
        frame.add(textField);
        frame.add(gameLabel);
        frame.add(getSelectorButtons());
        //Then final step
        frame.setVisible(true);
    }

    private JLabel getGameLabel() {
        JLabel label = new JLabel(player1.title + " Vs " + player2.title);
        label.setFont(new Font("Arial", Font.BOLD, 50));
        label.setOpaque(false);
        label.setBounds(380,75,500,85);
        return label;
    }
    private void updateGameLabel(){
        gameLabel.setText(player1.title + " Vs " + player2.title);
        gameLabel.updateUI();
    }

    private JTextField getTextField() {
        JTextField textField = new JTextField("Board Size:");
        Font fieldFont = new Font("Arial", Font.PLAIN, 20);
        textField.setFont(fieldFont);
        textField.setBackground(Color.white);
        textField.setForeground(Color.gray.brighter());
        textField.setColumns(30);
        textField.setBounds(400,30,400,30);
        textField.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                String number = textField.getText();
                length = Integer.parseInt(textField.getText());

            }
        });
        textField.addMouseListener(new MouseListener(){
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
    private JPanel getSelectorButtons(){
        JPanel panel = new JPanel(new GridLayout(3,2));

        ImageIcon p1Human = new ImageIcon(UIMainIntro.class.getResource("../images/Human Player Button.png"));
        JButton bp1Human = new JButton(p1Human);
        bp1Human.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                player1 = new Game_Strategy_Human(true);
                updateGameLabel();
            }
        });
        ImageIcon p1Ai1 = new ImageIcon(UIMainIntro.class.getResource("../images/Ai 1 button.png"));
        JButton bp1Ai1 = new JButton(p1Ai1);
        bp1Ai1.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                player1 = new Game_Strategy_AI_1_example(true);
                updateGameLabel();
            }
        });
        ImageIcon p1Ai2 = new ImageIcon(UIMainIntro.class.getResource("../images/Ai 2 button.png"));
        JButton bp1Ai2 = new JButton(p1Ai2);
        bp1Ai2.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                player1 = new Game_Strategy_AI_2_example(true);
                updateGameLabel();
            }
        });
        ImageIcon p2Human = new ImageIcon(UIMainIntro.class.getResource("../images/Human Player Button.png"));
        JButton bp2Human = new JButton(p1Human);
        bp2Human.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                player2 = new Game_Strategy_Human(false);
                updateGameLabel();

            }
        });
        ImageIcon p2Ai1 = new ImageIcon(UIMainIntro.class.getResource("../images/Ai 1 button.png"));
        JButton bp2Ai1 = new JButton(p1Ai1);
        bp2Ai1.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                player2 = new Game_Strategy_AI_1_example(false);
                updateGameLabel();
            }
        });
        ImageIcon p2Ai2 = new ImageIcon(UIMainIntro.class.getResource("../images/Ai 2 button.png"));
        JButton bp2Ai2 = new JButton(p1Ai2);
        bp2Ai2.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                player2 = new Game_Strategy_AI_2_example(false);
                updateGameLabel();
            }
        });

        panel.add(bp1Human);
        panel.add(bp2Human);
        panel.add(bp1Ai1);
        panel.add(bp2Ai1);
        panel.add(bp1Ai2);
        panel.add(bp2Ai2);
        panel.setBounds(375,175,265,180);
        return panel;
    }

    private JButton getStartGameButton() {
        ImageIcon startB = new ImageIcon(UIMainIntro.class.getResource("../images/start.png"));
        JButton startGameBoard = new JButton(startB);
        startGameBoard.setBounds(990, 550, 100, 100);
        startGameBoard.setBorderPainted(false);
        startGameBoard.setContentAreaFilled(false);
        startGameBoard.setFocusPainted(false);
        startGameBoard.setVisible(true);

        startGameBoard.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                startGameBoard.setIcon(startB);
                startGameBoard.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                startGameBoard.setIcon(startB);
                startGameBoard.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }

            @Override
            public void mousePressed(MouseEvent e) {
                if(length<1 || player1 == null || player2 == null){
                    //todo when selection is not made
                }else {
                    length = Integer.parseInt(textField.getText());
                    UIGameBoard gameBoard = new UIGameBoard(length,frame,player1,player2);
                }
            }
        });
        return startGameBoard;
    }

    private JPanel getBackgroundImagePanel() {
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {

                super.paintComponent(g);
                ImageIcon img = new ImageIcon("C:/CODE101/project2.1/DotsAndBoxes/src/images/dotsnboxes.jpg","blah");
                g.drawImage(img.getImage(),0,0, UIMainIntro.SCREEN_WIDTH,UIMainIntro.SCREEN_HEIGHT,this);
            }
        };
        panel.setLayout(null);
        return panel;
    }
}
//class PlayerSelectionMouseAdapter extends MouseAdapter {
//    @Override
//    public void mouseEntered(MouseEvent e) {
//
//    }
//
//    @Override
//    public void mouseExited(MouseEvent e) {
//
//    }
//
//    @Override
//    public void mousePressed(MouseEvent e) {
//
//        Object tmp = e.getSource();
//        for (int i = 0; i < UISelectionMenu.selectStrategyButtons.size(); i++) {
//
//        }
//    }
//}