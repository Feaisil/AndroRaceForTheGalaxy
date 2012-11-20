package feaisil.raceforthegalaxy.card;

import java.util.List;

import feaisil.raceforthegalaxy.power.Power;
import feaisil.raceforthegalaxy.victorypointbonus.EndGameBonus;

public class Development extends Card {

	public Development(
			String iName, 
			String iGraphicId, 
			int iCost, 
			int iVictoryPoints, 
			boolean iPrestige,
			List<Keyword> iKeywords,
			List<Power> iPowers,
			List<EndGameBonus> iEgb) {
		super(iName, iGraphicId, iCost, iVictoryPoints, iPrestige, iKeywords, iPowers, iEgb);
	}

	@Override
	public String toString() {
		return "Development [getName()=" + getName() + ", getCost()="
				+ getCost() + ", getPowers()=" + getPowers() + ", getOwner()="
				+ getOwner() + ", getVictoryPoints()=" + getVictoryPoints()
				+ ", isPrestige()=" + isPrestige() + ", getGraphicId()="
				+ getGraphicId() + ", toString()=" + super.toString()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ "]\n";
	}

}
