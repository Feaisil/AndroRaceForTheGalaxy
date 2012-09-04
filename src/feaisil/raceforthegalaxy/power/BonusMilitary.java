package feaisil.raceforthegalaxy.power;

import feaisil.raceforthegalaxy.Game;
import feaisil.raceforthegalaxy.Player;
import feaisil.raceforthegalaxy.card.Card;
import feaisil.raceforthegalaxy.common.GoodType;
import feaisil.raceforthegalaxy.common.Phase;

public final class BonusMilitary extends Power {
	private GoodType type;
	private int strength;
	
	public BonusMilitary(GoodType iType, int iStrength) {
		super(Phase.settle);
		
		type = iType;
		strength = iStrength;
		
		// TODO Auto-generated constructor stub
	}

	@Override
	public void trigger(Game iGame, Player iPlayer, Card iCard) {
		// TODO Auto-generated method stub

	}

}
