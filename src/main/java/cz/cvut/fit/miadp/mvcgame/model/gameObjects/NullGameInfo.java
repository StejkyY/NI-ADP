package cz.cvut.fit.miadp.mvcgame.model.gameObjects;

import cz.cvut.fit.miadp.mvcgame.model.IGameModel;
import cz.cvut.fit.miadp.mvcgame.model.Position;

import java.util.List;

public class NullGameInfo extends AbsGameInfo {
    public NullGameInfo() {
        super(new Position(0,0), null, null);
    }

    @Override
    public List<String> getTexts() {
        return null;
    }
}
