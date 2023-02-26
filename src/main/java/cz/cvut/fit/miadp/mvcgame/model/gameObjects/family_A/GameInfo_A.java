package cz.cvut.fit.miadp.mvcgame.model.gameObjects.family_A;

import cz.cvut.fit.miadp.mvcgame.model.IGameModel;
import cz.cvut.fit.miadp.mvcgame.model.Position;
import cz.cvut.fit.miadp.mvcgame.model.gameObjects.AbsCannon;
import cz.cvut.fit.miadp.mvcgame.model.gameObjects.AbsGameInfo;
import cz.cvut.fit.miadp.mvcgame.state.IShootingMode;
import cz.cvut.fit.miadp.mvcgame.strategy.IMovingStrategy;

import java.util.ArrayList;
import java.util.List;

public class GameInfo_A extends AbsGameInfo {

    public GameInfo_A(Position pos, AbsCannon cannon, IGameModel model) {
        super(pos, cannon, model);
    }

    @Override
    public List<String> getTexts() {
        List<String> gameInfoTexts = new ArrayList<String>();
        gameInfoTexts.add("Skóre: " + score);
        if (model != null) {
            gameInfoTexts.add("Úroveň: " + model.getLevel());
            gameInfoTexts.add("Pohybová strategie: " + model.getMovingStrategy().getName());
        }
        if (cannon != null) {
            gameInfoTexts.add("Mód střílení: " + cannon.getShootingMode().getName());
            gameInfoTexts.add("Úhel míření: " + (double) Math.round(cannon.getAngle() * 100.0) / 100.0);
            gameInfoTexts.add("Síla střílení: " + cannon.getPower());
        }
        if (model != null) {
            gameInfoTexts.add("Aktivní nepřátelé: " + model.getEnemies().size());
            gameInfoTexts.add("Aktivní kolize: " + model.getCollisions().size());
            gameInfoTexts.add("Počet raket: " +
                    (model.getCannonAvailableMissiles() > 0 ? model.getCannonAvailableMissiles() : "NUTNÉ NABÍT"));
        }
        return gameInfoTexts;
    }

}