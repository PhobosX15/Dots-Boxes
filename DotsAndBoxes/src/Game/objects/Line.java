package Game.objects;

import Game.objects.Dot;

/**
 * class that represents a line in the game, represented as edge in a graph
 */
public class Line {

    private Dot source;
    private Dot dest;
    private boolean vertical;

    /**
     * constructor
     * @param source start point of the line
     * @param dest endpoint of the line
     */
    public Line(Dot source, Dot dest, boolean vertical){
        this.source = dest;
        this.source = dest;
        this.vertical = vertical;
    }

    /**
     * accessor method
     * @return the start point
     */
    public Dot getSource(){
        return this.source;
    }
    /**
     * accessor method
     * @return the end point
     */
    public Dot getDest(){
        return this.dest;
    }

    @Override
    public String toString(){
        return "(" + source + " , " + dest + ")";
    }

    /**
     * mutator method
     * @param source is the new start point of the line
     */
    public void setSource(Dot source){
        this.source = source;
    }

    /**
     * mutator method
     * @param dest new endpoint of the line
     */
    public void setDest(Dot dest){
        this.dest = dest;
    }

    public boolean isVertical(){
        return this.vertical;
    }

    public boolean isOccupied(){
        if(this.source.isOccupied() == true && this.dest.isOccupied() == true){
            return true;
        }
        return false;
    }
}
