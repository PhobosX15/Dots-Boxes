package Game.objects;

/**
 * this class represents the box object in the game
 */
public class Box {

    private Dot bottomLeft;
    private Dot topLeft;
    private Dot bottomRight;
    private Dot topRight;


    public Box(Dot btLeft, Dot topLeft, Dot btRight, Dot topRight){
        this.bottomLeft = btLeft;
        this.topLeft = topLeft;
        this.bottomRight = btRight;
        this.topRight = topRight;
    }


    public boolean canBeCreated(){
        //TODO: complete this method
        return false;
    }

    public boolean isComplete(){
        return false;
    }
    @Override
    public String toString() {
        return "Box{" +
                "bottomLeft=" + bottomLeft +
                ", topLeft=" + topLeft +
                ", topRight=" + topRight +
                ", bottomRight=" + bottomRight +
                '}';
    }
}
