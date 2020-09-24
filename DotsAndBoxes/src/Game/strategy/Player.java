package Game.strategy;

/**
 * class that represents a player in the game
 */
public class Player{

    private boolean isTurn;
    private GameStrategy strategy;
    private int score;
    private boolean isWinner;

    /**
     * constructor
     * @param strategy type of ai or human player
     */
    public Player(GameStrategy strategy){
        this.score = 0;
        this.isTurn = false;
        this.isWinner = false;
        this.strategy = strategy;
    }

    /**
     * sets the player's turn to true or false
     * @param turn
     */
    public void setTurn(boolean turn){
        this.isTurn = turn;
    }

    public boolean getTurn(){return  this.isTurn; }

    /**
     * accesor method for the score of the player
     * @return the score
     */
    public int getScore(){
        return this.score;
    }

    /**
     * mutator method for the player's score
     * @param score is the new score
     */
    public void setScore(int score){
        this.score = score;
    }

    /**
     * method to add scores to the player's total score
     * @param score score to be added
     */
    public void addScore(int score){
        this.score += score;
    }

    private boolean isWinner(){
        return this.isWinner;
    }




}
