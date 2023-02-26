package cz.cvut.fit.miadp.mvcgame.abstractFactory;

import cz.cvut.fit.miadp.mvcgame.model.IGameModel;
import cz.cvut.fit.miadp.mvcgame.model.Position;
import cz.cvut.fit.miadp.mvcgame.model.gameObjects.*;
import cz.cvut.fit.miadp.mvcgame.model.gameObjects.family_A.Enemy_A;

import java.util.List;

public interface IGameObjectFactory {
    public AbsCannon createCannon( );
    public AbsMissile createMissile( double initAngle, int initVelocity );
    public AbsEnemy createEnemy(int lives);
    public AbsCollision createCollision(Position pos);
    public AbsGameInfo createGameInfo(AbsCannon cannon);
    public AbsBound createBound();
    public AbsEndScene createEndScene();
}
