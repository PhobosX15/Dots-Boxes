package Game.strategy;

import Game.objects.Graph;
import Game.objects.Line;

public class Game_Strategy_AI_2_example extends GameStrategy {

    public Game_Strategy_AI_2_example(boolean isPlayer1) {
        super( "Ai 2", isPlayer1);
    }

    /**
     * Each Strategy needs to receive a graph to be able to decide where to place an edge
     *
     * @param graph
     * @return ##todo should we return edge or void, should we pass the graph or a copy of the graph ??
     */
    @Override
    public Line makeMove(Graph graph) {
        return null;
    }
}
