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

    /**
     * method to join two dots with a line
     * @param d1 source
     * @param d2 dest
     * @param player player
     * @return true if the line is joined
     */
   public boolean join(Dot d1, Dot d2, Player player){

        if(player.getTurn() == false){
           throw new IllegalArgumentException("it's other player's turn ! ");
       }
        if(d1.isWithin(lowerBound,upperBound) && d2.isWithin(lowerBound,upperBound) && d1.canBeJoined(d2)){
               d1.setOccupied(true);
               d2.setOccupied(true);
               if(!checkBox()){
                   nextPlayer();
               }
               player.addScore(10);
               board.possibleBoxCount--;
            return true;
        }

        return false;
   }

   public boolean checkBox(){
        Box[] boxes = board.getPossibleBoxes();
        for(int i = 0; i < board.getPossibleBoxes().length; i++){
            if(boxes[i].isComplete() == true){
                return true;
            }
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

   public boolean isOver(){
       if(board.possibleBoxCount == 0){
           return true;
       }
       return false;
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


   /*public boolean Boxdetection( Dot d1, Graph moves)
   {
       if(d1.getPosX()==0 && d1.getPosY()<board.getySize())
       {
           List<Dot> adj= moves.getAdjacents(d1);
           for( Dot i : adj)
           {

           }
           Graph subGraph  = new Graph(moves.getAdjacents(d1));
       }
   }*/



}
