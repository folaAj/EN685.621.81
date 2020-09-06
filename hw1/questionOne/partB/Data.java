package hw1.questionOne.partB;

public class Data {
    private  int key;
    private double sepalLength;
    private double sepalWidth;
    private double petalLength;
    private double petalWidth;
    private String irisClassName;

    public Data(int key, double sepalLength, double sepalWidth, double petalLength, double petalWidth, String irisClassName) {
        this.key = key;
        this.sepalLength = sepalLength;
        this.sepalWidth = sepalWidth;
        this.petalLength = petalLength;
        this.petalWidth = petalWidth;
        this.irisClassName = irisClassName;
    }

    int getKey() {
        return key;
    }

    double getSepalLength() {
        return sepalLength;
    }

    double getSepalWidth() {
        return sepalWidth;
    }

    double getPetalLength() {
        return petalLength;
    }

    double getPetalWidth() {
        return petalWidth;
    }

    String getIrisClassName(){
        return irisClassName;
    }
}
