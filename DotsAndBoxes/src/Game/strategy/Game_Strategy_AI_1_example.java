package Game.strategy;

import UI.Edge;
import UI.GameBoard;

import java.awt.*;


public class Game_Strategy_AI_1_example extends GameStrategy {
    public Game_Strategy_AI_1_example(boolean isPlayer1 , Color color) {
        super("Ai 1",isPlayer1,color);
    }

    /**
     * Each Strategy needs to receive a graph to be able to decide where to place an edge
     * @param gameBoard
     * @return ##todo should we return edge or void, should we pass the graph or a copy of the graph ??
     */
    @Override
    public Edge makeMove(GameBoard gameBoard) {
        return null;
    }
}
