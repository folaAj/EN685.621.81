package hw5;

import java.util.Arrays;

public class KnapsackProblem0_1 {

    int solve(int[] weights, int [] values, int capacity, boolean useMemoization){
        if(useMemoization){
            Integer [][] dp = new Integer[weights.length][capacity+1];
            return  solveInternalWithMemoization(weights, values, capacity, 0, dp);
        }
        return solveInternal(weights, values, capacity, 0);
    }

    int solveInternal(int [] weights, int [] values, int capacity, int currentIndex){
        if(currentIndex<0 || currentIndex>= weights.length){
            return 0;
        }
        if(weights[currentIndex] > capacity){
            return 0;
        }
        return Integer.max(solveInternal(weights, values, capacity - weights[currentIndex], currentIndex+1) + values[currentIndex],
                solveInternal(weights, values, capacity, currentIndex+1));
    }

    int solveInternalWithMemoization(int [] weights, int [] values, int capacity, int currentIndex, Integer [][] dp){
        if(currentIndex<0 || currentIndex>= weights.length){
            return 0;
        }
        if(weights[currentIndex] > capacity){
            return 0;
        }

        if(dp[currentIndex][capacity] != null){
            return dp[currentIndex][capacity];
        }

        int value =  Integer.max(solveInternal(weights, values, capacity - weights[currentIndex], currentIndex+1) + values[currentIndex],
                solveInternal(weights, values, capacity, currentIndex+1));
        dp[currentIndex][capacity] = value;
        return value;
    }

    public static void main(String[] args) {
        test1();
    }

    static void test1(){
        int[] weights = new int[] {5, 10, 30};
        int[] values = new int[] {100, 20, 120};
        int capacity = 40;
        System.out.println(new KnapsackProblem0_1().solve(weights, values, capacity, false));
        System.out.println(new KnapsackProblem0_1().solve(weights, values, capacity, true));
    }
}
