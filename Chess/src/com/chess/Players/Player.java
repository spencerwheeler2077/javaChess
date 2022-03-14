package com.chess.Players;

import com.chess.Move.Move;
import com.chess.Move.MoveStatus;
import com.chess.board.GameBoard;

public class Player {
    MoveStatus moveStatus = new MoveStatus();
    String type = "human";
    boolean color;

    public Player(boolean color){
        this.color = color;
    }

    public boolean getStatus() {
        return moveStatus.getStatus();
    }

    public void setMove(Move move){moveStatus.setMove(move);}
    public Move useMove(){return moveStatus.useMove();}
    public boolean isWhite(){return color;}
    public String getType() {return type;}
    public void generateMove(GameBoard gameBoard){}
}
