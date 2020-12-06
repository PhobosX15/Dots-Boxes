package UI;
import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JLabel;

import Game.strategy.GameStrategy;
import UI.Edge;
import UI.GameBoard;

public class DummyBoard {

    public GameStrategy player1;
    public GameStrategy player2;
    public GameStrategy currentPlayer;
    public int n;
    public int possibleBoxCount;

    public int edgeAndDotWidth2;
    public int edgeLength2;
    public int scorePlayer1 = 0;
    public int scorePlayer2 = 0;
    public boolean ai1, ai2 = true;
    public int comboBoxIndex;
    Edge move;

    public boolean[][] isSetHEdge;
    public boolean[][] isSetVEdge;
    public boolean[][] isSetBox;
    public int[][] boxOwner;
    public int[][] HedgeOwner;
    public int[][] VedgeOwner;

    public ArrayList<DummyBoard> children;

    public DummyBoard(int n) {
        this.n=n;

        isSetHEdge = new boolean[n-1][n];
        HedgeOwner=new int [n-1][n];
        isSetVEdge = new boolean[n][n-1];
        VedgeOwner=new int [n][n-1];
        isSetBox = new boolean[n-1][n-1];
        boxOwner=new int[n-1][n-1];
        currentPlayer = player1;
        for (int i = 0; i < isSetHEdge.length; i++) {
            for (int j = 0; j < isSetHEdge[0].length; j++) {
                isSetHEdge[i][j] = false;
                HedgeOwner[i][j] = -1;
            }
        }
        for (int i = 0; i < isSetVEdge.length; i++) {
            for (int j = 0; j < isSetVEdge[0].length; j++) {
                isSetVEdge[i][j] = false;
                VedgeOwner[i][j] = -1;
            }
        }
        for (int i = 0; i < isSetBox.length; i++) {
            for (int j = 0; j < isSetBox[0].length; j++) {
                isSetBox[i][j] = false;
                boxOwner[i][j]= -1;
            }
        }
        this.possibleBoxCount = (n - 1) * (n - 1);

    }

    public void copyGameBoard(DummyBoard realboard) {


        for (int i = 0; i < isSetHEdge.length; i++) {
            for (int j = 0; j < isSetHEdge[0].length; j++) {
                this.isSetHEdge[i][j] = realboard.isSetHEdge[i][j];
                this.HedgeOwner[i][j] = realboard.HedgeOwner[i][j];
            }
        }

        for (int i = 0; i < isSetVEdge.length; i++) {
            for (int j = 0; j < isSetVEdge[0].length; j++) {
                this.isSetVEdge[i][j] = realboard.isSetVEdge[i][j];
                this.VedgeOwner[i][j] = realboard.VedgeOwner[i][j];
            }
        }

        for (int i = 0; i < isSetBox.length; i++) {
            for (int j = 0; j < isSetBox[0].length; j++) {

                this.isSetBox[i][j] = realboard.isSetBox[i][j];
            }
        }

        for (int i = 0; i < boxOwner.length; i++) {
            for (int j = 0; j < boxOwner[0].length; j++) {

                this.boxOwner[i][j] = realboard.boxOwner[i][j];
            }
        }

        this.scorePlayer1 = realboard.scorePlayer1;
        this.scorePlayer2 = realboard.scorePlayer2;
        this.currentPlayer=realboard.getCurrentPlayer();
        this.player1=realboard.player1;
        this.player2=realboard.player2;

//	        clonedBoard.currentPlayer=currentPlayer;
//	        clonedBoard.backgroundLabel=backgroundLabel;
//	        clonedBoard.ai1=ai1;
//	        clonedBoard.ai2=ai2;
//	        clonedBoard.player1=player1;
//	        clonedBoard.player2=player2;
//	        clonedBoard.player1scoreLabel=player1scoreLabel;
//	        clonedBoard.player2scoreLabel=player2scoreLabel;
//	        clonedBoard.messageLabel=messageLabel;



    }

    public void  processPlayerMove(Edge location) {
        fillEdge(location);
        if (!onlyFillBoxIfPossible()) {
            switchPlayers();
        }

    }

    public void processAIMove(Edge location) {
        fillEdge(location);
        if (!onlyFillBoxIfPossible()) {
            switchPlayers();
        }else {
            move=player2.makeMove(this);
        }

    }

    public void processAIMove2(Edge location) {
        fillEdge(location);
        if (!onlyFillBoxIfPossible()) {
            if (currentPlayer.isPlayer1) {
                currentPlayer=player2;
            }else {
                currentPlayer=player1;
            }

        }else {
            //move=player2.makeMove(this);
        }

    }

    public boolean processMove(Edge location) {

        if (fillEdge(location)) {
            if (!onlyFillBoxIfPossible()) {
                //switchPlayers();
                //currentPlayer.makeMove(this);
            }else {
                move=null;
                currentPlayer.makeMove(this);
            }
            return true;
        } else {
            return false;
        }
    }

    public boolean fillEdge(Edge edge) {
        //mark the edge within given coordinates as filled

        int playernumber=currentPlayer.isPlayer1? 1:2;
        if (edge.isHorizontal() && !isSetHEdge[edge.getX()][edge.getY()]) {
            isSetHEdge[edge.getX()][edge.getY()] = true;
            HedgeOwner[edge.getX()][edge.getY()] =playernumber;
            return true;
        } else if (!edge.isHorizontal() && !isSetVEdge[edge.getX()][edge.getY()]) {
            isSetVEdge[edge.getX()][edge.getY()] = true;
            VedgeOwner[edge.getX()][edge.getY()] =playernumber;
            return true;
        } else {
            return false;
        }
    }

    public boolean onlyFillBoxIfPossible() {
        boolean boxUpdated = false;
        for (int i = 0; i < isSetBox.length; i++) {
            for (int j = 0; j < isSetBox[0].length; j++) {
                if ((isSetHEdge[i][j]) && (isSetVEdge[i][j]) && (isSetHEdge[i][j + 1]) && (isSetVEdge[i + 1][j]) && !isSetBox[i][j]) {
                    isSetBox[i][j] = true;
                    possibleBoxCount--;
                    currentPlayer.setScore(currentPlayer.getScore() + 1);
                    if (currentPlayer.isPlayer1) {
                        boxOwner[i][j]=1;
                    }else {
                        boxOwner[i][j]=2;
                    }

                    boxUpdated = true;
                }
            }
        }
        return boxUpdated;

    }

    public Edge switchPlayers() {
        move=null;
        if (currentPlayer.isPlayer1) {
            currentPlayer = player2;
            if (player2.title.equals("Human")) {
            } else if(player2.title.equals("Minimax")){

                move =  player2.makeMove(this);
            }else if(player2.title.equals("Greedy")){

                move = player2.makeMove(this);
                //processMove(move);
                //currentPlayer=player1;
            }
            else {

            }
        } else {
            currentPlayer = player1;
            if (player1.title.equals("Human")) {

            } else if(player2.title.equals("Minimax")){

                move =  player2.makeMove(this);
            }else if(player2.title.equals("Greedy")){
                System.out.println("Random AIs turn");

                move =   player2.makeMove(this);
                //processMove(move);
                // currentPlayer=player2;
            } else {

            }
        }

        return move;

    }

    public int getScore(boolean isPlayer1) {
        if (isPlayer1) {
            return player1.getScore();
        }
        return player2.getScore();
    }

    public ArrayList<Edge> getMoves() {
        ArrayList<Edge> moves = new ArrayList<Edge>();

        for (int i = 0; i < isSetHEdge.length; i++) {
            for (int j = 0; j < isSetHEdge[0].length; j++) {
                if (isSetHEdge[i][j] == false) {
                    moves.add(new Edge(i, j, true));
                }
            }
        }

        for (int i = 0; i < isSetVEdge.length; i++) {
            for (int j = 0; j < isSetVEdge[0].length; j++) {
                if (isSetVEdge[i][j] == false) {
                    moves.add(new Edge(i, j, false));
                }
            }
        }

        return moves;

    }

    public DummyBoard updateBoard(Edge edge) {
        DummyBoard updatedBoard=null;
//	        DummyBoard updatedBoard = copyGameBoard();
//	        updatedBoard.
//	        updatedBoard.processMove(edge);


        return updatedBoard;

    }

//	    public DummyBoard copyGameBoard() {
//	    	DummyBoard clonedBoard = new DummyBoard(n);
//
//	        for (int i = 0; i < hEdge.length; i++) {
//	            for (int j = 0; j < hEdge[0].length; j++) {
//	                clonedBoard.hEdge[i][j] = hEdge[i][j];
//	                clonedBoard.isSetHEdge[i][j] = isSetHEdge[i][j];
//	            }
//	        }
//
//	        for (int i = 0; i < vEdge.length; i++) {
//	            for (int j = 0; j < vEdge[0].length; j++) {
//	                clonedBoard.vEdge[i][j] = vEdge[i][j];
//	                clonedBoard.isSetVEdge[i][j] = isSetVEdge[i][j];
//	            }
//	        }
//
//	        for (int i = 0; i < box.length; i++) {
//	            for (int j = 0; j < box[0].length; j++) {
//	                clonedBoard.box[i][j] = box[i][j];
//	                clonedBoard.isSetBox[i][j] = isSetBox[i][j];
//	            }
//	        }
//
//	        clonedBoard.scorePlayer1 = scorePlayer1;
//	        clonedBoard.scorePlayer2 = scorePlayer2;
//	        clonedBoard.currentPlayer=currentPlayer;
//	        clonedBoard.backgroundLabel=backgroundLabel;
//	        clonedBoard.ai1=ai1;
//	        clonedBoard.ai2=ai2;
//	        clonedBoard.player1=player1;
//	        clonedBoard.player2=player2;
//	        clonedBoard.player1scoreLabel=player1scoreLabel;
//	        clonedBoard.player2scoreLabel=player2scoreLabel;
//	        clonedBoard.messageLabel=messageLabel;
//
//
//	        return clonedBoard;
//	    }

    public GameStrategy getCurrentPlayer(){
        return this.currentPlayer;
    }

    public int getNumberOfBoxes(int missingLine) {
        int numBox = 0;
        for (int i = 0; i < isSetBox.length; i++) {
            for (int j = 0; j < isSetBox[0].length; j++) {
                if (getMissingLines(i, j) == missingLine) {
                    numBox++;
                }
            }
        }
        return numBox;
    }

    public int getMissingLines(int x, int y) {
        int missingLines = 0;
        missingLines = 4 - countEdges(x, y);

        return missingLines;
    }


    private int countEdges(int x, int y) {
        int numberEdges = 0;
        if (isSetHEdge[x][y]) {
            numberEdges++;
        }
        if (isSetHEdge[x][y + 1]) {
            numberEdges++;
        }
        if (isSetVEdge[x][y]) {
            numberEdges++;
        }
        if (isSetVEdge[x + 1][y]) {
            numberEdges++;
        }
        return numberEdges;
    }

    public ArrayList<DummyBoard> getChildren(){
        if(this.children == null){
            computeChildren();
        }
        return this.children;
    }
    public void computeChildren(){
        ArrayList<DummyBoard> output = new ArrayList<>();

        for(Edge edge : this.getMoves()){
            DummyBoard child = computeAChild(edge);
            output.add(child);
        }
        this.children = output;
    }

    public DummyBoard computeAChild(Edge edge) {

        DummyBoard child = new DummyBoard(this.n);
        child.copyGameBoard(this);
        child.updateBoard(edge);

        return child;
    }

    public int getDimension() {
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

    public static GameStrategy switchTurn(GameStrategy currentPlayer){
        if(currentPlayer.isPlayer1){
            currentPlayer.isPlayer1 = false;
            return currentPlayer;//set player 2 true
        }else{
            currentPlayer.isPlayer1 = true;
            return currentPlayer;
        }
    }

    public int calculateScorePlayer1() {
        int score=0;
        for (int i = 0; i < boxOwner.length; i++) {
            for (int j = 0; j < boxOwner[0].length; j++) {

                if (boxOwner[i][j]==1) {
                    score++;
                }else if (boxOwner[i][j]==2) {
                    //scoreplayer2++;
                }
            }
        }
        return score;
    }

    public int calculateScorePlayer2() {
        int score=0;
        for (int i = 0; i < boxOwner.length; i++) {
            for (int j = 0; j < boxOwner[0].length; j++) {

                if (boxOwner[i][j]==1) {
                    //scoreplayer1++;
                }else if (boxOwner[i][j]==2) {
                    score++;
                }
            }
        }
        return score;
    }
}

