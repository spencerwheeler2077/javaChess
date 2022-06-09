package com.chess.board;

import java.util.ArrayList;

public class BPawn extends Pieces{
    private boolean hasMoved;
    private boolean canEP;
    public BPawn(GameBoard gameBoard, int location) {
        super(false, gameBoard, location);
        this.type = "pawn";
        this.value = 1;
        this.hasMoved = false;
        this.canEP = false;
        this.imagePath = "Chess/Art/BP.gif";
    }
    private BPawn(GameBoard gameBoard, int location, boolean hasMoved, boolean canEP) {
        super(false, gameBoard, location);
        this.type = "pawn";
        this.value = 1;
        this.hasMoved = hasMoved;
        this.canEP = canEP;
        this.imagePath = "Art/BP.gif";
    }

    @Override
    ArrayList<Integer> returnPossibleMoves() {
        ArrayList<Integer> moves = new ArrayList<>();
        Pieces nextSpace=gameBoard.getIndex(location + 8);
        if(nextSpace == null){
            moves.add(location + 8);
            if(!hasMoved()){
                nextSpace = gameBoard.getIndex(location + 16);
                if(nextSpace == null){
                    moves.add(location + 16);
                }
            }
        }

        if(location%8 > 0) {
            nextSpace = gameBoard.getIndex(location + 7);
            if (nextSpace != null && nextSpace.color != color) {
                moves.add(location + 7);
            }
            nextSpace = gameBoard.getIndex(location - 1);
            if(nextSpace != null && nextSpace.enPassant() && nextSpace.color != color){
                moves.add(location + 7);
            }
        }
        if((location%8) < 7) {
            nextSpace = gameBoard.getIndex(location +9);
            if (nextSpace != null && nextSpace.color != color) {
                moves.add(location + 9);
            }
            nextSpace = gameBoard.getIndex(location + 1);
            if(nextSpace != null && nextSpace.enPassant() && nextSpace.color != color){
                moves.add(location+ 9);
            }
        }
        return moves;
    }
    @Override
    public void setCanEP(boolean canEP) {
        this.canEP = canEP;
    }

    @Override
    public boolean enPassant() {
        return canEP;
    }
    @Override
    void move(int newLocation){
        if(location + 16 == newLocation){
            canEP = true;
        }
        hasMoved = true;
        location = newLocation;
    }
    @Override
    boolean hasMoved() {
        return hasMoved;
    }

    @Override
    public double eval() {
        return (-1*gameBoard.evalList.getPawn(-1*(location-63)))-value;
    }

    @Override
    public Pieces clone(GameBoard newGameBoard) {
        return new BPawn(newGameBoard, location, hasMoved, canEP);
    }
}
