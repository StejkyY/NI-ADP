package cz.cvut.fit.miadp.mvcgame.model.gameObjects;

import cz.cvut.fit.miadp.mvcgame.config.MvcGameConfig;
import cz.cvut.fit.miadp.mvcgame.model.IGameModel;
import cz.cvut.fit.miadp.mvcgame.model.Position;
import cz.cvut.fit.miadp.mvcgame.state.IShootingMode;
import cz.cvut.fit.miadp.mvcgame.strategy.IMovingStrategy;
import cz.cvut.fit.miadp.mvcgame.visitor.IVisitor;

import java.util.List;
import java.util.OptionalDouble;
import java.util.OptionalInt;

public abstract class AbsGameInfo {
    protected int score;
    protected AbsCannon cannon;
    protected IGameModel model;
    protected int spacing;
    protected Position pos;

    public AbsGameInfo(Position pos, AbsCannon cannon, IGameModel model){
        this.pos = pos;
        this.spacing = MvcGameConfig.GAME_INFO_TEXT_SPACING;
        this.score = 0;
        this.cannon = cannon;
        this.model = model;
    }

    public abstract List<String> getTexts();

    public void acceptVisitor(IVisitor visitor) {
        visitor.visitGameInfo(this);
    }

    public int getScore () { return this.score;}

    public void updateScore (int score) {this.score = score;}

    public int getSpacing () {return this.spacing;}

    public Position getPosition () {return this.pos;}
}
