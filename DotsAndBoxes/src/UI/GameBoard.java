package UI;

import Game.strategy.GameStrategy;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Arrays;

public class GameBoard {
    public DummyBoard boardstate;
    public JFrame frame;
    public GameStrategy player1;
    public GameStrategy player2;
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
    public int scorePlayer1 = 0;
    public int scorePlayer2 = 0;
    private boolean ai1, ai2 = true;


    private GameBoard currentBoard;
    private ArrayList<GameBoard> children;
    Edge move;

    /**
     * fields for the board generation
     */
    private JLabel[][] hEdge, vEdge, box;


    public static boolean mouseEnabled = true;
    /**
     * fields required for playing the board
     */
    private MouseListener mouseListener = new MouseListener() {
        @Override
        public void mouseClicked(MouseEvent mouseEvent) {
            if (!mouseEnabled) return;
            //processMove(getSourceEdge(mouseEvent.getSource()));
            boardstate.processPlayerMove(getSourceEdge(mouseEvent.getSource()));
            updateLabels();


            while (boardstate.move!=null && boardstate.getMoves().size()>0) {
                boardstate.processAIMove(boardstate.move);
            }

            updateLabels();




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

                if (boardstate.isSetHEdge[x][y]) return;
                hEdge[x][y].setCursor(new Cursor(Cursor.HAND_CURSOR));
                hEdge[x][y].setBackground(boardstate.currentPlayer.color);
            } else {
                if (boardstate.isSetVEdge[x][y]) return;
                vEdge[x][y].setCursor(new Cursor(Cursor.HAND_CURSOR));
                vEdge[x][y].setBackground(boardstate.currentPlayer.color);
            }
        }

        @Override
        public void mouseExited(MouseEvent mouseEvent) {
            if (!mouseEnabled) return;
            Edge location = getSourceEdge(mouseEvent.getSource());
            int x = location.getX(), y = location.getY();
            if (location.isHorizontal()) {
                if (boardstate.isSetHEdge[x][y]) return;
                hEdge[x][y].setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                hEdge[x][y].setBackground(Color.WHITE);
            } else {
                if (boardstate.isSetVEdge[x][y]) return;
                vEdge[x][y].setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                vEdge[x][y].setBackground(Color.WHITE);
            }
        }
    };


    public GameBoard(int n, JFrame frame, GameStrategy player1, GameStrategy player2) {
        boardstate=new DummyBoard(getDimension(n));
        boardstate.comboBoxIndex = n;
        boardstate.n = getDimension(n);
        this.comboBoxIndex=n;
        this.n=boardstate.n ;

        this.frame = frame;
        boardstate.player1 = player1;
        boardstate.player2 = player2;
        this.player1=player1;
        this.player2=player2;
        this.currentPlayer=player1;
        boardstate.player1.setScore(scorePlayer1);
        boardstate.player2.setScore(scorePlayer2);
        boardstate.currentPlayer = boardstate.player1;
        this.possibleBoxCount = (this.n - 1) * (this.n - 1);
        if(!player1.title.equals("Human")){
            mouseEnabled = false;
            boardstate.player1.makeMove(boardstate);
        }
        initializeUIGameBoard();



    }

    public void GameBoard2(int n) {
        this.comboBoxIndex = n;
        this.n = getDimension(n);
        n = 8;
        hEdge = new JLabel[n - 1][n];
        vEdge = new JLabel[n][n - 1];
        box = new JLabel[n - 1][n - 1];

        currentPlayer = player1;
        for (int i = 0; i < boardstate.isSetHEdge.length; i++) {
            for (int j = 0; j < boardstate.isSetHEdge[0].length; j++) {
                boardstate.isSetHEdge[i][j] = false;
            }
        }
        for (int i = 0; i < boardstate.isSetVEdge.length; i++) {
            for (int j = 0; j < boardstate.isSetVEdge[0].length; j++) {
                boardstate.isSetVEdge[i][j] = false;
            }
        }
        for (int i = 0; i < boardstate.isSetBox.length; i++) {
            for (int j = 0; j < boardstate.isSetBox[0].length; j++) {
                boardstate.isSetBox[i][j] = false;
            }
        }
        this.possibleBoxCount = (n - 1) * (n - 1);

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
        if (boardstate.currentPlayer.isPlayer1) {
            messageLabel.setForeground(boardstate.player1.color);
            messageLabel.setText("Player 1's turn.");
        } else {
            messageLabel.setForeground(boardstate.player2.color);
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
        if (player1.title.equals("Human")) {
            player1GameStrategyLabel.setText("Human");
        } else {
            player1GameStrategyLabel.setText("AI");
        }
        frame.getContentPane().add(player1GameStrategyLabel);
        player1GameStrategyLabel.setBounds(820, 70, 89, 24);

        player2GameStrategyLabel.setFont(new java.awt.Font("Arial", 0, 20));
        if (player2.title.equals("Human")) {
            player2GameStrategyLabel.setText("Human");
        } else {
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
        //manageGame();
    }

    public void updateLabels() {
        //player1scoreLabel.setText(Integer.toString(boardstate.player1.getScore()));
        //player2scoreLabel.setText(Integer.toString(boardstate.player2.getScore()));
        int scoreplayer1 = 0;
        int scoreplayer2 = 0;
        if (boardstate.currentPlayer.isPlayer1) {
            messageLabel.setForeground(boardstate.player1.color);
            messageLabel.setText("Player 1's turn.");
        } else {
            messageLabel.setForeground(boardstate.player2.color);
            messageLabel.setText("Player 2's turn.");
        }

        for (int i = 0; i < boardstate.boxOwner.length; i++) {
            for (int j = 0; j < boardstate.boxOwner[0].length; j++) {

                if (boardstate.boxOwner[i][j]==1) {
                    box[i][j].setBackground(boardstate.player1.color);
                    scoreplayer1++;
                }else if (boardstate.boxOwner[i][j]==2) {
                    box[i][j].setBackground(boardstate.player2.color);
                    scoreplayer2++;
                }
            }
        }

        player1scoreLabel.setText(Integer.toString(scoreplayer1));
        player2scoreLabel.setText(Integer.toString(scoreplayer2));

        for (int i = 0; i < boardstate.isSetHEdge.length; i++) {
            for (int j = 0; j < boardstate.isSetHEdge[0].length; j++) {
                if (boardstate.HedgeOwner[i][j] == 1) {
                    hEdge[i][j].setBackground(player1.color);
                }else if (boardstate.HedgeOwner[i][j] == 2) {
                    hEdge[i][j].setBackground(player2.color);
                }
            }
        }

        for (int i = 0; i < boardstate.isSetVEdge.length; i++) {
            for (int j = 0; j < boardstate.isSetVEdge[0].length; j++) {
                if (boardstate.VedgeOwner[i][j] == 1) {
                    vEdge[i][j].setBackground(player1.color);
                }else if (boardstate.VedgeOwner[i][j] == 2) {
                    vEdge[i][j].setBackground(player2.color);
                }
            }
        }




    }

    public void manageGame() {
        while (possibleBoxCount > 0) {
            if (!ai1 && !ai2) {

            } else {
                processMove(move);
            }

        }

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

    private JLabel drawDots() {
        JLabel dot = new JLabel();
        ImageIcon ico = new ImageIcon(UIMainIntro.class.getResource("../images/dotBlack.png"));
        Image image = ico.getImage();
        Image newimg = image.getScaledInstance(edgeAndDotWidth2, edgeAndDotWidth2, java.awt.Image.SCALE_SMOOTH);
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

    private JPanel drawNewBoard() {
        JPanel grid = new JPanel(new GridBagLayout());
        grid.setOpaque(false);
        grid.setBackground(Color.WHITE);
        edgeLength2 = 720 / (n + 2);
        edgeAndDotWidth2 = edgeLength2 / 10;
        grid.setBounds(70, 0, 720, 720);

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        n=boardstate.n;
        hEdge = new JLabel[n - 1][n];
//        isSetBox = new boolean[n - 1][n - 1];
//        isSetHEdge = new boolean[n - 1][n];

        vEdge = new JLabel[n][n - 1];
//        isSetVEdge = new boolean[n][n - 1];

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
            return boardstate.isSetHEdge[edge.getX()][edge.getY()];
        }
        return boardstate.isSetVEdge[edge.getX()][edge.getY()];
    }

    /**
     * fills a an edge if possible
     *
     * @param edge
     * @return false if edge is already filled
     */
    public boolean fillEdge(Edge edge) {
        //mark the edge within given coordinates as filled
        if (edge.isHorizontal() && !boardstate.isSetHEdge[edge.getX()][edge.getY()]) {
            boardstate.isSetHEdge[edge.getX()][edge.getY()] = true;
            hEdge[edge.getX()][edge.getY()].setBackground(currentPlayer.color);
            return true;
        } else if (!edge.isHorizontal() && !boardstate.isSetVEdge[edge.getX()][edge.getY()]) {
            boardstate.isSetVEdge[edge.getX()][edge.getY()] = true;
            System.out.println(edge.getY());
            vEdge[edge.getX()][edge.getY()].setBackground(currentPlayer.color);
            return true;
        } else {
            return false;
        }
    }

    public boolean onlyFillBoxIfPossible() {
        boolean boxUpdated = false;
        for (int i = 0; i < boardstate.isSetBox.length; i++) {
            for (int j = 0; j < boardstate.isSetBox[0].length; j++) {
                if ((boardstate.isSetHEdge[i][j]) && (boardstate.isSetVEdge[i][j]) && (boardstate.isSetHEdge[i][j + 1]) && (boardstate.isSetVEdge[i + 1][j]) && !boardstate.isSetBox[i][j]) {
                    boardstate.isSetBox[i][j] = true;
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
        if (boardstate.isSetBox[x][y]) {
            return true;
        }
        if ((boardstate.isSetHEdge[x][y]) && (boardstate.isSetVEdge[x][y]) && (boardstate.isSetHEdge[x][y + 1]) && (boardstate.isSetVEdge[x + 1][y])) {
            boardstate.isSetBox[x][y] = true;
            box[x][y].setBackground(currentPlayer.color);
            currentPlayer.setScore(currentPlayer.getScore() + 1);
            possibleBoxCount--;
            updateLabels();
            return true;
        }
        return false;


    }

    public void switchPlayers() {
        boardstate.switchPlayers();
        updateLabels();
    }

    public static GameStrategy switchTurn(GameStrategy currentPlayer){
        if(currentPlayer.isPlayer1){
            currentPlayer.isPlayer1 = false;
            return currentPlayer;//set player 2 true
        }else{
            currentPlayer.isPlayer1 = true;
            return currentPlayer;
        }
    }

    public int getPossibleBoxCount() {
        return this.possibleBoxCount;
    }

    private boolean processMove(Edge location) {

        return boardstate.processMove(location);

    }


    /**
     * @return available moves
     */
    public ArrayList<Edge> getMoves() {

        ArrayList<Edge> moves = new ArrayList<Edge>();
        for(int i = 0; i < (n-1); i++)
        {
            for(int j = 0; j<n; j++)
            {
                if(hEdge[i][j].getBackground() == Color.WHITE)
                {
                    moves.add(new Edge(i,j,true));
                }
            }
        }
        for(int i = 0; i < n; i++)
        {
            for(int j = 0; j < (n-1); j++)
            {
                if(vEdge[i][j].getBackground() == Color.WHITE)
                {
                    moves.add(new Edge(i,j,false));
                }
            }
        }
        return moves;
    }


    public int getDimension(int comboBoxIndex) {
        int res = -1;
        if (comboBoxIndex == 0) {
            res = 3;
        } else if (comboBoxIndex == 1) {
            res = 4;
        } else if (comboBoxIndex == 2) {
            res = 5;
        } else if (comboBoxIndex == 3) {
            res = 7;
        } else {
            res = 8;
        }
        return res;
    }


    public int getScore(boolean isPlayer1) {
        if (isPlayer1) {
            return player1.getScore();
        }
        return player2.getScore();
    }



    /**
     * finds the neew added edge if there is any between parent and child state
     * @param parentEdges
     * @param childEdges
     * @return
     */
    public Edge findNewLine(ArrayList<Edge> parentEdges, ArrayList<Edge> childEdges){

        Edge arbitraryEdge = null;

        for(Edge edge : parentEdges){
            if(childEdges.contains(edge) == false){
                return  edge;
            }
        }
        if(arbitraryEdge == null){
            return childEdges.get(0);
        }

        return arbitraryEdge;
    }

    public GameStrategy getCurrentPlayer(){
        return this.currentPlayer;
    }






}

