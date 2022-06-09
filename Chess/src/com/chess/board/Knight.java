package com.chess.board;

import java.util.ArrayList;

public class Knight extends Pieces{
    public Knight(boolean white, GameBoard gameBoard, int location) {
        super(white, gameBoard, location);
        this.value = 3;
        this.type = "knight";
        this.imagePath= "Chess/Art/" + (color ? "WN": "BN") + ".gif";
    }

    @Override
    ArrayList<Integer> returnPossibleMoves() {
        ArrayList<Integer> list = new ArrayList<>();
        Pieces nextSpace;
        if(location <49 && location %8 > 0){
            nextSpace = gameBoard.getIndex(location +15);
            if(nextSpace == null || nextSpace.color != color) {
                list.add(location + 15);
            }
        }
        if(location < 48 && location %8 < 7){
            nextSpace = gameBoard.getIndex(location +17);
            if(nextSpace == null || nextSpace.color != color) {
                list.add(location + 17);
            }
        }
        if(location < 53 && location %8 < 6){
            nextSpace = gameBoard.getIndex(location +10);
            if(nextSpace == null || nextSpace.color != color) {
                list.add(location + 10);
            }
        }
        if(location < 57 && location %8 > 1){
            nextSpace = gameBoard.getIndex(location +6);
            if(nextSpace == null || nextSpace.color != color) {
                list.add(location + 6);
            }
        }
        if(location > 8 && location %8 < 6){
            nextSpace = gameBoard.getIndex(location -6);
            if(nextSpace == null || nextSpace.color != color) {
                list.add(location -6);
            }
        }
        if(location > 8 && location %8 > 1) {
            nextSpace = gameBoard.getIndex(location -10);
            if(nextSpace == null || nextSpace.color != color) {
                list.add(location - 10);
            }
        };
        if(location > 16 && location %8 > 0){
            nextSpace = gameBoard.getIndex(location -17);
            if(nextSpace == null || nextSpace.color != color) {
                list.add(location - 17);
            }
        };
        if(location>16 && location %8 < 7) {
            nextSpace = gameBoard.getIndex(location - 15);
            if (nextSpace == null || nextSpace.color != color) {
                list.add(location - 15);
            }

        }
        return list;
    }

    public Pieces clone(GameBoard newBoard) {
        return new Knight(color, newBoard, location);
    }

    @Override
    public double eval() {
        return color ?
                (value + gameBoard.evalList.getKnight(location)):
                (-1 * gameBoard.evalList.getKnight(-1* (location-63))-value);
    }
}
