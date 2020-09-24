package Game;

import Game.objects.*;
import Game.strategy.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Game {

    private Player player1;
    private Player player2;
    private boolean isOver = false;
    private GameBoard board;
    private int boxCount;
    private Graph moves;
    private List<Line> lines;
    private Dot lowerBound, upperBound;


    public Game(Player player1, Player player2,int xSize, int ySize){

        lines = new ArrayList<>();
        if (board.getxSize() < 1 || board.getySize() < 1 ){
            throw new IllegalArgumentException("There should be at least 1 row/cols");
        }
        this.player1 = player1;
        this.player2 = player2;
        this.boxCount = 0;

        setFirstPlayer();

        board = new GameBoard(xSize,ySize);
        moves = new Graph(lines);
        lowerBound = new Dot(0,0);
        upperBound = new Dot(board.getxSize()-1, board.getySize()-1);
        boxCount = 0;


    }



   public boolean join(Dot d1, Dot d2, Player player){

        if(player.getTurn() == false){
           throw new IllegalArgumentException("it's other player's turn ! ");
       }
        if(d1.isWithin(lowerBound,upperBound) && d2.isWithin(lowerBound,upperBound) && d1.canBeJoined(d2)){

            if(d1.formsVerticalLine(d2)){
                moves.addLine(d1,d2,true);
            }

            moves.addLine(d1,d2,false);
            nextPlayer();
            //TODO: add box detection
            return true;
        }

        return false;
   }

    /**
     * this method chooses who is going to play first randomly
     */
   public void setFirstPlayer(){
       Random random = new Random();
       if(random.nextInt(2) == 1){
           player1.setTurn(true);
       }else{
           player2.setTurn(true);
       }
   }
   public void nextPlayer(){
       if(player1.getTurn() == true){
           player1.setTurn(false);
           player2.setTurn(true);
       }else{
           player2.setTurn(false);
           player1.setTurn(true);
       }

   }


   public String winner(){

       if(player1.getScore() > player2.getScore()){
           return "Player 1 wins ! ";
       }
       else if(player1.getScore() == player2.getScore()){
           return "Player 2 wins !";
       }
       return "Game has drawn!";
   }





}
