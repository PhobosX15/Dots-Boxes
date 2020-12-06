package Game.strategy;

import UI.DummyBoard;
import UI.Edge;
import UI.GameBoard;

import java.awt.*;
import java.util.ArrayList;

public class AlphaBeta extends GameStrategy{

    public AlphaBeta(String title, boolean isPlayer1, Color color) {
        super(title, isPlayer1, color);
    }

    public WeightedEdge initialize(DummyBoard board,int depth, int alpha, int beta){
        ArrayList<Edge> moves = board.getMoves();
        if(moves.size() == 0){
            return new WeightedEdge(null, evaluationFunction(board));
        }
        ArrayList<DummyBoard> children = board.getChildren();
		return null;

    }


}
