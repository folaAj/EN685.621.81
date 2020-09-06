package hw1.questionOne.partB;

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

        IrisDataLinkedList irisDataLinkedList = new IrisDataLinkedList(DATASET_SIZE);
        sc.nextLine(); // Skip heading of file.

        int key = 0;
        while(sc.hasNext()){
            String line  = sc.nextLine();
            String[] splitted = line.split(",");
            irisDataLinkedList.insert(new Data(key, Double.parseDouble(splitted[0]), Double.parseDouble(splitted[1]),
                    Double.parseDouble(splitted[2]), Double.parseDouble(splitted[3]), splitted[0]));
        }
        sc.close();
    }

    public static void main(String[] args) {
        read();
    }
}
