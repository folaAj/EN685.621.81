package hw1.questionTwo.partC;

import hw1.questionOne.partB.Data;
import hw1.questionTwo.partB.IrisDataHashTable;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class IrisDataRemover {
    // Replace with your own iris data file.
    private static final String FILE_NAME = "/Users/folawiyo/Downloads/iris.csv";
    private static final int SEPAL_LENGTH_POSITION= 0;
    private static final int SEPAL_WIDTH_POSITION= 1;
    private static final int PETAL_LENGTH_POSITION= 2;
    private static final int PETAL_WIDTH_POSITION= 3;
    private static final int CLASS_NAME_POSITION= 4;

    private static IrisDataHashTable readData(){
        final Scanner sc;
        try {
            sc = new Scanner(new File(FILE_NAME));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }

        IrisDataHashTable irisDataHashTable = new IrisDataHashTable();
        sc.nextLine(); // Skip heading of file.

        int key = 0;
        while(sc.hasNext()){
            String line  = sc.nextLine();
            String[] splitted = line.split(",");
            irisDataHashTable.put(key,
                    new Data(
                            key++,
                            Double.parseDouble(splitted[SEPAL_LENGTH_POSITION]),
                            Double.parseDouble(splitted[SEPAL_WIDTH_POSITION]),
                            Double.parseDouble(splitted[PETAL_LENGTH_POSITION]),
                            Double.parseDouble(splitted[PETAL_WIDTH_POSITION]),
                            splitted[CLASS_NAME_POSITION])
            );
        }
        sc.close();
        return irisDataHashTable;
    }

    private static void remove20Percent(IrisDataHashTable irisDataHashTable){
        int upperbound = 50;
        if(irisDataHashTable == null){
            return;
        }
        Set<Integer> randomPositions = collectRandomPositions(upperbound);

        for(int position: randomPositions){
            irisDataHashTable.remove(position);
            irisDataHashTable.remove(position+upperbound);
            irisDataHashTable.remove(position+(upperbound*2));
        }
    }

    private static Set<Integer>  collectRandomPositions(int upperbound){
        Set<Integer> positions = new HashSet<>();
        Random random = new Random();
        while (positions.size()<10){
            positions .add(random.nextInt(upperbound));
        }
        System.out.println(positions);
        return positions;
    }

    public static void main(String[] args) {
        IrisDataHashTable irisDataHashTable = readData();
        remove20Percent(irisDataHashTable);
    }
}
