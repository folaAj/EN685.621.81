package hw1.questionOne.partA;

/**
 * A linked list data structure for the Iris data set.
 *
 * <p> The feature data is backed by an array which is linked by th key.
 **/
class IrisDataLinkedList {
    private final int size;
    private final double [] sepalLengths;


    /**
     * Creates a {@link IrisDataLinkedList} instance.
     * @param size Number of the observations.
     **/
    IrisDataLinkedList(int size){
        this.size = size;
        sepalLengths = new double[size];
    }

    /**
     * Inserts the observation in the linked list.
     *
     * @param key This represents the observation. It is usually the position of the observation in the file.
     *            It falls in the ranfe of 0 to {@code size}-1.
     **/
    void insert(int key, double sepalLength){
        if(key<0 || key>=size){
            throw new IllegalArgumentException("Key must be in range of 0 to "+(key-1));
        }
        sepalLengths[key] = sepalLength;
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
    }
}
