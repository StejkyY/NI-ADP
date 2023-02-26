package cz.cvut.fit.miadp.mvcgame.model.gameObjects;

import cz.cvut.fit.miadp.mvcgame.visitor.IVisitor;

public class AbsBound extends GameObject {

    @Override
    public void acceptVisitor(IVisitor visitor) {
        visitor.visitBound(this);
    }
}
