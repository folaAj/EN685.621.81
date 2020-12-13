package hw5;

public class KnapsackProblem0_1 {

    int solve(int[] weights, int [] values, int capacity){
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

    public static void main(String[] args) {
        test1();
    }

    static void test1(){
        int[] weights = new int[] {5, 10, 30};
        int[] values = new int[] {100, 20, 120};
        int capacity = 40;
        System.out.println(new KnapsackProblem0_1().solve(weights, values, capacity));
    }
}
