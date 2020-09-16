package Game;

/**
 * class that represent a dot component of the game, represented as a vertex of a graph
 */
public class Dot {
   private int posX, posY; //position of the dot on the gameboard

    /**
     * constructor
     * @param posX is the x-coordinate of the dot on the game grid
     * @param posY y-coordinate of the dot on the game grid
     */
    public Dot(int posX, int posY){
        this.posX = posX;
        this.posY = posY;
    }

    /**
     * accessor method
     * @return x position of the dot
     */
    public int getPosX(){
        return this.posX;
    }

    /**
     * accesor method
     * @return y-position of the dot
     */
    public int getPosY(){
        return this.posY;
    }



}
