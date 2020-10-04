package Game;

import Game.objects.*;
import Game.strategy.Player;
import UI.GameBoard;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Game {


    private boolean isOver = false;
    private GameBoard board;


    public Game(){

    }

   public boolean join(){


        return false;
   }



    /**
     * this method chooses who is going to play first randomly
     */
   /*public void setFirstPlayer(){
       Random random = new Random();
       if(random.nextInt(2) == 1){
           board.
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

   }*/

   public boolean isOver(){
       if(board.getPossibleBoxCount() == 0){
           return true;
       }
       return false;
   }

   public String winner(){

       if(board.getPlayer1Score() > board.getPlayer2Score()){
           return "Player 1 wins ! ";
       }
       else if(board.getPlayer1Score() < board.getPlayer2Score()){
           return "Player 2 wins !";
       }
       return "Game has drawn!";
   }

}
