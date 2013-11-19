package observer;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Implements Observer
 * @author stgebhar
 */
public class Observable implements IObservable {

        private List<IObserver> subscribers = new ArrayList<IObserver>(2);

        /**
         * adds an observer
         */
        public void addObserver(IObserver s) {
                subscribers.add(s);
        }

        /**
         * notify all observers
         */
        @Override
        public void notifyObservers() {
                for ( Iterator<IObserver> iter = subscribers.iterator(); iter.hasNext();) {
                        IObserver observer = iter.next();
                        observer.update();
                }
        }
}