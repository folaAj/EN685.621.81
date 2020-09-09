package hw1.questionTwo.partB;

import hw1.questionOne.partB.Data;

import java.util.Random;

/** A hashtable for the Iris datatset.
 *
 * <p> It makes use of perfect hashing using universal hashing at each level.
 **/
public class IrisDataHashTable {
    private final int size = 150;
    private final int primeNumber = 151;
    private final Random random = new Random();
    private final int aLevel1;
    private final int bLevel1;
    private final int aLevel2;
    private final int bLevel2;

    private Data[] level1;
    private Data[] level2;

    public IrisDataHashTable() {
        this.level1 = new Data[size];
        this.level2 = new Data[size];
        // random.nextInt(max - min + 1) + min
        // reference: https://stackoverflow.com/questions/20389890/generating-a-random-number-between-1-and-10-java
        aLevel1 = random.nextInt((primeNumber-1) -1 + 1) + 1; // {1...primeNumber-1}
        aLevel2 = random.nextInt((primeNumber-1) -1 + 1) + 1;
        bLevel1 = random.nextInt((primeNumber-1) -0 + 1) + 0; // {0...primeNumber-1}
        bLevel2 = random.nextInt((primeNumber-1) -0 + 1) + 0;
    }

    /** Adds an element to the hash table such that {@code key} points to {@code data}. */
    public void put(int key, Data data) {
        if(this.level1[hashLevel1(key)]!=null){
            this.level2[hashLevel2(key)] = data;
        }else{
            this.level1[hashLevel1(key)] = data;
        }
    }

    /** Removes an element with the supplied {@code key} from the hashtable.  */
    public void remove(int key) {
        Data data = this.level1[hashLevel1(key)];
        if(data != null && data.getKey() == key){
            Data level2Data = this.level2[hashLevel2(key)];
            if(level2Data!=null){
                this.level1[hashLevel1(key)] = level2Data;
                this.level2[hashLevel2(key)] = null;
            }else{
                this.level1[hashLevel1(key)] = null;
            }
            return;
        }

        data = this.level2[hashLevel2(key)];
        if(data!=null){
            this.level2[hashLevel2(key)] = null;
        }
    }

    /** Retrieves the element the {@code key} points to. */
    public Data get(int key){
        Data dataLevel1 = this.level1[hashLevel1(key)];
        Data dataLevel2 = this.level2[hashLevel2(key)];
        if(dataLevel2 != null && dataLevel2.getKey() == key){
            return dataLevel2;
        }
        return dataLevel1;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for(Data data: this.level1){
            stringBuilder.append(data).append("\n");
        }
        for(Data data: this.level2){
            stringBuilder.append(data).append("\n");
        }
        return stringBuilder.toString();
    }

    /** Returns the number of elements in the hashtable. */
    public int size(){
        int count = 0;
        for(Data data: this.level1){
            if(data!=null){
                count++;
            }
        }
        for(Data data: this.level2){
            if(data!=null){
                count++;
            }
        }
        return count;
    }

    /**
     * Returns a hash of {@code k}.
     *
     * <p>We make use of universal hashing and a hash is generated from a family of hash functions for every instance.
     * <p> Hash function is of the form ((ak + b) mod p) mod m where p us a prime number, p>m, m is the number of slots
     * available for hashing and a is an element in {1...p-1} and b is an element in {0...p-1}.
     **/
    private int hashLevel1(int k) {
        return (((aLevel1 * k) + bLevel1) % primeNumber) % size;
    }

    /**
     * Returns a hash of {@code k}.
     *
     * <p>We make use of universal hashing and a hash is generated from a family of hash functions for every instance.
     * <p> Hash function is of the form ((ak + b) mod p) mod m where p us a prime number, p>m, m is the number of slots
     * available for hashing and a is an element in {1...p-1} and b is an element in {0...p-1}.
     **/
    private int hashLevel2(int k){
        return (((aLevel2 * k) + bLevel2) % primeNumber) % size;
    }
}
