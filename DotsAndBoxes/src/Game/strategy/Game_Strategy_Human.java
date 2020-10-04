package Game.strategy;

import UI.Edge;
import UI.GameBoard;

import java.awt.*;


public class Game_Strategy_Human extends GameStrategy {


    public Game_Strategy_Human(boolean isPlayer1, Color color) {
        super("Human", isPlayer1, color);
    }

    /**
     * Each Strategy needs to receive a graph to be able to decide where to place an edge
     *
     * @param gameBoard
     * @return Line selected from two dots by user
     */
    @Override
    public Edge makeMove(GameBoard gameBoard) {

        return null;
    }

}
