package cz.cvut.fit.miadp.mvcgame.observer;

import java.io.IOException;

public interface IObservable {

    public void registerObserver( IObserver obs );
    public void unregisterObserver( IObserver obs );
    public void notifyObservers( ) ;

}
