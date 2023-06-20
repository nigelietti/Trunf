package model;

public class Scala {
    private int num;
    private Value value;
    private Seed seed;
    public Scala(){
        num=0;
        value =null;
    }
    public int getNum(){
        return num;
    }

    public Value getValue() {
        return value;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public void setValue(Value value) {
        this.value = value;
    }
}
