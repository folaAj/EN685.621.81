package hw1.questionTwo.partA;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class IrisDataProcessor {
    // Replace with your own iris data file.
    private static final String FILE_NAME = "/Users/folawiyo/Downloads/iris.csv";
    // For simplicity we provide the size of the data set.
    private static final int DATASET_SIZE = 150;

    private static void read(){
        final Scanner sc;
        try {
            sc = new Scanner(new File(FILE_NAME));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return;
        }

        IrisDataHashTable irisDataHashTable = new IrisDataHashTable(DATASET_SIZE);
        sc.nextLine(); // Skip heading of file.

        int key = 0;
        while(sc.hasNext()){
            String line  = sc.nextLine();
            String[] splitted = line.split(",");
            irisDataHashTable.put(key++, Double.parseDouble(splitted[0]));
        }
        sc.close();
        System.out.println(irisDataHashTable.size());
    }

    public static void main(String[] args) {
        read();
    }
}
