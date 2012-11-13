package feaisil.raceforthegalaxy;

import feaisil.raceforthegalaxy.common.Action;
import feaisil.raceforthegalaxy.common.Reply;
import feaisil.raceforthegalaxy.common.Request;
import feaisil.raceforthegalaxy.exception.TwoManyPlayersException;

public final class AIPlayer extends Player {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AIPlayer(PlayerInterface pi, Game iGame) throws TwoManyPlayersException {
		super(pi, iGame, false);
	}
	
	@Override
	public String toString() {
		StringBuilder _builder = new StringBuilder();
		_builder.append("AIPlayer [");
		if (super.toString() != null) {
			_builder.append("toString()=");
			_builder.append(super.toString());
		}
		_builder.append("]");
		return _builder.toString();
	}

	public Action selectAction(boolean prestigeActionUsed) {
		return Action.exploreDraw;
	}

}
