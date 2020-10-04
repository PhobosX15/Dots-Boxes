package Game.strategy;

import UI.Edge;
import UI.GameBoard;

import java.awt.*;


public class Game_Strategy_AI_2_example extends GameStrategy {

    public Game_Strategy_AI_2_example(boolean isPlayer1, Color color) {
        super( "Ai 2", isPlayer1, color);
    }

    /**
     * Each Strategy needs to receive a graph to be able to decide where to place an edge
     *
     * @param board
     * @return ##todo should we return edge or void, should we pass the graph or a copy of the graph ??
     */
    @Override
    public Edge makeMove(GameBoard board) {
        return null;
    }
}
