package feaisil.raceforthegalaxy.power;

import feaisil.raceforthegalaxy.Game;
import feaisil.raceforthegalaxy.Player;
import feaisil.raceforthegalaxy.card.Card;
import feaisil.raceforthegalaxy.common.Phase;
import feaisil.raceforthegalaxy.exception.PlayerNotInGameException;

public abstract class Power {
	private Phase phase;
	
	public Power(Phase iPhase)
	{
		phase = iPhase;
	}
	
	public Phase getPhase()
	{
		return phase;
	}
	
	public abstract void trigger(Game iGame, Player iPlayer, Card iCard) throws PlayerNotInGameException;

	@Override
	public String toString() {
		StringBuilder _builder = new StringBuilder();
		_builder.append("Power [");
		if (phase != null) {
			_builder.append("phase=");
			_builder.append(phase);
		}
		_builder.append("]");
		return _builder.toString();
	}
	
}
