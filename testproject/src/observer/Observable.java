package observer;

import java.util.Iterator;
import java.util.Vector;

public class Observable  {

	private Vector<IObserver> subscribers = new Vector<IObserver>(2);

	/**
	 * adds an observer
	 * @param s
	 */
	public void addObserver(IObserver s) {
		subscribers.addElement(s);
	}

	/**
	 * notifies all observers
	 */
	public void notifyObservers() {
		for ( Iterator<IObserver> iter = subscribers.iterator(); iter.hasNext();) {
			IObserver observer = iter.next();
			observer.update();
		}
	}
}