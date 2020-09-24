package Game.objects;

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



    /**
     * to check if a dot is within the boundaries
      * @param lower is the lower dots
     * @param upper upper dot
     * @return
     */
    public boolean isWithin(Dot lower, Dot upper){

        return posX >= lower.posX && posX <= upper.posX
                && posY >= lower.posY && posY <= upper.posY;
    }


    /**
     * checks if a dot can be joined with other dot vertically
     * @param d2 other dot
     * @return true if they can be joined vertically
     */
    public boolean formsVerticalLine(Dot d2){
        if(this.posX == d2.posX){
            return true;
        }
        return false;
    }

    /**
     * checks if a dot can be joined with other dot horizontally
     * @param d2 other dot
     * @return true if they can be joined horizontally
     */
    public boolean formsHorizontalLine(Dot d2){
        if(this.posY == d2.posY){
            return true;
        }
        return false;
    }

    /**
     * checks if a dot can be joined with another dot
     * @param d2 is another dot
     * @return true if they can be joined
     */
    public boolean canBeJoined(Dot d2){
        if(this == d2){
            return false;
        } else if(formsHorizontalLine(d2) || formsVerticalLine(d2)){
            return true;
        }
        return false;
    }


}
