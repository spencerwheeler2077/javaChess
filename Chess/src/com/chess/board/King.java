package com.chess.board;

import java.util.ArrayList;

public class King extends Pieces{
    private boolean hasMoved = false;
    public King(boolean white, GameBoard gameBoard, int location) {
        super(white, gameBoard, location);
        this.value = 999999999;
        this.type = "king";
        this.imagePath= "Chess/Art/" + (color ? "WK": "BK") + ".gif";
    }
    private King(boolean white, GameBoard gameBoard, int location, boolean hasMoved){
        super(white, gameBoard, location);
        this.value = 999999999;
        this.type = "king";
        this.imagePath = "Art/" + (color ? "WK": "BK") + ".gif";
        this.hasMoved = hasMoved;
    }

    @Override
    ArrayList<Integer> returnPossibleMoves() {
        ArrayList<Integer> list = new ArrayList<>();
        Pieces nextPiece;
        int file = location%8;
        if(file >0 && location>7) {
            nextPiece = gameBoard.getIndex(location-9);
            if(nextPiece == null){
                list.add(location-9);
            }
            else if(nextPiece.color != color){
                list.add(location-9);
            }
        }
        if(file>0 && location<56) {
            nextPiece = gameBoard.getIndex(location+7);
            if(nextPiece == null){
                list.add(location+7);
            }
            else if(nextPiece.color != color){
                list.add(location+7);
            }
        }
        if(file>0){
            nextPiece = gameBoard.getIndex(location-1);
            if(nextPiece == null){
                list.add(location-1);
            }
            else if(nextPiece.color != color){
                list.add(location-1);
            }
        }
        if(file<7){
            nextPiece = gameBoard.getIndex(location +1);
            if(nextPiece == null){
                list.add(location+1);
            }
            else if(nextPiece.color != color) {
                list.add(location + 1);
            }
            if(location>7){
                nextPiece = gameBoard.getIndex(location-7);
                if(nextPiece == null){
                    list.add(location-7);
                }
                else if(nextPiece.color != color){
                    list.add(location-7);
                }
            }
            if(location<56){
                nextPiece = gameBoard.getIndex(location+9);
                if(nextPiece == null){
                    list.add(location+9);
                }
                else if(nextPiece.color != color){
                    list.add(location+9);
                }
            }

        }
        if(location > 7) {
            nextPiece = gameBoard.getIndex(location - 8);
            if (nextPiece == null) {
                list.add(location - 8);
            } else if (nextPiece.color != color) {
                list.add(location - 8);
            }
        }
        if(location < 56) {
            nextPiece = gameBoard.getIndex(location + 8);
            if (nextPiece == null) {
                list.add(location + 8);
            } else if (nextPiece.color != color) {
                list.add(location + 8);
            }
        }

        //Check if you can castle
        if(!hasMoved){
            Pieces rightCorner = gameBoard.getIndex(location + 3);
            if(rightCorner != null && rightCorner.getType().equals("rook")){
                if (!rightCorner.hasMoved()){
                    if(gameBoard.getIndex(location+2)==null && gameBoard.getIndex(location + 1)==null){
                        list.add(100);
                    }
                }
            }
            Pieces leftCorner = gameBoard.getIndex(location -4);
            if(leftCorner != null && leftCorner.getType().equals("rook")){
                if (!leftCorner.hasMoved()){
                    if(gameBoard.getIndex(location-1)==null
                            && gameBoard.getIndex(location-2)==null
                            && gameBoard.getIndex(location-3) == null){
                        list.add(150);
                    }
                }
            }

        }
        return list;
    }
    public Pieces clone(GameBoard newGameBoard) {
        return new King(color, newGameBoard, location, hasMoved);
    }

    @Override
    public double eval() {
        return color ?
                gameBoard.evalList.getKing(location):
                -1 * gameBoard.evalList.getKing(-1* (location-63));
    }

    @Override
    boolean hasMoved() {
        return hasMoved;
    }

    @Override
    void move(int newLocation) {
        location= newLocation;
        hasMoved = true;
    }
}
