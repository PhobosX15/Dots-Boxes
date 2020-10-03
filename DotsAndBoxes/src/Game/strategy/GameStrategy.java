package Game.strategy;

import Game.objects.Graph;
import Game.objects.Line;

import java.util.Objects;

public abstract class GameStrategy {
    public boolean isTurnToPlay = false;
    public boolean isPlayer1;
    public String title;

    /**
     * Each Strategy needs to receive a graph to be able to decide where to place an edge
     *
     * @param graph
     * @return ##todo should we return edge or void, should we pass the graph or a copy of the graph ??
     */
    //should return edge
    public Line makeMove(Graph graph) {
        return null;
    }


    public GameStrategy(String title, boolean isPlayer1){
        this.title =title;
        this.isPlayer1 = isPlayer1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GameStrategy that = (GameStrategy) o;
        return isPlayer1 == that.isPlayer1 &&
                title.equals(that.title);
    }

}
