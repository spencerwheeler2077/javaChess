package com.chess.board;

import java.util.ArrayList;

public class Rook extends Pieces{
    private boolean hasMoved;
    public Rook(boolean white, GameBoard gameBoard, int position) {
        super(white, gameBoard, position);
        this.value = 5;
        hasMoved = false;
        this.type="rook";
        this.imagePath= "Chess/Art/" + (color ? "WR": "BR") + ".gif";
    }
    public Rook(boolean white, GameBoard gameBoard, int position, boolean hasMoved) {
        super(white, gameBoard, position);
        this.value = 5;
        this.hasMoved = hasMoved;
        this.type="rook";
        this.imagePath= "Chess/Art/" + (color ? "WR": "BR") + ".gif";

    }

    @Override
    ArrayList<Integer> returnPossibleMoves() {
        ArrayList<Integer> list = new ArrayList<>();
        Pieces nextSpace;
        int i = 0;
        while ((location -i) % 8 > 0) {
            i++;
            nextSpace = gameBoard.getIndex(location-i);
            if (nextSpace == null) {
                list.add(location - i);
            }
            else if(nextSpace.color == this.color){
                break;
            }
            else{
                list.add(location - i);
                break;
            }
        }
        i=0;

        while ((location +i) % 8 < 7) {
            i++;
            nextSpace = gameBoard.getIndex(location+i);
            if (nextSpace == null) {
                list.add(location + i);
            }
            else if(nextSpace.color == this.color){
                break;
            }
            else{
                list.add(location + i);
                break;
            }
        }
        i=0;
        while (location+(8*i) < 56) {
            i++;
            nextSpace = gameBoard.getIndex(location+8*i);
            if (nextSpace == null) {
                list.add(location + 8*i);
            }
            else if(nextSpace.color == this.color){
                break;
            }
            else{
                list.add(location + 8*i);
                break;
            }
        }
        i=0;
        while (location-(8*i) > 7) {
            i++;
            nextSpace = gameBoard.getIndex(location-8*i);
            if (nextSpace == null) {
                list.add(location - 8*i);
            }
            else if(nextSpace.color == this.color){
                break;
            }
            else{
                list.add(location - 8*i);
                break;
            }
        }
        return list;
    }

    @Override
    public Pieces clone(GameBoard newGameBoard) {
        return new Rook(color, newGameBoard, location, hasMoved);
    }

    @Override
    boolean hasMoved() {
        return hasMoved;
    }

    @Override
    void move(int newLocation) {
        location = newLocation;
        hasMoved = true;
    }

    @Override
    public double eval() {
        double thing = gameBoard.evalList.getRook(63);
        return color ?
                (value+gameBoard.evalList.getRook(location)):
                (-1 * gameBoard.evalList.getRook(-1* (location-63))-value);
    }
}
