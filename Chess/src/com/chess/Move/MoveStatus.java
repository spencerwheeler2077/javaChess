package com.chess.Move;

public class MoveStatus {
    private boolean status = false;
    private Move move = null;

    public boolean getStatus(){
        return status;
    }

    public void setStatus(){
        status = true;
    }
    public void setMove(Move newMove){
        move = newMove;
        System.out.println(move);
        if (move != null){ status = true;}
        System.out.println("hello");
    }

    public Move useMove(){
        if(move != null){
            final Move temp = move;
            move = null;
            status = false;
            return temp;
        }
        return null;
    }


}
