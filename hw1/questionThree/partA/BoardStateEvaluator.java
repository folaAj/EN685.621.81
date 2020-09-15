package hw1.questionThree.partA;

import java.util.ArrayList;
import java.util.List;

/** Evaluates the state of a tic tac toe board. */
public class BoardStateEvaluator {
    private static final int X = 1;
    private static final int O = 0;
    private static final int __ = -1;
    private static final int BOARD_SIZE = 3;
    private static final int NUMBER_OF_BOARD_STATES_OF_INTEREST = 14;
    private static final int [][] X_BOARD_VALUES = new int [BOARD_SIZE][BOARD_SIZE];
    private static final int [][] O_BOARD_VALUES = new int[BOARD_SIZE][BOARD_SIZE];

    public static void main(String[] args) {
        populateXBoardValues();
        populateOBoardValues();
        int [][][] allBoardStatesOfInterest = generateBoardStatesOfInterest();
        List<Integer> integerRepresentations = new ArrayList<>();

        for(int[][] boardState: allBoardStatesOfInterest){
            integerRepresentations.add(evaluate(boardState));
        }
        System.out.println(integerRepresentations);
        // Output: [-36, -18, 16, 1, -48, -34, -36, 20, -28, 2, 7, -24, -15, -21]
    }

    private static int evaluate(int[][] boardState){
        return calculateValue(X, boardState) - calculateValue(O, boardState);
    }

    private static final int calculateValue(int player, int [][] board){
        // horizontal value;
        int horizontal = 0;
        int [][] visited = new int [BOARD_SIZE][BOARD_SIZE];
        for(int row = 0; row <BOARD_SIZE; row ++){
            int sum = 0;
            int count = 0;
            for (int col = 0; col <BOARD_SIZE; col++){
                if(visited[row][col] == -1){
                    sum = 0;
                    break;
                }
                if(board[row][col]!=player && board[row][col] != __ ){
                    sum = 0;
                    break;
                }
                if(board[row][col] == player){
                    sum += (++count) * (player==X ? X_BOARD_VALUES[row][col] : O_BOARD_VALUES[row][col]);
                    // Mark as visited;
                    visited[row][col] = -1;
                }
            }
            horizontal+=sum;
        }

        // Vertical value;
        int vertical = 0;
        visited = new int [BOARD_SIZE][BOARD_SIZE];
        for(int col = 0; col <BOARD_SIZE; col ++){
            int sum = 0;
            int count = 0;
            for (int row = 0; row <BOARD_SIZE; row++){
                if(visited[row][col] == -1){
                    sum = 0;
                    break;
                }
                if(board[row][col]!=player && board[row][col] != __ ){
                    sum = 0;
                    break;
                }
                if(board[row][col] == player){
                    sum += (++count) * (player==X ? X_BOARD_VALUES[row][col] : O_BOARD_VALUES[row][col]);
                    // Mark as visited;
                    visited[row][col] = -1;
                }
            }
            vertical+=sum;
        }

        // Diagonal value.
        int diagonal = 0;
        int sum = 0;
        int count = 0;
        for(int index = 0; index <BOARD_SIZE; index ++){
            if(board[index][index]!=player && board[index][index] != __ ){
                sum = 0;
                break;
            }
            if(board[index][index] == player){
                sum += (++count) * (player==X ? X_BOARD_VALUES[index][index] : O_BOARD_VALUES[index][index]);
            }
        }
        diagonal+=sum;
        sum = 0;
        count = 0;
        int backwards = 0;
        for(int index = BOARD_SIZE-1; index >=0; index--){
            if(board[index][backwards]!=player && board[index][backwards] != __ ){
                sum = 0;
                break;
            }
            if(board[index][backwards] == player){
                sum += (++count) * (player==X ? X_BOARD_VALUES[index][backwards] : O_BOARD_VALUES[index][backwards]);

            }
            backwards++;
        }
        diagonal+=sum;

        return horizontal+vertical+diagonal;
    }

    private static int[][][] generateBoardStatesOfInterest(){
        int[][][] allBoardStatesOfInterest = new int[NUMBER_OF_BOARD_STATES_OF_INTEREST][BOARD_SIZE][BOARD_SIZE];
        // Hard code board states of interest and keep track of them.
        int[][] row1Col1 = new int[][]{
                {O, __, X},
                {__, O, __},
                {__, __, __}
        };
        allBoardStatesOfInterest[0] = row1Col1;

        int[][] row1Col2 = new int[][]{
                {O, __, X},
                {__, __, __},
                {O, __, __}
        };
        allBoardStatesOfInterest[1] = row1Col2;

        int[][] row1Col3 = new int[][]{
                {O, __, X},
                {__, __, __},
                {__, __, __}
        };
        allBoardStatesOfInterest[2] = row1Col3;

        int[][] row1Col4 = new int[][]{
                {X,__, __},
                {__, O, O},
                {__,__,__}
        };
        allBoardStatesOfInterest[3] = row1Col4;

        int[][] row1Col5 = new int[][]{
                {__,__, __},
                {X, O, __},
                {O,__,__}
        };
        allBoardStatesOfInterest[4]= row1Col5;

        int[][] row1Col6 = new int[][]{
                {__,__, X},
                {__, O, X},
                {O, __, O}
        };
        allBoardStatesOfInterest[5] = row1Col6;

        int[][] row1Col7 = new int[][]{
                {__, __, __},
                {__, O, __},
                {__, __, __}
        };
        allBoardStatesOfInterest[6] = row1Col7;

        int[][] row2Col1 = new int[][]{
                {__,__, X},
                {__, __, __},
                {O, __, __}
        };
        allBoardStatesOfInterest[7] = row2Col1;

        int[][] row2Col2 = new int[][]{
                {O,__, X},
                {__, __, __},
                {O, X, O}
        };
        allBoardStatesOfInterest[8] = row2Col2;

        int[][] row2Col3 = new int[][]{
                {O, __ ,__},
                {X, X, O},
                {O, __, __}
        };
        allBoardStatesOfInterest[9] = row2Col3;

        int[][] row2Col4 = new int[][]{
                {O, __, X},
                {O, __, __},
                {__, __, __}
        };
        allBoardStatesOfInterest[10] = row2Col4;

        int[][] row2Col5 = new int[][]{
                {O, __, __},
                {__, __, __},
                {__, __, __}
        };
        allBoardStatesOfInterest[11] = row2Col5;

        int[][] row2Col6 = new int[][]{
                {O, __, X},
                {__, __, __},
                {__, __, O}
        };
        allBoardStatesOfInterest[12] = row2Col6;

        int[][] row2Col7 = new int[][]{
                {__, __, O},
                {__, __, __},
                {__, __, __}
        };
        allBoardStatesOfInterest[13] = row2Col7;
        return allBoardStatesOfInterest;
    }

    /**
     * We represent a tic tac toe board by a two dimensional array.
     * The X player's placement on the board will map to corresponding priority values which represent the best places
     * to play. The player ranks the center as most valuable and the edges after.
     * The below represents player X's priority values.
     *  17 13 16
     *  12 18 11
     *  15 10 14
     *
     *  Our representation is of the form board[row][column], so 12 for example will map to row 1 column 0 -> board[1][0].
     * */
    private static final void populateXBoardValues(){
        X_BOARD_VALUES[0][0] = 17;
        X_BOARD_VALUES[0][1] = 13;
        X_BOARD_VALUES[0][2] = 16;
        X_BOARD_VALUES[1][0] = 12;
        X_BOARD_VALUES[1][1] = 18;
        X_BOARD_VALUES[1][2] = 11;
        X_BOARD_VALUES[2][0] = 15;
        X_BOARD_VALUES[2][1] = 10;
        X_BOARD_VALUES[2][2] = 14;
    }

    /**
     * We represent a tic tac toe board by a two dimensional array.
     * The O player's placement on the board will map to corresponding priority values which represent the best places
     * to play. The player ranks the center as most valuable and the edges after.
     * The below represents player 0's priority values.
     *  8 4 7
     *  3 9 2
     *  6 1 5
     *
     *  Our representation is of the form board[row][column], so 3 for example will map to row 1 column 0 -> board[1][0].
     * */
    private static final void populateOBoardValues(){
        O_BOARD_VALUES[0][0] = 8;
        O_BOARD_VALUES[0][1] = 4;
        O_BOARD_VALUES[0][2] = 7;
        O_BOARD_VALUES[1][0] = 3;
        O_BOARD_VALUES[1][1] = 9;
        O_BOARD_VALUES[1][2] = 2;
        O_BOARD_VALUES[2][0] = 6;
        O_BOARD_VALUES[2][1] = 1;
        O_BOARD_VALUES[2][2] = 5;
    }
}
