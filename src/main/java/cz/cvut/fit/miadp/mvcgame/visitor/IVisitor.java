package cz.cvut.fit.miadp.mvcgame.visitor;

import cz.cvut.fit.miadp.mvcgame.Composite.Composite;
import cz.cvut.fit.miadp.mvcgame.model.gameObjects.*;

import java.io.IOException;

public interface IVisitor {

    public void visitCannon( AbsCannon cannon );
    public void visitMissile( AbsMissile missile );

    public void visitCollision( AbsCollision collision );
    public void visitEnemy( AbsEnemy enemy );
    public void visitGameInfo( AbsGameInfo gameInfo );

    public void visitBound(AbsBound bound);

    public void visitEndScene(AbsEndScene endScene);

    public void visitComposite(Composite composite);
}
