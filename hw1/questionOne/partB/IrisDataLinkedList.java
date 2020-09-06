package hw1.questionOne.partB;

import java.util.Arrays;

/**
 * A linked list data structure for the Iris data set.
 *
 * <p> It is backed by an array for each feature and the observation key is used to link to te feature data.
 **/
class IrisDataLinkedList {
    private final int size;
    private final int [] keys;
    private final double [] sepalLengths;
    private final double [] sepalWidths;
    private final double[] petalLengths;
    private final double[] petalWidths;
    private final String[] irisClassNames;

    /**
     * Creates a {@link IrisDataLinkedList} instance.
     * @param size Number of the observations.
     **/
    IrisDataLinkedList(int size){
        this.size = size;
        keys = new int[size];

        sepalLengths = new double[size];
        Arrays.fill(sepalLengths, -1);
        sepalWidths = new double[size];
        Arrays.fill(sepalWidths, -1);
        petalLengths = new double[size];
        Arrays.fill(petalLengths, -1);
        petalWidths = new double[size];
        Arrays.fill(petalWidths, -1);
        irisClassNames = new String[size];

    }

    /** Inserts the observation in the linked list. */
    void insert(Data data){
        if(data.getKey()<0 || data.getKey()>=size){
            throw new IllegalArgumentException("Key must be in range of 0 to "+(data.getKey()-1));
        }
        sepalLengths[data.getKey()] = data.getSepalLength();
        sepalWidths[data.getKey()] = data.getSepalWidth();
        petalLengths[data.getKey()] = data.getPetalLength();
        petalWidths[data.getKey()] = data.getPetalWidth();
        irisClassNames[data.getKey()] = data.getIrisClassName();
    }

    /** Deletes the observation from the  linked list.
     *
     * @param key The key from the {@link Data} object. It is
     *                       the position of the observation when reading the data.
     **/
    void delete(int key){
        if(key<0 || key>=size){
            throw new IllegalArgumentException("Key must be in range of 0 to "+(key-1));
        }
        sepalLengths[key] = -1;
        sepalWidths[key] = -1;
        petalLengths[key] = -1;
        petalWidths[key] = -1;
        irisClassNames[key] = null;
    }
}
