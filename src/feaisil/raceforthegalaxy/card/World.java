package feaisil.raceforthegalaxy.card;

import java.util.List;

import feaisil.raceforthegalaxy.common.GoodType;
import feaisil.raceforthegalaxy.power.Power;
import feaisil.raceforthegalaxy.victorypointbonus.EndGameBonus;

public class World extends Card{
	@Override
	public String toString() {
		StringBuilder _builder = new StringBuilder();
		_builder.append("World [");
		if (good != null) {
			_builder.append("good=");
			_builder.append(good);
			_builder.append(", ");
		}
		if (goodType != null) {
			_builder.append("goodType=");
			_builder.append(goodType);
			_builder.append(", ");
		}
		if (super.toString() != null) {
			_builder.append("toString()=");
			_builder.append(super.toString());
		}
		_builder.append("]");
		return _builder.toString();
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
