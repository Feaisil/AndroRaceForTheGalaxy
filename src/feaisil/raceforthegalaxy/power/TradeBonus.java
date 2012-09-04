package feaisil.raceforthegalaxy.power;

import feaisil.raceforthegalaxy.Game;
import feaisil.raceforthegalaxy.Player;
import feaisil.raceforthegalaxy.card.Card;
import feaisil.raceforthegalaxy.common.GoodType;
import feaisil.raceforthegalaxy.common.Phase;

public final class TradeBonus extends Power {

	private GoodType goodType;
	private int bonusValue;
	
	public TradeBonus(GoodType iGoodType, int iBonusValue) {
		super(Phase.consume);
		goodType = iGoodType;
		bonusValue = iBonusValue;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void trigger(Game iGame, Player iPlayer, Card iCard) {
		// TODO Auto-generated method stub

	}

}
