package com.chess.board;

import java.util.ArrayList;

public class WPawn extends Pieces{
        private boolean hasMoved;
        private boolean canEP;
    public WPawn(GameBoard gameBoard, int location) {
        super(true, gameBoard, location);
        this.value = 100;
        this.type = "pawn";
        this.hasMoved = false;
        this.canEP = false;
        this.imagePath = "Art/WP.gif";
    }

    @Override
    ArrayList<Integer> returnPossibleMoves() {
        ArrayList<Integer> list = new ArrayList<>();
        if(location>7) {
            Pieces nextSpace = gameBoard.getIndex(location - 8);
            if (nextSpace == null){
                list.add(location -8);
            }
            if(!hasMoved){
                nextSpace = gameBoard.getIndex(location - 16);
                if(nextSpace == null){
                    list.add(location - 16);
                }
            }
            if((location %8) >0 ) {
                nextSpace = gameBoard.getIndex(location - 9);
                if (nextSpace != null && nextSpace.color != color) {
                    list.add(location - 9);
                }
                nextSpace = gameBoard.getIndex(location - 1);
                if(nextSpace != null && nextSpace.enPassant()){
                    list.add(location-9);
                }
            }
            if((location%8)< 7){
                nextSpace = gameBoard.getIndex(location - 7);
                if(nextSpace != null && nextSpace.color != color){
                    list.add(location-7);
                }
                nextSpace = gameBoard.getIndex(location+1);
                if(nextSpace != null && nextSpace.enPassant()){
                    list.add(location-7);
                }
            }
            return list;
        }
        return null;
    }

    public void setCanEP(boolean canEP) {
        this.canEP = canEP;
    }

    @Override
    public boolean enPassant() {
        return canEP;
    }

    @Override
    void move(int newLocation){
        if(location - 16 == newLocation){canEP = true;}
        location = newLocation;
        hasMoved = true;
    }
    @Override
    boolean hasMoved() {
        return hasMoved;
    }

    @Override
    public double eval() {
        return gameBoard.evalList.getPawn(location);
    }
}
