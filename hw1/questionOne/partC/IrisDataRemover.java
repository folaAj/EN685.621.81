package hw1.questionOne.partC;

import hw1.questionOne.partB.Data;
import hw1.questionOne.partB.IrisDataLinkedList;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class IrisDataRemover {
    // Replace with your own iris data file.
    private static final String FILE_NAME = "/Users/folawiyo/Downloads/iris.csv";
    // For simplicity we provide the size of the data set.
    private static final int DATASET_SIZE = 150;
    private static final int SEPAL_LENGTH_POSITION= 0;
    private static final int SEPAL_WIDTH_POSITION= 1;
    private static final int PETAL_LENGTH_POSITION= 2;
    private static final int PETAL_WIDTH_POSITION= 3;
    private static final int CLASS_NAME_POSITION= 4;

    private static IrisDataLinkedList readData(){
        final Scanner sc;
        try {
            sc = new Scanner(new File(FILE_NAME));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }

        IrisDataLinkedList irisDataLinkedList = new IrisDataLinkedList(DATASET_SIZE);
        sc.nextLine(); // Skip heading of file.

        int key = 0;
        while(sc.hasNext()){
            String line  = sc.nextLine();
            String[] splitted = line.split(",");
            irisDataLinkedList.insert(
                    new Data(
                            key,
                            Double.parseDouble(splitted[SEPAL_LENGTH_POSITION]),
                            Double.parseDouble(splitted[SEPAL_WIDTH_POSITION]),
                            Double.parseDouble(splitted[PETAL_LENGTH_POSITION]),
                            Double.parseDouble(splitted[PETAL_WIDTH_POSITION]),
                            splitted[CLASS_NAME_POSITION])
            );
        }
        sc.close();
        return irisDataLinkedList;
    }

    private static void separate(IrisDataLinkedList irisDataLinkedList){
        int upperbound = 50;
        if(irisDataLinkedList == null){
            return;
        }
        List<Integer> randomPositions = collectRandomPositions(upperbound);

        for(int position: randomPositions){
            irisDataLinkedList.delete(position);
            irisDataLinkedList.delete(position+upperbound);
            irisDataLinkedList.delete(position+(upperbound*2));
        }
    }

    private static List<Integer>  collectRandomPositions(int upperbound){
        List<Integer> positions = new ArrayList<>();
        Random random = new Random();
        for(int i = 0;  i<10; i++){
            positions .add(random.nextInt(upperbound));
        }
        return positions;
    }

    public static void main(String[] args) {
        IrisDataLinkedList irisDataLinkedList = readData();
        separate(irisDataLinkedList);
    }
}
