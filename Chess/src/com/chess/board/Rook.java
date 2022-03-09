package com.chess.board;

import java.util.ArrayList;

public class Rook extends Pieces{
    private boolean hasMoved;
    public Rook(boolean white, GameBoard gameBoard, int position) {
        super(white, gameBoard, position);
        this.value = 500;
        hasMoved = false;
        this.type="rook";
        this.imagePath= "Art/" + (color ? "WR": "BR") + ".gif";

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
    boolean hasMoved() {
        return hasMoved;
    }

    @Override
    void move(int newLocation) {
        location = newLocation;
        hasMoved = true;
    }
}
