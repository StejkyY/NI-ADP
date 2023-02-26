package cz.cvut.fit.miadp.mvcgame.model.gameObjects;

import cz.cvut.fit.miadp.mvcgame.model.Position;

import java.util.List;

public class NullEndScene extends AbsEndScene {
    public NullEndScene() {
        super(new Position(0,0), 0);
    }

    @Override
    public String getText() {
        return null;
    }
}
