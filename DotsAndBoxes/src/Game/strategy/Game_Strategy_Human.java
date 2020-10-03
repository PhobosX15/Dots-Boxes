package Game.strategy;

import Game.objects.*;
import UI.UIGameBoard;

public class Game_Strategy_Human extends GameStrategy {


    public Game_Strategy_Human(boolean isPlayer1) {
        super("Human",isPlayer1);
    }

    /**
     * methods that check if a move is valid
     * game - combine everything
     * scoring (bonus scores)
     * make distinction for the vertexes on the edge
     *
     */

    public void makeMove(){
        UIGameBoard.mouseEnabled=true;
        while(isTurnToPlay){
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        UIGameBoard.mouseEnabled=false;

    }

    /**
     * Each Strategy needs to receive a graph to be able to decide where to place an edge
     * @param graph
     * @return Line selected from two dots by user
     */
    @Override
    public Line makeMove(Graph graph) {
        addListeners();
        return null;
    }

    /**
     * Mehtod adds listeners to dots,
     */
    private void addListeners() {
        //##todo keep listening until we have two dots that form a
        //should we add buttons to the dots here each move and have the buttons create store the dots and then a listener
        //waiting until two acceptable dots selected return the line
        //if two dots not acceptable clear start again
    }
}
