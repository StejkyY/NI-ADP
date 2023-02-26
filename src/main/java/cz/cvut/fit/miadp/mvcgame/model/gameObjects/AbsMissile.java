package cz.cvut.fit.miadp.mvcgame.model.gameObjects;

import cz.cvut.fit.miadp.mvcgame.config.MvcGameConfig;
import cz.cvut.fit.miadp.mvcgame.model.Position;
import cz.cvut.fit.miadp.mvcgame.visitor.IVisitor;

import java.util.ArrayList;
import java.util.List;

public abstract class AbsMissile extends LifetimeLimitedGameObject {

    private double initAngle;
    private int initVelocity;

    protected AbsMissile( Position initialPosition, double initAngle, int initVelocity ) {
        super( initialPosition );
        this.initAngle = initAngle;
        this.initVelocity = initVelocity;
    }

    @Override
    public void acceptVisitor( IVisitor visitor ) {
        visitor.visitMissile( this );
    }

    public int getInitVelocity( ){
        return this.initVelocity;
    }

    public double getInitAngle( ) {
        return this.initAngle;
    }

    public abstract void move( );

    public abstract boolean hit(AbsEnemy enemy);
}
