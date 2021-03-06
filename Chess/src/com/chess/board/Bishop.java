package com.chess.board;


import java.util.ArrayList;

public class Bishop extends Pieces{
    public Bishop(boolean white, GameBoard gameBoard, int location) {
        super(white, gameBoard, location);
        this.value = 3.1;
        this.type = "bishop";
        this.imagePath= "Chess/Art/" + (color ? "WB": "BB") + ".gif";
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

        return list;
    }

    public Pieces clone(GameBoard newBoard) {
        return new Bishop(color, newBoard, location);
    }

    public double eval(){
        return color ?
                (value + gameBoard.evalList.getBishop(location)):
                (-1 * gameBoard.evalList.getBishop(-1*(location-63))-value);
    }
}
