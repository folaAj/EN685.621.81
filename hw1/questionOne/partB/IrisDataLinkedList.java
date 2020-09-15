package hw1.questionOne.partB;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * A linked list data structure for the Iris data set.
 *
 * <p> It is backed by an array for each feature and the observation key is used to link to te feature data.
 **/
public class IrisDataLinkedList {
    private final Set<Integer> usedKeys;
    private  int size;
    private  double [] sepalLengths;
    private  double [] sepalWidths;
    private  double[] petalLengths;
    private  double[] petalWidths;
    private  String[] irisClassNames;

    /**
     * Creates a {@link IrisDataLinkedList} instance.
     * @param size Number of the observations.
     **/
    public IrisDataLinkedList(int size){
        this.size = size;
        sepalLengths = new double[size];
        Arrays.fill(sepalLengths, -1);
        sepalWidths = new double[size];
        Arrays.fill(sepalWidths, -1);
        petalLengths = new double[size];
        Arrays.fill(petalLengths, -1);
        petalWidths = new double[size];
        Arrays.fill(petalWidths, -1);
        irisClassNames = new String[size];
        usedKeys = new HashSet();
    }

    /** Inserts the observation in the linked list. */
    public void insert(Data data){
        if(usedKeys.size() == size){
            // double the arrays
            sepalLengths = Arrays.copyOf(sepalLengths, size * 2);
            sepalWidths = Arrays.copyOf(sepalWidths, size * 2);
            petalLengths = Arrays.copyOf(petalLengths, size * 2);
            petalWidths = Arrays.copyOf(petalWidths, size * 2);
            size = size * 2;
        }

        if(data.getKey()<0 || data.getKey()>=size){
            throw new IllegalArgumentException("Key must be in range of 0 to "+(data.getKey()-1));
        }
        sepalLengths[data.getKey()] = data.getSepalLength();
        sepalWidths[data.getKey()] = data.getSepalWidth();
        petalLengths[data.getKey()] = data.getPetalLength();
        petalWidths[data.getKey()] = data.getPetalWidth();
        irisClassNames[data.getKey()] = data.getIrisClassName();
        usedKeys.add(data.getKey());
    }

    /** Deletes the observation from the  linked list.
     *
     * @param key The key from the {@link Data} object. It is
     *                       the position of the observation when reading the data.
     **/
   public void delete(int key){
        if(key<0 || key>=size){
            throw new IllegalArgumentException("Key must be in range of 0 to "+(key-1));
        }
        sepalLengths[key] = -1;
        sepalWidths[key] = -1;
        petalLengths[key] = -1;
        petalWidths[key] = -1;
        irisClassNames[key] = null;
        usedKeys.remove(key);
    }


    /**
     * Returns the next feature date.
     **/
    Data successor(int key){
        int successorKey = key + 1;
        if(successorKey<0 || successorKey>=size){
            throw new IllegalArgumentException("Key must be in range of 0 to "+(key-1));
        }
        return new Data(
                successorKey,
                sepalLengths[successorKey],
                sepalWidths[successorKey],
                petalLengths[successorKey],
                petalWidths[successorKey],
                irisClassNames[successorKey]);
    }

    /**
     * Returns the previous feature data.
     **/
    Data predecessor(int key){
        int predecessorKey = key - 1;
        if(predecessorKey<0 || predecessorKey>=size){
            throw new IllegalArgumentException("Key must be in range of 0 to "+(key-1));
        }
        return new Data(
                predecessorKey,
                sepalLengths[predecessorKey],
                sepalWidths[predecessorKey],
                petalLengths[predecessorKey],
                petalWidths[predecessorKey],
                irisClassNames[predecessorKey]);
    }

    /** Returns the number of elements in the linked list.*/
    public int size(){
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
        for(int i = 0; i<sepalWidths.length; i++){
            stringBuilder.append(i).append(", ")
                    .append(sepalLengths[i]).append(", ")
                    .append(sepalWidths[i]).append(", ")
                    .append(petalLengths[i]).append(", ")
                    .append(petalWidths[i]).append(", ")
                    .append(irisClassNames[i]).append("\n");
        }
        return stringBuilder.toString();
    }
}
