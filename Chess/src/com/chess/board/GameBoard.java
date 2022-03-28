package com.chess.board;

import com.chess.Move.EvalList;
import com.chess.Move.Move;

import java.util.ArrayList;

public class GameBoard {
    private final Pieces[] board = new Pieces[64];
    private int whiteKing;
    private int blackKing;
    private int moveCount = 0;
    public final EvalList evalList = new EvalList("EvalFIles/firstEvalFile");
    public double evaluation;

    public GameBoard(){
        setBoard();
        evaluation();
        System.out.println();
    }

    private Pieces movePiece(Move move){
        moveCount++;
        board[move.getFrom()].move(move.getTo());
        Pieces removed = board[move.getTo()];
        board[move.getTo()] = board[move.getFrom()];
        board[move.getFrom()] = null;
        System.out.println();
        Pieces piece = board[move.getTo()];
        if(piece.getType().equals("king")) {
            if(piece.color){moveWKing(move.getTo());}
            else{moveBKing(move.getTo());}
        }
        else if(piece.getType().equals("pawn") && (piece.location > 55 || piece.location < 8)){
            //TODO if you want to make this trully accurate then this needs to be updated so that a pawn doesn't have
            // to be promoted to a queen.
            board[piece.location] = new Queen(piece.color, this, piece.location);
        }
        return removed;
    }
    private boolean castleShort(boolean color){
        ArrayList<Move> enemyMoves = color ? getBlackMoves():getWhiteMoves();
        int to;
        int kingLocation = color ? whiteKing:blackKing;
        for(Move move: enemyMoves){
            to = move.getTo();
            if (to == kingLocation || to == kingLocation + 1 || to == kingLocation + 2){
                return false;
            }
        }
        board[kingLocation+2] = board[kingLocation];
        board[kingLocation+1] = board[kingLocation + 3];
        board[kingLocation+2].move(kingLocation+2);
        board[kingLocation+1].move(kingLocation+1);
        board[kingLocation]=null;
        board[kingLocation+3]=null;
        if(color){whiteKing = kingLocation+2;}
        else{blackKing = kingLocation + 2;}
        return true;
    }

    private boolean castleLong(boolean color){
        ArrayList<Move> enemyMoves = color ? getBlackMoves():getWhiteMoves();
        int to;
        int kingLocation = color ? whiteKing:blackKing;
        for(Move move: enemyMoves){
            to = move.getTo();
            if (to == kingLocation || to == kingLocation - 1 || to == kingLocation - 2){
                return false;
            }
        }
        board[kingLocation-2] = board[kingLocation];
        board[kingLocation-1] = board[kingLocation - 4];
        board[kingLocation-2].move(kingLocation-2);
        board[kingLocation-1].move(kingLocation-1);
        board[kingLocation]=null;
        board[kingLocation-4]=null;
        if(color){whiteKing = kingLocation-2;}
        else{blackKing = kingLocation - 2;}
        return true;
    }

    private void resetEnPassant(boolean color, Move move){
        if(!color){
            //check white pawns
            for(int i = 32; i < 40; i++){
                //this big logic chunk check if the last move was enpassant
                if(board[i]!= null){
                    if(board[i].getType().equals("pawn") &&
                            board[i].enPassant() &&
                            board[i+8] != null &&
                            board[i+8].getType().equals("pawn")){

                        board[i] = null;}

                    else {board[i].setCanEP(false);};
                }
            }
        }
        else{
            //check black pawns
            for(int i = 24; i < 32; i++){
                if(board[i]!= null){
                    if(board[i].getType().equals("pawn") &&
                            board[i].enPassant() &&
                            board[i-8]!=null &&
                            board[i-8].getType().equals("pawn")){

                        board[i] = null;}
                    else {board[i].setCanEP(false);}
                }
            }
        }
    }

    private void moveBack(Move move, Pieces removed){
        board[move.getFrom()] = board[move.getTo()];
        board[move.getTo()] = removed;
        board[move.getFrom()].move(move.getFrom()); // change the location back to where it came from.
        Pieces piece = board[move.getFrom()];
        if(piece.getType().equals("king")) {
            if(piece.color){moveWKing(move.getFrom());}
            else{moveBKing(move.getFrom());}
        }
    }

    public Pieces getIndex(int index){
        return board[index];
    }
    public final void moveWKing(int newPos){ whiteKing = newPos;}
    public final void moveBKing(int newPos){blackKing = newPos;}
    public final int getMoveCount(){return moveCount;}

    public void setBoard(){
        board[0] = new Rook(false, this, 0);
        board[1] = new Knight(false, this, 1);
        board[2] = new Bishop(false, this, 2);
        board[3] = new Queen(false, this, 3);
        board[4] = new King(false, this, 4);
        board[7] = new Rook(false, this, 7);
        board[6] = new Knight(false, this, 6);
        board[5] = new Bishop(false, this, 5);
        for(int i=8; i<16; i++){
            board[i] = new BPawn(this, i);
        }
        board[56] = new Rook(true, this, 56);
        board[57] = new Knight(true, this, 57);
        board[58] = new Bishop(true, this, 58);
        board[59]= new Queen(true, this, 59);
        board[60] = new King(true, this, 60);
        board[63] = new Rook(true,this, 63);
        board[62] = new Knight(true, this, 62);
        board[61] = new Bishop(true, this, 61);
        for(int i=48; i<56; i++){
            board[i]= new WPawn(this, i);
        }
        whiteKing = 60;
        blackKing = 4;
    }
    public ArrayList<Move> getWhiteMoves(){
        ArrayList<Move> moves = new ArrayList<>();
        for(Pieces piece : board){
            if(piece != null && piece.color){
                ArrayList<Integer> pieceMoves = piece.returnPossibleMoves();
                for(Integer pieceMove: pieceMoves) {
                    if(pieceMove == 100){
                        Move move = new Move(piece.location, piece.location + 2);
                        move.setCanCastleShort();
                        moves.add(move);
                    }
                    else if(pieceMove == 150){
                        Move move = new Move(piece.location, piece.location - 2);
                        move.setCanCastleLong();
                        moves.add(move);
                    }
                    else {
                        moves.add(new Move(piece.location, pieceMove));
                    }
                }
            }
        }
        return moves;
    }

    public ArrayList<Move> getBlackMoves(){
        ArrayList<Move> moves = new ArrayList<>();
        for(Pieces piece : board){
            if(piece != null && !piece.color){
                ArrayList<Integer> pieceMoves = piece.returnPossibleMoves();
                for(Integer pieceMove: pieceMoves) {
                    if(pieceMove == 100){
                        Move move = new Move(piece.location, piece.location + 2);
                        move.setCanCastleShort();
                        moves.add(move);
                    }
                    else if(pieceMove == 150){
                        Move move = new Move(piece.location, piece.location - 2);
                        move.setCanCastleLong();
                        moves.add(move);
                    }
                    else {
                        moves.add(new Move(piece.location, pieceMove));
                    }
                }
            }
        }
        return moves;
    }

    public Boolean tryMove(Move attempt, Boolean color){
        ArrayList<Move> friendlyMoves = !color ? getBlackMoves():getWhiteMoves();
        boolean found = false;
        for(Move move: friendlyMoves){
            if(move.equals(attempt)){
                found = true;
                break;
            }
        }
        if(!found){return false;}

        if(attempt.getCastle().equals("short")){
            return castleShort(color);
        }
        if(attempt.getCastle().equals("long")){
            return castleLong(color);
        }
        Pieces removed = movePiece(attempt);
        int king = color ? whiteKing:blackKing;
        ArrayList<Move> opponentsMoves = color ? getBlackMoves():getWhiteMoves();

        for(Move move: opponentsMoves){
            if (move.getTo() ==  king){
                moveBack(attempt, removed);
                return false;
            }
        }
        resetEnPassant(color, attempt);
        return true;
    }
    public void printMoves(boolean color){
        //this is a function that lets you see what moves can be made, mostly just used for testing.
        ArrayList<Move> moves = color ? getWhiteMoves():getBlackMoves();
        int counter = 0;
        for(Move move : moves){
            System.out.print(move);
            counter++;
            if (counter == 5){ System.out.println(); counter = 0;}
        }
    }
    public void print(){
        int count = 0;
        System.out.println("---------------------------------");
        for(Pieces piece : board){
            System.out.print("| ");
            if(piece == null){
                System.out.print("  ");
            }
            else if(piece.type.equals("pawn")){
                System.out.print(piece.color ? "P " : "p ");
            }
            else if(piece.type.equals("rook")){
                System.out.print(piece.color ? "R " : "r ");
            }
            else if(piece.type.equals("knight")){
                System.out.print(piece.color ? "N " : "n ");
            }
            else if(piece.type.equals("bishop")){
                System.out.print(piece.color ? "B " : "b ");
            }
            else if(piece.type.equals("king")){
                System.out.print(piece.color ? "K " : "k ");
            }
            else if(piece.type.equals("queen")){
                System.out.print(piece.color ? "Q " : "q ");
            }
            count++;
            if(count % 8 ==0){
                System.out.println("|   |" + (count - 1) + "|");
                System.out.println("---------------------------------");
            }

        }
        System.out.println("  0   1   2   3   4   5   6   7");
    }

    public double evaluation(){
        double eval = 0;
        for(Pieces piece : board){
            if(piece != null) {
                eval += piece.eval();
            }
        }
        return eval;
    }


}
