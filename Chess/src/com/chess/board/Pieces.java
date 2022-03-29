package com.chess.board;

import java.util.ArrayList;

public abstract class Pieces {
    String imagePath;
    int location;
    boolean color;
    String type;
    double value;
    GameBoard gameBoard;


    //TODO add the images for each piece.
    public Pieces(boolean white, GameBoard gameBoard, int location){
        this.gameBoard = gameBoard;
        this.location = location;
        this.color = white;
        this.imagePath = "pixil-frame-0.png";
    }
    abstract public double eval();
    abstract ArrayList<Integer> returnPossibleMoves();
    void move(int newLocation){
        location = newLocation;
    };
    public String getImagePath(){return this.imagePath;}
    public String getType(){
        return this.type;
    }
    public boolean enPassant(){ return false; }
    public void setCanEP(boolean canEP){}
    public abstract Pieces clone(GameBoard newGameBoard);


    boolean hasMoved() {
        return false;
    }
}
