package observer;

public interface IObservable {

    void addObserver(IObserver s);
    void notifyObservers();
}