package com.chess.board;

import java.util.ArrayList;

public class Queen extends Pieces{
    public Queen(boolean white, GameBoard gameBoard, int location) {
        super(white, gameBoard, location);
        this.value = 9;
        this.type = "queen";
        this.imagePath= "Chess/Art/" + (color ? "WQ": "BQ") + ".gif";
    }

    @Override
    public double eval() {
        return color ?
                (value + gameBoard.evalList.getQueen(location)) :
                (-1 * gameBoard.evalList.getQueen(-1 * (location - 63))-value);
    }

    @Override
    ArrayList<Integer> returnPossibleMoves() {
        ArrayList<Integer> list = new ArrayList<>();
        Pieces nextSpace;
        int i = 0;
        while(location+9*i < 56 && ((location+(9*i)) % 8) < 7){
            i++;
            nextSpace = gameBoard.getIndex(location+9*i);
            if (nextSpace == null) {
                list.add(location +9*i);
            }
            else if(nextSpace.color == this.color){
                break;
            }
            else{
                list.add(location + 9*i);
                break;
            }
        }
        i = 0;
        while(location-9*i >7 && ((location-(9*i)) % 8) > 0){
            i++;
            nextSpace = gameBoard.getIndex(location-9*i);
            if (nextSpace == null) {
                list.add(location - 9*i);
            }
            else if(nextSpace.color == this.color){
                break;
            }
            else{
                list.add(location - 9*i);
                break;
            }
        }
        i=0;
        while(location-7*i>7 && ((location-(7*i)) % 8) < 7){
            i++;
            nextSpace = gameBoard.getIndex(location-7*i);
            if (nextSpace == null) {
                list.add(location - 7*i);
            }
            else if(nextSpace.color == this.color){
                break;
            }
            else{
                list.add(location - 7*i);
                break;
            }
        }
        i=0;
        while(location+7*i<56 && ((location+(7*i)) % 8) > 0){
            i++;
            nextSpace = gameBoard.getIndex(location+7*i);
            if (nextSpace == null) {
                list.add(location + 7*i);
            }
            else if(nextSpace.color == this.color){
                break;
            }
            else{
                list.add(location + 7*i);
                break;
            }
        }
        i = 0;

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

    public Pieces clone(GameBoard newBoard) {
        return new Queen(color, newBoard, location);
    }
}
