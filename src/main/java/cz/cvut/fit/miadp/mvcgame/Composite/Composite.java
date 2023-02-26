package cz.cvut.fit.miadp.mvcgame.Composite;

import cz.cvut.fit.miadp.mvcgame.model.gameObjects.GameObject;
import cz.cvut.fit.miadp.mvcgame.visitor.IVisitor;

import java.util.ArrayList;
import java.util.List;

public class Composite extends GameObject {
    private List<GameObject> children;

    public Composite () {
        children = new ArrayList<>();
    }

    public void addChild (GameObject child) {
        children.add(child);
    }

    @Override
    public void acceptVisitor(IVisitor visitor) {
        for (GameObject child : children) {
            child.acceptVisitor(visitor);
        }
        visitor.visitComposite(this);
    }
}
