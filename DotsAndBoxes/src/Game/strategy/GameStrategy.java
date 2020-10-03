package Game.strategy;

import Game.objects.Graph;
import Game.objects.Line;

public interface GameStrategy {
    /**
     * Each Strategy needs to receive a graph to be able to decide where to place an edge
     * @param graph
     * @return ##todo should we return edge or void, should we pass the graph or a copy of the graph ??
     */
    //should return edge
    public Line makeMove(Graph graph);


}
