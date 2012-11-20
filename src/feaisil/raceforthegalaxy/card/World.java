package feaisil.raceforthegalaxy.card;

import java.util.List;

import feaisil.raceforthegalaxy.common.GoodType;
import feaisil.raceforthegalaxy.power.Power;
import feaisil.raceforthegalaxy.victorypointbonus.EndGameBonus;

public class World extends Card{
	@Override
	public String toString() {
		return "World [good=" + good + ", goodType=" + goodType + ", military="
				+ military + ", production=" + production + ", getName()="
				+ getName() + ", getCost()=" + getCost() + ", getPowers()="
				+ getPowers() + ", getOwner()=" + getOwner()
				+ ", getVictoryPoints()=" + getVictoryPoints()
				+ ", isPrestige()=" + isPrestige() + ", getGraphicId()="
				+ getGraphicId() + ", toString()=" + super.toString()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ "]";
	}
	
	private Card good;
	private GoodType goodType;

	private boolean military;
	private boolean production;
	
	public World(
			String iName,
			String iGraphicId,
			int iCost, 
			int iVictoryPoints,
			boolean iPrestige,
			GoodType iGoodType,
			boolean iMilitary,
			boolean iProduction,
			List<Keyword> iKeywords,
			List<Power> iPowers,
			List<EndGameBonus> iEgb) {
		super(iName, iGraphicId, iCost, iVictoryPoints, iPrestige, iKeywords, iPowers, iEgb);
		
		goodType = iGoodType;
		good = null;
		military = iMilitary;
		production = iProduction;
	}

	public GoodType getGoodType() {
		return goodType;
	}
	public void consumeGood()
	{
		good = null;
	}
	public boolean hasGood()
	{
		return good != null;
	}
	public void setGood(Card iCard)
	{
		good = iCard;
	}
	public boolean isMilitary() {
		return military;
	}

	public boolean isProduction() {
		return production;
	}
}
