package com.chess.Players;

import com.chess.Move.Move;
import com.chess.board.GameBoard;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Random;

public class AI extends Player{
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
        int origPieceCount = gameBoard.getPieceCount();//Just something that needs to found, to give the AItryMove function.
        GameBoard copy;
        ArrayList<Move> allMoves = color? gameBoard.getWhiteMoves():gameBoard.getBlackMoves();
        ArrayList<Move> badMoves = color? gameBoard.getBlackMoves() : gameBoard.getWhiteMoves();
        final int LIST_SIZE = 6;
        PriorityQueue<GameBoard> moveList = new PriorityQueue<>(allMoves.size());
        for(Move move: allMoves) {
            copy = gameBoard.copy();
            if (copy.AItryMove(move, color, badMoves, origPieceCount)) {
                copy.evaluation();
                moveList.add(copy);
            }
        }
        GameBoard best = null;
        Move bestMove = null;
        for(int i = 0; i < LIST_SIZE; i++){
            GameBoard board = moveList.remove();
            Move currentMove = board.getLastMove();
            board.evaluation = findBesteval(board, !color, LIST_SIZE -1, badMoves);
            if(best == null){
                best = board;
                bestMove = currentMove;
            }
            if(color) {
                if (best.evaluation < board.evaluation) {
                    best = board;
                    bestMove = currentMove;
                }
            }
            else{
                if(best.evaluation > board.evaluation){
                    best = board;
                    bestMove =currentMove;
                }
            }
        }
        this.moveStatus.setMove(bestMove);
    }
    private double findBesteval(GameBoard gameBoard, Boolean color, int depth, ArrayList<Move> allMoves) {
        int oriPieceCount = gameBoard.getPieceCount();
        if(depth == 2){
            return gameBoard.evaluation();
        }
        ArrayList<Move> badMoves = color? gameBoard.getBlackMoves() : gameBoard.getWhiteMoves();
        GameBoard copy;
        if(allMoves.size()== 0){
            gameBoard.checkFinish();
            return gameBoard.evaluation();
        }
        gameBoard.print();
        PriorityQueue<GameBoard> boards = new PriorityQueue<>(allMoves.size());
        for(Move currentMove: allMoves){
            copy = gameBoard.copy();
            if(copy.AItryMove(currentMove, color, badMoves, oriPieceCount)){
                copy.evaluation();
                gameBoard.print();
                boards.add(copy);
            }
        }
        GameBoard best = null;
        for(int i = 0; i<depth; i++){
            if(!boards.isEmpty()) {
                GameBoard board = boards.remove();
                board.evaluation = findBesteval(board, !color, depth - 1, badMoves);
                if (best == null) {
                    best = board;
                }
                if (color) {
                    if (best.evaluation < board.evaluation) {
                        best = board;
                    }
                } else {
                    if (best.evaluation > board.evaluation) {
                        best = board;
                    }
                }
            }
        }
        if(best == null){
            gameBoard.checkFinish();
            return gameBoard.evaluation();
        }
      return best.evaluation;
    }
}
