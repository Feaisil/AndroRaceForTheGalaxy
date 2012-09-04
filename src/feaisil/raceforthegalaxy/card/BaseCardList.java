package feaisil.raceforthegalaxy.card;

import feaisil.raceforthegalaxy.common.GoodType;
import feaisil.raceforthegalaxy.power.*;

public class BaseCardList extends CardList {

	@Override
	public void initCardList() {
		Consume aConsumeAnyX1VP = new Consume(GoodType.Any);
		aConsumeAnyX1VP.addTarget(new GainVP());

		Consume aConsumeAnyX1CardVP = new Consume(GoodType.Any);
		aConsumeAnyX1CardVP.addTarget(new GainVP());
		//aConsumeAnyX1CardVP.addTarget(new GainCard());// TODO Add target get Card
		
		// Old Earth
		World aOldEarth = new World("Old Earth",3,2,GoodType.None);
		aOldEarth.addPower(new TradeBonus(GoodType.Any, 1));
		aOldEarth.addPower(aConsumeAnyX1VP);
		aOldEarth.addPower(aConsumeAnyX1VP);
		addStartingBlueWorld(aOldEarth);
		
		// Epsilon Eridani
		World aEpsilonEridani = 
				new World("Epsilon Eridani",2,1,GoodType.None);
		aEpsilonEridani.addPower(new BonusMilitary(GoodType.Any, 1));
		aEpsilonEridani.addPower(aConsumeAnyX1CardVP);
		addStartingRedWorld(aEpsilonEridani);
		
		// Alpha Centauri
		World aAlphaCentauri = new World("Alpha Centauri",2,0,GoodType.Rare);
		aAlphaCentauri.addPower(new ReduceSettleCost(GoodType.Rare, 1));
		aAlphaCentauri.addPower(new BonusMilitary(GoodType.Rare, 1));
		//TODO add flag Windfall
		addStartingBlueWorld(aAlphaCentauri);
		
		// New Sparta
		World aNewSparta = new World("New Sparta",2,1,GoodType.None);
		aNewSparta.addPower(new BonusMilitary(GoodType.Rare, 2));
		//TODO add flag Military
		addStartingRedWorld(aNewSparta);
		
		// Alpha Centauri
		World aEarthLostColony = new World("Earth's Lost Colony",2,1,GoodType.Novelty);
		aEarthLostColony.addPower(aConsumeAnyX1VP);
		aEarthLostColony.addPower(new Produce());
		addStartingBlueWorld(aEarthLostColony);
		
	}

}
