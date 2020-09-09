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

    public int getKey() {
        return key;
    }

    public double getSepalLength() {
        return sepalLength;
    }

    public double getSepalWidth() {
        return sepalWidth;
    }

    public double getPetalLength() {
        return petalLength;
    }

    public double getPetalWidth() {
        return petalWidth;
    }

    public String getIrisClassName(){
        return irisClassName;
    }

    @Override
    public String toString(){
        return ""+this.key+", "+this.sepalLength+", "+this.sepalWidth+", "+this.petalLength+", "+this.petalWidth+", "
                +this.irisClassName;
    }
}
