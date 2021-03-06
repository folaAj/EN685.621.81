package pa1;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/** Simulates a Tic Tac Toe game between an AI and a human player.*/
class TicTacToe {
    private final PlayerSymbol aiPlayerSymbol;
    private final PlayerSymbol humanPlayerSymbol;
    private final PlayerSymbol [][] board ;
    private TreeMap<Integer, Coordinate> availableBestMoves;
    private Map<Coordinate, Integer> availableBestMovesReversed;

    TicTacToe(PlayerSymbol aiPlayerSymbol, PlayerSymbol humanPlayerSymbol){
        this.board = new PlayerSymbol[3][3];
        this.availableBestMoves = new TreeMap<>();
        this.availableBestMovesReversed = new HashMap<>();
        this.aiPlayerSymbol = aiPlayerSymbol;
        this.humanPlayerSymbol = humanPlayerSymbol;
        generateBestMoves();
    }

    /**
     * Generates a mapping of board place priorities to coordinates for the AI.
     *
     * <p>The AI will prioritize these board places when making a move.
     * It would consider if it can already win or if it needs to block. Please see {@link #aiPlay()}
     * Priorities are of the form:
     *  8 4 7
     *  3 9 2
     *  6 1 5
     */
    void generateBestMoves(){
        availableBestMoves.put(8, new Coordinate(0,0));
        availableBestMovesReversed.put(new Coordinate(0,0), 8);

        availableBestMoves.put(4, new Coordinate(0,1));
        availableBestMovesReversed.put(new Coordinate(0,1), 4);

        availableBestMoves.put(7, new Coordinate(0,2));
        availableBestMovesReversed.put(new Coordinate(0,2), 7);

        availableBestMoves.put(3, new Coordinate(1,0));
        availableBestMovesReversed.put(new Coordinate(1,0), 3);

        availableBestMoves.put(9, new Coordinate(1,1));
        availableBestMovesReversed.put(new Coordinate(1,1), 9);

        availableBestMoves.put(2, new Coordinate(1,2));
        availableBestMovesReversed.put(new Coordinate(1,2), 2);

        availableBestMoves.put(6, new Coordinate(2,0));
        availableBestMovesReversed.put(new Coordinate(2,0), 6);

        availableBestMoves.put(1, new Coordinate(2,1));
        availableBestMovesReversed.put(new Coordinate(2,1), 1);

        availableBestMoves.put(5, new Coordinate(2,2));
        availableBestMovesReversed.put(new Coordinate(2,2), 5);
    }

    /** Checks if the AI has a win and returns the {@link Coordinate} for the winning play. */
    Coordinate winningPlace(){
       return winningDirection(aiPlayerSymbol);
    }

    /** Checks if the AI needs to block the human player and returns the {@link Coordinate} to play the block.*/
    Coordinate blockingPlace(){
        return winningDirection(humanPlayerSymbol);
    }

    Coordinate winningDirection(PlayerSymbol playerSymbol){
        Coordinate winningDirection = Coordinate.EMPTY;
        // check horizontal
        for(int row = 0; row<board.length; row++){
            int symbolCount = 0;
            for(int col = 0; col<board[row].length; col++){
                if(board[row][col]!=playerSymbol  && board[row][col]!=null){
                    break;
                }else if(board[row][col] == playerSymbol){
                    symbolCount++;
                }else if(board[row][col] == null){
                    winningDirection = new Coordinate(row, col);
                }
            }
            if(symbolCount == 2 && winningDirection!=Coordinate.EMPTY){
                return winningDirection;
            }
            winningDirection = Coordinate.EMPTY;
        }

        // check vertical
        for(int col = 0; col<board.length; col++){
            int symbolCount = 0;
            for(int row = 0; row<board[col].length; row++){
                if(board[row][col]!=playerSymbol  && board[row][col]!=null){
                    break;
                }else if(board[row][col] == playerSymbol){
                    symbolCount++;
                }else if(board[row][col] == null){
                    winningDirection = new Coordinate(row, col);
                }
            }
            if(symbolCount == 2 && winningDirection!=Coordinate.EMPTY){
                return winningDirection;
            }
            winningDirection = Coordinate.EMPTY;
        }

        // check diagonal
        int symbolCount = 0;
        for(int index = 0; index<board.length; index++){
            if(board[index][index]!=playerSymbol && board[index][index]!=null){
                break;
            }else if(board[index][index] == playerSymbol){
                symbolCount++;
            }else if(board[index][index] == null){
                winningDirection = new Coordinate(index, index);
            }
        }
        if(symbolCount == 2 && winningDirection!=Coordinate.EMPTY){
            return winningDirection;
        }
        winningDirection = Coordinate.EMPTY;
        symbolCount = 0;
        for(int index = board.length-1; index>=0; index--){
            if(board[index][index]!=playerSymbol && board[index][index]!=null){
                break;
            }else if(board[index][index] == playerSymbol){
                symbolCount++;
            }else if(board[index][index] == null){
                winningDirection = new Coordinate(index, index);
            }
        }
        if(symbolCount == 2 && winningDirection!=Coordinate.EMPTY){
            return winningDirection;
        }
        return  Coordinate.EMPTY;
    }

    /** Removes the {@code coordinate} as the AI's potential best move.
     *
     * <p> Usually called after the human player has played.
     */
    void updateAvailableBestMoves(Coordinate coordinate){
        Integer priority = availableBestMovesReversed.get(coordinate);
        availableBestMoves.remove(priority);
        availableBestMovesReversed.remove(coordinate);
    }

    /** Executes AI's turn. */
    void aiPlay(){
        Coordinate toWin = winningPlace();
        if(toWin != Coordinate.EMPTY){
            board[toWin.row][toWin.col] = aiPlayerSymbol;
            return;
        }
        Coordinate toBlock = blockingPlace();
        if(toBlock != Coordinate.EMPTY){
            board[toBlock.row][toBlock.col] = aiPlayerSymbol;
            return;
        }
        Coordinate bestMove = availableBestMoves.pollLastEntry().getValue();
        board[bestMove.row][bestMove.col] = aiPlayerSymbol;
    }

    /** Executes the human's turn.
     *
     * @param row The row on the board the human wants to play.
     * @param col The column on the board the human wants to play.
     */
    void humanPlay(int row, int col){
        board[row][col] = humanPlayerSymbol;
        updateAvailableBestMoves(new Coordinate(row, col));
    }

    public static void main(String[] args) {
        PlayerSymbol aiPlayer = PlayerSymbol.X;
        PlayerSymbol humanPlayer = PlayerSymbol.O;
        TicTacToe ticTacToe = new TicTacToe(aiPlayer, humanPlayer);
        System.out.println("Starting Board State");
        System.out.println(Arrays.deepToString(ticTacToe.board));

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

        System.out.println("Human Player played");
        ticTacToe.humanPlay(2,2);
        System.out.println(Arrays.deepToString(ticTacToe.board));

        System.out.println("Ai Player played");
        ticTacToe.aiPlay();
        System.out.println(Arrays.deepToString(ticTacToe.board));

    }

    /** Represents a board coordinate. */
    private static class Coordinate{
        private int row;
        private int col;
        private static final Coordinate EMPTY = new Coordinate(-1, -1);
        private Coordinate(int row, int col){
            this.row  = row;
            this.col = col;
        }

        @Override
        public String toString(){
            return "row: "+row+" "+"col: "+col;
        }

        @Override
        public boolean equals(Object other){
        Coordinate otherCoordinate = (Coordinate)other;
        return (row == otherCoordinate.row) && (col == otherCoordinate.col);
        }

        @Override
        public int hashCode(){
            return Integer.hashCode(row)^Integer.hashCode(col);
        }
    }

    /** Enum representing either an X or O symbol. */
    enum PlayerSymbol {
        X, O;

        @Override
        public String toString(){
            switch (this){
                case X: return "X";
                case O: return "O";
                default: return"";
            }
        }
    }
}
