package hw1.questionTwo.partA;

import java.util.Arrays;

class IrisDataHashTable {
    private int size;
    private double[] sepalLengths;

    IrisDataHashTable(int size){
        this.size = size;
        this.sepalLengths = new double [size];
        Arrays.fill(sepalLengths, -1);
    }

    /** Adds an element to the hash table such that {@code key} points to {@code value}. */
    void put(int key, double value){
        // TODO: Implement chaining. As of now the current hash function is ok for the typical iris data set of size 150.
        sepalLengths[hash(key)] = value;
    }

    /** Removes an element with the supplied {@code key} from the hashtable. */
    void remove(int key){
        sepalLengths[hash(key)]  = -1;
    }

    /** Retrieves the element {@code key} points to. Returns -1 if the element does not exist. */
    double get(int key){
        return sepalLengths[hash(key)];
    }

    /** Returns the number of elements in the hashtable. */
    int size(){
        int count = 0;
        for(double sepalLength: sepalLengths){
            if(sepalLength!=-1){
                count++;
            }
        }
        return count;
    }

    @Override
    public String toString(){
        StringBuilder stringBuilder = new StringBuilder();
        for(double sepalLength: sepalLengths){
            stringBuilder.append(sepalLength).append(",");
        }

        return stringBuilder.toString();
    }

    private int hash(int x){
        return  x % size;
    }
}
