package observer;

/**
 * interface for observers
 * @author stgebhar
 */
public interface IObservable {

    void addObserver(IObserver s);
    void notifyObservers();
}