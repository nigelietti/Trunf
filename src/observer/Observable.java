package observer;

import Network.GenericMessage;

import java.util.ArrayList;


/**
 * this is the Observable class that is extended by the Client
 * it defines the addObserver method to add observer that will observe the class that extends this class
 * and the notifyObserver to update the observer
 */
public class Observable {

    private final ArrayList<Observer> observers = new ArrayList<>();

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    protected void notifyObserver(GenericMessage message) {
        for (Observer observer : observers) {
            observer.update(message);
        }
    }
}