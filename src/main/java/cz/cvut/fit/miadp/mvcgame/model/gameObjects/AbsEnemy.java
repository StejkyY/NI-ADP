package cz.cvut.fit.miadp.mvcgame.model.gameObjects;

import cz.cvut.fit.miadp.mvcgame.visitor.IVisitor;

public abstract class AbsEnemy extends GameObject {
    protected int lives;

    @Override
    public void acceptVisitor(IVisitor visitor) {
        visitor.visitEnemy(this);
    }

    public int getLives() { return lives;}

    public void setLives(int newLives) {this.lives = newLives;}
}
