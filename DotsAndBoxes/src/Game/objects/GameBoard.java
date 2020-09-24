package Game.objects;

import Game.Game;
import Game.strategy.GameStrategy;
import Game.strategy.Human;
import Game.strategy.Player;

public class GameBoard {

  private int xSize;
  private int ySize;
  private Dot[][] board;


    public GameBoard(int xSize, int ySize){
        this.xSize = xSize;
        this.ySize = ySize;
        this.board = new Dot[xSize][ySize];

    }

    public Dot[][] getBoard(){
        return this.board;
    }

    public int getxSize(){
        return this.xSize;
    }

    public int getySize(){
        return this.ySize;
    }



}
