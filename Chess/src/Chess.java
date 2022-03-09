import com.chess.Move.MoveStatus;
import com.chess.board.GameBoard;
import com.chess.Move.Move;
import com.chess.gui.Board;

import java.util.logging.Handler;


public class Chess{
    public static void main(String[] args) {
        Chess f = new Chess();
    }
    public MoveStatus status = new MoveStatus();
    Chess() {

        GameBoard gameBoard = new GameBoard();
        Board board = new Board(gameBoard, status);
        gameBoard.print();

        boolean run = true;
        boolean turn = true;
        int count = 0;
        while (run) {
            if(count == 999999999){
                count = 0;
                System.out.print("k");
            }
            count++;

            if(status.getStatus()){
                System.out.println("success");
                Boolean success = gameBoard.tryMove(status.useMove(), turn);
                if(success){
                    turn = !turn;
                    gameBoard.print();
                    gameBoard.printMoves(turn);
                    board.updateBoard(gameBoard);
                }
            }
        }
    }
}
