package cz.cvut.fit.miadp.mvcgame.model;

import java.io.IOException;
import java.util.List;

import cz.cvut.fit.miadp.mvcgame.command.AbstractGameCommand;
import cz.cvut.fit.miadp.mvcgame.model.gameObjects.*;
import cz.cvut.fit.miadp.mvcgame.observer.IObservable;
import cz.cvut.fit.miadp.mvcgame.state.IShootingMode;
import cz.cvut.fit.miadp.mvcgame.strategy.IMovingStrategy;

public interface IGameModel extends IObservable {
    public void update( );
    public Position getCannonPosition( );
    public void moveCannonUp( );
    public void moveCannonDown( );
    public void aimCannonUp( );
    public void aimCannonDown( );
    public void cannonPowerUp( );
    public void cannonPowerDown( );
    public void cannonShoot( ) ;
    public void cannonReload( ) ;
    public List<AbsMissile> getMissiles( );
    public List<AbsEnemy> getEnemies();
    public List<AbsCollision> getCollisions();
    public List<GameObject> getGameObjects( );
    public IMovingStrategy getMovingStrategy( );
    public AbsGameInfo getGameInfo();
    public AbsBound getBound();
    public int getLevel();
    public int getCannonAvailableMissiles();
    public void toggleMovingStrategy( );
    public void toggleShootingMode( );
    public Object createMemento( );
    public void setMemento( Object memento );

    public void registerCommand( AbstractGameCommand cmd );
    public void undoLastCommand( );

    public int getScore();

    public void restartGame();
}
