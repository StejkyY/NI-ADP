package cz.cvut.fit.miadp.mvcgame.model.gameObjects;

import cz.cvut.fit.miadp.mvcgame.config.MvcGameConfig;
import cz.cvut.fit.miadp.mvcgame.model.Position;
import cz.cvut.fit.miadp.mvcgame.visitor.IVisitor;

import java.util.ArrayList;
import java.util.List;

public abstract class AbsEndScene extends GameObject {
    protected int score;

    public AbsEndScene(Position pos, int score) {
        this.position = pos;
        this.score = score;
    }

    @Override
    public void acceptVisitor(IVisitor visitor) {
        visitor.visitEndScene(this);
    }

    public abstract String getText();
}
