package com.chess.Players;

import com.chess.Move.Move;
import com.chess.board.GameBoard;

import java.util.ArrayList;
import java.util.Random;

public class AI extends Player{
    int maxDepth = 4;
    public AI(boolean color) {
        super(color);
        this.type = "AI";
    }

    public void rangenerateMove(GameBoard gameBoard){
        //TODO
        ArrayList<Move> moves = color ? gameBoard.getWhiteMoves(): gameBoard.getBlackMoves();
        Random index = new Random();
        this.moveStatus.setMove(moves.get(index.nextInt(moves.size()))); //end goal
    }
    @Override
    public void generateMove(GameBoard gameBoard){
        ArrayList<Move> moves = color ? gameBoard.getWhiteMoves(): gameBoard.getBlackMoves();
        GameBoard copy;
        Move max = null;
        for(Move current : moves){
            copy = gameBoard.copy(); //TODO this is a bug this copy returns a list with a null values.
            copy.tryMove(current, color);
            copy.print();
            current.eval = (copy.evaluation());

            if(max == null){
                max = current;
                max.eval = generateMove(copy, !color, current, 1).eval;
            }
            else if(max.eval < current.eval){
                max.eval = generateMove(copy, !color, current, 1).eval;
            }
        }
        if(max == null){
            gameBoard.checkFinish();
        }
        this.moveStatus.setMove(max);
    }
    private Move generateMove(GameBoard gameBoard, Boolean color, Move previous, int depth){
        if(depth == maxDepth){ //This is the base case
            return previous;
        }
        ArrayList<Move> moves = color ? gameBoard.getWhiteMoves(): gameBoard.getBlackMoves();
        GameBoard copy;
        Move max = null;
        for(Move current : moves){
            copy = gameBoard.copy();
            copy.print();
            copy.tryMove(current, color);
            current.eval = (copy.evaluation());
            if(max == null){
                max = generateMove(copy, !color, current, depth+1);
            }
            else if(max.eval < current.eval){
                max = generateMove(copy, !color, current, depth+1);
            }
        }
        if(max == null){
            gameBoard.checkFinish();
            previous.eval = gameBoard.evaluation();
            return previous;
        }
        return max;
    }
}
