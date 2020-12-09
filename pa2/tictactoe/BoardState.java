package pa2.tictactoe;

import java.util.ArrayList;
import java.util.List;

/** Represents the state of the board.
 *
 * <p> Useful for making operations when simulating the game state.
 * */
final class BoardState {
    private final PlayerSymbol[][] board ;

    BoardState(PlayerSymbol[][] board){
        this.board = board;
    }

    List<Coordinate> getAvailableMoves(){
        List<Coordinate> availableMoves = new ArrayList<>();
        for(int row = 0; row<board.length; row++){
            for(int col = 0 ; col<board[0].length; col++){
                if(board[row][col] == null){
                    availableMoves.add(new Coordinate(row, col));
                }
            }
        }
        return availableMoves;
    }

    BoardState copyWithMove(Coordinate coordinate, PlayerSymbol symbol){
        PlayerSymbol [][] copy = new PlayerSymbol[board.length][board[0].length];
        for (int row = 0; row<board.length; row++){
            for(int col = 0; col<board[0].length; col++){
                copy[row][col] = board[row][col];
            }
        }
        copy[coordinate.row()][coordinate.column()] = symbol;

        return new BoardState(copy);
    }

    PlayerSymbol[][] getBoard(){
        return board;
    }
}
