package feaisil.raceforthegalaxy.power;

import feaisil.raceforthegalaxy.Game;
import feaisil.raceforthegalaxy.Player;
import feaisil.raceforthegalaxy.card.Card;
import feaisil.raceforthegalaxy.common.Phase;
import feaisil.raceforthegalaxy.exception.PlayerNotInGameException;

public class ChooseActionLast extends Power {

	public ChooseActionLast() {
		super(Phase.selectAction);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void trigger(Game iGame, Player iPlayer, Card iCard)
			throws PlayerNotInGameException {
		iPlayer.initPhase(Phase.selectAction);
	}

}
