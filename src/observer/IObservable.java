package observer;

/**
 * interface for observers
 * @author stgebhar
 */
public interface IObservable {

    /**
     * adds an Observer
     * @param s
     */
    void addObserver(IObserver s);
    
    /**
     * notfities all observers
     */
    void notifyObservers();
}