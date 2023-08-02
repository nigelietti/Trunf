package View;

import java.util.ArrayList;
import java.util.function.Consumer;

public abstract class ViewObservable {

    protected final ArrayList<ViewObserver> observers = new ArrayList<>();


    public void addObserver(ViewObserver obs) {
        observers.add(obs);
    }

    public void addAllObservers(ArrayList<ViewObserver> observerList) {
        observers.addAll(observerList);
    }

    protected void notifyObserver(Consumer<ViewObserver> lambda) {
        for (ViewObserver observer : observers) {
            lambda.accept(observer);
        }
    }

}