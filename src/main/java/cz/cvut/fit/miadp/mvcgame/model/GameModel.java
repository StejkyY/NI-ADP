package cz.cvut.fit.miadp.mvcgame.model;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;
import java.util.concurrent.LinkedBlockingQueue;

import cz.cvut.fit.miadp.mvcgame.Composite.Composite;
import cz.cvut.fit.miadp.mvcgame.abstractFactory.GameObjectFactory_A;
import cz.cvut.fit.miadp.mvcgame.abstractFactory.IGameObjectFactory;
import cz.cvut.fit.miadp.mvcgame.command.AbstractGameCommand;
import cz.cvut.fit.miadp.mvcgame.config.MvcGameConfig;
import cz.cvut.fit.miadp.mvcgame.model.gameObjects.*;
import cz.cvut.fit.miadp.mvcgame.observer.IObserver;
import cz.cvut.fit.miadp.mvcgame.state.IShootingMode;
import cz.cvut.fit.miadp.mvcgame.strategy.IMovingStrategy;
import cz.cvut.fit.miadp.mvcgame.strategy.RealisticMovingStrategy;
import cz.cvut.fit.miadp.mvcgame.strategy.SimpleMovingStrategy;

public class GameModel implements IGameModel {
    private AbsCannon cannon;
    private List<AbsMissile> missiles;
    private List<AbsEnemy> enemies;
    private List<IObserver> observers;
    private List<AbsCollision> collisions;
    private IGameObjectFactory goFact;
    private IMovingStrategy movingStrategy;
    private Queue<AbstractGameCommand> unExecutedCmds;
    private Stack<AbstractGameCommand> executedCmds;
    private Composite composite;
    private AbsGameInfo gameInfo;
    private AbsBound bound;
    private AbsEndScene endScene;
    private int level;
    private final int maxLevel;

    private int score;

    public GameModel( ) {
        this.observers = new ArrayList<IObserver>( );
        this.goFact = new GameObjectFactory_A( this );
        this.maxLevel = MvcGameConfig.MAX_LEVEL;
        init();
        spawnEnemies();
    }

    private void init(){
        this.bound = this.goFact.createBound();
        this.cannon = this.goFact.createCannon( );
        this.missiles = new ArrayList<AbsMissile>();
        this.movingStrategy = new SimpleMovingStrategy( );
        this.score = 0;
        this.unExecutedCmds = new LinkedBlockingQueue<AbstractGameCommand>( );
        this.executedCmds = new Stack<AbstractGameCommand>();
        this.enemies = new ArrayList<AbsEnemy>( );
        this.collisions = new ArrayList<AbsCollision>();
        this.gameInfo = this.goFact.createGameInfo(this.cannon);
        this.level = 1;
        this.endScene = new NullEndScene();
        this.composite = new Composite();
        this.composite.addChild(this.bound);
    }

    private void spawnEnemies(){
        if (level == 1) {
            for (int i = 0; i < MvcGameConfig.ENEMIES_LEVEL_1; ++i){
                enemies.add(goFact.createEnemy(MvcGameConfig.ENEMY_LIVES_LEVEL_1));
            }
        } else if (level == 2) {
            for (int i = 0; i < MvcGameConfig.ENEMIES_LEVEL_2; ++i){
                enemies.add(goFact.createEnemy(MvcGameConfig.ENEMY_LIVES_LEVEL_2));
            }
        } else if (level == 3) {
            for (int i = 0; i < MvcGameConfig.ENEMIES_LEVEL_3; ++i){
                enemies.add(goFact.createEnemy(MvcGameConfig.ENEMY_LIVES_LEVEL_3));
            }
        }
    }

    public void update( ) {
        this.executedCmds( );
        this.moveMissiles( );
        this.checkHits();
        this.destroyCollisions();
    }

    private void checkLevelSwitch() {
        if (enemies.isEmpty() && collisions.isEmpty()) {
            if (level == maxLevel) {
                this.gameInfo = new NullGameInfo();
                this.endScene = this.goFact.createEndScene();
            } else {
                level++;
                spawnEnemies();
            }
        }
    }

    private void executedCmds( ) {
        while( !this.unExecutedCmds.isEmpty( ) ){
            AbstractGameCommand cmd = this.unExecutedCmds.poll( );
            cmd.doExecute( );
            this.executedCmds.push( cmd );
        }
    }

    private void moveMissiles( )  {
        for ( AbsMissile missile : this.missiles ) {
            missile.move(  );
        }
        this.destroyMissiles( );
        this.notifyObservers( );
    }

    private void checkHits() {
        List<AbsEnemy> enemiesToRemove = new ArrayList<AbsEnemy>();
        for (AbsMissile missile : this.missiles) {
            for (AbsEnemy enemy : this.enemies) {
                if (missile.hit(enemy)) {
                    if (enemy.getLives() > 1) {
                        enemy.setLives(enemy.getLives() - 1);
                    }
                    else
                    {
                        this.collisions.add(goFact.createCollision(enemy.getPosition()));
                        enemiesToRemove.add(enemy);
                        this.gameInfo.updateScore(this.gameInfo.getScore() + MvcGameConfig.KILL_SCORE);
                    }
                }
            }
        }
        this.enemies.removeAll(enemiesToRemove);
    }

    private void destroyCollisions() {
        List<AbsCollision> collisionsToRemove = new ArrayList<AbsCollision>();
        for ( AbsCollision collision : this.collisions ) {
            if(collision.destroy()) {
                collisionsToRemove.add( collision );
            }
        }
        this.collisions.removeAll(collisionsToRemove);
        checkLevelSwitch();
    }

    private void destroyMissiles( ) {
        List<AbsMissile> missilesToRemove = new ArrayList<AbsMissile>();
        for ( AbsMissile missile : this.missiles ) {
            if( missile.getPosition( ).getX( ) > MvcGameConfig.MAX_X ) {
                missilesToRemove.add( missile );
            }
        }
        this.missiles.removeAll(missilesToRemove);
    }

    public Position getCannonPosition( ) {
        return this.cannon.getPosition( );
    }

    public void moveCannonUp( ) {
        if(!this.cannon.touchBound(this.bound) || (this.cannon.getPosition().getY() < this.bound.getPosition().getY())) {
            this.cannon.moveUp();
            this.notifyObservers();
        }
    }

    public void moveCannonDown( ) {
        if(!this.cannon.touchBound(this.bound) || (this.cannon.getPosition().getY() > this.bound.getPosition().getY())) {
            this.cannon.moveDown();
            this.notifyObservers();
        }
    }

    public void aimCannonUp( ) {
        this.cannon.aimUp( );
        this.notifyObservers( );
    }

    public void aimCannonDown( ) {
        this.cannon.aimDown( );
        this.notifyObservers( );
    }

    public void cannonPowerUp( ) {
        this.cannon.powerUp( );
        this.notifyObservers( );
    }

    public void cannonPowerDown( ) {
        this.cannon.powerDown( );
        this.notifyObservers( );
    }

    @Override
    public void registerObserver( IObserver obs ) {
        if( !this.observers.contains( obs ) ) {
            this.observers.add( obs );
        }
    }

    @Override
    public void unregisterObserver( IObserver obs ) {
        if( this.observers.contains( obs ) ) {
            this.observers.remove( obs );
        }
    }

    @Override
    public void notifyObservers( )  {
        for( IObserver obs : this.observers ){
            obs.update( );
        }
    }

    public void cannonShoot( )  {
        this.missiles.addAll( cannon.shoot( ) ) ;
        this.notifyObservers( );
    }

    @Override
    public void cannonReload() {
        this.cannon.reload();
    }

    public List<AbsMissile> getMissiles( ) {
        return this.missiles;
    }

    @Override
    public List<AbsEnemy> getEnemies() {
        return this.enemies;
    }

    @Override
    public List<AbsCollision> getCollisions() {
        return this.collisions;
    }

    public List<GameObject> getGameObjects( ) {
        List<GameObject> go = new ArrayList<GameObject>();
        go.add(this.cannon);
        go.add(this.composite);
        go.addAll(this.missiles);
        go.addAll(this.enemies);
        go.addAll(this.collisions);
        go.add(this.endScene);
        return go;
    }

    public IMovingStrategy getMovingStrategy( ){
        return this.movingStrategy;
    }

    @Override
    public AbsGameInfo getGameInfo() {
        return this.gameInfo;
    }

    @Override
    public AbsBound getBound() {
        return this.bound;
    }

    @Override
    public int getLevel() {
        return this.level;
    }

    @Override
    public int getCannonAvailableMissiles() {
        return cannon.getAvailableMissiles();
    }

    public void toggleMovingStrategy( ) {
        if ( this.movingStrategy instanceof SimpleMovingStrategy ) {
            this.movingStrategy = new RealisticMovingStrategy( );
        }
        else if ( this.movingStrategy instanceof RealisticMovingStrategy ){
            this.movingStrategy = new SimpleMovingStrategy( );
        }
        else {

        }
    }

    public void toggleShootingMode( ){
        this.cannon.toggleShootingMode( );
    }

    private class Memento {
        private int score;
        private Position cannonPosition;
        private List<AbsEnemy> enemies;
        private List<AbsCollision> collisions;
        private IMovingStrategy movingStrategy;
        private IShootingMode shootingMode;
        private double angle;
        private int power;
        private int availableMissiles;
        private int level;
    }

    public Object createMemento( ) {
        Memento m = new Memento( );
        m.score = this.gameInfo.getScore();
        m.cannonPosition = new Position(this.getCannonPosition().getX(), this.getCannonPosition().getY());
        m.enemies = new ArrayList<>(this.enemies);
        m.collisions = new ArrayList<>(this.collisions);
        m.movingStrategy = this.movingStrategy;
        m.shootingMode = this.cannon.getShootingMode();
        m.angle = this.cannon.getAngle();
        m.power = this.cannon.getPower();
        m.availableMissiles = this.cannon.getAvailableMissiles();
        m.level = this.level;
        return m;
    }

    public void setMemento( Object memento ) {
        Memento m = ( Memento ) memento;
        this.gameInfo.updateScore(m.score);
        this.cannon.getPosition( ).setX( m.cannonPosition.getX() );
        this.cannon.getPosition( ).setY( m.cannonPosition.getY() );
        this.cannon.setAngle(m.angle);
        this.cannon.setPower(m.power);
        this.enemies = new ArrayList<>(m.enemies);
        this.collisions = new ArrayList<>(m.collisions);
        this.movingStrategy = m.movingStrategy;
        this.cannon.setShootingMode(m.shootingMode);
        this.cannon.setAvailableMissiles(m.availableMissiles);
        this.level = m.level;
    }

    @Override
    public void registerCommand( AbstractGameCommand cmd ) {
        this.unExecutedCmds.add( cmd );
    }

    @Override
    public void undoLastCommand( ) {
        if( !this.executedCmds.isEmpty( ) ){
            AbstractGameCommand cmd = this.executedCmds.pop( );
            cmd.unExecute( );
        }
        this.notifyObservers( );
    }

    @Override
    public int getScore() {
        return this.gameInfo.getScore();
    }

    @Override
    public void restartGame() {
        init();
        spawnEnemies();
    }

}