package com.chess.Players;

import com.chess.Move.Move;
import com.chess.board.GameBoard;

import java.util.ArrayList;
import java.util.Random;

public class AI extends Player{
    public AI(boolean color) {
        super(color);
        this.type = "AI";
    }

    @Override
    public void generateMove(GameBoard gameBoard){
        //TODO
        ArrayList<Move> moves = color ? gameBoard.getWhiteMoves(): gameBoard.getBlackMoves();
        Random index = new Random();
        this.moveStatus.setMove(moves.get(index.nextInt(moves.size()))); //end goal
    }
}
