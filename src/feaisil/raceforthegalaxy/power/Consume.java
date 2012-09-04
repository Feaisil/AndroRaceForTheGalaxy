package feaisil.raceforthegalaxy.power;

import java.util.List;

import feaisil.raceforthegalaxy.Game;
import feaisil.raceforthegalaxy.Player;
import feaisil.raceforthegalaxy.card.Card;
import feaisil.raceforthegalaxy.common.GoodType;
import feaisil.raceforthegalaxy.common.Phase;

public class Consume extends Power {
	private GoodType goodType;
	private int numberOfGood;
	private List<Power>	 targets;
	
	public Consume(GoodType iGoodType, int iNumberOfGood) {
		super(Phase.consume);
		this.goodType = iGoodType;
		numberOfGood = iNumberOfGood;
	}
	public Consume(GoodType iGoodType) {
		super(Phase.consume);
		this.goodType = iGoodType;
		numberOfGood = 1;
	}

	public final void addTarget(Power iTarget)
	{
		targets.add(iTarget);
	}
	
	@Override
	public void trigger(Game iGame, Player iPlayer, Card iCard) {
		// TODO Auto-generated method stub

	}

}
