package model;

public class Scala {
    private int num;
    private Value value;

    private Seed seed;

    public Scala(int num, Value value, Seed seed){
        this.num = num;
        this.value = value;
        this.seed = seed;
    }
    public int getNum(){
        return num;
    }

    public Value getValue() {
        return value;
    }

    public Seed getSeed(){
        return seed;
    }


}
