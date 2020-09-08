package hw1.questionOne.partA;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * A linked list data structure for the Iris data set.
 *
 * <p> The feature data is backed by an array which is linked by the key.
 **/
class IrisDataLinkedList {
    private int size;
    private double [] sepalLengths;
    private final Set<Integer> usedKeys;


    /**
     * Creates a {@link IrisDataLinkedList} instance.
     * @param size Number of the observations.
     **/
    IrisDataLinkedList(int size){
        this.size = size;
        sepalLengths = new double[size];
        Arrays.fill(sepalLengths, -1);
        usedKeys = new HashSet();
    }

    /**
     * Inserts the observation in the linked list.
     *
     * @param key This represents the observation. It is usually the position of the observation in the file.
     *            It falls in the range of 0 to {@code size}-1.
     **/
    void insert(int key, double sepalLength){
        if(usedKeys.size() == size){
            // double the array
            sepalLengths = Arrays.copyOf(sepalLengths, size *2);
            size = size*2;
        }
        if(key<0 || key>=size){
            throw new IllegalArgumentException("Key must be in range of 0 to "+(key-1));
        }
        sepalLengths[key] = sepalLength;
        usedKeys.add(key);
    }

    /**
     * Deletes the observation from the  linked list.
     *
     * @param key  The position of the observation when reading the data.
     **/
    void delete(int key){
        if(key<0 || key>=size){
            throw new IllegalArgumentException("Key must be in range of 0 to "+(key-1));
        }
        sepalLengths[key] = -1;
        usedKeys.remove(key);
    }

    /**
     * Returns the next feature.
     **/
    double successor(int key){
        int successorKey = key + 1;
        if(successorKey<0 || successorKey>=size){
            throw new IllegalArgumentException("Key must be in range of 0 to "+(key-1));
        }
        return sepalLengths[successorKey];
    }

    /**
     * Returns the previous feature.
     **/
    double predecessor(int key){
        int predecessorKey = key - 1;
        if(predecessorKey<0 || predecessorKey>=size){
            throw new IllegalArgumentException("Key must be in range of 0 to "+(key-1));
        }
        return sepalLengths[predecessorKey];
    }
}
