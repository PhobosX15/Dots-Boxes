package UI;

import Game.strategy.GameStrategy;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GameBoard {
    public JFrame frame;
    private GameStrategy player1;
    private GameStrategy player2;
    private GameStrategy currentPlayer;
    private int n;
    private int possibleBoxCount;

    private int comboBoxIndex;
    private JLabel backgroundLabel;
    private JLabel messageLabel;
    private JLabel player1Label;
    private JLabel player1GameStrategyLabel;
    private JLabel player1scoreLabel;
    private JLabel score1Label;
    private JLabel player2Label;
    private JLabel player2GameStrategyLabel;
    private JLabel player2scoreLabel;
    private JLabel score2Label;
    private int edgeAndDotWidth2;
    private int edgeLength2;
    private int scorePlayer1 = 0;
    private int scorePlayer2 = 0;


    /**
     * fields for the board generation
     */
    private JLabel[][] hEdge, vEdge, box;
    private boolean[][] isSetHEdge, isSetVEdge, isSetBox;

    public static boolean mouseEnabled = true;
    /**
     * fields required for playing the board
     */
    private MouseListener mouseListener = new MouseListener() {
        @Override
        public void mouseClicked(MouseEvent mouseEvent) {
            if (!mouseEnabled) return;
            processMove(getSourceEdge(mouseEvent.getSource()));
        }

        @Override
        public void mousePressed(MouseEvent mouseEvent) {

        }

        @Override
        public void mouseReleased(MouseEvent mouseEvent) {

        }

        @Override
        public void mouseEntered(MouseEvent mouseEvent) {
            if (!mouseEnabled) return;
            Edge location = getSourceEdge(mouseEvent.getSource());
            int x = location.getX(), y = location.getY();
            if (location.isHorizontal()) {

                if (isSetHEdge[x][y]) return;
                hEdge[x][y].setCursor(new Cursor(Cursor.HAND_CURSOR));
                hEdge[x][y].setBackground(currentPlayer.color);
            } else {
                if (isSetVEdge[x][y]) return;
                vEdge[x][y].setCursor(new Cursor(Cursor.HAND_CURSOR));
                vEdge[x][y].setBackground(currentPlayer.color);
            }
        }

        @Override
        public void mouseExited(MouseEvent mouseEvent) {
            if (!mouseEnabled) return;
            Edge location = getSourceEdge(mouseEvent.getSource());
            int x = location.getX(), y = location.getY();
            if (location.isHorizontal()) {
                if (isSetHEdge[x][y]) return;
                hEdge[x][y].setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                hEdge[x][y].setBackground(Color.WHITE);
            } else {
                if (isSetVEdge[x][y]) return;
                vEdge[x][y].setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                vEdge[x][y].setBackground(Color.WHITE);
            }
        }
    };


    public GameBoard(int n, JFrame frame, GameStrategy player1, GameStrategy player2) {

        this.comboBoxIndex = n;
        this.n = getDimension();

        this.frame = frame;
        this.player1 = player1;
        this.player2 = player2;
        player1.setScore(scorePlayer1);
        player2.setScore(scorePlayer2);
        currentPlayer = this.player1;
        this.possibleBoxCount = (n - 1) * (n - 1);
        initializeUIGameBoard();
    }

    public void initializeUIGameBoard() {

        backgroundLabel = new JLabel();
        messageLabel = new JLabel();
        player1Label = new JLabel();
        player1GameStrategyLabel = new JLabel();
        player1scoreLabel = new JLabel();
        score1Label = new JLabel();
        player2Label = new JLabel();
        player2GameStrategyLabel = new JLabel();
        player2scoreLabel = new JLabel();
        score2Label = new JLabel();

        frame.getContentPane().removeAll();
        frame.revalidate();
        frame.repaint();

        frame.getContentPane().setLayout(null);
        frame.getContentPane().add(newBackToMenuButton());
        frame.getContentPane().add(drawNewBoard());
        
        messageLabel.setFont(new java.awt.Font("Arial", 0, 20));
        if(currentPlayer.isPlayer1)
        {
            messageLabel.setForeground(player1.color);
            messageLabel.setText("Player 1's turn.");
        }
        else
        {
            messageLabel.setForeground(player2.color);
            messageLabel.setText("Player 2's turn.");
        }
        frame.getContentPane().add(messageLabel);
        messageLabel.setBounds(820, 210, 275, 24);

        player1Label.setFont(new java.awt.Font("Arial", 0, 20));
        player1Label.setForeground(player1.color);
        player1Label.setText("Player 1:");
        frame.getContentPane().add(player1Label);
        player1Label.setBounds(820, 30, 89, 24);

        player2Label.setFont(new java.awt.Font("Arial", 0, 20));
        player2Label.setForeground(player2.color);
        player2Label.setText("Player 2:");
        frame.getContentPane().add(player2Label);
        player2Label.setBounds(1030, 30, 89, 24);

        player1GameStrategyLabel.setFont(new java.awt.Font("Arial", 0, 20));
        if(player1.title.equals("Human"))
        {
            player1GameStrategyLabel.setText("Human");
        }
        else
        {
            player1GameStrategyLabel.setText("AI");
        }
        frame.getContentPane().add(player1GameStrategyLabel);
        player1GameStrategyLabel.setBounds(820, 70, 89, 24);

        player2GameStrategyLabel.setFont(new java.awt.Font("Arial", 0, 20));
        if(player2.title.equals("Human"))
        {
            player2GameStrategyLabel.setText("Human");
        }
        else
        {
            player2GameStrategyLabel.setText("AI");
        }
        frame.getContentPane().add(player2GameStrategyLabel);
        player2GameStrategyLabel.setBounds(1030, 70, 89, 24);

        score1Label.setFont(new java.awt.Font("Arial", 0, 20));
        score1Label.setText("Score:");
        frame.getContentPane().add(score1Label);
        score1Label.setBounds(820, 110, 89, 24);

        score2Label.setFont(new java.awt.Font("Arial", 0, 20));
        score2Label.setText("Score:");
        frame.getContentPane().add(score2Label);
        score2Label.setBounds(1030, 110, 89, 24);

        player2scoreLabel.setFont(new java.awt.Font("Arial", 0, 20));
        player2scoreLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        player2scoreLabel.setText("0");
        frame.getContentPane().add(player2scoreLabel);
        player2scoreLabel.setBounds(1030, 150, 89, 24);

        player1scoreLabel.setFont(new java.awt.Font("Arial", 0, 20));
        player1scoreLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        player1scoreLabel.setText("0");
        frame.getContentPane().add(player1scoreLabel);
        player1scoreLabel.setBounds(820, 150, 89, 24);

        ImageIcon background = new ImageIcon(UIMainIntro.class.getResource("../images/dotsnboxes.jpg"));
        Image backG = background.getImage();
        Image newBackG = backG.getScaledInstance(1170, 720, java.awt.Image.SCALE_SMOOTH);
        ImageIcon newBackGround = new ImageIcon(newBackG);
        backgroundLabel.setPreferredSize(new java.awt.Dimension(1170, 720));
        backgroundLabel.setIcon(newBackGround);
        frame.getContentPane().add(backgroundLabel);
        backgroundLabel.setBounds(0, 0, 1170, 720);
        backgroundLabel.setOpaque(true);

        frame.getContentPane().validate();
        frame.setVisible(true);

        // manageGame(); --- a voir //
    }

    public void updateLabels()
    {
        player1scoreLabel.setText(Integer.toString(player1.getScore()));
        player2scoreLabel.setText(Integer.toString(player2.getScore()));

        if(currentPlayer.isPlayer1)
        {
            messageLabel.setForeground(player1.color);
            messageLabel.setText("Player 1's turn.");
        }
        else
        {
            messageLabel.setForeground(player2.color);
            messageLabel.setText("Player 2's turn.");
        }
    }

    public void manageGame() {
        while (possibleBoxCount > 0) {
            if (currentPlayer.title.equals("Human")) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                processMove(currentPlayer.makeMove(this));
            }

        }
        new UISelectionMenu(frame).initializeSelectionMenu();
    }

    private JLabel newHorizontalEdge() {
        JLabel label = new JLabel();
        label.setPreferredSize(new Dimension(edgeLength2, edgeAndDotWidth2));
        label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        label.setOpaque(true);
        label.addMouseListener(mouseListener);
        return label;
    }

    private JLabel newVerticalEdge() {
        JLabel label = new JLabel();
        label.setPreferredSize(new Dimension(edgeAndDotWidth2, edgeLength2));
        label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        label.setOpaque(true);
        label.addMouseListener(mouseListener);
        return label;
    }

    private JLabel drawDots()
    {
        JLabel dot = new JLabel();
        ImageIcon ico = new ImageIcon(UIMainIntro.class.getResource("../images/dotBlack.png"));
        Image image = ico.getImage(); 
        Image newimg = image.getScaledInstance(edgeAndDotWidth2, edgeAndDotWidth2,  java.awt.Image.SCALE_SMOOTH); 
        ImageIcon newIcon = new ImageIcon(newimg);
        dot.setPreferredSize(new Dimension(edgeAndDotWidth2, edgeAndDotWidth2));
        dot.setIcon(newIcon);
        dot.setOpaque(true);
        return dot;
    }

    private JLabel newBox() {
        JLabel label = new JLabel();
        label.setPreferredSize(new Dimension(edgeLength2, edgeLength2));
        label.setOpaque(true);
        return label;
    }

    private JPanel drawNewBoard()
    {
        JPanel grid = new JPanel(new GridBagLayout());
        grid.setOpaque(false);
        grid.setBackground(Color.WHITE);
        edgeLength2 = 720/(n+2);
        edgeAndDotWidth2 = edgeLength2/10;
        grid.setBounds(70, 0, 720, 720);

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;

        hEdge = new JLabel[n - 1][n];
        isSetBox = new boolean [n-1][n-1];
        isSetHEdge = new boolean[n - 1][n];

        vEdge = new JLabel[n][n - 1];
        isSetVEdge = new boolean[n][n - 1];

        box = new JLabel[n - 1][n - 1];

        for (int i = 0; i < ((2 * n) - 1); i++) {
            JPanel pane = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
            if (i % 2 == 0) {
                pane.add(drawDots());
                for (int j = 0; j < (n - 1); j++) {
                    hEdge[j][i / 2] = newHorizontalEdge();
                    pane.add(hEdge[j][i / 2]);
                    pane.add(drawDots());
                }
            } else {
                for (int j = 0; j < (n - 1); j++) {
                    vEdge[j][i / 2] = newVerticalEdge();
                    pane.add(vEdge[j][i / 2]);
                    box[j][i / 2] = newBox();
                    pane.add(box[j][i / 2]);
                }
                vEdge[n - 1][i / 2] = newVerticalEdge();
                pane.add(vEdge[n - 1][i / 2]);
            }
            ++constraints.gridy;
            grid.add(pane, constraints);
        }
        return grid;
    }

    private JButton newBackToMenuButton() {
        ImageIcon exitB = new ImageIcon(UIMainIntro.class.getResource("../images/exit.png"));
        JButton exitButton = new JButton(exitB);
        exitButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                exitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                exitButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }

            @Override
            public void mousePressed(MouseEvent e) {
                new UISelectionMenu(frame).initializeSelectionMenu();
            }
        });
        exitButton.setBounds(990, 550, 100, 100);
        return exitButton;
    }

    private Edge getSourceEdge(Object object) {
        for (int i = 0; i < (n - 1); i++)
            for (int j = 0; j < n; j++)
                if (hEdge[i][j] == object)
                    return new Edge(i, j, true);
        for (int i = 0; i < n; i++)
            for (int j = 0; j < (n - 1); j++)
                if (vEdge[i][j] == object)
                    return new Edge(i, j, false);
        return new Edge();
    }

    public boolean isFilled(Edge edge) {
        if (edge.isHorizontal()) {
            return isSetHEdge[edge.getX()][edge.getY()];
        }
        return isSetVEdge[edge.getX()][edge.getY()];
    }

    /**
     * fills a an edge if possible
     *
     * @param edge
     * @return false if edge is already filled
     */
    public boolean fillEdge(Edge edge) {
        //mark the edge within given coordinates as filled
        if (edge.isHorizontal() && !isSetHEdge[edge.getX()][edge.getY()]) {
            isSetHEdge[edge.getX()][edge.getY()] = true;
            hEdge[edge.getX()][edge.getY()].setBackground(currentPlayer.color);
            return true;
        } else if (!edge.isHorizontal() && !isSetVEdge[edge.getX()][edge.getY()]) {
            isSetVEdge[edge.getX()][edge.getY()] = true;
            vEdge[edge.getX()][edge.getY()].setBackground(currentPlayer.color);
            return true;
        } else {
            return false;
        }
    }

    public boolean onlyFillBoxIfPossible(){
        boolean boxUpdated = false;
        for (int i = 0; i < isSetBox.length; i++) {
            for (int j = 0; j < isSetBox[0].length; j++) {
                if ((isSetHEdge[i][j]) && (isSetVEdge[i][j]) && (isSetHEdge[i][j+1]) && (isSetVEdge[i+1][j])&&!isSetBox[i][j]) {
                    isSetBox[i][j]=true;
                    box[i][j].setBackground(currentPlayer.color);
                    possibleBoxCount--;
                    currentPlayer.setScore(currentPlayer.getScore() + 1);
                    updateLabels();
                    boxUpdated = true;
                }
            }
        }
        return boxUpdated;
    }

    /**
     * checks if a box is complete by comparing the filled edges around it
     *
     * @param x,y location of box
     * @return true if a box is detected as set
     */
    public boolean isBoxComplete(int x, int y) {
        if (isSetBox[x][y]) {
            return true;
        }
        if ((isSetHEdge[x][y]) && (isSetVEdge[x][y]) && (isSetHEdge[x][y+1]) && (isSetVEdge[x+1][y])) {
            isSetBox[x][y]=true;
            box[x][y].setBackground(currentPlayer.color);
            currentPlayer.setScore(currentPlayer.getScore() + 1);
            possibleBoxCount--;
            updateLabels();
            return true;
        }
        return false;


    }

    public void switchPlayers() {
        if (currentPlayer.isPlayer1) {
            currentPlayer = player2;
            if (player2.title.equals("Human")) {
                mouseEnabled = true;
            } else {
                mouseEnabled = false;
            }
        } else {
            currentPlayer = player1;
            if (player1.title.equals("Human")) {
                mouseEnabled = true;
            } else {
                mouseEnabled = false;
            }
        }
        updateLabels();
    }

    public int getPossibleBoxCount() {
        return this.possibleBoxCount;
    }

    private boolean processMove(Edge location) {

        if (fillEdge(location)) {
            if(!onlyFillBoxIfPossible())
            {
                switchPlayers();
            }
            return true;
        } else {
            return false;
        }
    }

    public int getDimension()
    {
        int res = -1;
        if(comboBoxIndex == 0)
        {
            res = 3;
        }
        else if(comboBoxIndex == 1)
        {
            res = 4;
        }
        else if(comboBoxIndex == 2)
        {
            res = 5;
        }
        else if(comboBoxIndex == 3)
        {
            res = 7;
        }
        else
        {
            res = 8;
        }
        return res;
    }
}
