import com.chess.Move.MoveStatus;
import com.chess.Players.AI;
import com.chess.Players.Player;
import com.chess.board.GameBoard;
import com.chess.Move.Move;
import com.chess.board.Pieces;
import com.chess.gui.Board;

import java.util.logging.Handler;


public class Chess{
    public static void main(String[] args) {
        Chess f = new Chess();
    }
    Chess() {
        Player white =  new Player(true);
        Player black = new AI(false);
        GameBoard gameBoard = new GameBoard();
        Board board = new Board(gameBoard);
        gameBoard.print();

        boolean run = true;
        boolean turn = true;
        int count = 0;
        board.setPlayer(white);
        Player currentPlayer = white;
        while (!gameBoard.stalemate && gameBoard.checkmate==0) {
            if(count == 999999999){
                count = 0;
                System.out.print("");
            }
            count++;
            if(currentPlayer.getType().equals("AI")){
                currentPlayer.generateMove(gameBoard);
            }

            if(currentPlayer.getStatus()){
                System.out.println("success");
                Pieces[] pieces = gameBoard.getBoard();
                Boolean success = gameBoard.tryMove(currentPlayer.useMove(), turn);
                if(success){
                    turn = !turn;
                    currentPlayer = turn ? white:black;
                    board.setPlayer(currentPlayer);
                    gameBoard.print();
                    gameBoard.printMoves(turn);
                    board.updateBoard(gameBoard);
                }
            }
        }
    }
}
