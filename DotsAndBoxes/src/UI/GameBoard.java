package UI;

import Game.objects.Edge;
import Game.strategy.GameStrategy;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.jar.JarEntry;

public class GameBoard {
    private JFrame frame;
    private  GameStrategy player1;
    private GameStrategy player2;
    private int player1Score, player2Score;
    private int n;
    private int bound = n;
    private int possibleBoxCount;


    /**
     * fields for the board generation
     */
    private final static int size = 8;
    private final static int dist = 40;
    private JLabel[][] hEdge, vEdge, box;
    private boolean[][] isSetHEdge, isSetVEdge,isSetBox;
    /**
     * fields required for playing the board
     */
    private MouseListener mouseListener = new MouseListener() {
        @Override
        public void mouseClicked(MouseEvent mouseEvent) {
            if(!mouseEnabled) return;
            processMove(getSource(mouseEvent.getSource()));
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
            Edge location = getSource(mouseEvent.getSource());
            int x = location.getX(), y = location.getY();
            if (location.isHorizontal()) {
                if (isSetHEdge[x][y]) return;
                hEdge[x][y].setBackground(Color.BLUE);//(turn == Board.RED) ? Color.RED : Color.BLUE);
            } else {
                if (isSetVEdge[x][y]) return;
                vEdge[x][y].setBackground(Color.BLUE);//(turn == Board.RED) ? Color.RED : Color.BLUE);
            }
        }

        @Override
        public void mouseExited(MouseEvent mouseEvent) {
            if(!mouseEnabled) return;
            Edge location = getSource(mouseEvent.getSource());
            int x=location.getX(), y=location.getY();
            if(location.isHorizontal()) {
                if(isSetHEdge[x][y]) return;
                hEdge[x][y].setBackground(Color.WHITE);
            }
            else {
                if(isSetVEdge[x][y]) return;
                vEdge[x][y].setBackground(Color.WHITE);
            }
        }
    };
    public static boolean mouseEnabled = true;

    public GameBoard(int n, JFrame frame, GameStrategy player1, GameStrategy player2) {
        this.n=n;
        this.frame = frame;
        this.player1=player1;
        this.player2=player2;
        initializeUIGameBoard();
        this.player1Score = 0;
        this.player2Score = 0;
        this.possibleBoxCount  = (n-1) * (n-1);
    }

    public void initializeUIGameBoard (){

        frame.getContentPane().removeAll();
        frame.revalidate();
        frame.repaint();


        frame.setContentPane(getBackgroundImagePanel());
        frame.add(setupBoard());
        frame.add(getBackToMenuButton());


        frame.setVisible(true);

        manageGame();
    }

    private void manageGame(){
//        while(!board.isComplete()) {
//            if(goBack) return;
//            if(player1.is) {
//                mouseEnabled = true;
//            }
//            else {
//                mouseEnabled = false;
//                processMove(solver.makeMove(board, turn));
//            }
//            try {
//                Thread.sleep(100);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
    }

    private JLabel getHorizontalEdge() {
        JLabel label = new JLabel();
        label.setPreferredSize(new Dimension(dist, size));
        label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        label.setOpaque(true);
        label.addMouseListener(mouseListener);
        return label;
    }

    private JLabel getVerticalEdge() {
        JLabel label = new JLabel();
        label.setPreferredSize(new Dimension(size, dist));
        label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        label.setOpaque(true);
        label.addMouseListener(mouseListener);
        return label;
    }

    private JLabel getDot() {
        JLabel label = new JLabel();
        label.setPreferredSize(new Dimension(size, size));
        label.setBackground(Color.BLACK);
        label.setOpaque(true);
        return label;
    }

    private JLabel getBox() {
        JLabel label = new JLabel();
        label.setPreferredSize(new Dimension(dist, dist));
        label.setOpaque(true);
        return label;
    }

    private JLabel getEmptyLabel(Dimension d) {
        JLabel label = new JLabel();
        label.setPreferredSize(d);
        return label;
    }

    private JPanel setupBoard(){
        JPanel grid = new JPanel(new GridBagLayout());
        int sizeOfGridSide = (n*size)+((n-1)*dist);
        grid.setBounds((UIMainIntro.SCREEN_WIDTH/2) - (sizeOfGridSide/2),UIMainIntro.SCREEN_HEIGHT/2 - sizeOfGridSide/2 ,sizeOfGridSide,sizeOfGridSide);
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx =0;
        constraints.gridy = 0;

        hEdge = new JLabel[n-1][n];
        isSetHEdge = new boolean[n-1][n];

        vEdge = new JLabel[n][n-1];
        isSetVEdge = new boolean[n][n-1];

        box = new JLabel[n-1][n-1];

        for(int i=0; i<(2*n-1); i++) {
            JPanel pane = new JPanel(new FlowLayout(FlowLayout.CENTER,0,0));
            if(i%2==0) {
                pane.add(getDot());
                for(int j=0; j<(n-1); j++) {
                    hEdge[j][i/2] = getHorizontalEdge();
                    pane.add(hEdge[j][i/2]);
                    pane.add(getDot());
                }
            }
            else {
                for(int j=0; j<(n-1); j++) {
                    vEdge[j][i/2] = getVerticalEdge();
                    pane.add(vEdge[j][i/2]);
                    box[j][i/2] = getBox();
                    pane.add(box[j][i/2]);
                }
                vEdge[n-1][i/2] = getVerticalEdge();
                pane.add(vEdge[n-1][i/2]);
            }
            ++constraints.gridy;
            grid.add(pane, constraints);
        }
        return grid;
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
    private JButton getBackToMenuButton(){
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
        return  exitButton;
    }
        private Edge getSource(Object object) {
            for(int i=0; i<(n-1); i++)
                for(int j=0; j<n; j++)
                    if(hEdge[i][j] == object)
                        return new Edge(i,j,true);
            for(int i=0; i<n; i++)
                for(int j=0; j<(n-1); j++)
                    if(vEdge[i][j] == object)
                        return new Edge(i,j,false);
            return new Edge();
        }

        public boolean isFilled(Edge edge){
        if(edge.isHorizontal()){
            return isSetHEdge[edge.getX()][edge.getY()];
        }
        return isSetVEdge[edge.getX()][edge.getY()];
        }

    /**
     * fills a horizontal edge, by setting isSetHedge to true, so we can visualize
     * @param edge
     * @param player1 /player 2
     */
        public void fillHorizontalEdge(Edge edge, boolean player1){
        //mark the edge within given coordinates as filled
            isSetHEdge[edge.getX()][edge.getY()] = true;
            //check if it forms a box
            if(isBoxComplete(edge,true)){
                if(player1){
                    player1Score += 10;
                }
                player2Score +=10;
            }
            //if the box is not complete switch players
            switchPlayers(player1);
        }

    /**
     * fills a vertical edge, by setting isSetVedge to true, so we can visualize
     * @param edge
     * @param player1 /player 2
     */
        public void fillVerticalEdge(Edge edge, boolean player1){
        isSetVEdge[edge.getX()][edge.getY()] = true;

        if(isBoxComplete(edge,false)){
                if(player1){
                    player1Score += 10;
                }
                player2Score +=10;
            }
            switchPlayers(player1);
        }

    /**
     * checks if a box is complete by comparing the filled edges with a newly filled edge
     * @param edge
     * @param horizontal /vertical
     * @return true if a box is detected.
     */
        public boolean isBoxComplete(Edge edge,boolean horizontal) {
            if (horizontal) {
                if (edge.getY() < bound - 1 && isSetVEdge[edge.getX()][edge.getY()] && isSetVEdge[edge.getX()+ 1][edge.getY()] && isSetHEdge[edge.getX()][edge.getY() + 1]) {
                    isSetBox[edge.getX()][edge.getY()] = true;
                    possibleBoxCount--;
                    return true;
                }
            } else {
                if (edge.getY() < bound - 1 && isSetHEdge[edge.getX()][edge.getY()] && isSetHEdge[edge.getX() + 1][edge.getY()] && isSetVEdge[edge.getX()][edge.getY() + 1]) {
                    isSetBox[edge.getX()][edge.getY()] = true;
                    possibleBoxCount--;
                    return true;

                }
            }
            return false;
        }

        public void switchPlayers(boolean player1){
        if(player1) player1 = false;
        }

        public int getPossibleBoxCount(){
        return this.possibleBoxCount;
        }

        public int getPlayer1Score(){
        return this.player1Score;
        }

        public int getPlayer2Score(){
        return this.player2Score;
        }








    private void processMove(Edge location) {
        int x=location.getX(), y=location.getY();
//        ArrayList<Point> ret;
//        if(location.isHorizontal()) {
//            if(isSetHEdge[x][y]) return;
//            ret = board.setHEdge(x,y,turn);
//            hEdge[x][y].setBackground(Color.BLACK);
//            isSetHEdge[x][y] = true;
//        }
//        else {
//            if(isSetVEdge[x][y]) return;
//            ret = board.setVEdge(x,y,turn);
//            vEdge[x][y].setBackground(Color.BLACK);
//            isSetVEdge[x][y] = true;
//        }
//
//        for(Point p : ret)
//            box[p.x][p.y].setBackground((turn == Board.RED) ? Color.RED : Color.BLUE);
//
//        redScoreLabel.setText(String.valueOf(board.getRedScore()));
//        blueScoreLabel.setText(String.valueOf(board.getBlueScore()));
//
//        if(board.isComplete()) {
//            int winner = board.getWinner();
//            if(winner == Board.RED) {
//                statusLabel.setText("Player-1 is the winner!");
//                statusLabel.setForeground(Color.RED);
//            }
//            else if(winner == Board.BLUE) {
//                statusLabel.setText("Player-2 is the winner!");
//                statusLabel.setForeground(Color.BLUE);
//            }
//            else {
//                statusLabel.setText("Game Tied!");
//                statusLabel.setForeground(Color.BLACK);
//            }
//        }
//
//        if(ret.isEmpty()) {
//            if(turn == Board.RED) {
//                turn = Board.BLUE;
//                solver = blueSolver;
//                statusLabel.setText("Player-2's Turn...");
//                statusLabel.setForeground(Color.BLUE);
//            }
//            else {
//                turn = Board.RED;
//                solver = redSolver;
//                statusLabel.setText("Player-1's Turn...");
//                statusLabel.setForeground(Color.RED);
//            }
//        }

    }
}
