package model;

public class MazzoG extends Mazzo {
    public boolean ifStuck(Seed trunf) {//metodo
        boolean trovato = false;
        for (Card k : super.getCards()) { //itera su tutte le carte(c) col metodo che eredita da mazzo

            if (k.getValue().equals('K') && k.getSeed().equals(trunf)) {
                for (Card q : super.getCards()) {
                    if (q.getValue().equals('Q') && q.getSeed().equals(trunf)) {
                        return true;
                    }
                }
            }
        }

    return false;}
    public Value ifpoker(){
        Value valore = null;
        int contatore;
        for (Value v: Value.values()){
            contatore = 0;
            for (Card k : super.getCards()) {
                if (k.getValue().compareTo(v) == 0)
                    contatore++;

            }
            if (contatore==4) {
                valore=v;}
        }
    return valore;
    }

    public Scala ifscala(){
        Scala scala=new Scala();
        Scala scalaprima = new Scala();
        Value valore= null;
        for (int i=0; i<10; i++)  {
            valore=super.getCards().get(i).getValue();
            for (int j=0; j<4; j++){
                if (super.getCards().get(i+j+1).getSeed().compareTo(super.getCards().get(i+j).getSeed())==0 && super.getCards().get(i+j+1).getValue().compareTo(super.getCards().get(i+j).getValue())==0){
                    scala.setNum(j+2);
                    scala.setValue(super.getCards().get(i).getValue());
                }
                else{
                    break;
                }
            if (scala.getNum()<3){
                scala.setNum(0);
                scala.setValue(null);
                break;
            }
            }
            scalaprima.setValue(scala.getValue());
            scalaprima.setNum(scala.getNum());
        }

        return null;
    }


}