package cz.cvut.fit.miadp.mvcgame.model.gameObjects.family_A;

import cz.cvut.fit.miadp.mvcgame.config.MvcGameConfig;
import cz.cvut.fit.miadp.mvcgame.model.Position;
import cz.cvut.fit.miadp.mvcgame.model.gameObjects.AbsCollision;

public class Collision_A extends AbsCollision {
    public Collision_A(Position pos, int deleteTime){
        super(pos);
        this.deleteTime = deleteTime;
    }

    @Override
    public boolean destroy() {
        return getAge() >= deleteTime;
    }
}
