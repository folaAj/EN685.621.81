package pa2.tictactoe;

import java.util.Arrays;
import java.util.List;

/** Simulates a Tic Tac Toe game between an AI and a human player.*/
class TicTacToe {
    private static final int BOARD_SIZE = 3;
    private final PlayerSymbol[][] board;
    private final PlayerSymbol aiPlayerSymbol;
    private final PlayerSymbol humanPlayerSymbol;

    TicTacToe(PlayerSymbol aiPlayerSymbol, PlayerSymbol humanPlayerSymbol){
        this.board = new PlayerSymbol[BOARD_SIZE][BOARD_SIZE];
        this.aiPlayerSymbol = aiPlayerSymbol;
        this.humanPlayerSymbol = humanPlayerSymbol;
    }

    private Result miniMax(BoardState boardState){
       return max(boardState);
    }

    private Result max(BoardState boardState){
        int bestValue = Integer.MIN_VALUE;
        Result bestResult = null;
        List<Coordinate> possibleMoves = boardState.getAvailableMoves();
        if(possibleMoves.isEmpty()){
            return new Result(utility(boardState), null);
        }

        // for all actions compare (bestMove) and get the maximum value assuming AI is max.
        for(Coordinate coordinate: possibleMoves){
            Result result = min(boardState.copyWithMove(coordinate, aiPlayerSymbol));
            if(result.value >= bestValue){
                bestValue = result.value;
                bestResult = new Result(bestValue, coordinate);
            }
        }
        return bestResult;
    }

    private Result min(BoardState boardState){
        int bestValue = Integer.MAX_VALUE;
        Result bestResult = null;
        List<Coordinate> possibleMoves = boardState.getAvailableMoves();
        if(possibleMoves.isEmpty()){
            return new Result(utility(boardState), null);
        }

        // for all actions compare bestMove with the minimum value because this is the human player, min, and we assume min plays optimally.
        for(Coordinate coordinate: possibleMoves){
            Result result = max(boardState.copyWithMove(coordinate, humanPlayerSymbol));
            if(result.value < bestValue){
                bestValue = result.value;
                bestResult = new Result(bestValue, null);
            }
        }
        return bestResult;
    }

    /** The utility function used to calculate the AI utility at this {@code boardState}.*/
    private int utility(BoardState boardState){
        // returns -1 for loss, 0 for draw and 1 for a win.
        if(hasWon(aiPlayerSymbol, boardState)){
            return 1;
        }

        if(hasWon(humanPlayerSymbol, boardState)){
            //System.out.println("human won");
            return -1;
        }
        return 0;
    }

   private boolean hasWon(PlayerSymbol playerSymbol, BoardState boardState){
        PlayerSymbol [][] board = boardState.getBoard();

        // check horizontal
        for(int row = 0; row<board.length; row++){
            int symbolCount = 0;
            for(int col = 0; col<board[row].length; col++){
                if(board[row][col] == playerSymbol){
                    symbolCount++;
                }
            }
            if(symbolCount == 3){
                return true;
            }
        }

        // check vertical
        for(int col = 0; col<board.length; col++){
            int symbolCount = 0;
            for(int row = 0; row<board[col].length; row++){
                if(board[row][col]== playerSymbol){
                    symbolCount++;
                }
             }
            if(symbolCount == 3){
                return true;
            }
        }

        // check diagonal
        int symbolCount = 0;
        for(int index = 0; index<board.length; index++){
            if(board[index][index] == playerSymbol){
                symbolCount++;
            }
        }
       if(symbolCount == 3){
           return true;
       }

       int row = 0;
       int col = board.length - 1;

       symbolCount = 0;
       while(row<board.length && col>=0){
           if(board[row][col] == playerSymbol){
               symbolCount++;
           }
           row++;
           col--;
       }
       if(symbolCount == 3){
           return true;
       }
       return false;
    }

    private PlayerSymbol[][] copyBoard(){
        PlayerSymbol [][] copy = new PlayerSymbol[board.length][board[0].length];
        for (int row = 0; row<board.length; row++){
            for(int col = 0; col<board[0].length; col++){
                copy[row][col] = board[row][col];
            }
        }
        return copy;
    }

    /** Executes the AI's turn. */
    void aiPlay(){
        BoardState currentState = new BoardState(copyBoard());
        Result bestMove = miniMax(currentState);

        if(bestMove.move!=null){
            board[bestMove.move.row()][ bestMove.move.column()] = aiPlayerSymbol;
        }
    }

    /** Executes the human's turn.
     *
     * @param row The row on the board the human wants to play.
     * @param col The column on the board the human wants to play.
     */
    void humanPlay(int row, int col){
        board[row][col] = humanPlayerSymbol;
    }

    public static void main(String[] args) {
        PlayerSymbol aiPlayer = PlayerSymbol.X;
        PlayerSymbol humanPlayer = PlayerSymbol.O;
        TicTacToe ticTacToe = new TicTacToe(aiPlayer, humanPlayer);

        System.out.println("Starting Board State");
        System.out.println(Arrays.deepToString(ticTacToe.board));
        test1(ticTacToe);

        System.out.println("Starting Board State");
        System.out.println(Arrays.deepToString(ticTacToe.board));
        test2(ticTacToe);
    }

    static void test2(TicTacToe ticTacToe){
        System.out.println("Human Player played");
        ticTacToe.humanPlay(1,1);
        System.out.println(Arrays.deepToString(ticTacToe.board));

        System.out.println("Ai Player played");
        ticTacToe.aiPlay();
        System.out.println(Arrays.deepToString(ticTacToe.board));

        System.out.println("Human Player played");
        ticTacToe.humanPlay(0,1);
        System.out.println(Arrays.deepToString(ticTacToe.board));

        System.out.println("Ai Player played");
        ticTacToe.aiPlay();
        System.out.println(Arrays.deepToString(ticTacToe.board));

        System.out.println("Human Player played");
        ticTacToe.humanPlay(0,2);
        System.out.println(Arrays.deepToString(ticTacToe.board));

        System.out.println("Ai Player played");
        ticTacToe.aiPlay();
        System.out.println(Arrays.deepToString(ticTacToe.board));
    }

    static void test1 (TicTacToe ticTacToe){
        System.out.println("Human Player played");
        ticTacToe.humanPlay(1,1);
        System.out.println(Arrays.deepToString(ticTacToe.board));

        System.out.println("Ai Player played");
        ticTacToe.aiPlay();
        System.out.println(Arrays.deepToString(ticTacToe.board));

        System.out.println("Human Player played");
        ticTacToe.humanPlay(0,1);
        System.out.println(Arrays.deepToString(ticTacToe.board));

        System.out.println("Ai Player played");
        ticTacToe.aiPlay();
        System.out.println(Arrays.deepToString(ticTacToe.board));

        System.out.println("Human Player played");
        ticTacToe.humanPlay(2,0);
        System.out.println(Arrays.deepToString(ticTacToe.board));

        System.out.println("Ai Player played");
        ticTacToe.aiPlay();
        System.out.println(Arrays.deepToString(ticTacToe.board));

        System.out.println("Human Player played");
        ticTacToe.humanPlay(1, 2);
        System.out.println(Arrays.deepToString(ticTacToe.board));

        System.out.println("Ai Player played");
        ticTacToe.aiPlay();
        System.out.println(Arrays.deepToString(ticTacToe.board));

    }

    /** Represents the result returned by min or max calculation. */
    private class Result {
        int value;
        Coordinate move;
        Result(int value, Coordinate move) {
            this.value = value;
            this.move = move;
        }
    }
}
