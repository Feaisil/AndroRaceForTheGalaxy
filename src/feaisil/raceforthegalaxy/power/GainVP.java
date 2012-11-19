package feaisil.raceforthegalaxy.power;

import feaisil.raceforthegalaxy.Game;
import feaisil.raceforthegalaxy.Player;
import feaisil.raceforthegalaxy.card.Card;
import feaisil.raceforthegalaxy.common.Phase;
import feaisil.raceforthegalaxy.exception.InactiveGameException;
import feaisil.raceforthegalaxy.exception.PlayerNotInGameException;

public final class GainVP extends Power {

	private int number;
	public GainVP() {
		super(Phase.consume);
		number = 1;
	}
	public GainVP(int iNumber) {
		super(Phase.consume);
		number = iNumber;
	}
	public GainVP(Phase iPhase, int iNumber) {
		super(iPhase);
		number = iNumber;
	}

	@Override
	public String toString() {
		StringBuilder _builder = new StringBuilder();
		_builder.append("GainVP [number=");
		_builder.append(number);
		_builder.append("]");
		return _builder.toString();
	}
	@Override
	public void trigger(Game iGame, Player iPlayer, Card iCard) throws PlayerNotInGameException, InactiveGameException {
		iGame.drawVP(iPlayer, number);
	}

}
