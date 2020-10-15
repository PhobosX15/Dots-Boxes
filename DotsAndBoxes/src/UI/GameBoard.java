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
    private int score_Player1;
	private int score_Player2;
	private JLabel scoreLabel;

	private String bljh;


    /**
     * fields for the board generation
     */
    private final static int edgeAndDotWidth = 8;
    private final static int edgeLength = 40;
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
            if(isOver()){
                JLabel label = new JLabel(getWinner());
                label.setFont(new Font("Arial", Font.BOLD, 25));
                label.setText("Game is over. " + getWinner());
                label.setBounds(380, 75, 900, 85);
                frame.add(label);
                frame.repaint();

            }
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
        this.n = n;
        this.frame = frame;
        this.player1 = player1;
        score_Player1 = 0;
        this.player2 = player2;
        score_Player2 = 0;
        currentPlayer = this.player1;
        this.possibleBoxCount = (n - 1) * (n - 1);
        initializeUIGameBoard();
    }

    public void initializeUIGameBoard() {

        frame.getContentPane().removeAll();
        frame.revalidate();
        frame.repaint();


        frame.setContentPane(newBackgroundImagePanel());
        frame.add(setUpNewBoard());
        
        frame.add(initScoreLabel());

        frame.add(newBackToMenuButton());
        frame.getContentPane().validate();
        frame.setVisible(true);
        //manageGame();
    }
    
	private JLabel initScoreLabel() {
		scoreLabel = new JLabel("<html><p style=\"font-size:25px\">Score: </p>"
				+ "<p style=\"color:#00FFFF\">Player 1: " + score_Player1 + "<br/></p>"
				+ "<p style=\"color:green\">Player 2: " + score_Player2 + "<br/></p>" + "</html>",
				SwingConstants.CENTER);
		scoreLabel.setBounds(0, 0, 100, 100);
		return scoreLabel;
	}
	
	private void updateScore() {
		frame.remove(scoreLabel);
		frame.add(initScoreLabel());
		frame.revalidate();
		frame.repaint();
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
        label.setPreferredSize(new Dimension(edgeLength, edgeAndDotWidth));
        label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        label.setOpaque(true);
        label.addMouseListener(mouseListener);
        return label;
    }

    private JLabel newVerticalEdge() {
        JLabel label = new JLabel();
        label.setPreferredSize(new Dimension(edgeAndDotWidth, edgeLength));
        label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        label.setOpaque(true);
        label.addMouseListener(mouseListener);
        return label;
    }

    private JLabel newDot() {
        JLabel label = new JLabel();
        label.setPreferredSize(new Dimension(edgeAndDotWidth, edgeAndDotWidth));
        label.setBackground(Color.BLACK);
        label.setOpaque(true);
        return label;
    }

    private JLabel newBox() {
        JLabel label = new JLabel();
        label.setPreferredSize(new Dimension(edgeLength, edgeLength));
        label.setOpaque(true);
        return label;
    }

    private JPanel setUpNewBoard() {
        JPanel grid = new JPanel(new GridBagLayout());
        int sizeOfGridSide = (n * edgeAndDotWidth) + ((n - 1) * edgeLength);
        grid.setBounds((UIMainIntro.SCREEN_WIDTH / 2) - (sizeOfGridSide / 2), UIMainIntro.SCREEN_HEIGHT / 2 - sizeOfGridSide / 2, sizeOfGridSide, sizeOfGridSide);
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
                pane.add(newDot());
                for (int j = 0; j < (n - 1); j++) {
                    hEdge[j][i / 2] = newHorizontalEdge();
                    pane.add(hEdge[j][i / 2]);
                    pane.add(newDot());
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

    private JPanel newBackgroundImagePanel() {
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
                    if (currentPlayer.isPlayer1) {
        				score_Player1++;
        				updateScore();
        			} else {
        				score_Player2++;
        				updateScore();
        			}
                    boxUpdated = true;
                }
            }
        }
        return boxUpdated;
    }

    public boolean isOver(){
        if(possibleBoxCount == 0){

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
    }


    private boolean processMove(Edge location) {
        if (fillEdge(location)) {
            if(!onlyFillBoxIfPossible()) {
                switchPlayers();
            }
            return true;
        } else {
            return false;
        }
    }

    public String getWinner(){
        if(score_Player1 > score_Player2){
            return "Player 1 Wins!";
        }
        else if(score_Player2 > score_Player1){
            return "Player 2 Wins!";
        }
        return "It's a Tie!" ;
    }
}

