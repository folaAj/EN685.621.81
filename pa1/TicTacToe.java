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

    Coordinate winningPlace(){
       return winningDirection(aiPlayerSymbol);
    }

    Coordinate blockingPlace(){
        return winningDirection(humanPlayerSymbol);
    }

    /**
     * Checks for a winning direction for the player with sybol {@code playerSymbol} and returns the {@lnk Coordinate}
     * of the winning place.
     */
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

    void updateAvailableBestMoves(Coordinate coordinate){
        Integer priority = availableBestMovesReversed.get(coordinate);
        availableBestMoves.remove(priority);
        availableBestMovesReversed.remove(coordinate);
    }

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

    /** Represents a board coordnate. */
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
