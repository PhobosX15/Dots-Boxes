package Game.strategy;

import UI.Edge;
import UI.GameBoard;

import java.awt.*;

public abstract class GameStrategy {
    public Color color;
    public boolean isPlayer1;
    public String title;
    int score = 0;

    /**
     * Each Strategy needs to receive a graph to be able to decide where to place an edge
     *
     *
     * @return ##todo should we return edge or void, should we pass the graph or a copy of the graph ??
     */
    //should return edge
    public Edge makeMove(GameBoard board) {
        return null;
    }


    public GameStrategy(String title, boolean isPlayer1, Color color){
        this.title =title;
        this.isPlayer1 = isPlayer1;
        this.color = color;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GameStrategy that = (GameStrategy) o;
        return isPlayer1 == that.isPlayer1 &&
                title.equals(that.title);
    }
    public void setScore(int i){
        this.score = i;
    }
    public int getScore(){
        return score;
    }

}
