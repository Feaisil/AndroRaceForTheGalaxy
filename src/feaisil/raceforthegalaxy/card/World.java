package feaisil.raceforthegalaxy.card;

import java.util.List;

import feaisil.raceforthegalaxy.common.GoodType;

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
	// TODO World flags: Military etc
	
	public World(
			String iName, 
			int iCost, 
			int iVictoryPoints,
			GoodType iGoodType) {
		super(iName, iCost, iVictoryPoints);
		
		goodType = iGoodType;
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
}
