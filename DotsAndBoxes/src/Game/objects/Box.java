/*
package Game.objects;

import Game.strategy.Player;

*/
/**
 * this class represents the box object in the game
 *//*

public class Box {

    private Line left;
    private Line right;
    private Line top;
    private Line bottom;
    private Player owner;
    private Line[] lines ;


    public Box(Line left, Line right, Line top, Line bottom){
        this.left= left;
        this.right = right;
        this.top = top;
        this.bottom =bottom;
        this.owner = owner;

        lines = new Line[]{this.left,this.right,this.top,this.bottom};
    }


    public boolean isComplete() {

        for (int i = 0; i < lines.length; i++) {
            if (lines[i].isOccupied()) {
                return true;
            }
        }
        return false;
    }


    public Line[] getEdges(){
        return this.lines;
    }

    public void setOwner(Player owner){ this.owner = owner; }

    public Player getOwner(){ return  this.owner; }


    @Override
    public String toString() {
        return "Box{" +
                "left =" + left +
                ", right =" + right +
                ", top =" + top +
                ", bottom=" + bottom +
                '}';
    }
}
*/
