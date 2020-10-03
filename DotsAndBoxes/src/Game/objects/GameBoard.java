package Game.objects;

import Game.Game;
import Game.strategy.*;
import java.util.List;

public class GameBoard {

  private int xSize;
  private int ySize;
  private boolean[][] board;
  private Box[] possibleBoxes;
  public int possibleBoxCount;


    public GameBoard(int xSize, int ySize){
        this.xSize = xSize;
        this.ySize = ySize;
        this.board = new boolean[xSize][ySize];

        possibleBoxes = new Box[xSize];

        initializeBoard();


    }

    /**
     * the part to be hardcoded
     */
    public void initializeBoard(){

        if(xSize == 3 && ySize == 3){
            //possibleBoxCount = 4;
            //initialize the boxes here (provide the coordinates in Dot's c
            // constructor)
            //Box box1 = new Box(new Line(new Dot()))
        }
        else if(xSize == 5 && ySize == 5){
            //possibleBoxCount =
            //TODO: init the boxes
        }
        else{
            //init the boxes for 7*7 grid

        }
    }

    public Box[] getPossibleBoxes(){
        return this.possibleBoxes;
    }

    public boolean[][] getBoard(){
        return this.board;
    }

    public int getxSize(){
        return this.xSize;
    }

    public int getySize(){
        return this.ySize;
    }



}
