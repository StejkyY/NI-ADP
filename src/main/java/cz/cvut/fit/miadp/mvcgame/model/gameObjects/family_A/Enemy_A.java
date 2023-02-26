package cz.cvut.fit.miadp.mvcgame.model.gameObjects.family_A;

import cz.cvut.fit.miadp.mvcgame.abstractFactory.IGameObjectFactory;
import cz.cvut.fit.miadp.mvcgame.model.Position;
import cz.cvut.fit.miadp.mvcgame.model.gameObjects.AbsEnemy;

public class Enemy_A extends AbsEnemy {
    private IGameObjectFactory goFact;

    public Enemy_A(Position pos, IGameObjectFactory goFact, int lives){
        this.position = pos;
        this.goFact = goFact;
        this.lives = lives;
    }
}
